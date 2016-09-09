package com.atto.developers.atto.networkdata.makerdata;


public class MakerData implements java.io.Serializable {
    private static final long serialVersionUID = 6984295785682812238L;
    private int maker_id;
    private String maker_name;
    private String maker_representation_img;
    private int maker_product_category_1;
    private int maker_product_category;// null 가능
    private int maker_product_category_2;
    private String maker_line_tag;// null 가능
    private float maker_score;
    private int[] maker_key_word_lists;


    public int getMaker_id() {
        return maker_id;
    }

    public void setMaker_id(int maker_id) {
        this.maker_id = maker_id;
    }

    public String getMaker_name() {
        return maker_name;
    }

    public void setMaker_name(String maker_name) {
        this.maker_name = maker_name;
    }

    public String getMaker_representation_img() {
        return maker_representation_img;
    }

    public void setMaker_representation_img(String mader_representation_img) {
        this.maker_representation_img = mader_representation_img;
    }

    public int getMaker_product_category_1() {
        return maker_product_category_1;
    }

    public void setMaker_product_category_1(int maker_product_category_1) {
        this.maker_product_category_1 = maker_product_category_1;
    }

    public int getMaker_product_category() {
        return maker_product_category;
    }

    public void setMaker_product_category(int maker_product_category) {
        this.maker_product_category = maker_product_category;
    }

    public int getMaker_product_category_2() {
        return maker_product_category_2;
    }

    public void setMaker_product_category_2(int maker_product_category_2) {
        this.maker_product_category_2 = maker_product_category_2;
    }

    public String getMaker_line_tag() {
        return maker_line_tag;
    }

    public void setMaker_line_tag(String maker_line_tag) {
        this.maker_line_tag = maker_line_tag;
    }

    public float getMaker_score() {
        return maker_score;
    }

    public void setMaker_score(float maker_score) {
        this.maker_score = maker_score;
    }

    public int[] getMaker_key_word_lists() {
        return maker_key_word_lists;
    }

    public void setMaker_key_word_lists(int[] maker_key_word_lists) {
        this.maker_key_word_lists = maker_key_word_lists;
    }
}
