package com.atto.developers.atto.data.NetworkData.TradeData;

public class TradeRegister implements java.io.Serializable {
    private static final long serialVersionUID = 3311847018150535221L;
    private TradeItemData data;
    private String message;

    public TradeItemData getData() {
        return this.data;
    }

    public void setData(TradeItemData data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
