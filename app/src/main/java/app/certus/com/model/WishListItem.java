package app.certus.com.model;

import java.io.Serializable;

/**
 * Created by shanaka on 2/27/16.
 */
public class WishListItem implements Serializable {
    private int img_id;
    private String product_name;
    private String product_brand;
    private double product_price;

    public WishListItem() {
    }

    public WishListItem(int img_id, String product_name, String product_brand, double product_price) {
        this.img_id = img_id;
        this.product_name = product_name;
        this.product_brand = product_brand;
        this.product_price = product_price;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
