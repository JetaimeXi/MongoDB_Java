package service;

import com.mongodb.client.MongoCursor;
import dao.ProductDao;
import org.bson.Document;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/19 8:54
 * @Version: 1.0
 */
public class ProductService {
    /**
     * @Description: 查询所有信息
     * @Method: findAllProducts
     * @Implementation:
     * @Return: com.mongodb.DBCursor
     * @Date: 2019/11/19 11:01
     * @Author: Tod
     * @return
     */
    public MongoCursor<Document> findAllProducts() {
        // 调用dao层对象
        return new ProductDao().findAllProducts();
    }

    public MongoCursor<Document> findProductByPid(int pid) {
        // 调用dao层对象
        return new ProductDao().findProductByPid(pid);
    }
}
