package com.atto.developers.atto.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportDialogFragment extends Fragment {


    public ReportDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_dialog, container, false);
    }

}
