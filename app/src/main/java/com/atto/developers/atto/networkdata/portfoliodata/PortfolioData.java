package com.atto.developers.atto.networkdata.portfoliodata;


import com.atto.developers.atto.networkdata.listdata.KeywordList;

public class PortfolioData implements java.io.Serializable {
    private static final long serialVersionUID = 4583482272816862747L;
    private String maker_id;
    private String[] portfolio_img_info;
    private String portfolio_id;
    private String portfolio_img;

    public String getPortfolio_img() {
        return portfolio_img;
    }

    public void setPortfolio_img(String portfolio_img) {
        this.portfolio_img = portfolio_img;
    }

    private KeywordList portfolio_key_word_info;
    private String maker_name;



    public String getMaker_name() {
        return maker_name;
    }

    public void setMaker_name(String maker_name) {
        this.maker_name = maker_name;
    }


    public String getMaker_id() {
        return this.maker_id;
    }

    public void setMaker_id(String maker_id) {
        this.maker_id = maker_id;
    }

    public String[] getPortfolio_img_info() {
        return this.portfolio_img_info;
    }

    public void setPortfolio_img_info(String[] portfolio_img_info) {
        this.portfolio_img_info = portfolio_img_info;
    }

    public String getPortfolio_id() {
        return this.portfolio_id;
    }

    public void setPortfolio_id(String portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public KeywordList getPortfolio_key_word_info() {
        return this.portfolio_key_word_info;
    }

    public void setPortfolio_key_word_info(KeywordList portfolio_key_word_info) {
        this.portfolio_key_word_info = portfolio_key_word_info;
    }
}
