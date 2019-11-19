package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import domain.Product;
import org.bson.Document;
import utils.MongoDBUtils;

/**
 * @Author: Tod
 * @Description: MongoDB接口层
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

    /**
     * @param product 新添商品信息
     * @Description: 添加商品信息
     * @Method: addProduct
     * @Implementation:
     * @Return: boolean
     * @Date: 2019/11/19 15:00
     * @Author: Tod
     */
    public boolean addProduct(Product product) {
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

    /**
     * @param deleteId 待删除的商品编号
     * @Description: 根据编号清除商品
     * @Method: deleteProductByPid
     * @Implementation:
     * @Return: boolean
     * @Date: 2019/11/19 15:04
     * @Author: Tod
     */
    public boolean deleteProductByPid(int deleteId) {
        MongoCollection<Document> mongoDBCollection = MongoDBUtils.getMongoDBCollection();
        DeleteResult deleteResult = mongoDBCollection.deleteOne(Filters.eq("pid", deleteId));
        if (deleteResult != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param updateId       待更新的商品编号
     * @param update_product 更改后的商品信息
     * @Description: 更改商品信息
     * @Method: updateProduct
     * @Implementation:
     * @Return: boolean
     * @Date: 2019/11/19 15:05
     * @Author: Tod
     */
    public boolean updateProduct(int updateId, Product update_product) {
        MongoCollection<Document> mongoDBCollection = MongoDBUtils.getMongoDBCollection();
        Document document = new Document("pid", update_product.getPid()).append("pname", update_product.getPname()).append("price", update_product.getPrice());
        UpdateResult updateResult = mongoDBCollection.updateOne(Filters.eq("pid", updateId), new Document("$set", document));
        if (updateResult != null) {
            return true;
        } else {
            return false;
        }
    }
}
