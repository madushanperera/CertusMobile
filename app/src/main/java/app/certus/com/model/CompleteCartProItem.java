package app.certus.com.model;

import java.io.Serializable;

/**
 * Created by shanaka on 3/6/16.
 */
public class CompleteCartProItem implements Serializable {

    public CompleteCartProItem() {
    }

    public CompleteCartProItem(int pid, double p_price, String p_name, String p_brand, String p_img, String p_size, int p_dscPer) {
        this.pid = pid;
        this.p_price = p_price;
        this.p_name = p_name;
        this.p_brand = p_brand;
        this.p_img = p_img;
        this.p_size = p_size;
        this.p_dscPer = p_dscPer;
    }

    /**
     * pid : 1
     * p_price : 1300
     * p_name : Multi Checked Shirt
     * p_brand : Dorothy Perkins
     * p_img : uploads/products/Dorothy-Perkins-Multi-Checked-Shirt-4510-9254471-1-pdp_slider_l.jpg
     * p_size : M
     * p_dscPer : 5
     */


    private int pid;
    private double p_price;
    private String p_name;
    private String p_brand;
    private String p_img;
    private String p_size;
    private int p_dscPer;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setP_brand(String p_brand) {
        this.p_brand = p_brand;
    }

    public void setP_img(String p_img) {
        this.p_img = p_img;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public void setP_dscPer(int p_dscPer) {
        this.p_dscPer = p_dscPer;
    }

    public int getPid() {
        return pid;
    }

    public double getP_price() {
        return p_price;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_brand() {
        return p_brand;
    }

    public String getP_img() {
        return p_img;
    }

    public String getP_size() {
        return p_size;
    }

    public int getP_dscPer() {
        return p_dscPer;
    }
}
