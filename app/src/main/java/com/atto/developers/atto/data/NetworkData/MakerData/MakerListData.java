package com.atto.developers.atto.data.networkData.makerData;

import com.atto.developers.atto.data.networkData.listData.PagingData;

public class MakerListData implements java.io.Serializable {
    private static final long serialVersionUID = -1250749530431167091L;
    private MakerData[] data;
    private PagingData paging;
    private String message;

    public MakerData[] getData() {
        return this.data;
    }

    public void setData(MakerData[] data) {
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
