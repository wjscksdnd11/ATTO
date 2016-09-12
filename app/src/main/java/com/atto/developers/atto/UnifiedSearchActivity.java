package com.atto.developers.atto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.ListData;
import com.atto.developers.atto.request.SearchListRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnifiedSearchActivity extends AppCompatActivity {

    @BindView(R.id.edit_input_search)
    AppCompatEditText inputSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unified_search);
        ButterKnife.bind(this);
        initToolBar();

    }

    @OnClick(R.id.btn_search)
    public void onSearch() {

        String pageNumber = "1";
        String rowCount = "50";
        String keyword = inputSearchView.getText().toString();
        String keywordId = "1";

        SearchListRequest request = new SearchListRequest(this, pageNumber, rowCount, keyword, keywordId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ListData<TradeData>>() {
            @Override

            public void onSuccess(NetworkRequest<ListData<TradeData>> request, ListData<TradeData> result) {
                TradeData[] tradeData = result.getData();
                if (tradeData != null) {
                    if (tradeData.length > 0) {
                        Toast.makeText(UnifiedSearchActivity.this, "search size : " + result.getData().length, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(UnifiedSearchActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFail(NetworkRequest<ListData<TradeData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(UnifiedSearchActivity.this, "실패", Toast.LENGTH_LONG).show();

            }
        });
    }
    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_unified_search);
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
