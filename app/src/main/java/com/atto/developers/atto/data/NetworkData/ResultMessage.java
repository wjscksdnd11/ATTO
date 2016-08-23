package com.atto.developers.atto.data.NetworkData;


import java.io.Serializable;

//로그인 관련 메세지 처리 데이터 클래스
public class ResultMessage implements Serializable {


    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
