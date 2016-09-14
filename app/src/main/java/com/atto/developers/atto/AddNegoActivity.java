package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.request.AddNegoCardRequest;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNegoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nego);
        ButterKnife.bind(this);
        initToolBar();


    }
    @OnClick(R.id.btn_nego_register)
    public void onCompleteNegoRegister() {
        Intent intent = new Intent(AddNegoActivity.this, MainActivity.class);
        AddNegoCardRequest request = new AddNegoCardRequest(this, "1", "5000", "2016. 02. 23.", "깔깔깔", null);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NegoData>() {
            @Override
            public void onSuccess(NetworkRequest<NegoData> request, NegoData result) {
                Log.d("AddNegoActivity", "성공 : " + result.getCode());
            }

            @Override
            public void onFail(NetworkRequest<NegoData> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("AddNegoActivity", "실패 : " + errorMessage);


            }
        });

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    @OnClick(R.id.btn_move_setting_price)
    public void onMoveSettingPrice() {

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_add_nego);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
