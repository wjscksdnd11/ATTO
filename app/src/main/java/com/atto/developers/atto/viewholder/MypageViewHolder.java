package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.data.MypageData;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class MypageViewHolder extends RecyclerView.ViewHolder {


    ImageView my_page_profile;
    TextView my_page_staus, my_page_title, my_page_price,
            my_page_day, my_page_limit_date, my_page_nickname;
    ImageButton btn_chat;

    MypageData mypageData;
    public interface OnTradeItemClickListener {
        public void onTradeItemClick(View view, MypageData mypageData, int position);
    }


    OnTradeItemClickListener listener;
    public void setOnTradeItemClickListener(OnTradeItemClickListener listener) {
        this.listener = listener;
    }
    public MypageViewHolder(View itemView) {
        super(itemView);
        my_page_profile =(ImageView)itemView.findViewById(R.id.my_page_img_trade_profile);
        my_page_staus=(TextView)itemView.findViewById(R.id.my_page_text_trade_staus);
        my_page_title=(TextView)itemView.findViewById(R.id.my_page_text_trade_title);
        my_page_price=(TextView)itemView.findViewById(R.id.my_page_text_trade_price);
        my_page_day=(TextView)itemView.findViewById(R.id.my_page_text_trade_dday);
        my_page_limit_date=(TextView)itemView.findViewById(R.id.my_page_text_trade_limit_date);
        my_page_nickname=(TextView)itemView.findViewById(R.id.my_page_text_trade_nickname);
        btn_chat=(ImageButton)itemView.findViewById(R.id.btn_chat);
    }

    public void setMypageData(MypageData mypageData){
        this.mypageData = mypageData;

    }


}
