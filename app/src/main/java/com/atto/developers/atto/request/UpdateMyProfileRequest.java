package com.atto.developers.atto.request;

import android.content.Context;

import com.atto.developers.atto.networkdata.ResultMessage;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-01.
 */

//  프로필 수정
//        member_phone: 회원 핸드폰 번호(number)
//        member_zipcode_1 : 회원 우편번호(number)
//                member_address_1: 회원 주소
//        member_alias: 회원 닉네임
//        member_gender: 회원 성별(number/malel - 1 female -0)
//        member_profile_img: 회원 프로필 사진

public class UpdateMyProfileRequest extends AbstractRequest<ResultMessage> {

    Request mRequest;

    private final static String MEMBERS = "members";
    private final static String ME = "me";

    final static String ZIP_CODE = "member_zipcode_1";
    final static String ADRESS = "member_address_1";
    final static String PHONE_NUM = "member_phone";
    final static String NICKNAME = "alias";
    final static String PROFILE_IMG = "member_profile_img";
    final static String GENDER = "member_gender";


    public UpdateMyProfileRequest(Context context, String member_phone, String member_zipcode_1, String member_address_1, String alias, String member_profile_img, String member_gender) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment(MEMBERS)
                .addPathSegment(ME)
                .build();
        RequestBody body = new FormBody.Builder()
                .add(PHONE_NUM, member_phone)
                .add(ZIP_CODE, member_zipcode_1)
                .add(ADRESS, member_address_1)
                .add(NICKNAME, alias)
                .add(GENDER, member_gender)
                .add(PROFILE_IMG, member_gender)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .put(body)
                .tag(context)
                .build();


    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultMessage>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
