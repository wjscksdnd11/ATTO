package com.atto.developers.atto.request;

import com.atto.developers.atto.networkdata.ResultMessage;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-01.
 */
//   /auth/logout
public class LogoutRequest extends AbstractRequest<ResultMessage> {

    Request mRequest;
    private final static String AUTH = "auth";
    private final static String LOGOUT = "logout";
    public LogoutRequest() {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(AUTH)
                .addPathSegment(LOGOUT)
                .build();

        mRequest = new Request.Builder()
                .url(url)

                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<ResultMessage>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return null;
    }


}