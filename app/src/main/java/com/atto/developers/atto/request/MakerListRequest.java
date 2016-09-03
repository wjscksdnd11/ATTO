package com.atto.developers.atto.request;

import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.networkdata.tradedata.TradeListData;

import java.lang.reflect.Type;

import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-02.
 */

public class MakerListRequest extends AbstractRequest<TradeListData<MakerData>> {
    public MakerListRequest() {
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
