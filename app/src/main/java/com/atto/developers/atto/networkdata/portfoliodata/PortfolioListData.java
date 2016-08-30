
package com.atto.developers.atto.networkdata.portfoliodata;


import com.atto.developers.atto.networkdata.listdata.PagingData;

public class PortfolioListData implements java.io.Serializable {
    private static final long serialVersionUID = 3135415551461301466L;
    private PortfolioData[] data;
    private PagingData paging;
    private String message;

    public PortfolioData[] getData() {
        return this.data;
    }

    public void setData(PortfolioData[] data) {
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
