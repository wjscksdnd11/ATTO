package com.atto.developers.atto.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.atto.developers.atto.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPostcodeDialogFragment extends DialogFragment {

    private String key = "13156a02d459a7fdd1472444149179";
    ListView listView;

    private ArrayAdapter<String> mAdapter;
    private String mAddress;
    private ArrayList<String> addressItems = new ArrayList<String>();



    public SearchPostcodeDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search_postcode_dialog, container, false);

        final TextInputEditText inputAddress = (TextInputEditText) view.findViewById(R.id.edit_search_address);
        listView = (ListView)view.findViewById(R.id.listView);
        Button btn = (Button)view.findViewById(R.id.btn_search_address);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAddress(inputAddress.getText().toString());

            }
        });
        return view;
    }

    private void getAddress(String address) {
        mAddress = address;
    }


}
