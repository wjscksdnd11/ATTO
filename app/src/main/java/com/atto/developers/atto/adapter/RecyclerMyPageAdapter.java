package com.atto.developers.atto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;
import com.atto.developers.atto.data.networkdata.userdata.MyProfileData;
import com.atto.developers.atto.viewholder.MyPageViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class RecyclerMyPageAdapter extends RecyclerView.Adapter<MyPageViewHolder> implements MyPageViewHolder.OnMyPageClickListener {

    List<MyProfileData> items = new ArrayList<>();

    public void add(MyProfileData myProfileData) {
        items.add(myProfileData);
        notifyDataSetChanged();
    }


    public interface OnAdapterItemClickListener {

        public void onAdapterItemClick(View view, MyProfileData myProfileData, int position);
    }

    OnAdapterItemClickListener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_mypage, parent, false);
        MyPageViewHolder holder = new MyPageViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyPageViewHolder holder, int position) {
        holder.setMypageData(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onMyPageItemClick(View view, MyProfileData myProfileData, int position) {

        if (listener != null) {
            listener.onAdapterItemClick(view, myProfileData, position);
        }

    }
}
