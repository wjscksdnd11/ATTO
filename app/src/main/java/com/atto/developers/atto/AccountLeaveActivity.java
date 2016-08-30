package com.atto.developers.atto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import com.atto.developers.atto.fragment.CheckLeaveDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccountLeaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_leave);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_leave_atto)
    public void onLogOut() {
        CheckLeaveDialogFragment dialogFragment = new CheckLeaveDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "Leave");
    }

    public void startIntent() {
        Intent intent = new Intent(AccountLeaveActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
