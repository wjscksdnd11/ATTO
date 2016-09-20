package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeOrderActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    @BindView(R.id.text_set_address)
    TextView addressView;
    @BindView(R.id.text_set_postcode)
    TextView postCodeView;
    @BindView(R.id.img_maker_profile)
    ImageView makeMakerProfile;
    @BindView(R.id.ratingbar_maker_grade)
    TextView makeMakerGrade;
    @BindView(R.id.ratingbar_maker_grade_text)
    TextView makeMakerGradeText;
    @BindView(R.id.text_detail_maker_nickname)
    TextView makeMakerName;
    //아직 덜추가함



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        ButterKnife.bind(this);
        //initData();
    }

//    private void initData() {
//        String name = makeMakerName.getText().toString();
//
//        DetailMakerRequest request = new DetailMakerRequest(this, );
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<MakerListItemData>() {
//            @Override
//            public void onSuccess(NetworkRequest<MakerListItemData> request, MakerListItemData result) {
//
//            }
//
//            @Override
//            public void onFail(NetworkRequest<MakerListItemData> request, int errorCode, String errorMessage, Throwable e) {
//
//            }
//        });
//    }

    @OnClick(R.id.btn_update_order)
    public void onClick() {

        String postCode = postCodeView.getText().toString();
        String address = addressView.getText().toString();


        Intent intent = new Intent(MakeOrderActivity.this, MakeOrderNextActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_make_address)
    public void onSearchAddress() {
        Intent i = new Intent(MakeOrderActivity.this, SearchAddressActivity.class);
        startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        String[] strList;
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        strList = new String(data).split(",");
                        postCodeView.setText(strList[0]);
                        addressView.setText(strList[1]);
                    }
                }
                break;
        }
    }
}
