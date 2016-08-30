package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.LoginActivity;
import com.atto.developers.atto.MyPageSettingActivity;
import com.atto.developers.atto.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckLogoutDialogFragment extends DialogFragment {


    public CheckLogoutDialogFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_logout_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_check_complete)
    public void onCheckComplete() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        MyPageSettingActivity callerActivity = (MyPageSettingActivity)getActivity();
        dismiss();
        callerActivity.startIntent();

    }

    @OnClick(R.id.btn_cancel)
    public void onCancel() {
        dismiss();

    }
}
