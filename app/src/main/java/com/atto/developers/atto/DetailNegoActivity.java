package com.atto.developers.atto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.atto.developers.atto.fragment.MakerOrderDialogFragment;

public class DetailNegoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nego);

        Button btn = (Button)findViewById(R.id.btn_trade_approve_request);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCustomDialog();
            }
        });
    }

    public void onCustomDialog() {
        MakerOrderDialogFragment dialog = new MakerOrderDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }
}
