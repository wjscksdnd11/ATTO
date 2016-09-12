package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.atto.developers.atto.manager.FontManager;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.manager.PropertyManager;
import com.atto.developers.atto.networkdata.ResultMessage;
import com.atto.developers.atto.request.LocalLoginRequest;
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
        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/nanumgothic.ttf"));
// be sure to set custom typeface before setting the animate type, otherwise the font may not be updated.
        hTextView.setAnimateType(HTextViewType.LINE);
        hTextView.animateText("atto");

    /*    textView = (TextView)findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_scale);
        textView.startAnimation(animation);*/
        mHandler = new Handler(Looper.getMainLooper());
        loginSharedPreference();

    }

    private void moveMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void moveLoginActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }

    private void loginSharedPreference() {
        if (isAutoLogin()) {
            processAutoLogin();
        } else {
            moveLoginActivity();
        }
    }

    private boolean isAutoLogin() {
        String email = PropertyManager.getInstance().getEmail();
        return !TextUtils.isEmpty(email);
    }

    private void processAutoLogin() {
        String email = PropertyManager.getInstance().getEmail();
        if (!TextUtils.isEmpty(email)) {
            String password = PropertyManager.getInstance().getPassword();
            String regid = PropertyManager.getInstance().getRegistrationId();
            LocalLoginRequest request = new LocalLoginRequest(this, email, password, regid);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
                @Override
                public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                    moveMainActivity();
                }

                @Override
                public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
                    moveLoginActivity();
                }
            });

        }
    }
}