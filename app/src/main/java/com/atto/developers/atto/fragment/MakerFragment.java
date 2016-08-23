package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atto.developers.atto.DetailMakerActivity;
import com.atto.developers.atto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakerFragment extends Fragment {

    ListView listView;

    public MakerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_maker, container, false);

        listView = (ListView)view.findViewById(R.id.rv_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), DetailMakerActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
