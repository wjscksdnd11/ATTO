package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.adapter.RecyclerDetailTradeAdapter;
import com.atto.developers.atto.networkdata.negodata.NegoData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class DetailTradeViewHolder extends RecyclerView.ViewHolder {
	private static final String TAG = DetailTradeViewHolder.class.getSimpleName();
	@BindView(R.id.img_maker_profile)
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

	//private Context mContext;
	NegoData negoData;




	public interface OnNegoImageItemClickListener {
		public void onNegoImageItemClick(View view, NegoData negoData, int position);
	}

	OnNegoImageItemClickListener listener;

	public void setOnNegoImageItemClickListener(RecyclerDetailTradeAdapter listener) {
		this.listener = listener;
	}



	public DetailTradeViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (listener != null) {
					listener.onNegoImageItemClick(view, negoData, getAdapterPosition());
				}
			}
		});
	}

	public void setNegoDataList(NegoData negoData) {
		Log.e(TAG, "Nego onSuccess 성공 : " + negoData);
		if (negoData != null) {
			mTvNickName.setText(negoData.getMaker_info().getMaker_name());
			mTvOfferPrice.setText(String.valueOf(negoData.getNegotiation_price() + "원"));
			mTvLimitDate.setText(negoData.getNegotiation_dtime()); //yyyy-mm-dd까지
//          checkDdaytest(negoData);
//           checkImageData(negoData.getMaker_info().getMaker_profile_img());

		}
	}



//    private void checkImageData(String maker_profile_img) {
//        if (!TextUtils.isEmpty(negoData.getMaker_info().getMaker_profile_img()))
//            Glide.with(itemView.getContext()).load(negoData.getMaker_info().getMaker_profile_img()).into(mIvProfile);
//    }
}