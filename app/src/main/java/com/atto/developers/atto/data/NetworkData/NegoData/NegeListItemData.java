package com.atto.developers.atto.data.networkData.negodata;

public class NegeListItemData implements java.io.Serializable {
    private static final long serialVersionUID = 2319219203764986017L;
    private String message;
    private NegoData data;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NegoData getData() {
        return data;
    }

    public void setData(NegoData data) {
        this.data = data;
    }
}
