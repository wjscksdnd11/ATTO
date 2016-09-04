package com.atto.developers.atto.request;

import android.content.Context;

import com.atto.developers.atto.networkdata.makerdata.MakerListItemData;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * Created by jeon on 2016-09-04.
 */
public class DetailMakerRequest extends AbstractRequest<MakerListItemData> {
    Request mRequest;
//제작자 등록

    MediaType jpeg = MediaType.parse("image/jpeg");
    private final static String MAKER = "makers";
    private final static String LINE_TAG = "line_tag";
    private final static String REPRESETATION_IMG = "representation_img";


    public DetailMakerRequest(Context context, String line_tag) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment(MAKER)
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