package com.atto.developers.atto.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.data.NetworkData.TradeData.TradeData;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class TradeViewHolder extends RecyclerView.ViewHolder{

    TextView statusView;
    ImageView photoView;
    TextView titleView;
    TextView priceView;
    TextView limitView;
    ImageView profileView;
    TextView nickNameView;
    TextView keywordView;
    TextView ddayView;

    TradeData tradeData;

    public interface OnTradeItemClickListener {
        public void onTradeItemClick(View view, TradeData tradeData, int position);
    }

    OnTradeItemClickListener listener;
    public void setOnTradeItemClickListener(OnTradeItemClickListener listener) {
        this.listener = listener;
    }

    public TradeViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onTradeItemClick(view, tradeData, getAdapterPosition());
                }
            }
        });

        photoView = (ImageView)itemView.findViewById(R.id.img_realtime_photo);
        profileView = (ImageView)itemView.findViewById(R.id.img_trade_profile);

        statusView = (TextView)itemView.findViewById(R.id.text_trade_staus);
        titleView = (TextView)itemView.findViewById(R.id.text_trade_title);
        priceView = (TextView)itemView.findViewById(R.id.text_trade_price);
        limitView = (TextView)itemView.findViewById(R.id.text_trade_limit_datetextView);
        nickNameView = (TextView)itemView.findViewById(R.id.text_trade_nickname);
        keywordView = (TextView)itemView.findViewById(R.id.text_trade_keyword);
        ddayView = (TextView)itemView.findViewById(R.id.text_trade_dday);



    }

    public void setTradeData(TradeData tradeData) {
        this.tradeData = tradeData;
        // photoView.setImageDrawable(tradeData.getTrade_product_img());
        statusView.setText(tradeData.getTrade_status());
        titleView.setText(tradeData.getTrade_title());
        priceView.setText(tradeData.getTrade_price() + "원");
        nickNameView.setText(tradeData.getMember_info().getMember_alias());
        keywordView.setText(tradeData.getTrade_key_word_lists().getKey_word_1());
        limitView.setText(tradeData.getTrade_dtime());
        ddayView.setText(tradeData.getTrade_dtime());
    }

}
