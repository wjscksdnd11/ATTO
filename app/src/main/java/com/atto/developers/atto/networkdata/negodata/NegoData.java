package com.atto.developers.atto.networkdata.negodata;

public class NegoData implements java.io.Serializable {
    private static final long serialVersionUID = -2572726441148153839L;
    private String negotiation_id;
    private NegoDataMaker_info maker_info;
    private String negotiation_price;
    private String negotiation_dtime;
    private String[] negotiation_product_imges;// null 가능

    public String getNegotiation_id() {
        return this.negotiation_id;
    }

    public void setNegotiation_id(String negotiation_id) {
        this.negotiation_id = negotiation_id;
    }

    public NegoDataMaker_info getMaker_info() {
        return this.maker_info;
    }

    public void setMaker_info(NegoDataMaker_info maker_info) {
        this.maker_info = maker_info;
    }

    public String getNegotiation_price() {
        return this.negotiation_price;
    }

    public void setNegotiation_price(String negotiation_price) {
        this.negotiation_price = negotiation_price;
    }

    public String getNegotiation_dtime() {
        return this.negotiation_dtime;
    }

    public void setNegotiation_dtime(String negotiation_dtime) {
        this.negotiation_dtime = negotiation_dtime;
    }

    public String[] getNegotiation_product_imges() {
        return negotiation_product_imges;
    }

    public void setNegotiation_product_imges(String[] negotiation_product_imges) {
        this.negotiation_product_imges = negotiation_product_imges;
    }
}
