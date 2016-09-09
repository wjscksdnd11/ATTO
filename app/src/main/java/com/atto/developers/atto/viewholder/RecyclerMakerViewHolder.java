package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class RecyclerMakerViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.img_maker_profile)
    ImageView maker_profile;

    @BindView(R.id.ratingbar_maker_grade)
    RatingBar ratingbar_maker_grade;

    @BindView(R.id.text_detail_maker_category_one)
    TextView trade_category_one;
    @BindView(R.id.text_detail_maker_category_two)
    TextView trade_category_two;

    @BindView(R.id.text_detail_maker_nickname)
    TextView trade_nickname;

    MakerData makerData;

    public interface OnMakerItemClickListener {
        public void onMakerItemClick(View view, MakerData makerData, int position);
    }

    OnMakerItemClickListener listener;

    public void setOnMakerItemClickListener(OnMakerItemClickListener listener) {
        this.listener = listener;
    }

    public RecyclerMakerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onMakerItemClick(view, makerData, getAdapterPosition());
                }
            }
        });

    }

    public void setMakerData(MakerData makerData) {

        this.makerData = makerData;

//        Glide.with(itemView.getContext()).load(makerData.getMader_representation_img()).bitmapTransform(new CropCircleTransformation(itemView.getContext()))
//                .into(maker_profile);

        if(makerData != null) {
            Glide.with(itemView.getContext()).load(makerData.getMaker_representation_img())
                    .bitmapTransform(new CropCircleTransformation(itemView.getContext())).into(maker_profile);
            Log.d("MakerFragment", "image url : " + makerData.getMaker_representation_img());
            ratingbar_maker_grade.setRating(makerData.getMaker_score());
            trade_category_one.setText(makerData.getMaker_key_word_lists() + "");
            trade_category_two.setText(makerData.getMaker_key_word_lists() + "");
            trade_nickname.setText(makerData.getMaker_name());
        }

    }
}

