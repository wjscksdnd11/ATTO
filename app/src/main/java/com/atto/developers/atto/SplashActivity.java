package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler;

    HTextView hTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        hTextView = (HTextView) findViewById(R.id.text);
        hTextView.setTextColor(Color.WHITE);
        hTextView.setBackgroundColor(Color.BLACK);
        hTextView.setTypeface(null);
        hTextView.setAnimateType(HTextViewType.LINE);
        /*
        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/nanumgothic.ttf"));
        hTextView.setAnimateType(HTextViewType.LINE);
        */
        hTextView.animateText("ATTO");
        mHandler = new Handler(Looper.getMainLooper());
        moveLoginActivity();

    }

    private void moveMainActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    private void moveLoginActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 10000);

    }
}