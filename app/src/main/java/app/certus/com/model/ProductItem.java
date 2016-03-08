package app.certus.com.model;

import java.io.Serializable;

/**
 * Created by shanaka on 2/25/16.
 */
public class ProductItem implements Serializable {

    private String pro_name;
    private String pro_brand;
    private double real_price;
    private double disc_price;
    private int img_id;

    public ProductItem() {
    }

    public ProductItem(String pro_name, String pro_brand, double real_price, double disc_price, int img_id) {
        this.pro_name = pro_name;
        this.pro_brand = pro_brand;
        this.real_price = real_price;
        this.disc_price = disc_price;
        this.img_id = img_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_brand() {
        return pro_brand;
    }

    public void setPro_brand(String pro_brand) {
        this.pro_brand = pro_brand;
    }

    public double getReal_price() {
        return real_price;
    }

    public void setReal_price(double real_price) {
        this.real_price = real_price;
    }

    public double getDisc_price() {
        return disc_price;
    }

    public void setDisc_price(double disc_price) {
        this.disc_price = disc_price;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
