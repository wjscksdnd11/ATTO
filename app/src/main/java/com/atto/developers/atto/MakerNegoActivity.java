package com.atto.developers.atto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MakerNegoActivity extends AppCompatActivity {

    TextView titleView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maker_nego);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ImageButton imageBtn = (ImageButton) toolbar.findViewById(R.id.ic_back);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        titleView = (TextView) toolbar.findViewById(R.id.text_title);
        titleView.setText(R.string.activity_maker_nego);

    }
}
