
package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atto.developers.atto.DetailMakerActivity;
import com.atto.developers.atto.R;
import com.atto.developers.atto.adapter.RecyclerMakerAdapter;
import com.atto.developers.atto.networkdata.listdata.KeywordList;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.view.DividerItemDecoration;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */

public class MakerFragment extends Fragment {

    RecyclerView listView;
    RecyclerMakerAdapter mAdapter;

    private static final int VERTICAL_ITEM_SPACE = 6;


    public MakerFragment() {
        // Required empty public constructor
    }

    public static MakerFragment newInstance() {

        MakerFragment fragment = new MakerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maker, container, false);

        listView = (RecyclerView) view.findViewById(R.id.rv_list);
        mAdapter = new RecyclerMakerAdapter();
        listView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        listView.addItemDecoration(new DividerItemDecoration(getActivity()));
        //add ItemDecoration
        //listView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
//        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
//        listView.addItemDecoration(itemDecoration);
        listView.setLayoutManager(manager);
        listView.addItemDecoration(
                new DividerItemDecoration(getContext(), R.drawable.divider));
        listView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnAdapterItemClickListener(new RecyclerMakerAdapter.OnAdapterItemClickLIstener() {

            @Override
            public void onAdapterItemClick(View view, MakerData makerItemData, int position) {

                Intent intent = new Intent(getContext(), DetailMakerActivity.class);
                startActivity(intent);
            }
        });

        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    String[] nicknames = {"Lana Delange", "Sueann Membreno", "Kent Huntoon", "Dione Kogut", "Chelsea Ribeiro", "Ruth Kearns",
            "Maryanne Sweigart", "Chasidy Scheffer", "Kyla Seddon", "Trinh Farr", "Tandy Norby", "Augustus Helm", "Annabel Schenck",
            "Lurlene Meares", "Micheline Vannote", "Coretta Salaam", "Anya Quesenberry", "Gavin Caskey", "Annie Nellum", "Hershel Parkman"};

    private void initData() {

        mAdapter.clear();
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            MakerData makerData = new MakerData();
            KeywordList keywordList = new KeywordList();
            keywordList.setKey_word_1("keyword : " + i);
            makerData.setMaker_score(r.nextInt(5) + "");
            makerData.setMaker_id(nicknames[i]);
            makerData.setMaker_line_tag("tag " + i);
            makerData.setMaker_key_word_lists(keywordList);
            makerData.setMaker_product_category("category 1 ");
            makerData.setMaker_product_category_1("category 2");
            makerData.setMaker_product_category_2("category 3");
            makerData.setMaker_score(r.nextInt(5) + "");
            mAdapter.add(makerData);

        }

    }

}
