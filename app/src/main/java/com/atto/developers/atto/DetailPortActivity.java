package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.atto.developers.atto.fragment.AttoFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListItemData;
import com.atto.developers.atto.request.DetailTradeRequest;
import com.bumptech.glide.Glide;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPortActivity extends AppCompatActivity {
//
//    int[] images = {R.drawable.sample_rectangle_image1, R.drawable.sample_rectangle_image2, R.drawable.sample_rectangle_image3,
//            R.drawable.sample_rectangle_image4, R.drawable.sample_rectangle_image5, R.drawable.sample_rectangle_image6, R.drawable.sample_rectangle_image7,
//            R.drawable.sample_rectangle_image8, R.drawable.sample_rectangle_image9, R.drawable.sample_rectangle_image10
//            ,R.drawable.sample_rectangle_image11, R.drawable.sample_rectangle_image12};

    @BindView(R.id.img_detail_port)
    ImageView portView;
    Random r = new Random();

    @BindView(R.id.text_detail_port_title)
    TextView titleView;

    @BindView(R.id.text_detail_port_category)
    TextView categoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_port);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int tradeId = intent.getIntExtra(AttoFragment.TRADE_ID, 0);

        Log.d("DetailPortActivity", "tradeId : " + tradeId);
        getDataRequest(tradeId);
//        portView.setImageResource(images[r.nextInt(12)]);

    }

    public void getDataRequest(int tradeId) {
        DetailTradeRequest request = new DetailTradeRequest(this, String.valueOf(tradeId), "", "", "");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListItemData>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListItemData> request, TradeListItemData result) {

                TradeData tradeData = result.getData();
                if(tradeData != null)
                setDetailData(tradeData);
                Log.d("DetailPortActivity", "성공 : " + tradeData.getTrade_product_img());

            }
            @Override
            public void onFail(NetworkRequest<TradeListItemData> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("DetailPortActivity", "실패 : " + errorCode);

            }
        });

    }

    private void setDetailData(TradeData tradeData) {

        String[] image = tradeData.getTrade_product_imges_info();
        if(image != null) {
            Glide.with(this).load(image[0]).into(portView);
        }
        titleView.setText(tradeData.getTrade_title());
        categoryView.setText("# "+tradeData.getTrade_key_word_info()[0] + "");

    }

    @OnClick(R.id.btn_detail_port_move_trade)
    public void onMoveDetailPort() {
        Intent intent = new Intent(DetailPortActivity.this, AddTradeActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_detail_port_cancel)
    public void onCancel() {
        finish();
    }
}
