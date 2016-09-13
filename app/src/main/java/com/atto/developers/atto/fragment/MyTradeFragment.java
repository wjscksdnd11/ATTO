package com.atto.developers.atto.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.R;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MyTradeFragment extends Fragment {
    private static final String ARG_TRADE_DATA = "tradeData";

    @BindView(R.id.my_page_text_trade_title)
    TextView titleView;

    @BindView(R.id.my_page_text_trade_keyword)
    TextView keywordOneView;

    @BindView(R.id.my_page_text_trade_keyword_b)
    TextView keywordTwoView;

    @BindView(R.id.my_page_text_trade_price)
    TextView priceView;

    @BindView(R.id.my_page_text_trade_dday)
    TextView dDayView;

    @BindView(R.id.my_page_text_trade_limit_date)
    TextView limitDateView;

    @BindView(R.id.my_page_img_trade_profile)
    ImageView profileImageView;

    @BindView(R.id.my_page_text_trade_nickname)
    TextView nickNameView;

    @BindView(R.id.my_page_text_trade_staus)
    TextView statusView;

    @BindView(R.id.btn_chat)
    ImageView chatButton;

    public MyTradeFragment() {
        // Required empty public constructor
    }

    public static MyTradeFragment newInstance(TradeData tradeData) {
        MyTradeFragment fragment = new MyTradeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRADE_DATA, tradeData);
        fragment.setArguments(args);
        return fragment;
    }

    TradeData tradeData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tradeData = (TradeData) getArguments().getSerializable(ARG_TRADE_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_trade, container, false);
        ButterKnife.bind(this, view);

        titleView.setText(tradeData.getTrade_title());
        keywordOneView.setText(tradeData.getTrade_product_category_1() + "");
        keywordTwoView.setText(tradeData.getTrade_product_category_2() + "");
        int price = tradeData.getTrade_price();
        String s_price = String.format("%,d", price);
        priceView.setText(s_price + "원");
        dDayView.setText(tradeData.getTrade_dday());
        limitDateView.setText(tradeData.getTrade_dtime());
        Glide.with(this)
                .load(tradeData.getMember_info().getMember_profile_img())
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(profileImageView);
        nickNameView.setText(tradeData.getMember_info().getMember_alias());
        statusView.setText(tradeData.getTrade_status() + "");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "id : " + tradeData.getTrade_id(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}
