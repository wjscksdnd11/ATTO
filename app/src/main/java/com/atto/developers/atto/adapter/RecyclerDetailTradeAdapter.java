package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.tradedata.TradeListItemData;
import com.atto.developers.atto.viewholder.DetailTradeHeaderViewHolder;
import com.atto.developers.atto.viewholder.DetailTradeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class RecyclerDetailTradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private TradeListItemData mTradeData;
	private List<NegoData> mNegoDataList;

	public void setTradeData(TradeListItemData tradeListItemData ) {
		this.mTradeData = tradeListItemData ;
	}

	public void addNego(List<NegoData> list) {
		if (mNegoDataList == null) mNegoDataList = new ArrayList<>();
		else if (!mNegoDataList.isEmpty()) mNegoDataList.clear();
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
				return new DetailTradeHeaderViewHolder(view);
			}
			case VIEW_TYPE_GROUP: {
				View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_img_detail_trade, parent, false);
				return new DetailTradeViewHolder(view);
			}
		}
		throw new IllegalArgumentException("invalid view type");
	}


	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		// HEADER
		if (holder instanceof DetailTradeHeaderViewHolder) {
			DetailTradeHeaderViewHolder hvh = (DetailTradeHeaderViewHolder) holder;
			hvh.setTradeData(mTradeData);
		}
		// GROUP
		else {
			DetailTradeViewHolder gvh = (DetailTradeViewHolder) holder;
			gvh.setNegoDataList(mNegoDataList);
		}
	}


	@Override
	public int getItemCount() {
		return mTradeData == null ?
				0 : (mNegoDataList == null ? 0 : mNegoDataList.size() + 1);
	}
}


