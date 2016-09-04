package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.atto.developers.atto.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class DetailTradeHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_trade_staus)
    TextView statusView;
    Random r = new Random();


    private static final String TAG = "HeaderViewHolder";

    public DetailTradeHeaderViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        statusView.bringToFront();
        statusView.setText(r.nextInt(5) + "개협상중");
    }
}
