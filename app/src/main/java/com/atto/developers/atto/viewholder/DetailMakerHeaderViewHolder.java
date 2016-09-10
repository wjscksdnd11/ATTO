package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.bumptech.glide.Glide;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

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
        Toast.makeText(itemView.getContext(), makerData.getMaker_representation_img(), Toast.LENGTH_LONG).show();

        statusView.setText(makerData.getMaker_line_tag());
        Glide.with(itemView.getContext()).load(makerData.getMaker_representation_img())
                .bitmapTransform(new CropCircleTransformation(itemView.getContext())).into(profileImageView);
        nickNameView.setText(makerData.getMaker_name());
        ratingBar.setRating(makerData.getMaker_score());
        introView.setText(makerData.getMaker_line_tag());

        categoryOneView.setText(makerData.getMaker_product_category_info()[0] + "");
        categoryTwoView.setText(makerData.getMaker_product_category_info()[1] + "");

    }
}
