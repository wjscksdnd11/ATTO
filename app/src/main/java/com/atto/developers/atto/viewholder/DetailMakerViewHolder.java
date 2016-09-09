package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
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
    @BindView(R.id.img_mypage_port)
    ImageView portView;

    public interface OnMakerImageItemClickListener {
        public void onMakerImageItemClick(View view, MakerData makerData, int position);
    }

    OnMakerImageItemClickListener listener;

    public void setOnMakerImageItemClickListener(OnMakerImageItemClickListener listener) {
        this.listener = listener;
    }

    public DetailMakerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onMakerImageItemClick(view, makerData, getAdapterPosition());
                }
            }
        });
    }
    public void setImageData(PortfolioData portfolioData) {

        if (portfolioData != null) {
            portfolioData.getPortfolio_img_info();
            String image = portfolioData.getPortfolio_img_info();
                Glide.with(itemView.getContext()).load(image).into(portView);
            }
        }

}
