package com.atto.developers.atto.data.networkData;


import java.io.Serializable;

//메세지 처리 데이터 클래스
public class resultMessage implements Serializable {


    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
