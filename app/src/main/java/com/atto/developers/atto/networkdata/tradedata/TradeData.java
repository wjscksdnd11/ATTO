package com.atto.developers.atto.networkdata.tradedata;


import com.atto.developers.atto.networkdata.listdata.KeywordList;
import com.atto.developers.atto.networkdata.userdata.Member_info;

// // TODO: 2016-08-24  이미지 배열이랑, 이미지 만 받는거 둘 중 하나는 null 이 올 수 있음 .
public class TradeData implements java.io.Serializable {
    private static final long serialVersionUID = -7123457291554300083L;
    private String trade_id;
    private String trade_title;
    private String trade_price;
    private String trade_dtime;
    private String trade_dday;


    private String[] trade_product_imges;// null 가능
    private String trade_product_img;// null 가능
    private String trade_product_category_1;// null 가능
    private String trade_product_category_2;// null 가능
    private KeywordList trade_key_word_lists;
    private String trade_status;
    private Member_info member_info;

    public String getTrade_product_img() {
        return trade_product_img;
    }

    public void setTrade_product_img(String trade_product_img) {
        this.trade_product_img = trade_product_img;
    }

    public String getTrade_dday() {
        return trade_dday;
    }

    public void setTrade_dday(String trade_dday) {
        this.trade_dday = trade_dday;
    }
    public String getTrade_id() {
        return this.trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getTrade_title() {
        return this.trade_title;
    }

    public void setTrade_title(String trade_title) {
        this.trade_title = trade_title;
    }

    public String getTrade_price() {
        return this.trade_price;
    }

    public void setTrade_price(String trade_price) {
        this.trade_price = trade_price;
    }

    public String getTrade_dtime() {
        return this.trade_dtime;
    }

    public void setTrade_dtime(String trade_dtime) {
        this.trade_dtime = trade_dtime;
    }

    public String[] getTrade_product_imges() {
        return this.trade_product_imges;
    }

    public void setTrade_product_imges(String[] trade_product_imges) {
        this.trade_product_imges = trade_product_imges;
    }

    public KeywordList getTrade_key_word_lists() {
        return this.trade_key_word_lists;
    }

    public void setTrade_key_word_lists(KeywordList trade_key_word_lists) {
        this.trade_key_word_lists = trade_key_word_lists;
    }

    public String getTrade_status() {
        return this.trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public Member_info getMember_info() {
        return this.member_info;
    }

    public void setMember_info(Member_info member_info) {
        this.member_info = member_info;
    }
    public String getTrade_product_category_2() {
        return trade_product_category_2;
    }

    public void setTrade_product_category_2(String trade_product_category_2) {
        this.trade_product_category_2 = trade_product_category_2;
    }

    public String getTrade_product_category_1() {
        return trade_product_category_1;
    }

    public void setTrade_product_category_1(String trade_product_category_1) {
        this.trade_product_category_1 = trade_product_category_1;
    }
}
