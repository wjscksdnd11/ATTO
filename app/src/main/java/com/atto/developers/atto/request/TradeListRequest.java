package com.atto.developers.atto.request;

import android.util.Log;

import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListData;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class TradeListRequest extends AbstractRequest<TradeListData<TradeData>> {
    Request mRequest;

    private final static String TRADES = "trades";
    private final static String PAGE_NO = "pageNo";
    private final static String ROW_COUNT = "rowCount";

    public TradeListRequest(String page_no, String row_count) {

        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(TRADES)
                .addQueryParameter(PAGE_NO, page_no)
                .addQueryParameter(ROW_COUNT, row_count)
                .build();
        mRequest = new Request.Builder()
                .url(url)
                .build();
        Log.i("url", url.toString());
    }


    @Override
    protected Type getType() {
        return new TypeToken<TradeListData<TradeData>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
