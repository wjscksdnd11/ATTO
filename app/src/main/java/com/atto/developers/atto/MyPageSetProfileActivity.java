package com.atto.developers.atto;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.ResultMessage;
import com.atto.developers.atto.request.UpdateMyProfileRequest;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MyPageSetProfileActivity extends AppCompatActivity {

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private static final int RC_GET_IMAGE = 1;
    @BindView(R.id.text_profile_set_address)
    TextView addressView;
    @BindView(R.id.img_mypage_set_profile)
    ImageView profileView;

    @BindView(R.id.edit_profile_nickname)
    EditText inputProfileView;

    @BindView(R.id.edit_phone_number)
    EditText inputPhoneNumberView;

    @BindView(R.id.text_profile_set_postcode)
    TextView postCodeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_set_profile);
        ButterKnife.bind(this);
        initToolBar();
//        initData();

        checkPermission();

    }

    @OnClick(R.id.btn_img_address)
    public void onSearchAddress() {
        Intent i = new Intent(MyPageSetProfileActivity.this, SearchAddressActivity.class);
        startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
    }

    @OnClick(R.id.btn_camera)
    public void onCaptureProfile() {

    }

    @OnClick(R.id.btn_complete_update)
    public void onCompleteUpdate() {


        String member_zipconde_1 = "a";
        String member_phone = "a";
        String member_address_1 = "a";
        String member_alias = "a";
        File member_profile_img = new File("a");
        UpdateMyProfileRequest request = new UpdateMyProfileRequest(this, member_zipconde_1, member_phone, member_address_1, member_alias, member_profile_img) ;
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
            @Override
            public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {
                Toast.makeText(MyPageSetProfileActivity.this, "성공", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(MyPageSetProfileActivity.this, "실패", Toast.LENGTH_LONG).show();


            }
        });
        finish();
    }

    @OnClick(R.id.img_mypage_set_profile)
    public void onSetProfileImage() {
        Intent intent = new Intent(MyPageSetProfileActivity.this, AddImageActivity.class);
        startActivityForResult(intent, RC_GET_IMAGE);

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
            case RC_GET_IMAGE:
                StringBuilder str = new StringBuilder();
                if (resultCode == RESULT_OK) {
                    String[] files = intent.getStringArrayExtra("files");
                    for (String s : files) {
                        Log.i("ImageFiles", "files : " + s);
                        str.append(s);
                    }
                    Glide.with(MyPageSetProfileActivity.this)
                            .load(new File(str.toString())).bitmapTransform(new CropCircleTransformation(this))
                            .into(profileView);
                }
        }
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_my_page_set_profile);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_atto_main));
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_grey);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private static final int RC_PERMISSION = 100;

    private void checkPermission() {
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
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
                        ActivityCompat.requestPermissions(MyPageSetProfileActivity.this, perms, RC_PERMISSION);
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
