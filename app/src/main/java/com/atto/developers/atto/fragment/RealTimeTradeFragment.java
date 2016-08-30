package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.AddTradeActivity;
import com.atto.developers.atto.DetailTradeActivity;
import com.atto.developers.atto.R;
import com.atto.developers.atto.adapter.RecyclerRealTimeTradeAdapter;
import com.atto.developers.atto.networkdata.listdata.KeywordList;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.userdata.Member_info;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeTradeFragment extends Fragment {


    @BindView(R.id.rv_list)
    RecyclerView listView;
    RecyclerRealTimeTradeAdapter mAdapter;

    public RealTimeTradeFragment() {
        // Required empty public constructor
    }

    public static RealTimeTradeFragment newInstance() {

        RealTimeTradeFragment fragment = new RealTimeTradeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_real_time_trade, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new RecyclerRealTimeTradeAdapter();
        listView.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(manager);
        mAdapter.setOnAdapterItemClickListener(new RecyclerRealTimeTradeAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, TradeData tradeData, int position) {
                Intent intent = new Intent(getContext(), DetailTradeActivity.class);
                startActivity(intent);
            }
        });

        initData();

        return view;
    }

    @OnClick(R.id.btn_trade_add)
    void onAddTrade() {
        Intent intent = new Intent(getContext(), AddTradeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            TradeData tradeData = new TradeData();
            Member_info member_info = new Member_info();
            member_info.setMember_alias("atto " + i);
            KeywordList keywordList = new KeywordList();
            keywordList.setKey_word_1("무방부제");
            tradeData.setTrade_title("Title " + i);
            tradeData.setMember_info(member_info);
            tradeData.setTrade_status(r.nextInt(5) + " 개 협상 진행 중");
            tradeData.setTrade_price(r.nextInt(15000) + "");
            tradeData.setTrade_key_word_lists(keywordList);
            tradeData.setTrade_dtime("2016년 8월" + r.nextInt(30) + "일");
            tradeData.setTrade_dday("D-" + i);
            mAdapter.add(tradeData);
        }

    }
}
