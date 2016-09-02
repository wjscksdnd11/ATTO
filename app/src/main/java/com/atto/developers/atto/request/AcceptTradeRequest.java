package com.atto.developers.atto.request;

import android.content.Context;

import com.atto.developers.atto.networkdata.ResultMessage;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class AcceptTradeRequest extends AbstractRequest<ResultMessage> {

    Request mRequest;

    private final static String TRADES = "trades";

    private final static String ACTION = "action";
    private final static String ACTION_VAlUE = "t_accept";
    private final static String NID = "nid";

    public AcceptTradeRequest(Context context, String tid, String nid) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(TRADES)
                .addPathSegment(tid)
                .addQueryParameter(ACTION, ACTION_VAlUE)
                .build();
        RequestBody body = new FormBody.Builder()
                .add(NID, nid)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .put(body)
                .build();

    }


    @Override
    protected Type getType() {
        return null;
    }

    @Override
    public Request getRequest() {
        return null;
    }
}
