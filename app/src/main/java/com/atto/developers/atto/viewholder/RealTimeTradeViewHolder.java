package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.data.NetworkData.TradeData.TradeData;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class RealTimeTradeViewHolder extends RecyclerView.ViewHolder {
    ImageView realtime_photo, trade_profile;
    TextView trade_staus,trade_title,trade_price,
           trade_dday,trade_nickname,trade_limit_date,trade_keyword;

    TradeData tradeData;

    public interface OnTradeItemClickListener {
        public void onTradeItemClick(View view, TradeData tradeData, int position);
    }



    OnTradeItemClickListener listener;
    public void setOnTradeItemClickListener(OnTradeItemClickListener listener) {
        this.listener = listener;
    }


    public RealTimeTradeViewHolder(View itemView) {
        super(itemView);

       itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(listener != null) {
                   listener.onTradeItemClick(view, tradeData, getAdapterPosition());
               }
           }
       });

        realtime_photo = (ImageView)itemView.findViewById(R.id.img_realtime_photo);
        trade_profile = (ImageView)itemView.findViewById(R.id.img_trade_profile);
        trade_staus = (TextView)itemView.findViewById(R.id.text_trade_staus);
        trade_title = (TextView)itemView.findViewById(R.id.text_trade_title);
        trade_price = (TextView)itemView.findViewById(R.id.text_trade_price);
        trade_dday = (TextView)itemView.findViewById(R.id.text_trade_dday);
        trade_nickname = (TextView)itemView.findViewById(R.id.text_trade_nickname);
        trade_limit_date = (TextView)itemView.findViewById(R.id.text_trade_limit_date);
        trade_keyword = (TextView)itemView.findViewById(R.id.text_trade_keyword);

    }


    public void setTradeData(TradeData tradeData) {
        this.tradeData = tradeData;
        //realtime_photo.setImageDrawable(tradeData.getTrad_ );
        //trade_profile.setImageDrawable(tradeData.);
        trade_staus.setText(tradeData.getTrade_status());
        trade_title.setText(tradeData.getTrade_title());
        trade_price.setText(tradeData.getTrade_price() + "원");
        trade_dday.setText(tradeData.getTrade_dtime());
        trade_nickname.setText(tradeData.getMember_info().getMember_alias());
        trade_limit_date.setText(tradeData.getTrade_dtime());
        trade_keyword.setText(tradeData.getTrade_key_word_lists().getKey_word_1());
    }

}
