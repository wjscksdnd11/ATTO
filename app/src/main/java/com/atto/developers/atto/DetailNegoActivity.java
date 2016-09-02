package com.atto.developers.atto;

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
        initToolBar();

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


        }




    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_mypage_setting);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_grey);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
