package com.atto.developers.atto;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atto.developers.atto.fragment.PickupDateFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListItemData;
import com.atto.developers.atto.request.AddTradeRequest;
import com.bumptech.glide.Glide;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTradeActivity extends AppCompatActivity {

    private static final int RC_GET_IMAGE = 2;
    private static final int IMAGE_HEIGHT_SIZE = 140;
    @BindView(R.id.img_trade_preview)
    ImageView imagePreView;

    @BindView(R.id.text_trade_preview)
    TextView textPreView;

    @BindView(R.id.edit_trade_title)
    AppCompatEditText inputTitleView;

    @BindView(R.id.edit_trade_content)
    AppCompatEditText inputContentView;

    @BindView(R.id.text_pickup_date)
    AppCompatEditText pickUpDateView;

    @BindView(R.id.edit_keyword_one)
    AppCompatEditText keywordOneView;

    @BindView(R.id.text_setting_price)
    AppCompatEditText priceView;

    private String mainCategory;
    private String subCategory;

    private String file_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trade);
        ButterKnife.bind(this);
        initToolBar();

        MaterialSpinner main_spinner = (MaterialSpinner) findViewById(R.id.spinner_main_category);
        main_spinner.setBackgroundColor(getResources().getColor(R.color.color_edit_layout));
        main_spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
        main_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                mainCategory = item;
            }
        });
        MaterialSpinner sub_spinner = (MaterialSpinner) findViewById(R.id.spinner_sub_category);
        sub_spinner.setBackgroundColor(getResources().getColor(R.color.color_edit_layout));
        sub_spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
        sub_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                subCategory = item;
            }
        });
        checkPermission();


    }

    @OnClick(R.id.text_add_trade_register_trade)
    public void onTradeRegister() {
        addData();
        Intent intent = new Intent(AddTradeActivity.this, DetailTradeActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_wish_delevery)
    public void onPickUpDate() {
        FragmentManager fm = getSupportFragmentManager();
        PickupDateFragment dialogFragment = new PickupDateFragment();
        dialogFragment.show(fm, "fragment_pickup_date");
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_addtrade);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onDateSelectValue(String selectedDate) {
        TextView dateView = (TextView) findViewById(R.id.text_pickup_date);
        dateView.setText(selectedDate);
    }

    public void addData() {

        String trade_title = inputTitleView.getText().toString();
        String trade_product_category_1 = mainCategory;
        String trade_product_category_2 = subCategory;
        String trade_price = priceView.getText().toString();
        String trade_dtime = pickUpDateView.getText().toString();
        String trade_product_contents = inputContentView.getText().toString();
        File imageFile = null;
        if (file_path != null) {
            imageFile = new File(file_path);
        }
        File[] trade_product_images_info = {imageFile};
        String[] trade_key_words = {keywordOneView.getText().toString()};

        if (trade_title.isEmpty() || trade_product_category_1.isEmpty() || trade_product_category_2.isEmpty() || trade_price.isEmpty()
                || trade_dtime.isEmpty() || trade_product_contents.isEmpty() || imageFile == null || (trade_product_images_info.length > 0) || trade_key_words.length > 0) {
            Toast.makeText(this, "잘못된 입력입니다.", Toast.LENGTH_LONG).show();

        } else {
            AddTradeRequest request = new AddTradeRequest(this, trade_title, trade_product_category_1, trade_product_category_2,
                    trade_price, trade_dtime, trade_product_contents, trade_key_words, trade_product_images_info);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListItemData>() {

                @Override
                public void onSuccess(NetworkRequest<TradeListItemData> request, TradeListItemData result) {
                    TradeData tradeData = result.getData();
                    Toast.makeText(AddTradeActivity.this, "성공 : " + tradeData.getTrade_id(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFail(NetworkRequest<TradeListItemData> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(AddTradeActivity.this, "실패 : " + errorCode, Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    @OnClick(R.id.img_trade_preview)
    public void onSetPhotoImage() {
        Intent intent = new Intent(AddTradeActivity.this, AddImageActivity.class);
        startActivityForResult(intent, RC_GET_IMAGE);
    }

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
                    Glide.with(AddTradeActivity.this)
                            .load(new File(str.toString())).fitCenter()
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
                        ActivityCompat.requestPermissions(AddTradeActivity.this, perms, RC_PERMISSION);
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
