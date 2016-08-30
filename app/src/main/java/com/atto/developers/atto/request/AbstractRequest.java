package com.atto.developers.atto.request;

import com.atto.developers.atto.manager.NetworkRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-29.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {

    private  final static String HOST = "ec2-52-78-136-23.ap-northeast-2.compute.amazonaws.com";
    private  final static int HTTPS_PORT = 443;
    private final static int HTTP_PORT=80;

    protected HttpUrl.Builder getBaseUrlHttpsBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host(HOST);
        builder.port(HTTPS_PORT);
        return builder;
    }


        protected HttpUrl.Builder getBaseUrlBuilder() {
                HttpUrl.Builder builder = new HttpUrl.Builder();
                builder.scheme("http");
                builder.host(HOST);
                builder.port(HTTP_PORT);
                return builder;
        }
    @Override
    protected T parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        Type type = new TypeToken<T>(){}.getType();

        T  temp = gson.fromJson(text,type );
        return temp;
    }


    protected abstract Type getType();
}
