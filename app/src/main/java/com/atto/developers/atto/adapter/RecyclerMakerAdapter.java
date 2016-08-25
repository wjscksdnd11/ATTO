package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;


import com.atto.developers.atto.data.networkData.makerData.MakerData;
import com.atto.developers.atto.viewholder.RecyclerMakerViewHolder;

import java.util.ArrayList;
import java.util.List;



public class RecyclerMakerAdapter extends RecyclerView.Adapter<RecyclerMakerViewHolder>
        implements RecyclerMakerViewHolder.OnTradeItemClickListener{

    List<MakerData> items = new ArrayList<>();

    public void add(MakerData d) {
        items.add(d);
        notifyDataSetChanged();
    }


    public interface OnAdapterItemClickListener {
        public void onAdapterItemClick(View view, MakerData makerItemData, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
        this.listener = listener;
    }



    @Override
    public RecyclerMakerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_maker, parent, false);
        RecyclerMakerViewHolder holder = new RecyclerMakerViewHolder(view);
        holder.setOnTradeItemClickListener(this);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerMakerViewHolder holder, int position) {
        holder.setMakerData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public void onTradeItemClick(View view, MakerData makerItemData, int position) {
        if(listener != null) {
            listener.onAdapterItemClick(view, makerItemData, position);
        }
    }
}

