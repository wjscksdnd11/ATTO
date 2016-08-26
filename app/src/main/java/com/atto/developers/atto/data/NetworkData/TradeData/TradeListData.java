package com.atto.developers.atto.data.networkData.tradedata;

import com.atto.developers.atto.data.networkData.listdata.PagingData;

public class TradeListData implements java.io.Serializable {
    private static final long serialVersionUID = 4222295191657793439L;
    private TradeData[] data;
    private PagingData paging;
    private String message;

    public TradeData[] getData() {
        return this.data;
    }

    public void setData(TradeData[] data) {
        this.data = data;
    }

    public PagingData getPaging() {
        return this.paging;
    }

    public void setPaging(PagingData paging) {
        this.paging = paging;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
