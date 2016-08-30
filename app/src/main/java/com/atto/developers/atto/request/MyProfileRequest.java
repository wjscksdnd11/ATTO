package com.atto.developers.atto.request;

import android.content.Context;
import android.util.Log;

import com.atto.developers.atto.networkdata.userdata.MyProfile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class MyProfileRequest extends AbstractRequest<MyProfile> {

    Request request;

    public MyProfileRequest(Context context) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("members")
                .addPathSegment("me")
                .build();

        request = new Request.Builder()
                .url(url)
                .build();


        Log.i("URL", request.url().toString());


    }


    @Override
    protected MyProfile parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        MyProfile temp = gson.fromJson(text, MyProfile.class);
        Log.i("MyProfile Result :", temp.getMessage()+" , "+ temp.getData().getMember_alias()+" , "+ temp.getData().getMember_address_1()+" , "+ temp.getData().getMember_phone());
        return temp;
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
