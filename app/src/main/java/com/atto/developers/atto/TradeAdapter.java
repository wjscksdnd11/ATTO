package com.atto.developers.atto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.data.NetworkData.TradeData.TradeData;
import com.atto.developers.atto.view.TradeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class TradeAdapter extends RecyclerView.Adapter<TradeViewHolder> implements TradeViewHolder.OnTradeItemClickListener {

    List<TradeData> items = new ArrayList<>();

    public void add(TradeData tradeData) {
        items.add(tradeData);
        notifyDataSetChanged();
    }

    public interface OnAdapterItemClickListener {
        public void onAdapterItemClick(View view, TradeData tradeData, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public TradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_real_time_trade, parent, false);
        TradeViewHolder holder = new TradeViewHolder(view);
        holder.setOnTradeItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(TradeViewHolder holder, int position) {
        holder.setTradeData(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onTradeItemClick(View view, TradeData tradeData, int position) {

        if(listener != null) {
            listener.onAdapterItemClick(view, tradeData, position);
        }

    }
}
