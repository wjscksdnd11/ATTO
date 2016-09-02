package com.atto.developers.atto.request;

import com.atto.developers.atto.networkdata.ResultMessage;

import java.lang.reflect.Type;

import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class AcceptTradeRequest extends AbstractRequest<ResultMessage> {



    public AcceptTradeRequest() {
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
