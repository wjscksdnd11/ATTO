package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class DetailTradeHeaderViewHolder extends RecyclerView.ViewHolder {
	private static final int KEYWORD_COUNT = 3;
	TextView[] keywordView = new TextView[3];


	@BindView(R.id.img_realtime_photo)
	ImageView realtime_photo;

	@BindView(R.id.img_trade_profile)
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

	@BindView( R.id.text_trade_keyword_a)
	TextView trade_keyword_one;

	@BindView(R.id.text_trade_keyword_b)
	TextView trade_keyword_two;

	@BindView(R.id.text_trade_keyword_c)
	TextView trade_keyword_three;

	TradeData tradeData;



	private static final String TAG = "HeaderViewHolder";

	public DetailTradeHeaderViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		keywordView[0] = (TextView) itemView.findViewById(R.id.text_trade_keyword_a);
		keywordView[1] = (TextView) itemView.findViewById(R.id.text_trade_keyword_b);
		keywordView[2] = (TextView) itemView.findViewById(R.id.text_trade_keyword_c);
	}


	public void setTradeData(TradeData tradeData) {
		this.tradeData = tradeData;

		if (tradeData.getTrade_key_word_info() != null) {
			int[] keywordList = tradeData.getTrade_key_word_info();
			checkKeywordList(keywordList);
		}
		checkImageData(tradeData);
		trade_status.setText(tradeData.getTrade_status()+"");
		trade_title.setText(tradeData.getTrade_title());
		trade_price.setText(tradeData.getTrade_price() + "원");
		trade_dday.setText(tradeData.getTrade_dday());
		trade_nickname.setText(tradeData.getMember_info().getMember_alias());
		trade_limit_date.setText(tradeData.getTrade_dtime());

		}



		private void checkImageData (TradeData tradeData){

			if (tradeData.getTrade_product_img() != null) {

				Glide.with(itemView.getContext()).load(tradeData.getTrade_product_img()).centerCrop().into(realtime_photo);
			} else {
				realtime_photo.setImageResource(R.drawable.default_image);

			}

			if (tradeData.getMember_info().getMember_profile_img() != null) {

				Glide.with(itemView.getContext()).load(tradeData.getMember_info().getMember_profile_img()).centerCrop().into(trade_profile);

			} else {
				trade_profile.setImageResource(R.drawable.sample_profile);

			}

		}

	private void checkKeywordList(int[] keywordList) {

		if (keywordList != null) {
			for (int i = 0; i < keywordList.length; i++) {
				Log.i("realtime", "keywordList : " + keywordList[i]);
				if (keywordList[i] != 0) {
					keywordView[i].setText(keywordList[i] + "");
				} else {
					keywordView[i].setVisibility(View.GONE);

				}
			}
		} else {
			for (int i = 0; i < KEYWORD_COUNT; i++) {
				keywordView[i].setVisibility(View.GONE);
			}
		}
	}



}
