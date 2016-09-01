package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.listdata.KeywordList;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class RealTimeTradeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_realtime_photo)
    ImageView realtime_photo;
    @BindView(R.id.img_maker_profile)
    ImageView trade_profile;

    @BindView(R.id.text_trade_status)
    TextView trade_status;

    @BindView(R.id.text_trade_title)
    TextView trade_title;

    @BindView(R.id.text_trade_price)
    TextView trade_price;

    @BindView(R.id.text_trade_dday)
    TextView trade_dday;

    @BindView(R.id.text_trade_nickname)
    TextView trade_nickname;

    @BindView(R.id.text_trade_limit_date)
    TextView trade_limit_date;

    @BindView(R.id.text_trade_keyword_a)
    TextView trade_keyword_one;

    @BindView(R.id.text_trade_keyword_b)
    TextView trade_keyword_two;

    @BindView(R.id.text_trade_keyword_c)
    TextView trade_keyword_three;


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
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onTradeItemClick(view, tradeData, getAdapterPosition());
                }
            }
        });

    }

    public void setTradeData(TradeData tradeData) {
        this.tradeData = tradeData;
        KeywordList keywordList = tradeData.getTrade_key_word_lists();
        //realtime_photo.setImageDrawable(tradeData.getTrad_ );
        //trade_profile.setImageDrawable(tradeData.);

        checkImageData();

        trade_status.setText(tradeData.getTrade_status());
        trade_title.setText(tradeData.getTrade_title());
        trade_price.setText(tradeData.getTrade_price() + "원");
        trade_dday.setText(tradeData.getTrade_dday());
        trade_nickname.setText(tradeData.getMember_info().getMember_alias());
        trade_limit_date.setText(tradeData.getTrade_dtime());
        checkKeywordList(keywordList);


    }

    private void checkImageData() {

        if (tradeData.getTrade_product_img() == null) {
            realtime_photo.setImageResource(R.drawable.trade_sample01);
        } else {
            Glide.with(itemView.getContext()).load(tradeData.getTrade_product_img()).into(realtime_photo);
        }

        if (tradeData.getMember_info().getMember_profile_img() == null) {
            trade_profile.setImageResource(R.drawable.sample_profile);
        } else {
            Glide.with(itemView.getContext()).load(tradeData.getMember_info().getMember_profile_img()).into(trade_profile);
        }

    }

    private void checkKeywordList(KeywordList keywordList) {

        if (keywordList.getKey_word_1() != null) {
            trade_keyword_one.setText(keywordList.getKey_word_1());
        } else {
            trade_keyword_one.setVisibility(View.GONE);
        }
        if (keywordList.getKey_word_2() != null) {
            trade_keyword_two.setText(keywordList.getKey_word_2());
        } else {
            trade_keyword_two.setVisibility(View.GONE);
        }
        if (keywordList.getKey_word_3() != null) {
            trade_keyword_three.setText(keywordList.getKey_word_3());
        } else {
            trade_keyword_three.setVisibility(View.GONE);
        }
    }

}
