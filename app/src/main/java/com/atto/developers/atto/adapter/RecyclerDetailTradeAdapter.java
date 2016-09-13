package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.viewholder.DetailTradeHeaderViewHolder;
import com.atto.developers.atto.viewholder.DetailTradeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class RecyclerDetailTradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = RecyclerDetailTradeAdapter.class.getSimpleName();
    private TradeData tradeData;
    private List<NegoData> mNegoDataList = new ArrayList<>();


    public void setTradeData(TradeData tradeData) {
        this.tradeData = tradeData;
    }

    public void addAll(List<NegoData> list) {
//        if (!mNegoDataList.isEmpty()) mNegoDataList.clear();
        mNegoDataList.addAll(list);
        notifyDataSetChanged();
    }

    private static final int VIEW_TYPE_HEADER = 100;
    private static final int VIEW_TYPE_GROUP = 200;

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_GROUP;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header_detail, parent, false);
                DetailTradeHeaderViewHolder holder = new DetailTradeHeaderViewHolder(view);
                Log.e(TAG, "Adapter onCreateView header");
                return holder;
            }
            case VIEW_TYPE_GROUP: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_img_detail_trade, parent, false);
                DetailTradeViewHolder holder = new DetailTradeViewHolder(view);
                Log.e(TAG, "Adapter onCreateView group");
                return holder;
            }
        }
        throw new IllegalArgumentException("invalid view type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // HEADER
        if (holder instanceof DetailTradeHeaderViewHolder) {
            DetailTradeHeaderViewHolder hvh = (DetailTradeHeaderViewHolder) holder;
            hvh.setTradeData(tradeData);
        }
        // GROUP
        else {
            DetailTradeViewHolder gvh = (DetailTradeViewHolder) holder;
            gvh.setNegoDataList(mNegoDataList);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        count++;
        for (int i = 0; i < mNegoDataList.size(); i++) {
            count++;
        }
        return count;
    }


    /*@Override
    public int getItemCount() {
        return mTradeData == null ? 0 : (mNegoDataList == null ? 0 : mNegoDataList.size() + 1);
    }*/
}

