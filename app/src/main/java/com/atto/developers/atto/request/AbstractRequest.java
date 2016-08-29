package com.atto.developers.atto.request;

import com.atto.developers.atto.manager.NetworkRequest;

import okhttp3.HttpUrl;

/**
 * Created by Tacademy on 2016-08-29.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {

    protected HttpUrl.Builder getBaseUrlBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("ec2-52-78-136-23.ap-northeast-2.compute.amazonaws.com");
        builder.port(443);
        return builder;
    }


}
