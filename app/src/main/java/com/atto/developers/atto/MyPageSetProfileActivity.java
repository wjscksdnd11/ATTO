package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyPageSetProfileActivity extends AppCompatActivity {

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    TextInputEditText inputAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_set_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ImageButton imageBtn = (ImageButton) toolbar.findViewById(R.id.ic_back);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView textView = (TextView) toolbar.findViewById(R.id.text_title);
        textView.setText(R.string.activity_my_page_set_profile);

        inputAddress = (TextInputEditText) findViewById(R.id.edit_profile_set_address);

        Button btn = (Button) findViewById(R.id.btn_complete_update); // 수정 완료 버튼
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn = (Button) findViewById(R.id.btn_move_address);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MyPageSetProfileActivity.this, SearchAddressActivity.class);
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);

            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {

            case SEARCH_ADDRESS_ACTIVITY:

                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null)
                        inputAddress.setText(data);
                }
                break;

        }
    }
}
