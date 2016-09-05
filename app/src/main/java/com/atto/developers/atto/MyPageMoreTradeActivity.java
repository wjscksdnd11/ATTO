package com.atto.developers.atto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListData;
import com.atto.developers.atto.request.MyTradeListRequest;

public class MyPageMoreTradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_more_trade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        initToolBar();
        initData();


    }

    private void initData() {

        MyTradeListRequest request = new MyTradeListRequest(this, "1", "50");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<TradeData>>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListData<TradeData>> request, TradeListData<TradeData> result) {
                Toast.makeText(MyPageMoreTradeActivity.this, "성공 : " + result.getData(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFail(NetworkRequest<TradeListData<TradeData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(MyPageMoreTradeActivity.this, "실패 errorCode : " + errorCode, Toast.LENGTH_LONG).show();

            }
        });

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_mypage_more_trade);
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
