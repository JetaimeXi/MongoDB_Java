package domain;

import org.bson.types.ObjectId;

/**
 * @Author: Tod
 * @Description:
 * @Date: Created in 2019/11/19 8:54
 * @Version: 1.0
 */
public class Product {
    private ObjectId obj_id;
    private int pid;
    private String pname;
    private double price;

    public Product(ObjectId obj_id, int pid, String pname, double price) {
        this.obj_id = obj_id;
        this.pid = pid;
        this.pname = pname;
        this.price = price;
    }

    public Product() {
    }

    public void setObj_id(ObjectId obj_id) {
        this.obj_id = obj_id;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ObjectId getObj_id() {
        return obj_id;
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public double getPrice() {
        return price;
    }
}
