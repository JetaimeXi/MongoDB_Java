package service;

import com.mongodb.client.MongoCursor;
import dao.ProductDao;
import domain.Product;
import org.bson.Document;

/**
 * @Author: Tod
 * @Description: 业务层
 * @Date: Created in 2019/11/19 8:54
 * @Version: 1.0
 */
public class ProductService {
    /**
     * @return
     * @Description: 查询所有信息
     * @Method: findAllProducts
     * @Implementation:
     * @Return: com.mongodb.DBCursor
     * @Date: 2019/11/19 11:01
     * @Author: Tod
     */
    public MongoCursor<Document> findAllProducts() {
        // 调用dao层对象
        return new ProductDao().findAllProducts();
    }

    /**
     * @param queryId 商品编号
     * @Description: 根据商品编号查询商品信息
     * @Method: findProductByPid
     * @Implementation:
     * @Return: void
     * @Date: 2019/11/19 13:10
     * @Author: Tod
     */
    public MongoCursor<Document> findProductByPid(int queryId) {
        // 调用dao层对象
        return new ProductDao().findProductByPid(queryId);
    }

    public boolean addProduct(Product product) {
        return new ProductDao().addProduct(product);
    }

    public boolean deleteProductByPid(int deleteId) {
        return new ProductDao().deleteProductByPid(deleteId);
    }

    public boolean updateProduct(int updateId, Product update_product) {
        return new ProductDao().updateProduct(updateId, update_product);
    }
}
