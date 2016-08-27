
package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.DetailMakerActivity;
import com.atto.developers.atto.R;

import com.atto.developers.atto.adapter.RecyclerMakerAdapter;
import com.atto.developers.atto.data.networkdata.makerdata.MakerData;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */

public class MakerFragment extends Fragment {

    RecyclerView listView;
    RecyclerMakerAdapter mAdapter;

    public MakerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_maker, container, false);

        listView = (RecyclerView) view.findViewById(R.id.rv_list);
        mAdapter = new RecyclerMakerAdapter();
        listView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(manager);

        mAdapter.setOnAdapterItemClickListener(new RecyclerMakerAdapter.OnAdapterItemClickListener() {

            @Override
            public void onAdapterItemClick(View view, MakerData makerItemData, int position) {
                Intent intent = new Intent(getContext(), DetailMakerActivity.class);
                startActivity(intent);
            }
        });

        initData();

        return view;
    }

    private void initData() {

        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            MakerData makerData = new MakerData();
            mAdapter.add(makerData);

        }

    }

}
