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
import com.atto.developers.atto.request.SearchListRequest;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnifiedSearchActivity extends AppCompatActivity {

    String pageNumber = "1";
    String rowCount = "50";
    String keyword = "반지";
    String keywordId = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unified_search);
        ButterKnife.bind(this);
        initToolBar();

    }

    @OnClick(R.id.btn_search)
    public void onSearch() {
        SearchListRequest request = new SearchListRequest(this, pageNumber, rowCount, keyword, keywordId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<TradeData>>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListData<TradeData>> request, TradeListData<TradeData> result) {
                Toast.makeText(UnifiedSearchActivity.this, "search size : " + result.getData().length, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(NetworkRequest<TradeListData<TradeData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(UnifiedSearchActivity.this, "실패", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_unified_search);
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
}
