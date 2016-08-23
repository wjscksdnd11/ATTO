package com.atto.developers.atto.data.NetworkData;

public class TradeSearchData implements java.io.Serializable {
    private static final long serialVersionUID = 5103017597792251620L;
    private String trade_id;
    private String trade_title;
    private String member_alias;
    private String trade_price;
    private String trade_dtime;
    private String trade_product_img;
    private TradeSearchDataTrade_key_word_lists trade_key_word_lists;
    private String member_profile_img;
    private String trade_status;

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

    public String getMember_alias() {
        return this.member_alias;
    }

    public void setMember_alias(String member_alias) {
        this.member_alias = member_alias;
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

    public String getTrade_product_img() {
        return this.trade_product_img;
    }

    public void setTrade_product_img(String trade_product_img) {
        this.trade_product_img = trade_product_img;
    }

    public TradeSearchDataTrade_key_word_lists getTrade_key_word_lists() {
        return this.trade_key_word_lists;
    }

    public void setTrade_key_word_lists(TradeSearchDataTrade_key_word_lists trade_key_word_lists) {
        this.trade_key_word_lists = trade_key_word_lists;
    }

    public String getMember_profile_img() {
        return this.member_profile_img;
    }

    public void setMember_profile_img(String member_profile_img) {
        this.member_profile_img = member_profile_img;
    }

    public String getTrade_status() {
        return this.trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }
}
