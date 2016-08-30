package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ImageButton imageBtn = (ImageButton)toolbar.findViewById(R.id.ic_back);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView textView = (TextView)toolbar.findViewById(R.id.text_title);
        textView.setText(R.string.activity_signup);


       Button btn = (Button)findViewById(R.id.btn_complete_signup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SignUpRequest request = new SignUpRequest(String email, String password, String name, String zipcode, String adress_1, String phone, String registration_token);
//                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
//
//                    @Override
//                    public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
//                        Log.i("result",result.getMessage());
//                        Toast.makeText(SignUpActivity.this,result.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
//
//
//                        Log.e("error",request+" , "+errorCode+" , "+errorMessage);
//                    }
//                });

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
