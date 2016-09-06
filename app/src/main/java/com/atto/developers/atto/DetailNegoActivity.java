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
import com.atto.developers.atto.networkdata.tradedata.TradeListData;
import com.atto.developers.atto.request.NegoCardListRequest;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNegoActivity extends AppCompatActivity {
    @BindView(R.id.img_trade_profile)
    ImageView trade_profile;
    @BindView(R.id.text_trade_profile_nickname)
    TextView trade_nickname;
    @BindView(R.id.offer_pice)
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
    //int tYear, tMonth, tDay;           //오늘 연월일 변수

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nego);
        ButterKnife.bind(this);
        initToolBar();
        initData();
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

    private void initData() {

        NegoCardListRequest request = new NegoCardListRequest(this, "10", "10", "10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<NegoData>>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListData<NegoData>> request, TradeListData<NegoData> result) {
                NegoData[] data = result.getData();
                setNegoData(data);
                Log.d(this.toString(), "협상카드상세성공 : " + result.getMessage());
            }

            @Override
            public void onFail(NetworkRequest<TradeListData<NegoData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getApplicationContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setNegoData(NegoData[] data) {
        checkImageData(data[0]);
        trade_nickname.setText(data[0].getMaker_info().getMaker_name());
        //ratingbar_maker_grade.setRating(negoData.getMaker_info().getMaker_score());
        offer_pice.setText(data[0].getNegotiation_price() + "");
       // calenderDday(data[0]);
        limit_date.setText(data[0].getNegotiation_dtime()); //yyyy-mm-dd까지
        //trade_remain_time; //24시간 알림
        //trade_maker_contents.setText(negoData.);

    }


    private void checkImageData(NegoData negoData) {
        if (negoData.getMaker_info().getMaker_profile_img() != null) {
            Glide.with(this).load(negoData.getMaker_info().getMaker_profile_img()).into(trade_profile);

        } else {
            img_add_port_photo.setImageResource(R.drawable.default_image);
        }
//        if (negoData. != = null) {
//            Glide.with(this).load(negoData.).into(img_add_port_photo);
//        } else {
//            trade_profile.setImageResource(R.drawable.sample_profile);
//        }
    }

//    @TargetApi(Build.VERSION_CODES.N)
//    private int calenderDday(NegoData negoData) {
//        try {
//            //TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
//            Calendar Day = Calendar.getInstance();
//            Calendar TradeDay = Calendar.getInstance(TimeZone.getTimeZone(negoData.getNegotiation_dtime()));
//            //int 값
//            tYear = Day.get(Calendar.YEAR);
//            tMonth = Day.get(Calendar.MONTH);
//            tDay = Day.get(Calendar.DAY_OF_MONTH);
//
//            tYear = TradeDay.get
//
//
//            Day.set(tYear, tMonth - 1, tDay);
//            long Trade_Day = Day.getTimeInMillis() / 86400000;
//            long Trade_dDay = TradeDay.getTimeInMillis() / 86400000;
//            long sub = Trade_Day - Trade_dDay;
//
//            return(int)  sub + 1;
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        return 0;
    }

//        String dTradeday = negoData.getNegotiation_dtime();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
//        Date currentTime = new Date();
//        String dToday = formatter.format(currentTime);
        //trade_dday.setText(negoData.getNegotiation_dtime()); // D-