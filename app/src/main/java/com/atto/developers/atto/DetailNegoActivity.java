package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atto.developers.atto.fragment.MakerOrderDialogFragment;
import com.atto.developers.atto.fragment.ReportDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.tradedata.ListData;
import com.atto.developers.atto.request.NegoCardListRequest;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class DetailNegoActivity extends AppCompatActivity {
    private static final String TAG = DetailNegoActivity.class.getSimpleName();
    private List<NegoData> mNegoListData = new ArrayList<>();
    //private List<NegoData> mNegoData = new ArrayList<>();

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

    public void addAll(List<NegoData> list) {
        if (!mNegoListData.isEmpty()) mNegoListData.clear();
        mNegoListData.addAll(list);
    }


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nego);
        ButterKnife.bind(this);
        initToolBar();
        Intent intent = getIntent();
        int Nego_id = intent.getIntExtra("Nego_Id", -1);

        initData(Nego_id);
    }

    private void initData(int Nego_id) {
        NegoCardListRequest request = new NegoCardListRequest(this, Nego_id + "", "", "10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ListData<NegoData>>() {
            @Override
            public void onSuccess(NetworkRequest<ListData<NegoData>> request, ListData<NegoData> result) {
                NegoData[] data = result.getData();
                mNegoListData.addAll(Arrays.asList(data));
                checkNegoset(mNegoListData);
                Log.e(TAG, "Nego onSuccess 성공 : " + result);

            }

            @Override
            public void onFail(NetworkRequest<ListData<NegoData>> request, int errorCode, String errorMessage, Throwable e) {
                Log.e(TAG, "Trade onFail 실패: " + errorCode);
            }
        });
    }

    private void checkNegoset(List<NegoData> mNegoListData) {
        try {
            if (mNegoListData != null) {
                for (int i = 0; i < mNegoListData.size(); i++) {
                    checkImageData(mNegoListData.get(i));
                    checkDdayData(mNegoListData.get(i));
                    mTvNickName.setText(mNegoListData.get(i).getMaker_info().getMaker_name());
                    mTvOfferPrice.setText(String.valueOf(mNegoListData.get(i).getNegotiation_price() + "원"));
                    mTvLimitDate.setText(mNegoListData.get(i).getNegotiation_dtime());

                    String score = String.valueOf(mNegoListData.get(i).getMaker_info().getMaker_score() / 2);
                    mRbMakerGrade.setRating(mNegoListData.get(i).getMaker_info().getMaker_score() / 2);
                    mRbMakerScore.setText("(" + score + ")");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkImageData(NegoData negoData) {
        if (negoData.getMaker_info().getMaker_profile_img() != null) {
            if (!TextUtils.isEmpty(negoData.getMaker_info().getMaker_profile_img())) {
                Glide.with(getApplicationContext()).load(negoData.getMaker_info().getMaker_profile_img()).bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(mIvProfile);
            }
        }
        if (negoData.getNegotiation_product_imges_info() != null) {
            if (!TextUtils.isEmpty(negoData.getNegotiation_product_imges_info()[0])) {
                Glide.with(getApplicationContext()).load(negoData.getNegotiation_product_imges_info()[1]).centerCrop().into(mIvPortPhoto);
            } else {
                mIvPortPhoto.setImageResource(R.drawable.default_image);
            }
        }
    }

    private void checkDdayData(NegoData negoData) throws ParseException {
        Calendar toTime = Calendar.getInstance();
        long currentTiem = toTime.getTimeInMillis();
        SimpleDateFormat d = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        String negoTime = negoData.getNegotiation_dtime();
        Date trTime = d.parse(negoTime);
        long futureTime = trTime.getTime();
        long diff = futureTime - currentTiem;
        int day = (int) (diff / (1000 * 60 * 60 * 24));
        mTvDDay.setText("D" + day);
    }
}