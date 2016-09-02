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
import com.atto.developers.atto.networkdata.negodata.NegoListData;

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

        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
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
            public void onAdapterItemClick(View view, NegoListData negoListData, int position) {
                Toast.makeText(DetailTradeActivity.this, "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });
        initData();
    }


    private void initData() {

    }

    public void startIntent() {

    }
}
