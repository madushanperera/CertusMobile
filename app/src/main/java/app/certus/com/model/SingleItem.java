package app.certus.com.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shanaka on 3/1/16.
 */
public class SingleItem implements Serializable {


    /**
     * pid : 1
     * name : Multi Checked Shirt
     * price : 1250.0
     * disc_per : 5
     * brand : Dorothy Perkins
     * desc : <h5>Dorothy Perkins Multi Checked Shirt</h5><p>Look trendy and feel comfortable by wearing this multicoloured casual shirt for women from Dorothy Perkins. Made from cotton, this chequered shirt is comfortable to wear and easy to style. You can team it with casuals of your choice to look smart.</p>
     * img : uploads/products/Dorothy-Perkins-Multi-Checked-Shirt-4510-9254471-1-pdp_slider_l.jpg
     * sizes : ["M","XL","S","L","XXL"]
     * prices : [1300,1400,1250,1350,1450]
     * avl_qnty : [15,5,18,11,7]
     * reviews : [{"comment":"Nulla facilisi. Sed justo dui, scelerisque ut consectetur vel, eleifend id erat. Morbi auctor adipiscing tempor. Phasellus condimentum rutrum aliquet.","date":"2015-12-22","user":"Ganganath Bandara"},{"comment":"Suspendisse potenti. Morbi ac felis nec mauris imperdiet fermentum. Aenean sodales augue ac lacus hendrerit sed rhoncus erat hendrerit. Vivamus vel ultricies elit. Curabitur lacinia nulla vel tellus elementum non mollis justo aliquam.","date":"2015-10-08","user":"Tharana Yasas"},{"comment":"Nice Product. Good  fabric quality as well. I will recommend this product to those who willing to buy this product, ","date":"2015-12-28","user":"Sandaru Lakruvini"}]
     */

    private int pid;
    private String name;
    private double price;
    private int disc_per;
    private String brand;
    private String desc;
    private String img;
    private List<String> sizes;
    private List<Double> prices;
    private List<Integer> avl_qnty;

    public SingleItem() {
    }

    /**
     * comment : Nulla facilisi. Sed justo dui, scelerisque ut consectetur vel, eleifend id erat. Morbi auctor adipiscing tempor. Phasellus condimentum rutrum aliquet.
     * date : 2015-12-22
     * user : Ganganath Bandara
     */

    private List<ReviewsEntity> reviews;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDisc_per(int disc_per) {
        this.disc_per = disc_per;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public void setAvl_qnty(List<Integer> avl_qnty) {
        this.avl_qnty = avl_qnty;
    }

    public void setReviews(List<ReviewsEntity> reviews) {
        this.reviews = reviews;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDisc_per() {
        return disc_per;
    }

    public String getBrand() {
        return brand;
    }

    public String getDesc() {
        return desc;
    }

    public String getImg() {
        return img;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public List<Integer> getAvl_qnty() {
        return avl_qnty;
    }

    public List<ReviewsEntity> getReviews() {
        return reviews;
    }

    public static class ReviewsEntity implements Serializable {
        private String comment;
        private String date;
        private String user;

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getComment() {
            return comment;
        }

        public String getDate() {
            return date;
        }

        public String getUser() {
            return user;
        }
    }

    public double getPriceBySize(String size) {
        double p_price = 0;
        for (int i = 0; i < sizes.size() && i < prices.size(); i++) {
            if (sizes.get(i).equals(size)) {
                p_price = prices.get(i);
            }
        }
        return p_price;
    }

    public double getQntyBySize(String size) {
        double p_qnty = 0;
        for (int i = 0; i < sizes.size() && i < avl_qnty.size(); i++) {
            if (sizes.get(i).equals(size)) {
                p_qnty = avl_qnty.get(i);
            }
        }
        return p_qnty;
    }
}
