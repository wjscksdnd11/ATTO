package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPortActivity extends AppCompatActivity {

    @BindView(R.id.img_add_port_photo)
    ImageView photoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_port);
        ButterKnife.bind(this);
        initToolBar();
    }

    @OnClick(R.id.btn_add_port_delete_photo)
    public void onDeletePhotoButton() {
        photoView.setImageDrawable(null);
    }

    @OnClick(R.id.btn_add_port_register)
    public void onRegister() {
        Intent intent = new Intent(AddPortActivity.this, DetailMakerActivity.class);
        startActivity(intent);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_add_port);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
