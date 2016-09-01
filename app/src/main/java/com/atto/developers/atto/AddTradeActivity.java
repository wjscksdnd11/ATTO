package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.fragment.PickupDateFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListItemData;
import com.atto.developers.atto.request.AddTradeRequest;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trade);
        ButterKnife.bind(this);
        initToolBar();

    }

    @OnClick(R.id.text_add_trade_register_trade)
    public void onTradeRegister() {
        addData();
        Intent intent = new Intent(AddTradeActivity.this, DetailTradeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_wish_delevery)
    public void onPickUpDate() {
        FragmentManager fm = getSupportFragmentManager();
        PickupDateFragment dialogFragment = new PickupDateFragment();
        dialogFragment.show(fm, "fragment_pickup_date");
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_addtrade);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onDateSelectValue(String selectedDate) {
        TextView dateView = (TextView) findViewById(R.id.text_pickup_date);
        dateView.setText(selectedDate);
    }

    public void addData() {

        File[] files = {new File("sdf"), new File("sdf")};
        String[] str = {"str", "str"};

        AddTradeRequest request = new AddTradeRequest(this, "10", "10", "10", "10", "10", "10", str, files);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListItemData>() {

            @Override
            public void onSuccess(NetworkRequest<TradeListItemData> request, TradeListItemData result) {
                TradeData tradeData = result.getData();
                Toast.makeText(AddTradeActivity.this, "성공 : " + tradeData.getTrade_id(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<TradeListItemData> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(AddTradeActivity.this, "실패 : " + errorCode, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
