package web;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import domain.Product;
import org.bson.Document;
import service.ProductService;

import java.util.Scanner;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/19 8:55
 * @Version: 1.0
 */
public class ProductWeb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--------------欢迎来到商品管理系统--------------");
            System.out.println("输入以下命令进行操作：");
            System.out.println("C:添加商品\tD:根据编号清除商品\tDA:删除所有商品\tI:根据商品编号查询商品信息\tFA:查询所有商品信息\tQ:退出");
            switch (sc.next().toUpperCase()) {
                case "C":
                    System.out.println("添加商品");
                    break;
                case "D":
                    System.out.println("根据编号清除商品");
                    break;
                case "DA":
                    System.out.println("删除所有商品");
                    break;
                case "I":
                    System.out.println("请输入您想查询的商品编号：");
                    int i = sc.nextInt();
                    findProductByPid(i);
                    break;
                case "FA":
                    findAllProducts();
                    break;
                case "Q":
                default:
                    System.out.println("谢谢你的使用！");
                    System.exit(0);
                    break;
            }
        }
    }

    private static void findProductByPid(int i) {
        ProductService productService = new ProductService();
        MongoCursor<Document> documentMongoCursor = productService.findProductByPid(i);
        if (!documentMongoCursor.hasNext()) {
            System.out.println("没有您要查找的数据！");
        } else {
            System.out.println("商品编号\t\t\t商品名称\t\t\t商品价格");
            Document document = documentMongoCursor.next();
            System.out.println(document.getDouble("pid") + "\t\t\t" + document.getString("pname") + "\t\t\t" + document.getDouble("price"));
        }
    }

    /**
     * @Description: 查询所有信息
     * @Method: findAllProducts
     * @Implementation:
     * @Return: void
     * @Date: 2019/11/19 11:00
     * @Author: Tod
     */
    private static void findAllProducts() {
        // 创建业务层对象
        ProductService productService = new ProductService();
        // 调用业务层对象方法
        MongoCursor<Document> documentMongoCursor = productService.findAllProducts();
        if (!documentMongoCursor.hasNext()) {
            System.out.println("没有您要查找的数据！");
        } else {
            System.out.println("商品编号\t\t\t商品名称\t\t\t商品价格");
            Document document = documentMongoCursor.next();
            System.out.println(document.getDouble("pid") + "\t\t\t" + document.getString("pname") + "\t\t\t" + document.getDouble("price"));
            while (documentMongoCursor.hasNext()){
                document = documentMongoCursor.next();
                System.out.println(document.getDouble("pid") + "\t\t\t" + document.getString("pname") + "\t\t\t" + document.getDouble("price"));
            }
        }

    }
}
