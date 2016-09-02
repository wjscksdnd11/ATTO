package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.atto.developers.atto.fragment.MakerOrderDialogFragment;
import com.atto.developers.atto.fragment.ReportDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNegoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nego);
        ButterKnife.bind(this);

    }

        @OnClick(R.id.img_btn_Report)
        public void onReport(){
            ReportDialogFragment dialogFragment = new ReportDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "report");

        }

        @OnClick(R.id.btn_check_complete)
        public void onCustomDialog() {
            MakerOrderDialogFragment dialogFragment = new MakerOrderDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "custom");
        }
        @OnClick(R.id.btn_cancel)
        public void onCancel(){
            Intent intent = new Intent(DetailNegoActivity.this, DetailTradeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }



    }
