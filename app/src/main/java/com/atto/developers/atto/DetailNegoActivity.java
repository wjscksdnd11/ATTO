package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.fragment.MakerOrderDialogFragment;
import com.atto.developers.atto.fragment.ReportDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.tradedata.ListData;
import com.atto.developers.atto.request.NegoCardListRequest;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNegoActivity extends AppCompatActivity {
    @BindView(R.id.img_maker_profile)
    ImageView trade_profile;

    /*@BindView(R.id.text_trade_profile_nickname)
    TextView trade_nickname;
    */
    @BindView(R.id.offer_price)
    TextView offer_pice;
    @BindView(R.id.text_trade_dday)
    TextView trade_dday;
    @BindView(R.id.limit_date)
    TextView limit_date;
    @BindView(R.id.text_trade_remain_time)
    TextView trade_remain_time;
    @BindView(R.id.ratingbar_maker_grade)
    RatingBar maker_grade;
    @BindView(R.id.img_add_port_photo)
    ImageView img_add_port_photo;
    @BindView(R.id.text_trade_maker_contents)
    TextView trade_maker_contents;

    NegoData negoData;

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_mypage_setting);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.img_btn_Report)
    public void onReport() {
        ReportDialogFragment dialogFragment = new ReportDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "report");
    }

    @OnClick(R.id.btn_check_complete)
    public void onCustomDialog() {
        MakerOrderDialogFragment dialogFragment = new MakerOrderDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "custom");
    }

    @OnClick(R.id.btn_cancel)
    public void onCancel() {
        Intent intent = new Intent(DetailNegoActivity.this, DetailTradeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    int negoId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nego);
        ButterKnife.bind(this);
        initToolBar();
        Intent intent = getIntent();
        negoId = intent.getIntExtra("Nego_Id", -1);
        initData(negoId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(negoId);
    }

    private void initData(final int negoId) {
        NegoCardListRequest request = new NegoCardListRequest(this, negoId + "", "10", "10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ListData<NegoData>>() {
            @Override
            public void onSuccess(NetworkRequest<ListData<NegoData>> request, ListData<NegoData> result) {
                NegoData[] data = result.getData();
                if (data.length > 0) {
                    for (int i = 0; i < data.length; i++) {
                        if (data[i].getNegotiation_id() == negoId) {
                            setNegoData(data[0]);

                        }
                    }
                }

                Log.d(this.toString(), "협상카드상세성공 : " + result.getMessage());
            }

            @Override
            public void onFail(NetworkRequest<ListData<NegoData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getApplicationContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setNegoData(NegoData negoData) {
        checkImageData(negoData);
//        trade_nickname.setText(negoData.getMaker_info().getMaker_name());
        //ratingbar_maker_grade.setRating(negoData.getMaker_info().getMaker_score());
        offer_pice.setText(negoData.getNegotiation_price() + "원");
        // calenderDday(data[0]);
        limit_date.setText(negoData.getNegotiation_dtime()); //yyyy-mm-dd까지
        //trade_remain_time; //24시간 알림
        //trade_maker_contents.setText(negoData.);
    }

    private void checkImageData(NegoData data) {
        if (data.getMaker_info().getMaker_profile_img() != null) {
            Glide.with(this).load(data.getMaker_info().getMaker_profile_img()).into(trade_profile);

        } else {
            img_add_port_photo.setImageResource(R.drawable.default_image);
        }
        if (data.getNegotiation_product_imges_info() != null) {
            Glide.with(this).load(data.getNegotiation_product_imges_info()).into(img_add_port_photo);
        } else {
            trade_profile.setImageResource(R.drawable.default_image);
        }
    }


//    private void calenderDday(NegoData negoData) {
//        String[] data = negoData
//
//        Calendar a = Calendar.getInstance();
//        long currentTiem = a.getTimeInMillis();
//        a.set(Calendar.YEAR, Calendar.MONTH-1, Calendar.DAY_OF_MONTH);
//
//        Calendar b = Calendar.getInstance(TimeZone.getTimeZone(negoData.getNegotiation_dtime()));
//        long futureTime = b.getTimeInMillis();
//        b.set();
//        long diff = futureTime - currentTiem;
//        int day = (int) (diff / (1000 * 60 * 60 * 24));
//       \
//    }


}