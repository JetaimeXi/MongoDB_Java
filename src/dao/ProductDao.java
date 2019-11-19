package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import domain.Product;
import org.bson.Document;
import utils.MongoDBUtils;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/19 8:54
 * @Version: 1.0
 */
public class ProductDao {
    /**
     * @return
     * @Description: 查询所有商品信息
     * @Method: findAllProducts
     * @Implementation:
     * @Return: com.mongodb.DBCursor
     * @Date: 2019/11/19 11:02
     * @Author: Tod
     */
    public MongoCursor<Document> findAllProducts() {
        MongoCollection<Document> collection = MongoDBUtils.getMongoDBCollection();
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> mongoCursor = documents.iterator();
        return mongoCursor;
    }

    /**
     * @param pid 商品编号
     * @Description: 根据商品编号查询商品信息
     * @Method: findProductByPid
     * @Implementation:
     * @Return: void
     * @Date: 2019/11/19 13:10
     * @Author: Tod
     */
    public MongoCursor<Document> findProductByPid(int pid) {
        MongoCollection<Document> mongoDBCollection = MongoDBUtils.getMongoDBCollection();
        FindIterable<Document> documentFindIterable = mongoDBCollection.find(Filters.eq("pid", (double) pid));
        MongoCursor<Document> mongoCursor = documentFindIterable.iterator();
        return mongoCursor;
    }

    public boolean insertProduct(Product product) {
        try {
            MongoCollection<Document> mongoDBCollection = MongoDBUtils.getMongoDBCollection();
            Document document = new Document("pid", product.getPid())
                    .append("pname", product.getPname())
                    .append("price", product.getPrice());
            mongoDBCollection.insertOne(document);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
