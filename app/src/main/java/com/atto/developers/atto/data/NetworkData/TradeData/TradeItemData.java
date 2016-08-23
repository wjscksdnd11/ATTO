package com.atto.developers.atto.data.NetworkData.TradeData;

import com.atto.developers.atto.data.NetworkData.UserData.Member_info;

public class TradeItemData implements java.io.Serializable {
    private static final long serialVersionUID = -4971754694086847129L;
    private String trade_product_img_3;
    private String trade_id;
    private String trade_title;
    private String trade_product_img_1;
    private int trade_price;
    private String trade_dtime;
    private String trade_product_img_2;
    private TradeKeywordLists trade_key_word_lists;
    private String trade_status;
    private Member_info member_info;
    private String trade_product_contents;

    public String getTrade_product_img_3() {
        return this.trade_product_img_3;
    }

    public void setTrade_product_img_3(String trade_product_img_3) {
        this.trade_product_img_3 = trade_product_img_3;
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

    public String getTrade_product_img_1() {
        return this.trade_product_img_1;
    }

    public void setTrade_product_img_1(String trade_product_img_1) {
        this.trade_product_img_1 = trade_product_img_1;
    }

    public int getTrade_price() {
        return this.trade_price;
    }

    public void setTrade_price(int trade_price) {
        this.trade_price = trade_price;
    }

    public String getTrade_dtime() {
        return this.trade_dtime;
    }

    public void setTrade_dtime(String trade_dtime) {
        this.trade_dtime = trade_dtime;
    }

    public String getTrade_product_img_2() {
        return this.trade_product_img_2;
    }

    public void setTrade_product_img_2(String trade_product_img_2) {
        this.trade_product_img_2 = trade_product_img_2;
    }

    public TradeKeywordLists getTrade_key_word_lists() {
        return this.trade_key_word_lists;
    }

    public void setTrade_key_word_lists(TradeKeywordLists trade_key_word_lists) {
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

    public String getTrade_product_contents() {
        return this.trade_product_contents;
    }

    public void setTrade_product_contents(String trade_product_contents) {
        this.trade_product_contents = trade_product_contents;
    }
}
