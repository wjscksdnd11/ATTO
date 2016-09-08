package com.atto.developers.atto.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
	private static final String TAG = DetailTradeHeaderViewHolder.class.getSimpleName();

	@BindViews({
			R.id.text_trade_keyword_a,
			R.id.text_trade_keyword_b,
			R.id.text_trade_keyword_c
	})
	List<TextView> mTvKeywordList;

	@BindView(R.id.img_realtime_photo)
	ImageView mIvPhoto;

	@BindView(R.id.img_trade_profile)
	ImageView mIvProfile;

	@BindView(R.id.text_trade_status)
	TextView mTvStatus;

	@BindView(R.id.text_trade_title)
	TextView mTvTitle;

	@BindView(R.id.text_trade_price)
	TextView mTvPrice;

	@BindView(R.id.text_trade_dday)
	TextView mTvDDay;

	@BindView(R.id.text_trade_nickname)
	TextView mTvNickName;

	@BindView(R.id.text_trade_limit_date)
	TextView mTvLimitDate;

	private Context mContext;

	public DetailTradeHeaderViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		this.mContext = itemView.getContext();
	}


	public void setTradeData(TradeListItemData tradeData) {
		try {
			if (tradeData != null) {
				if (tradeData.getData().getTrade_key_word_info() != null) {
					int[] keywordList = tradeData.getData().getTrade_key_word_info();
					checkKeywordList(keywordList);
				}
				//checkImageData(tradeData.getData().g);
				mTvStatus.setText(String.valueOf(tradeData.getData().getTrade_status()));
				mTvTitle.setText(tradeData.getData().getTrade_title());
				mTvPrice.setText(tradeData.getData().getTrade_price().concat("원"));
				mTvDDay.setText(tradeData.getData().getTrade_dday());
				mTvNickName.setText(tradeData.getData().getMember_info().getMember_alias());
				mTvLimitDate.setText(tradeData.getData().getTrade_dtime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//private void checkImageData(TradeData tradeData) {
//		if (!TextUtils.isEmpty(tradeData.getTrade_product_img()))
//			Glide.with(mContext).load(tradeData.getTrade_product_img()).centerCrop().into(mIvPhoto);
//		else
//			Glide.with(mContext).load(R.drawable.default_image).centerCrop().into(mIvPhoto);
//	}

	private void checkKeywordList(int[] keywordList) {
		for (TextView keywordView : mTvKeywordList)
			keywordView.setVisibility(View.GONE);

		for (int i = 0; i < keywordList.length; i++)
			mTvKeywordList.get(i).setText(String.valueOf(keywordList[i]));
	}
}
