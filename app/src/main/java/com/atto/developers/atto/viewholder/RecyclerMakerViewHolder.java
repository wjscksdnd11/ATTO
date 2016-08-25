package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.data.networkData.makerData.MakerData;


public class RecyclerMakerViewHolder extends RecyclerView.ViewHolder {


    ImageView maker_profile;
    RatingBar ratingbar_maker_grade;
    TextView trade_keyword, trade_nickname, text_detail_maker_intro;

    MakerData makerData;


    public interface OnTradeItemClickListener {
        public void onTradeItemClick(View view, MakerData makerData, int position);
    }



    OnTradeItemClickListener listener;
    public void setOnTradeItemClickListener(OnTradeItemClickListener listener) {
        this.listener = listener;
    }




    public RecyclerMakerViewHolder(View itemView) {
        super(itemView);
        maker_profile = (ImageView) itemView.findViewById(R.id.img_maker_profile);
        ratingbar_maker_grade = (RatingBar) itemView.findViewById(R.id.ratingbar_maker_grade);
        trade_keyword = (TextView) itemView.findViewById(R.id.text_detail_maker_keyword);
        trade_nickname = (TextView) itemView.findViewById(R.id.text_detail_maker_nickname);


    }

    public void setMakerData(MakerData makerData) {
        this.makerData = makerData;
       // maker_profile.setImageDrawable(makerdata.getMader_representation_img());
        ratingbar_maker_grade.setRating(Float.parseFloat(makerData.getMaker_score()));
        trade_keyword.setText(makerData.getMaker_key_word_lists().getKey_word_1());
        trade_nickname.setText(makerData.getMaker_id());
    }
    }

