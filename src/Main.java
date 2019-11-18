import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/18 13:19
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase test = mongoClient.getDatabase("test");
        System.out.println("Connect to database successfully!");
    }
}
