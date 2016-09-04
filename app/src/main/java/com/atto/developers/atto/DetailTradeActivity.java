package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.atto.developers.atto.adapter.RecyclerDetailTradeAdapter;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListData;
import com.atto.developers.atto.request.NegoCardListRequest;
import com.atto.developers.atto.request.TradeListRequest;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTradeActivity extends AppCompatActivity {
    @BindView(R.id.re_list)
    RecyclerView listView;
    RecyclerDetailTradeAdapter mAdapter;

    @OnClick(R.id.btn_move_nego_register)
    public void onMoveAddNego() {
        Intent intent = new Intent(DetailTradeActivity.this, AddNegoActivity.class);
        startActivity(intent);
        finish();
    }


    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_detail_trade);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_grey);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trade);
        ButterKnife.bind(this);
        initToolBar();
        mAdapter = new RecyclerDetailTradeAdapter();
        listView.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(manager);
        mAdapter.setOnAdapterItemClickListener(new RecyclerDetailTradeAdapter.OnAdapterItemClickListener() {

            @Override
            public void onAdapterItemClick(View view, NegoData negoData, int position) {
                Toast.makeText(DetailTradeActivity.this, "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });
        initData();
        //init();
    }

    private void initData() {
        mAdapter.clear();
        TradeListRequest request = new TradeListRequest(getApplicationContext(), "10", "10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<TradeData>>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListData<TradeData>> request, TradeListData<TradeData> result) {
                TradeData[] data =  result.getData();
                Toast.makeText(getApplicationContext(), "성공 : ", Toast.LENGTH_SHORT).show();
                mAdapter.addAll(Arrays.asList(" "+data));
                init();

            }

            @Override
            public void onFail(NetworkRequest<TradeListData<TradeData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getApplicationContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();

            }
       });
    }
    private void init() {
        NegoCardListRequest request = new NegoCardListRequest(getApplicationContext(), "5", "10", "10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<NegoData>>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListData<NegoData>> request, TradeListData<NegoData> result) {
                NegoData [] data = result.getData();
                Toast.makeText(getApplicationContext(), "성공 : ", Toast.LENGTH_SHORT).show();
                mAdapter.addAll(Arrays.asList(" "+data));

            }

            @Override
            public void onFail(NetworkRequest<TradeListData<NegoData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getApplicationContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();

            }
        });














//    private void initData() {
//        mAdapter.clear();
//        TradeListRequest request = new TradeListRequest(getApplicationContext(), "10", "10");
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeData>() {
//            @Override
//            public void onSuccess(NetworkRequest<TradeData> request, TradeData result) {
//               TradeData data = result.getTrade_id();
//
//                Toast.makeText(getApplicationContext(), "성공 : ", Toast.LENGTH_SHORT).show();
//                mAdapter.addAll(result.getTrade_id());
//                init();
//            }
//
//            @Override
//            public void onFail(NetworkRequest<TradeData> request, int errorCode, String errorMessage, Throwable e) {
//                Toast.makeText(getApplicationContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//    private void init() {
//        NegoCardListRequest request = new NegoCardListRequest(getApplicationContext(), "1", "2", "10", "10");
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NegoListData>() {
//            @Override
//            public void onSuccess(NetworkRequest<NegoData> request, NegoData result) {
//                NegoListData [] data = request;
//                Toast.makeText(getApplicationContext(), "성공 : ", Toast.LENGTH_SHORT).show();
//                mAdapter.addAll(Arrays.asList(data));
//
//            }
//
//            @Override
//            public void onFail(NetworkRequest<NegoData> request, int errorCode, String errorMessage, Throwable e) {
//                Toast.makeText(getApplicationContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
    }

