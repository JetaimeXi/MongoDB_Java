import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/18 13:19
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {

        try {
            // 建立连接
            MongoClient mongoClient = new MongoClient("localhost",27017);
            // 获取数据库
            MongoDatabase test = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully!");
//            // 创建集合
//            test.createCollection("mycoll");
//            System.out.println("创建集合成功");
            // 获取集合
            MongoCollection<Document> mycoll = test.getCollection("mycoll");
            System.out.println("获取集合mycoll成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
