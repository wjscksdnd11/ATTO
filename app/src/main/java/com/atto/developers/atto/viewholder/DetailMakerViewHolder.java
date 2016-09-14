package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.networkdata.portfoliodata.PortfolioData;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class DetailMakerViewHolder extends RecyclerView.ViewHolder {

    MakerData makerData;
    PortfolioData portfolioData;

    @BindView(R.id.img_mypage_port)
    ImageView portView;

    public interface OnMakerImageItemClickListener {
        public void onMakerImageItemClick(View view, PortfolioData portfolioData, int position);
    }

    public interface OnPortImageItemClickListener {
        public void onPortImageItemClick(View view, PortfolioData portfolioData, int position);
    }

    OnPortImageItemClickListener listener;

    public void setOnPortImageItemClickListener(OnPortImageItemClickListener listener) {
        this.listener = listener;
    }

    public DetailMakerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onPortImageItemClick(view, portfolioData, getAdapterPosition());
                }
            }
        });
    }

    public void setImageData(PortfolioData portfolioData) {

        this.portfolioData = portfolioData;

        if (portfolioData != null) {
            String image = portfolioData.getPortfolio_img();
            Log.d("DetailMakerActivity", "사진 url : " + image);
            Glide.with(itemView.getContext()).load(image).centerCrop().into(portView);
        }
    }

}
