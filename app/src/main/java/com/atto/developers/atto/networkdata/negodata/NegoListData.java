package com.atto.developers.atto.data.networkdata.negodata;

import com.atto.developers.atto.networkdata.listdata.PagingData;
import com.atto.developers.atto.networkdata.negodata.NegoData;

public class NegoListData implements java.io.Serializable {
    private static final long serialVersionUID = 8180847119468994447L;
    private NegoData[] data;
    private PagingData paging;
    private String message;

    public NegoData[] getData() {
        return this.data;
    }

    public void setData(NegoData[] data) {
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
