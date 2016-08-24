package com.atto.developers.atto.data.NetworkData.MakerData;

public class MakerData implements java.io.Serializable {
    private static final long serialVersionUID = 6984295785682812238L;
    private String maker_id;
    private String mader_representation_img;
    private String maker_product_category_1;
    private String maker_product_category;// null 가능
    private String maker_product_category_2;
    private String   maker_line_tag;// null 가능
    private String maker_score;





    public String getMaker_id() {
        return this.maker_id;
    }

    public void setMaker_id(String maker_id) {
        this.maker_id = maker_id;
    }

    public String getMader_representation_img() {
        return this.mader_representation_img;
    }

    public void setMader_representation_img(String mader_representation_img) {
        this.mader_representation_img = mader_representation_img;
    }

    public String getMaker_product_category_1() {
        return maker_product_category_1;
    }

    public void setMaker_product_category_1(String maker_product_category_1) {
        this.maker_product_category_1 = maker_product_category_1;
    }

    public String getMaker_product_category_2() {
        return maker_product_category_2;
    }

    public void setMaker_product_category_2(String maker_product_category_2) {
        this.maker_product_category_2 = maker_product_category_2;
    }

    public String getMaker_score() {
        return this.maker_score;
    }

    public void setMaker_score(String maker_score) {
        this.maker_score = maker_score;
    }

    public String getMaker_product_category() {
        return maker_product_category;
    }


    public void setMaker_product_category(String maker_product_category) {
        this.maker_product_category = maker_product_category;
    }


    public String getMaker_line_tag() {
        return maker_line_tag;
    }

    public void setMaker_line_tag(String maker_line_tag) {
        this.maker_line_tag = maker_line_tag;
    }
}
