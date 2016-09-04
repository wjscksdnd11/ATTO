package com.atto.developers.atto.request;

import android.content.Context;

import com.atto.developers.atto.networkdata.ResultMessage;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by jeon on 2016-09-04.
 */

/*
file_name : 포트 폴리오 제목
        file_key_word_ids : 포트 폴리오 키워드 ID들 (3개 까지)(number)
        portfolio_img : 포트 폴리오 이미지

        예시) /portfolioes
*/

public class AddPortfolioRequest extends AbstractRequest<ResultMessage> {
Request mRequest;

    private final static String PORTFOLIO = "portfolioes";
    private final static String FILE_KEY_WORD_IDS="file_key_word_ids";
    private final static String PORTFOLIO_IMG = "portfolio_img";
    public AddPortfolioRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(PORTFOLIO)
                .build();
//        MultipartBody.Builder body=  new MultipartBody.Builder()
//                .addFormDataPart()

        //todo 상익이형한테 키워드랑 이미지 어떻게 올지 물어보고 마무리하면 되겠다..
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
