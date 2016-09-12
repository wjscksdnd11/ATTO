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
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class DetailTradeViewHolder extends RecyclerView.ViewHolder {
	private static final String TAG = DetailTradeViewHolder.class.getSimpleName();
	@BindView(R.id.img_maker_profile)
	ImageView mIvProfile;

	@BindView(R.id.text_maker_profile_nickname)
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

	@BindView(R.id.ratingbar_maker_grade_text)
	TextView mRbMakerScore;

	private Context mContext;

	public DetailTradeViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		this.mContext = itemView.getContext();
	}

	public void setNegoDataList(List<NegoData> negoDataList) {
		try {
			if (negoDataList != null) {
				for (int i = 0; i < negoDataList.size(); i++) {
					checkImageData(negoDataList.get(i));
					checkDdayData(negoDataList.get(i));
					mTvNickName.setText(negoDataList.get(i).getMaker_info().getMaker_name());
					mTvOfferPrice.setText(String.valueOf(negoDataList.get(i).getNegotiation_price() + "원"));
					mTvLimitDate.setText(negoDataList.get(i).getNegotiation_dtime());
					String score = String.valueOf(negoDataList.get(i).getMaker_info().getMaker_score() / 2);
					mRbMakerGrade.setRating(negoDataList.get(i).getMaker_info().getMaker_score() / 2);
					mRbMakerScore.setText("(" + score + ")");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkImageData(NegoData negoData) {
		if (negoData.getMaker_info().getMaker_profile_img() != null) {
			if (!TextUtils.isEmpty(negoData.getMaker_info().getMaker_profile_img())) {
				Glide.with(mContext).load(negoData.getMaker_info().getMaker_profile_img()).bitmapTransform(new CropCircleTransformation(mContext)).into(mIvProfile);
			}
		}
	}

	private void checkDdayData(NegoData negoData) throws ParseException {
		Calendar toTime = Calendar.getInstance();
		long currentTiem = toTime.getTimeInMillis();
		SimpleDateFormat d = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
		String negoTime = negoData.getNegotiation_dtime();
		Date trTime = d.parse(negoTime);
		long futureTime = trTime.getTime();
		long diff = futureTime - currentTiem;
		int day = (int) (diff / (1000 * 60 * 60 * 24));
		mTvDDay.setText("D" + day);
	}

	@OnClick(R.id.rl_img_detail_nego)
	void onContainerClicked() {
		int position = getAdapterPosition();
		Toast.makeText(mContext, "position : " + position, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(mContext, DetailNegoActivity.class);
		intent.putExtra("Nego_Id", position);
		mContext.startActivity(intent);
	}


}
