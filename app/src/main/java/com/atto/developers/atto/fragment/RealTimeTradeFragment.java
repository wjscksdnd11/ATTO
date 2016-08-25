package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.DetailTradeActivity;
import com.atto.developers.atto.R;
import com.atto.developers.atto.adapter.TradeAdapter;
import com.atto.developers.atto.data.NetworkData.ListData.KeywordList;
import com.atto.developers.atto.data.NetworkData.TradeData.TradeData;
import com.atto.developers.atto.data.NetworkData.UserData.Member_info;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeTradeFragment extends Fragment {


    RecyclerView listView;
    TradeAdapter mAdapter;
    public RealTimeTradeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_real_time_trade, container, false);

        setHasOptionsMenu(true);

        listView = (RecyclerView) view.findViewById(R.id.rv_list);
        mAdapter = new TradeAdapter();



        mAdapter.setOnAdapterItemClickListener(new TradeAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, TradeData tradeData, int position) {
                Intent intent = new Intent(getContext(),  DetailTradeActivity.class);
                startActivity(intent);
            }
        });

        listView.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(manager);

        initData();
        return view;
    }

    private void initData() {

        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            TradeData tradeData = new TradeData();
            Member_info member_info = new Member_info();
            member_info.setMember_alias("atto " + i);

            KeywordList keywordList = new KeywordList();
            keywordList.setKey_word_1("무방부제");
            tradeData.setMember_info(member_info);
            tradeData.setTrade_status(r.nextInt(5) + " 개 협상 진행 중");
            tradeData.setTrade_price(r.nextInt(15000) + "");
            tradeData.setTrade_key_word_lists(keywordList);
            tradeData.setTrade_dtime("2016년 8월" + r.nextInt(30) + "일");

            mAdapter.add(tradeData);

        }

    }
}
