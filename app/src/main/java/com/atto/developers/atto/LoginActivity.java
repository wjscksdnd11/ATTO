package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.manager.PropertyManager;
import com.atto.developers.atto.networkdata.FacebookLoginData;
import com.atto.developers.atto.networkdata.userdata.FacebookLogin;
import com.atto.developers.atto.networkdata.userdata.FacebookUserData;
import com.atto.developers.atto.request.FacebookLoginRequest;
import com.atto.developers.atto.request.LocalLoginRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.text_login_signup)
    TextView signUpView;
    @BindView(R.id.text_login_guest)
    TextView loginGuestView;
    @BindView(R.id.text_search_password)
    TextView searchPasswordView;

    @BindView(R.id.edit_login_email)
    AppCompatEditText emailView;

    @BindView(R.id.edit_login_password)
    AppCompatEditText passwordView;
@BindView(R.id.login_button)
LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        signUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
        loginGuestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveMainActivity();

            }
        });
        searchPasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
                startActivity(intent);

            }
        });




    }
    @OnClick(R.id.login_button)
    public void facebookLogin() {
        loginButton.setReadPermissions("email");
//        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                processAfterFacebookLogin();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void processAfterFacebookLogin() {
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            String token = accessToken.getToken();
            String regid = PropertyManager.getInstance().getRegistrationId();
            FacebookLoginRequest request = new FacebookLoginRequest(this, token);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<FacebookLogin<FacebookUserData>>() {
                @Override
                public void onSuccess(NetworkRequest<FacebookLogin<FacebookUserData>> request, FacebookLogin<FacebookUserData> result) {
                    if (result.getCode() == 1) {
                        String facebookId = accessToken.getUserId();
                        PropertyManager.getInstance().setFacebookId(facebookId);
                     moveMainActivity();
                    } else if (result.getCode() == 3){
                        FacebookUserData user = (FacebookUserData) result.getResult();
                        changeFacebookSignup(user);
                    }
                }

                @Override
                public void onFail(NetworkRequest<FacebookLogin<FacebookUserData>> request, int errorCode, String errorMessage, Throwable e) {

                }
            });
        }
    }
    @OnClick(R.id.btn_local_login)
    public void onLocalLogin() {


        final String e_mail = emailView.getText().toString();
        final String password = passwordView.getText().toString();
        String member_registration_token = PropertyManager.getInstance().getRegistrationId();

        if (e_mail.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_LONG).show();

        } else {

            LocalLoginRequest request = new LocalLoginRequest(this, e_mail, password, member_registration_token);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<FacebookLoginData>() {
                @Override
                public void onSuccess(NetworkRequest<FacebookLoginData> request, FacebookLoginData result) {
                    PropertyManager.getInstance().setEmail(e_mail);
                    PropertyManager.getInstance().setPassword(password);
                    Toast.makeText(LoginActivity.this, "성공 : " + result.getMessage(), Toast.LENGTH_LONG).show();
                    moveMainActivity();
                }

                @Override
                public void onFail(NetworkRequest<FacebookLoginData> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();

                }
            });

        }


    }
//
//    @OnClick(R.id.btn_kakao_login)
//    public void onKaKaoLogin() {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

    public void moveMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void changeFacebookSignup(FacebookUserData user){

        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        intent.putExtra("FacebookUser",user);
        startActivity(intent);
        finish();
    }

}
