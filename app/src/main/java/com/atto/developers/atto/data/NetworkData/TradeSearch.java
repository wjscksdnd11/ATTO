package com.atto.developers.atto.data.NetworkData;

public class TradeSearch implements java.io.Serializable {
    private static final long serialVersionUID = 5876909872389756561L;
    private TradeSearchData[] data;
    private TradeSearchPaging paging;
    private String message;

    public TradeSearchData[] getData() {
        return this.data;
    }

    public void setData(TradeSearchData[] data) {
        this.data = data;
    }

    public TradeSearchPaging getPaging() {
        return this.paging;
    }

    public void setPaging(TradeSearchPaging paging) {
        this.paging = paging;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
