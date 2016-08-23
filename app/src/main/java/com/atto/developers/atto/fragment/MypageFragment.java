package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.atto.developers.atto.MyPageSettingActivity;
import com.atto.developers.atto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MypageFragment extends Fragment {


    public MypageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        View headerView = view.findViewById(R.id.mypage_header);
        Button btn = (Button)headerView.findViewById(R.id.btn_mypage_setting_myprofile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyPageSettingActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }

}
