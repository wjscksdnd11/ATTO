package com.atto.developers.atto.request;

import android.content.Context;
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
public class TestRequest extends AbstractRequest<ResultMessage> {
    Request request;

    public TestRequest(Context context) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("members")
                .build();

        RequestBody body = new FormBody.Builder()
//                .add("email", email)
//                .add("password", password)
//                .add("registrationId", regId)
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
