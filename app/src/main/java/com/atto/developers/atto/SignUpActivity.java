package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.ResultMessage;
import com.atto.developers.atto.request.SignUpRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    String email = "gsd";
    String password = "SDfdsf";
    String name = "sdf";
    String zipcode = "sdf";
    String adress_1 = "sdf";
    String phone = "sdf";
    String registration_token = "sdf";

    @BindView(R.id.text_signup_set_address)
    TextView addressView;

    @BindView(R.id.text_signup_set_postcode)
    TextView postCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initToolBar();
    }

    @OnClick(R.id.btn_complete_signup)
    public void completeSignUp() {
        SignUpRequest request = new SignUpRequest(this, email, password, name, zipcode, adress_1, phone, registration_token);
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

    @OnClick(R.id.btn_img_address)
    public void onSearchAddress() {
        Intent i = new Intent(SignUpActivity.this, SearchAddressActivity.class);
        startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
    }


    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_signup);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_atto_main));
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_grey);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        String[] strList;
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        strList = new String(data).split(",");
                        postCodeView.setText(strList[0]);
                        addressView.setText(strList[1]);
                    }
                }
                break;
        }
    }
}
