package com.atto.developers.atto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.fragment.PickupDateFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegeListItemData;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.request.AddNegoCardRequest;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNegoActivity extends AppCompatActivity {
    private static final String TAG = AddNegoActivity.class.getSimpleName();
    private static final int RC_GET_IMAGE = 2;

    @BindView(R.id.img_trade_preview)
    ImageView imagePreView;

    @BindView(R.id.text_trade_preview)
    TextView textPreView;

    @BindView(R.id.edit_trade_content)
    AppCompatEditText inputContentView;

    @BindView(R.id.text_pickup_date)
    AppCompatEditText pickUpDateView;

    @BindView(R.id.text_setting_price)
    AppCompatEditText priceView;


    private String file_path;

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_add_nego);
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
        setContentView(R.layout.activity_add_nego);
        ButterKnife.bind(this);
        initToolBar();
        checkPermission();
    }

    @OnClick(R.id.text_add_nego_register_trade)
    public void onTradeRegister() {
        addData();

    }

    //달력 클릭 이동
    @OnClick(R.id.btn_wish_delevery)
    public void onPickUpDate() {
        FragmentManager fm = getSupportFragmentManager();
        PickupDateFragment dialogFragment = new PickupDateFragment();
        dialogFragment.show(fm, "fragment_pickup_date");

    }

    public void onDateSelectValue(String selectedDate) {
        TextView dateView = (TextView) findViewById(R.id.text_pickup_date);
        dateView.setText(selectedDate);
    }

    //가격 / 날짜/ !(디데이) / 설명 / 사진
    private void addData() {
        String negotiation_price = priceView.getText().toString();
        String negotiation_dtime = pickUpDateView.getText().toString();
        String negotiation_product_contents = inputContentView.getText().toString();
        File imageFile = null;
        if (file_path != null) {
            imageFile = new File(file_path);
        }
        File[] negotiation_product_imges_info = {imageFile};

        Log.e(TAG, "data : " + " " + negotiation_price + " " + negotiation_dtime + " " + negotiation_product_contents + negotiation_product_imges_info);
        if (TextUtils.isEmpty(negotiation_price) || TextUtils.isEmpty(negotiation_dtime) || TextUtils.isEmpty(negotiation_product_contents)) {
            Toast.makeText(this, "잘못된 입력입니다.", Toast.LENGTH_LONG).show();
        } else {
            AddNegoCardRequest request = new AddNegoCardRequest(this, "", negotiation_price, negotiation_dtime, negotiation_product_contents, negotiation_product_imges_info);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NegeListItemData>() {
                @Override
                public void onSuccess(NetworkRequest<NegeListItemData> request, NegeListItemData result) {
                    Log.e(TAG, "AddNegoActivity onSuccess 성공 : " + result);
                    NegoData negoData = result.getData();
                    if (negoData != null) {
                        Intent intent = new Intent(AddNegoActivity.this, DetailNegoActivity.class);
                        intent.putExtra("negotiation_id", negoData.getNegotiation_id());
                        Log.d("AddNegoActivity", "성공 : " + negoData.getNegotiation_id());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFail(NetworkRequest<NegeListItemData> request, int errorCode, String errorMessage, Throwable e) {
                    Log.e(TAG, "AddNegoActivity onFail 실패 : " + errorCode);
                }
            });
        }
    }

    //이미지 등록
    @OnClick(R.id.img_trade_preview)
    public void onSetPhotoImage() {
        Intent intent = new Intent(AddNegoActivity.this, AddImageActivity.class);
        startActivityForResult(intent, RC_GET_IMAGE);
    }

    //    @OnClick(R.id.btn_nego_register)
//        public void onCompleteNegoRegister() {
//            Intent intent = new Intent(AddNegoActivity.this, MainActivity.class);
//            AddNegoCardRequest request = new AddNegoCardRequest(this, "1", "5000", "2016. 02. 23.", "깔깔깔", null);
//            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NegoData>() {
//                @Override
//                public void onSuccess(NetworkRequest<NegoData> request, NegoData result) {
//                    Log.d("AddNegoActivity", "성공 : " + result.getCode());
//                }
//
//                @Override
//                public void onFail(NetworkRequest<NegoData> request, int errorCode, String errorMessage, Throwable e) {
//                    Log.d("AddNegoActivity", "실패 : " + errorMessage);
//
//
//                }
//            });
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        startActivity(intent);
//
//    @OnClick(R.id.btn_move_setting_price)
//    public void onMoveSettingPrice() {


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {

            case RC_GET_IMAGE:
                StringBuilder str = new StringBuilder();
                if (resultCode == RESULT_OK) {
                    String[] files = intent.getStringArrayExtra("files");
                    for (String s : files) {
                        Log.i("ImageFiles", "files : " + s);
                        str.append(s);
                    }
                    Glide.with(AddNegoActivity.this)
                            .load(new File(str.toString()))
                            .into(imagePreView);
                    textPreView.setVisibility(View.GONE);
                    file_path = str.toString();
                }
                break;
        }
    }

    private static final int RC_PERMISSION = 100;

    private void checkPermission() {
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (permissions.size() > 0) {
            boolean isShowUI = false;
            for (String perm : permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                    isShowUI = true;
                    break;
                }
            }

            final String[] perms = permissions.toArray(new String[permissions.size()]);

            if (isShowUI) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Permission");
                builder.setMessage("External Storage...");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(AddNegoActivity.this, perms, RC_PERMISSION);
                    }
                });
                builder.create().show();
                return;
            }

            ActivityCompat.requestPermissions(this, perms, RC_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERMISSION) {
            if (permissions != null) {
                boolean granted = true;
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                    }
                }
                if (!granted) {
                    Toast.makeText(this, "permission not granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }


}
