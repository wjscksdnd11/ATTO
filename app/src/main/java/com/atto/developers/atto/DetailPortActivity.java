package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPortActivity extends AppCompatActivity {

    int[] images = {R.drawable.sample_rectangle_image1, R.drawable.sample_rectangle_image2, R.drawable.sample_rectangle_image3,
            R.drawable.sample_rectangle_image4, R.drawable.sample_rectangle_image5, R.drawable.sample_rectangle_image6, R.drawable.sample_rectangle_image7,
            R.drawable.sample_rectangle_image8, R.drawable.sample_rectangle_image9, R.drawable.sample_rectangle_image10
            ,R.drawable.sample_rectangle_image11, R.drawable.sample_rectangle_image12};

    @BindView(R.id.img_detail_port)
    ImageView portView;
    Random r = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_port);
        ButterKnife.bind(this);
        portView.setImageResource(images[r.nextInt(12)]);

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
