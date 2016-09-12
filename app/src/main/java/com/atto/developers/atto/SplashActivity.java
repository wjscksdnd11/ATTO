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
import com.atto.developers.atto.networkdata.userdata.LoginData;
import com.atto.developers.atto.request.FacebookLoginRequest;
import com.atto.developers.atto.request.LocalLoginRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler;
    HTextView hTextView;

    LoginManager loginManager;
    CallbackManager callbackManager;


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

        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

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
        if (isFacebookLogin()) {
            processFacebookLogin();
        } else if (isAutoLogin()) {
            processAutoLogin();
        } else {
            moveLoginActivity();
        }
    }

    private boolean isFacebookLogin() {
        if (!TextUtils.isEmpty(PropertyManager.getInstance().getFacebookId())) {
            return true;
        }
        return false;
    }

    private void processFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
            resetFacebookAndMoveLoginActivity();
            return;
        }
        if (accessToken != null) {
            String token = accessToken.getToken();
            String regId = PropertyManager.getInstance().getRegistrationId();
            FacebookLoginRequest request = new FacebookLoginRequest(this, token);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<LoginData>() {
                @Override
                public void onSuccess(NetworkRequest<LoginData> request, LoginData result) {
                    if (result.getCode() == 1) {
                        moveMainActivity();
                    } else {
                        resetFacebookAndMoveLoginActivity();
                    }
                }

                @Override
                public void onFail(NetworkRequest<LoginData> request, int errorCode, String errorMessage, Throwable e) {
                    loginManager.logOut();
                    facebookLogin();
                }
            });
        } else {
            facebookLogin();
        }
    }

    private void facebookLogin() {
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
                    resetFacebookAndMoveLoginActivity();
                    return;
                }
                FacebookLoginRequest request = new FacebookLoginRequest(SplashActivity.this, accessToken.getToken());
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<LoginData>() {
                    @Override
                    public void onSuccess(NetworkRequest<LoginData> request, LoginData result) {
                        if (result.getCode() == 1) {
                            moveMainActivity();
                        } else {
                            resetFacebookAndMoveLoginActivity();
                        }
                    }

                    @Override
                    public void onFail(NetworkRequest<LoginData> request, int errorCode, String errorMessage, Throwable e) {
                        resetFacebookAndMoveLoginActivity();
                    }
                });
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        loginManager.logInWithReadPermissions(this, null);
    }

    private void resetFacebookAndMoveLoginActivity() {
        loginManager.logOut();
        PropertyManager.getInstance().setFacebookId("");
        moveLoginActivity();
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