package com.atto.developers.atto.networkdata;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class ResultTemp <T> {
private String messsage;

    public T getData() {

        return data;
    }

    public void setData(T data) {


        this.data = data;
    }

    private T data;
    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
