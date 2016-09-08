package com.atto.developers.atto.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.DetailNegoActivity;
import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.negodata.NegoDataMaker_info;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class DetailTradeViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.img_trade_profile)
	ImageView mIvProfile;

	@BindView(R.id.text_trade_profile_nickname)
	TextView mTvNickName;

	@BindView(R.id.offer_price)
	TextView mTvOfferPrice;

	@BindView(R.id.text_trade_dday)
	TextView mTvDDay;

	@BindView(R.id.limit_date)
	TextView mTvLimitDate;

	@BindView(R.id.text_trade_remain_time)
	TextView mRemainTime;

	@BindView(R.id.ratingbar_maker_grade)
	RatingBar mRbMakerGrade;

	private Context mContext;

	public DetailTradeViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		this.mContext = itemView.getContext();
	}

	public void setNegoDataList(List<NegoData> negoDataList) {
		try {
			NegoData negoData = negoDataList.get(getAdapterPosition());
			if (negoData != null) {
				checkImageData(negoData);
				mTvNickName.setText(negoData.getMaker_info().getMaker_name());
				mTvOfferPrice.setText(String.valueOf(negoData.getNegotiation_price() + "원"));
				mTvLimitDate.setText(negoData.getNegotiation_dtime()); //yyyy-mm-dd까지
//				checkDdaytest(negoData);

				//  mTvDDay.setText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void checkImageData(NegoData negoData) {
		NegoDataMaker_info negoDataMakerInfo = negoData.getMaker_info();
		if (negoDataMakerInfo != null && !TextUtils.isEmpty(negoDataMakerInfo.getMaker_profile_img()))
			Glide.with(itemView.getContext()).load(negoDataMakerInfo.getMaker_profile_img()).into(mIvProfile);
	}

	@OnClick(R.id.rl_img_detail_trade)
	void onContainerClicked() {
		int position = getAdapterPosition();
		Toast.makeText(mContext, "position : " + position, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(mContext, DetailNegoActivity.class);
		mContext.startActivity(intent);
	}
}
