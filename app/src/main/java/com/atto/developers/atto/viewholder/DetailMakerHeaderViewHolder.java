package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.bumptech.glide.Glide;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goodn on 2016-08-31.
 */
public class DetailMakerHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_detail_maker_signed)
    TextView statusView;

    @BindView(R.id.img_detail_maker_profile)
    ImageView profileImageView;

    @BindView(R.id.text_detail_maker_nickname)
    TextView nickNameView;

    @BindView(R.id.ratingbar_detail_maker_grade)
    RatingBar ratingBar;

    @BindView(R.id.text_detail_maker_intro)
    TextView introView;

    @BindView(R.id.text_detail_maker_keyword_one)
    TextView categoryOneView;

    @BindView(R.id.text_detail_maker_keyword_two)
    TextView categoryTwoView;


    Random r = new Random();

    MakerData makerData;

    private static final String TAG = "HeaderViewHolder";

    public DetailMakerHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        statusView.bringToFront();
    }

    public void setHeaderData(MakerData makerData) {
        this.makerData = makerData;

        statusView.setText("성사된 거래 " + makerData.getMaker_line_tag());
        Glide.with(itemView.getContext()).load(makerData.getMader_representation_img()).into(profileImageView);
//        nickNameView.setText(makerData.getMaker_id());
//        ratingBar.setRating(Float.parseFloat(makerData.getMaker_score()));
        introView.setText(makerData.getMaker_line_tag());
        categoryOneView.setText(makerData.getMaker_product_category_1() + "");
        categoryTwoView.setText(makerData.getMaker_product_category_2() + "");

    }
}
