package app.certus.com.model;

import java.util.List;

/**
 * Created by shanaka on 2/28/16.
 */
public class SubCategories {

    /**
     * cat_id : 2
     * cat_name : women
     * sub_categories : [{"sub_id":1,"sub_name":"frocks"},{"sub_id":3,"sub_name":"Leggings & Jeggings"},{"sub_id":5,"sub_name":"tops"},{"sub_id":2,"sub_name":"Jeans & Trousers"},{"sub_id":4,"sub_name":"Skirts & Shorts"}]
     */

    private int cat_id;
    private String cat_name;
    /**
     * sub_id : 1
     * sub_name : frocks
     */

    private List<SubCategoriesEntity> sub_categories;

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public void setSub_categories(List<SubCategoriesEntity> sub_categories) {
        this.sub_categories = sub_categories;
    }

    public int getCat_id() {
        return cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public List<SubCategoriesEntity> getSub_categories() {
        return sub_categories;
    }

    public static class SubCategoriesEntity {
        private int sub_id;
        private String sub_name;

        public void setSub_id(int sub_id) {
            this.sub_id = sub_id;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public int getSub_id() {
            return sub_id;
        }

        public String getSub_name() {
            return sub_name;
        }
    }
}
