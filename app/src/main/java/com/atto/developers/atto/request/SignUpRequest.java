package com.atto.developers.atto.request;

import android.util.Log;

import com.atto.developers.atto.networkdata.ResultMessage;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-29.
 */
//
//member_email: 회원 이메일  (필수항목) (아이디로 사용)
//        member_password: 회원 비밀번호  (필수항목)  (해쉬암호화)
//        member_name: 회원 이름 (필수)
//        member_zipcode : 회원 우편번호
//        member_address_1: 회원 주소
//        member_phone: 회원 핸드폰 번호 (필수)(number)
//        member_registration_token: 회원의 GCM 토큰 정보 (필수)
//
//        예시) /members
public class SignUpRequest extends AbstractRequest<ResultMessage> {
    Request request;

    public SignUpRequest(String email, String password, String name, String zipcode, String adress_1, String phone, String registration_token) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("members")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("member_email", email)
                .add("member_password", password)
                .add("member_name",name)
                .add("member_zipcode",zipcode)
                .add("member_address_1", adress_1)
                .add("member_phone",phone)
                .add("registration_token",registration_token)
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Log.i("URL", request.url().toString());
    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    protected ResultMessage parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        ResultMessage temp = gson.fromJson(text, ResultMessage.class);
        Log.i("Result", temp.getMessage());
        return temp;
    }
}
