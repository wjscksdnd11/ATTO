package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.negodata.NegoData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class DetailTradeViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.img_trade_profile)
	ImageView trade_profile;

	@BindView(R.id.text_trade_nickname)
	TextView trade_nickname;

	@BindView(R.id.offer_pice)
	TextView offer_pice;

	@BindView(R.id.text_trade_dday)
	TextView trade_dday;

	@BindView(R.id.limit_date)
	TextView limit_date;

	@BindView(R.id.text_trade_remain_time)
	TextView trade_remain_time;

	@BindView(R.id.ratingbar_maker_grade)
	RatingBar ratingbar_maker_grade;

	NegoData negoData;



	public interface OnMakerImageItemClickListener {
		public void onMakerImageItemClick(View view, NegoData negoData, int position);

	}

	OnMakerImageItemClickListener listener;

	public void setOnMakerImageItemClickListener(OnMakerImageItemClickListener listener) {
		this.listener = listener;
	}

	public DetailTradeViewHolder(View itemView) {
		super(itemView);
     ButterKnife.bind(this, itemView);
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (listener == null) {
					listener.onMakerImageItemClick(view, negoData, getAdapterPosition());
				}
			}
		});
	}


	public void setNegoData(NegoData negoData) {

	}

	private void checkImageData() {
		if (negoData.getMaker_info().getMaker_profile_img() != null) {
			trade_profile.setImageResource(Integer.parseInt(negoData.getMaker_info().getMaker_profile_img()));
		}

	}
}
