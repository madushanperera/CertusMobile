package app.certus.com.model;

/**
 * Created by shanaka on 2/29/16.
 */
public class Products {

    /**
     * pid : 1
     * name : Multi Checked Shirt
     * brand : Dorothy Perkins
     * price : 1250.0
     * dic_per : 5
     * size : S
     */

    private int pid;
    private String name;
    private String brand;
    private double price;
    private int dic_per;
    private String size;
    private String img;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDic_per(int dic_per) {
        this.dic_per = dic_per;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getDic_per() {
        return dic_per;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSize() {
        return size;
    }
}
