package com.atto.developers.atto.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by goodn on 2016-08-30.
 */
public class RatingBar extends android.widget.RatingBar {

    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = getIndeterminateDrawable().getIntrinsicWidth() * 5 + getPaddingLeft() +
                getPaddingRight();
        final int height = getIndeterminateDrawable().getIntrinsicHeight() + getPaddingTop()
                + getPaddingBottom();
        setMeasuredDimension(width, height);
    }
}