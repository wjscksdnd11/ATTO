package com.atto.developers.atto.data.networkdata.portfoliodata;


import com.atto.developers.atto.data.networkdata.listdata.KeywordList;

public class PortfolioData implements java.io.Serializable {
    private static final long serialVersionUID = 4583482272816862747L;
    private String maker_id;
    private String[] portfolio_img_info;
    private String portfolio_id;
    private KeywordList portfolio_key_word_lists;

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

    public KeywordList getPortfolio_key_word_lists() {
        return this.portfolio_key_word_lists;
    }

    public void setPortfolio_key_word_lists(KeywordList portfolio_key_word_lists) {
        this.portfolio_key_word_lists = portfolio_key_word_lists;
    }
}
