package com.atto.developers.atto.data.NetworkData;

import java.io.Serializable;

public class TradeListPaging implements Serializable {
    private String next;
    private String prev;

    public String getNext() {
        return this.next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return this.prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }
}
