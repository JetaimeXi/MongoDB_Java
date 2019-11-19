package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/19 8:53
 * @Version: 1.0
 */
public class MongoDBUtils {
    private static MongoClient mongoClient = null;
    private static String host;
    private static int port;
    private static String databaseName;

    private static String collectionName;

    static {
        try {
            // 创建Properties集合类
            Properties properties = new Properties();
            // 获取src路径
            ClassLoader classLoader = MongoDBUtils.class.getClassLoader();  //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d
            // 获取MongoDB_JDBC.properties的URL对象
            URL resource = classLoader.getResource("utils/MongoDB_JDBC.properties");    //file:/F:/MongoDB_Java/out/production/MongoDB_Java/utils/MongoDB_JDBC.properties
            String path = resource.getPath();   ///F:/MongoDB_Java/out/production/MongoDB_Java/utils/MongoDB_JDBC.properties
            FileReader fileReader = new FileReader(path);   //java.io.FileReader@12edcd21
            properties.load(fileReader);    //加载MongoDB_JDBC.properties文件
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
            databaseName = properties.getProperty("databaseName");
            collectionName = properties.getProperty("collectionName");
            // 建立连接
            mongoClient = new MongoClient(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MongoCollection<Document> getMongoDBCollection() {
        // 获取数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        // 获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection;
    }

}
