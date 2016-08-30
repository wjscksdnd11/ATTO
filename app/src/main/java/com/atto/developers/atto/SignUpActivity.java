package com.atto.developers.atto;

import android.content.Intent;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

=======
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.ResultMessage;
import com.atto.developers.atto.request.SignUpRequest;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    String email = "gsd";
    String password = "SDfdsf";
    String name = "sdf";
    String zipcode = "sdf";
    String adress_1 = "sdf";
    String phone = "sdf";
    String registration_token = "sdf";


>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
<<<<<<< HEAD

       Button btn_final_check = (Button)findViewById(R.id.button4);

        btn_final_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
=======
        ButterKnife.bind(this);
        initToolBar();
    }

    @OnClick(R.id.btn_complete_signup)
    public void completeSignUp() {
        SignUpRequest request = new SignUpRequest(email, password, name, zipcode, adress_1, phone, registration_token);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {

            @Override
            public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                Log.i("result", result.getMessage());
                Toast.makeText(SignUpActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {

                Log.e("error", request + " , " + errorCode + " , " + errorMessage);
            }
        });

        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_signup);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
>>>>>>> origin/master
            }
        });
    }
}
