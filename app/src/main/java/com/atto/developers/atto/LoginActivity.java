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
import com.atto.developers.atto.networkdata.ResultMessage;
import com.atto.developers.atto.request.LocalLoginRequest;

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

    @OnClick(R.id.btn_local_login)
    public void onLocalLogin() {

        final String e_mail = emailView.getText().toString();
        final String password = passwordView.getText().toString();
        String member_registration_token = PropertyManager.getInstance().getRegistrationId();

        LocalLoginRequest request = new LocalLoginRequest(this, e_mail, password, member_registration_token);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
            @Override
            public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                PropertyManager.getInstance().setEmail(e_mail);
                PropertyManager.getInstance().setPassword(password);
                Toast.makeText(LoginActivity.this, "성공 : " + result.getMessage(), Toast.LENGTH_LONG).show();
                moveMainActivity();
            }

            @Override
            public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(LoginActivity.this, "실패 : " + errorCode, Toast.LENGTH_LONG).show();

            }
        });
    }

    @OnClick(R.id.btn_kakao_login)
    public void onKaKaoLogin() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void moveMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
