package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    NegoData negoData;

  public interface OnNegoImageItemClickListener {
      public void onNegoImageItemClick(View view, NegoData negoData, int position);
  }
    OnNegoImageItemClickListener listener;

    public void setOnNegoImageItemClickListener(OnNegoImageItemClickListener listener){
        this.listener = listener;
    }



    public DetailTradeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onNegoImageItemClick(view, negoData, getAdapterPosition());
                }
            }
        });
    }

    public void setNegoDataList(NegoData negoData) {
        try {
        if (negoData != null) {
            this.negoData = negoData;
            mTvNickName.setText(negoData.getMaker_info().getMaker_name());
            mTvOfferPrice.setText(String.valueOf(negoData.getNegotiation_price() + "원"));
            mTvLimitDate.setText(negoData.getNegotiation_dtime());
            String score = String.valueOf(negoData.getMaker_info().getMaker_score() / 2);
            mRbMakerGrade.setRating(negoData.getMaker_info().getMaker_score() / 2);
            mRbMakerScore.setText("(" + score + ")");
            checkDdayData(negoData);
            checkImageData(negoData);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkDdayData(NegoData negoData) throws ParseException  {
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

    private void checkImageData(NegoData negoData) {
        if (negoData.getMaker_info().getMaker_profile_img() != null) {
            Glide.with(itemView.getContext()).load(negoData.getMaker_info().getMaker_profile_img()).bitmapTransform(new CropCircleTransformation(itemView.getContext())).into(mIvProfile);
        }
    }
}
