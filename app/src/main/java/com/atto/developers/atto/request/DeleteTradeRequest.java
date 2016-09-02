package com.atto.developers.atto.request;

import android.content.Context;

import com.atto.developers.atto.networkdata.ResultMessage;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class DeleteTradeRequest extends AbstractRequest<ResultMessage>{

    Request mRequest;
    private final static String TRADES = "trades";



    public DeleteTradeRequest(Context context, String tid) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(TRADES)
                .addPathSegment(tid)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .delete()
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultMessage>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }

}
