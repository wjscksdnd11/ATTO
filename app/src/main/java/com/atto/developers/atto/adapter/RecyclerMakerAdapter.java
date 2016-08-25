package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;
import com.atto.developers.atto.data.MakerItemData;
import com.atto.developers.atto.viewholder.RecyclerMakerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class RecyclerMakerAdapter extends RecyclerView.Adapter<RecyclerMakerViewHolder> {

    List<MakerItemData> items = new ArrayList<>();

    public void add(MakerItemData d) {
        items.add(d);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerMakerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_maker, parent, false);
        RecyclerMakerViewHolder holder = new RecyclerMakerViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerMakerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

