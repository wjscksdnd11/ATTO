package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
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
public class RecyclerDetailTradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DetailTradeViewHolder.OnNegoImageItemClickListener{
    private static final String TAG = RecyclerDetailTradeAdapter.class.getSimpleName();
    private TradeData tradeData;
    private List<NegoData> mNegoDataList = new ArrayList<>();



    public void setTradeData(TradeData tradeData) {
        this.tradeData = tradeData;
    }


    public void addAll(List<NegoData> list) {
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
                return holder;
            }
            case VIEW_TYPE_GROUP: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_img_detail_trade, parent, false);
                DetailTradeViewHolder holder = new DetailTradeViewHolder(view);
                holder.setOnNegoImageItemClickListener(this);
                return holder;
            }
        }
        throw new IllegalArgumentException("invalid view type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mNegoDataList.size() > 0) {
            if (position == 0) {
                DetailTradeHeaderViewHolder hvh = (DetailTradeHeaderViewHolder) holder;
                hvh.setTradeData(tradeData);
                return;
            }
            position--;
            for (int i = 0; i < mNegoDataList.size(); i++) {
                if (position == 0) {
                    if (holder.getItemViewType() != VIEW_TYPE_GROUP) {
                        throw new IllegalArgumentException("invalid view holder");
                    }
                    DetailTradeViewHolder gvh = (DetailTradeViewHolder) holder;
                    gvh.setNegoDataList(mNegoDataList.get(i));
                    return;
                }
                position--;
            }
            throw new IllegalArgumentException("invalid position");
        }
    }

    @Override
    public void onNegoImageItemClick(View view, NegoData negoData, int position) {
        if(listener != null){
            listener.onNegoAdapterItemClick(view, negoData, position);
        }
    }

    public interface OnNegoAdapterItemClickLisnter{
        public void onNegoAdapterItemClick(View view, NegoData negoData, int position);
    }

    OnNegoAdapterItemClickLisnter listener;

    public void setOnNegoAdapterItemClickListner(OnNegoAdapterItemClickLisnter listener){
        this.listener = listener;
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

}


