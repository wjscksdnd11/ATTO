package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.viewholder.DetailMakerHeaderViewHolder;
import com.atto.developers.atto.viewholder.DetailMakerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goodn on 2016-08-31.
 */
public class RecyclerDetailMakerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DetailMakerViewHolder.OnMakerImageItemClickListener {

    List<MakerData> items = new ArrayList<>();

    public boolean isHeader(int position) {
        return position == 0;
    }

    public void add(MakerData makerData) {
        items.add(makerData);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public static final int VIEW_TYPE_HEADER = 100;
    public static final int VIEW_TYPE_GROUP = 200;

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


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_detail_maker, parent, false);
                DetailMakerHeaderViewHolder holder = new DetailMakerHeaderViewHolder(view);
                return holder;
            }
            case VIEW_TYPE_GROUP: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_detail_maker_image, parent, false);
                DetailMakerViewHolder holder = new DetailMakerViewHolder(view);
                holder.setOnMakerImageItemClickListener(this);
                return holder;
            }
        }
        throw new IllegalArgumentException("invalid viewtype");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (items.size() > 0) {
            if (position == 0) {
                DetailMakerHeaderViewHolder hvh = (DetailMakerHeaderViewHolder) holder;
                hvh.setHeaderData(items.get(0));
                return;
            }
            position--;
            for (int i = 0; i < items.size(); i++) {
                if (position == 0) {
                    if (holder.getItemViewType() != VIEW_TYPE_GROUP) {
                        throw new IllegalArgumentException("invalid view holder");
                    }
                    DetailMakerViewHolder gvh = (DetailMakerViewHolder) holder;
                    gvh.setImageData(items.get(i));
                    return;
                }
                position--;
            }
            throw new IllegalArgumentException("invalid position");
        }


    }


    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, MakerData makerData, int position);
    }

    OnAdapterItemClickLIstener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
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
    public void onMakerImageItemClick(View view, MakerData makerData, int position) {
        if (listener != null) {
            listener.onAdapterItemClick(view, makerData, position);
        }
    }
}
