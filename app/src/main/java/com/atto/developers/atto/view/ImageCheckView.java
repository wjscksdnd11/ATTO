package com.atto.developers.atto.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atto.developers.atto.R;

/**
 * Created by goodn on 2016-08-31.
 */
public class ImageCheckView extends FrameLayout implements Checkable {
    public ImageCheckView(Context context) {
        super(context);
        init();
    }

    public ImageCheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    ImageView checkView;
    private void init() {
        inflate(getContext(), R.layout.view_check, this);
        checkView = (ImageView)findViewById(R.id.image_check);
    }

    boolean isChecked = false;
    private void drawCheck() {
        if (isChecked) {
            checkView.setImageResource(android.R.drawable.checkbox_on_background);
        } else {
            checkView.setImageResource(android.R.drawable.checkbox_off_background);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            drawCheck();
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
