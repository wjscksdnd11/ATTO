package com.atto.developers.atto.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.R;

import com.atto.developers.atto.networkdata.negodata.NegoListData;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class DetailTradeViewHolder extends RecyclerView.ViewHolder {
    NegoListData negoListData;
    ImageView img_maker_profile;
    TextView text_trade_profile_nickname, offer_pice, text_trade_dday, limit_date, text_trade_remain_time;
    RatingBar ratingbar_maker_grade;



    public interface OnMakerImageItemClickListener{
        public void onMakerImageItemClick(View view, NegoListData negoListData, int position);

    }
    OnMakerImageItemClickListener listener;

    public void setOnMakerImageItemClickListener(OnMakerImageItemClickListener listener){
        this.listener = listener;
    }

    public DetailTradeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener == null) {
                    listener.onMakerImageItemClick(view, negoListData, getAdapterPosition());
                }
            }
        });

        img_maker_profile = (ImageView) itemView.findViewById(R.id.img_maker_profile);
        text_trade_profile_nickname = (TextView) itemView.findViewById(R.id.text_trade_profile_nickname);
        offer_pice = (TextView) itemView.findViewById(R.id.offer_pice);
        text_trade_dday = (TextView) itemView.findViewById(R.id.text_trade_dday);
        limit_date = (TextView) itemView.findViewById(R.id.limit_date);
        text_trade_remain_time = (TextView) itemView.findViewById(R.id.text_trade_remain_time);
        ratingbar_maker_grade = (RatingBar)itemView.findViewById(R.id.ratingbar_maker_grade);
    }

    public void setNegoListData(NegoListData negoListData){
        this.negoListData = negoListData;
        for(int i = 0; i<3; i++) {
            //img_maker_profile.setImageDrawable(Drawable.createFromPath(negoListData.getData()[0].getMaker_info().getMaker_profile_img()));
            text_trade_profile_nickname.setText(negoListData.getData()[i].getMaker_info().getMaker_name());
            offer_pice.setText(negoListData.getData()[i].getNegotiation_price() + "원");
            text_trade_dday.setText("D-" + negoListData.getData()[i].getNegotiation_dtime());
            limit_date.setText(negoListData.getData()[i].getNegotiation_dtime());
            text_trade_remain_time.setText(negoListData.getData()[i].getNegotiation_dtime());
            ratingbar_maker_grade.setRating(Float.parseFloat(negoListData.getData()[i].getMaker_info().getMaker_score()));
        }

    }
}
