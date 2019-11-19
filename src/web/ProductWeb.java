package web;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import domain.Product;
import org.bson.Document;
import service.ProductService;
import utils.MongoDBUtils;

import java.util.Scanner;

/**
 * @Author: Tod
 * @Description: 页面层(UI)
 * @Date: Created in 2019/11/19 8:55
 * @Version: 1.0
 */
public class ProductWeb {
    private static ProductService productService = null;

    static {
        productService = new ProductService();
        MongoDBUtils.initMongoDataBase();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--------------欢迎来到商品管理系统--------------");
            System.out.println("输入以下命令进行操作：");
            System.out.println("C:添加商品\tD:根据编号清除商品\tU:更改商品信息\tI:根据商品编号查询商品信息\tFA:查询所有商品信息\tQ:退出");
            switch (sc.next().toUpperCase()) {
                case "C":
                    System.out.println("请输入添加的商品编号：");
                    int pid = sc.nextInt();
                    System.out.println("请输入添加的商品名称：");
                    String pname = sc.next();
                    System.out.println("请输入添加的商品价格：");
                    double price = sc.nextDouble();
                    Product product = new Product(null, pid, pname, price);
                    if (addProduct(product)) {
                        System.out.println("添加成功！");
                    } else {
                        System.out.println("添加异常！");
                    }
                    break;
                case "D":
                    System.out.println("请输入您想查询的商品编号：");
                    int deleteId = sc.nextInt();
                    findProductByPid(deleteId);
                    System.out.println("您确定要删除么？按y确认");
                    String yes = sc.next().toUpperCase();
                    if ("Y".equals(yes)) {
                        if (deleteProductByPid(deleteId)) {
                            System.out.println("删除成功！");
                        } else {
                            System.out.println("删除异常！");
                        }
                    }
                    break;
                case "U":
                    System.out.println("请输入您想更改的商品编号：");
                    int updateId = sc.nextInt();
                    findProductByPid(updateId);
                    System.out.println("请输入新的商品编号：");
                    int update_pid = sc.nextInt();
                    System.out.println("请输入新的商品名称：");
                    String update_pname = sc.next();
                    System.out.println("请输入新的商品价格：");
                    double update_price = sc.nextDouble();
                    Product update_product = new Product(null, update_pid, update_pname, update_price);
                    if (updateProduct(updateId, update_product)) {
                        System.out.println("更改成功！");
                    } else {
                        System.out.println("更改异常！");
                    }
                    break;
                case "I":
                    System.out.println("请输入您想查询的商品编号：");
                    int queryId = sc.nextInt();
                    findProductByPid(queryId);
                    break;
                case "FA":
                    findAllProducts();
                    break;
                case "Q":
                    System.out.println("谢谢你的使用！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
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
    private static boolean updateProduct(int updateId, Product update_product) {
        return productService.updateProduct(updateId, update_product);
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
    private static boolean deleteProductByPid(int deleteId) {
        return productService.deleteProductByPid(deleteId);
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
    private static boolean addProduct(Product product) {
        return productService.addProduct(product);
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
    private static void findProductByPid(int pid) {
        MongoCursor<Document> documentMongoCursor = productService.findProductByPid(pid);
        if (!documentMongoCursor.hasNext()) {
            System.out.println("没有您要查找的数据！");
        } else {
            System.out.println("商品编号\t\t\t商品名称\t\t\t商品价格");
            Document document = documentMongoCursor.next();
            System.out.println(document.get("pid") + "\t\t\t" + document.getString("pname") + "\t\t\t" + document.getDouble("price"));
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
        // 调用业务层对象方法
        MongoCursor<Document> documentMongoCursor = productService.findAllProducts();
        if (!documentMongoCursor.hasNext()) {
            System.out.println("没有您要查找的数据！");
        } else {
            System.out.println("商品编号\t\t\t商品名称\t\t\t商品价格");
            Document document = documentMongoCursor.next();
            System.out.println(document.get("pid") + "\t\t\t" + document.getString("pname") + "\t\t\t" + document.getDouble("price"));
            while (documentMongoCursor.hasNext()) {
                document = documentMongoCursor.next();
                System.out.println(document.get("pid") + "\t\t\t" + document.getString("pname") + "\t\t\t" + document.getDouble("price"));
            }
        }

    }
}
