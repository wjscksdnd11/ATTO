package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.atto.developers.atto.AddTradeActivity;
import com.atto.developers.atto.DetailTradeActivity;
import com.atto.developers.atto.R;
import com.atto.developers.atto.adapter.RecyclerRealTimeTradeAdapter;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListData;
import com.atto.developers.atto.request.TradeListRequest;

import java.util.Arrays;

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

        final ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.show(getFragmentManager(), "progress");
        mAdapter.clear();
        TradeListRequest request  = new TradeListRequest(getContext(), "10","10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<TradeData>>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListData<TradeData>> request, TradeListData<TradeData> result) {
                TradeData[] data =  result.getData();
                Toast.makeText(getContext(),"성공 : "+data[0].getTrade_product_img(),Toast.LENGTH_SHORT).show();
                Log.d("realtime", "성공 : " + data[0].getMember_info().getMember_profile_img());
                Log.d("realtime", "성공 : " + data[0].getTrade_key_word_info());
                mAdapter.addAll(Arrays.asList(data));
                dialogFragment.dismiss();
            }
            @Override
            public void onFail(NetworkRequest<TradeListData<TradeData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"실패 : "+errorCode,Toast.LENGTH_SHORT).show();
                dialogFragment.dismiss();
            }
        });

        /*Random r = new Random();
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
        }*/

    }
}
