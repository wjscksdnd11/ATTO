package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.networkdata.negodata.NegoListData;
import com.atto.developers.atto.viewholder.DetailTradeHeaderViewHolder;
import com.atto.developers.atto.viewholder.DetailTradeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class RecyclerDetailTradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DetailTradeViewHolder.OnMakerImageItemClickListener {

    List<NegoListData> items = new ArrayList<>();


    public boolean isHeader(int position) {
        return position == 0;
    }

    public void add(NegoListData negoListData) {
        items.add(negoListData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_TYPE_HEADER;
        position--;
        for (int i = 0; i < items.size(); i++) {
            if (position == 0) return VIEW_TYPE_GROUP;
            position--;
        }
        throw new IllegalArgumentException("invalid position");
    }

    private static final int VIEW_TYPE_HEADER = 100;
    private static final int VIEW_TYPE_GROUP = 200;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header_detail, parent, false);
                DetailTradeHeaderViewHolder holder = new DetailTradeHeaderViewHolder(view);
                return holder;
            }
            case VIEW_TYPE_GROUP: {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_img_detail_trade, parent, false);
                DetailTradeViewHolder holder = new DetailTradeViewHolder(view);
                return holder;
            }

        }
        throw new IllegalArgumentException("invalid viewtype");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            DetailTradeHeaderViewHolder hvh = (DetailTradeHeaderViewHolder)holder;
            return;
        }
        position--;
        for (int i = 0; i < items.size(); i++) {
            if (holder.getItemViewType() != VIEW_TYPE_GROUP) {
                throw new IllegalArgumentException("invalid view holder");
            }
            DetailTradeViewHolder gvh = (DetailTradeViewHolder) holder;
            gvh.setNegoListData(items.get(i));
            return;
        }
        position--;
    }


    public interface OnAdapterItemClickListener {
        public void onAdapterItemClick(View view, NegoListData negoListData, int position );
    }

    OnAdapterItemClickListener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        count++;
        for (int i = 0; i < items.size(); i++) {
            count++;
        }
        return count;
    }

        @Override
        public void onMakerImageItemClick(View view, NegoListData negoListData, int position) {
            if (listener != null) {
                listener.onAdapterItemClick(view, negoListData, position);
            }
        }
}


