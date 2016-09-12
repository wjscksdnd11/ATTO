package com.atto.developers.atto.networkdata.portfoliodata;


import com.atto.developers.atto.networkdata.listdata.KeywordList;

public class PortfolioData implements java.io.Serializable {
    private static final long serialVersionUID = 4583482272816862747L;
    private String[] portfolio_img_info;
    private KeywordList portfolio_key_word_info;
    private String maker_name;
    private int maker_id;
    private int portfolio_id;

    private String portfolio_img;
    private String portfolio_title;

    public String[] getPortfolio_img_info() {
        return portfolio_img_info;
    }

    public void setPortfolio_img_info(String[] portfolio_img_info) {
        this.portfolio_img_info = portfolio_img_info;
    }

    public KeywordList getPortfolio_key_word_info() {
        return portfolio_key_word_info;
    }

    public void setPortfolio_key_word_info(KeywordList portfolio_key_word_info) {
        this.portfolio_key_word_info = portfolio_key_word_info;
    }

    public String getMaker_name() {
        return maker_name;
    }

    public void setMaker_name(String maker_name) {
        this.maker_name = maker_name;
    }

    public int getMaker_id() {
        return maker_id;
    }

    public void setMaker_id(int maker_id) {
        this.maker_id = maker_id;
    }

    public int getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(int portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public String getPortfolio_img() {
        return portfolio_img;
    }

    public void setPortfolio_img(String portfolio_img) {
        this.portfolio_img = portfolio_img;
    }

    public String getPortfolio_title() {
        return portfolio_title;
    }

    public void setPortfolio_title(String portfolio_title) {
        this.portfolio_title = portfolio_title;
    }
}
