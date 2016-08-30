package com.atto.developers.atto.request;

import android.util.Log;

import com.atto.developers.atto.networkdata.ResultTemp;
import com.atto.developers.atto.networkdata.userdata.MyProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class MyProfileRequest extends AbstractRequest<MyProfile> {

    private final static String MEMBERS = "members";
    private final static String ME ="me";
    private final static String ACTION="action";
    private final static String ACTION_VALUE ="password";
    private final static String PASSWORD = "member_password";
    private final static String NEW_PASSWORD ="member_newpassword";

    Request mRequest;


    public MyProfileRequest() { //내 프로필 조회

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(MEMBERS)
                .addPathSegment(ME)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .build();


        Log.i("URL", mRequest.url().toString());


    }
    public MyProfileRequest(String password, String new_password) { //비밀번호 수정

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(MEMBERS)
                .addPathSegment(ME)
                .build();
        RequestBody body = new FormBody.Builder()
                .add(ACTION,ACTION_VALUE)
                .add(PASSWORD, password)
                .add(NEW_PASSWORD,new_password)
                .build();
        mRequest = new Request.Builder()
                .url(url)
                .put(body)
                .build();


        Log.i("URL", mRequest.url().toString());


    }

    @Override
    protected MyProfile parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        Type type = new TypeToken<MyProfile>(){}.getType();

        MyProfile  temp = gson.fromJson(text,type );
        return temp;
    }
    @Override
    public Request getRequest() {
        return mRequest;
    }




    @Override
    protected Type getType() {
        return new TypeToken<ResultTemp<MyProfile>>(){}.getType();
    }
}
