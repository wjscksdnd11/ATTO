package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.fragment.MakerOrderDialogFragment;
import com.atto.developers.atto.fragment.ReportDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegeListItemData;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.request.DetailNegoRequest;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class DetailNegoActivity extends AppCompatActivity {
    private static final String TAG = DetailNegoActivity.class.getSimpleName();

    @BindView(R.id.img_maker_profile)
    ImageView mIvProfile;

    @BindView(R.id.img_add_port_photo)
    ImageView mIvPortPhoto;

    @BindView(R.id.text_maker_profile_nickname)
    TextView mTvNickName;

    @BindView(R.id.offer_price)
    TextView mTvOfferPrice;

    @BindView(R.id.text_trade_dday)
    TextView mTvDDay;

    @BindView(R.id.limit_date)
    TextView mTvLimitDate;

    @BindView(R.id.text_trade_remain_time)
    TextView mRemainTime;

    @BindView(R.id.ratingbar_maker_grade)
    RatingBar mRbMakerGrade;

    @BindView(R.id.ratingbar_maker_grade_text)
    TextView mRbMakerScore;

    @BindView(R.id.text_trade_maker_contents)
    TextView mTmcontents;


//    private void initToolBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
//        toolbar.setTitle(R.string.activity_mypage_setting);
//        toolbar.setTitleTextColor(Color.WHITE);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nego);
        ButterKnife.bind(this);
//        initToolBar();
        Intent intent = getIntent();
        int Nego_id = intent.getIntExtra("negotiation_id", -1);
        initData(Nego_id);
        Log.e(TAG, "DetailNego : " +Nego_id);

    }

    private void initData(int negotiationId) {
        DetailNegoRequest request = new DetailNegoRequest(this, negotiationId+"", negotiationId+"");
        Log.e(TAG, "DetailNego request : " + request.getRequest());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NegeListItemData>() {
            @Override
            public void onSuccess(NetworkRequest<NegeListItemData> request, NegeListItemData result) {
                Log.e(TAG, "DetailNego onSuccess 성공 : " + result.getData());
                NegoData data = result.getData();
                checkNego(data);
            }

            @Override
            public void onFail(NetworkRequest<NegeListItemData> request, int errorCode, String errorMessage, Throwable e) {
                Log.e(TAG, "DetailNego onFail 실패 : " + errorCode);
            }
        });
    }

    private void checkNego(NegoData data) {
        try {
            if (data != null) {
                Log.e(TAG, "DetailNego checkNego : " + data.getNegotiation_id());
                checkImageData(data);
                checkDdayData(data);
                mTmcontents.setText(data.getNegotiation_product_contents());
                mTvNickName.setText(data.getMaker_info().getMaker_name());
                mTvOfferPrice.setText(data.getNegotiation_price() + "원");
                mTvLimitDate.setText(data.getNegotiation_dtime());
                String score = String.valueOf(data.getMaker_info().getMaker_score() / 2);
                mRbMakerGrade.setRating(data.getMaker_info().getMaker_score() / 2);
                mRbMakerScore.setText("(" + score + ")");
                Glide.with(this).load(data.getNegotiation_product_imges_info()[0]).centerCrop().into(mIvPortPhoto);
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    private void checkImageData(NegoData data) {
        if (data.getMaker_info().getMaker_profile_img() != null) {
            Log.e(TAG, "checkNego Image1 : " + data.getMaker_info().getMaker_profile_img());
                Glide.with(this).load(data.getMaker_info().getMaker_profile_img()).bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(mIvProfile);
            }
//        if (data.getNegotiation_product_imges_info()[0] != null) {
//            Log.e(TAG, "checkNego Image[] : " + data.getNegotiation_product_imges_info()[0]);
//                Glide.with(this).load(data.getNegotiation_product_imges_info()[0]).centerCrop().into(mIvPortPhoto);
//            }
        }

    private void checkDdayData(NegoData data) throws ParseException {
        Calendar toTime = Calendar.getInstance();
        long currentTiem = toTime.getTimeInMillis();
        SimpleDateFormat d = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        String negoTime = data.getNegotiation_dtime();
        Date trTime = d.parse(negoTime);
        long futureTime = trTime.getTime();
        long diff = futureTime - currentTiem;
        int day = (int) (diff / (1000 * 60 * 60 * 24));
        mTvDDay.setText("D" + day);
    }
}