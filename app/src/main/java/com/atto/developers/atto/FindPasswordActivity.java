package com.atto.developers.atto;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atto.developers.atto.fragment.SendMailDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.userdata.MyProfile;
import com.atto.developers.atto.request.MyProfileRequest;

public class FindPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
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
        textView.setText(R.string.activity_find_password);

        Button btn = (Button)findViewById(R.id.btn_find_email);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                SendMailDialogFragment dialogFragment = new SendMailDialogFragment();
                dialogFragment.show(fm, "fragment_send_mail");

            }
        });
        String password = "1";
        String new_password="2";
        MyProfileRequest request = new MyProfileRequest(password,new_password);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<MyProfile>() {
            @Override
            public void onSuccess(NetworkRequest<MyProfile> request, MyProfile result) {

            }

            @Override
            public void onFail(NetworkRequest<MyProfile> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

    }
}
