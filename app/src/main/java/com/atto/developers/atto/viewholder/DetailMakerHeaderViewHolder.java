package com.atto.developers.atto.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.atto.developers.atto.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goodn on 2016-08-31.
 */
public class DetailMakerHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_detail_maker_signed)
    TextView statusView;
    Random r = new Random();

    private static final String TAG = "HeaderViewHolder";

    public DetailMakerHeaderViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        statusView.bringToFront();
        statusView.setText("성사된 거래 " + r.nextInt(5));

    }
}
