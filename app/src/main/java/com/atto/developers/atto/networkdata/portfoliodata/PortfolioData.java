package com.atto.developers.atto.networkdata.portfoliodata;


public class PortfolioData implements java.io.Serializable {
    private static final long serialVersionUID = 4583482272816862747L;
    private int maker_id;
    private int portfolio_id;

    private String portfolio_img_info;
    private String portfolio_title;
    private int[] portfolio_key_word_info;

    public String getPortfolio_img_info() {
        return portfolio_img_info;
    }

    public void setPortfolio_img_info(String portfolio_img_info) {
        this.portfolio_img_info = portfolio_img_info;
    }

    public int[] getPortfolio_key_word_info() {
        return portfolio_key_word_info;
    }

    public void setPortfolio_key_word_info(int[] portfolio_key_word_info) {
        this.portfolio_key_word_info = portfolio_key_word_info;
    }

    public int getMaker_id() {
        return this.maker_id;
    }

    public void setMaker_id(int maker_id) {
        this.maker_id = maker_id;
    }

    public int getPortfolio_id() {
        return this.portfolio_id;
    }

    public void setPortfolio_id(int portfolio_id) {
        this.portfolio_id = portfolio_id;
    }


    public String getPortfolio_title() {
        return portfolio_title;
    }

    public void setPortfolio_title(String portfolio_title) {
        this.portfolio_title = portfolio_title;
    }


}
