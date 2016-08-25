package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;
import com.atto.developers.atto.data.MypageData;
import com.atto.developers.atto.viewholder.MypageViewHolder;

import java.util.ArrayList;
import java.util.List;


public class RecyclerMypageAdapter extends RecyclerView.Adapter<MypageViewHolder>
        implements MypageViewHolder.OnTradeItemClickListener {
    List<MypageData> items = new ArrayList<>();

    public void add(MypageData mypagedata){
        items.add(mypagedata);
        notifyDataSetChanged();
    }


    public interface OnAdapterItemClickListener {
        public void onAdapterItemClick(View view, MypageData mypagedata, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public MypageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_mypage, parent, false);
        MypageViewHolder holder = new MypageViewHolder(view);
        holder.setOnTradeItemClickListener(this);
        return holder;


    }

    @Override
    public void onBindViewHolder(MypageViewHolder holder, int position) {
        holder.setMypageData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public void onTradeItemClick(View view, MypageData mypageData, int position) {
        if(listener != null){
            listener.onAdapterItemClick(view, mypageData, position);
        }
    }

}
