import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

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

            // 插入文档
            // 创建一个文档对象，插入一条数据
//            Document documnet = new Document("title", "MongoDB").
//                    append("description", "database").
//                    append("likes", 100).
//                    append("by", "Fly");
//            mycoll.insertOne(documnet);
//            // 创建一个文档对象列表,插入多条数据
//            ArrayList<Document> documents = new ArrayList<>();
//            documents.add(documnet);    //添加进列表
//            mycoll.insertMany(documents);
//            System.out.println("插入数据成功！");

            // 检索文档
            // 获取迭代器对象
            FindIterable<Document> documentFindIterable = mycoll.find();
            // 获取游标
            MongoCursor<Document> mongoCursor = documentFindIterable.iterator();
            // 遍历游标
            while (mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }
            System.out.println("检索文档成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
