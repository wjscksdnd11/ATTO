
package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.atto.developers.atto.DetailMakerActivity;
import com.atto.developers.atto.R;
import com.atto.developers.atto.adapter.RecyclerMakerAdapter;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.networkdata.tradedata.TradeListData;
import com.atto.developers.atto.request.MakerListRequest;
import com.atto.developers.atto.view.DividerItemDecoration;

import java.util.Arrays;


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

        final ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.show(getFragmentManager(), "progress");
        mAdapter.clear();
        MakerListRequest request = new MakerListRequest(getContext(), "1", "10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<MakerData>>() {
            @Override
            public void onSuccess(NetworkRequest<TradeListData<MakerData>> request, TradeListData<MakerData> result) {
                MakerData[] data = result.getData();
                Toast.makeText(getContext(), "성공 : " + data[0].getMaker_score(), Toast.LENGTH_SHORT).show();
                Log.d("MakerFragment", String.valueOf(data[0].getMaker_score()));
                Log.d("MakerFragment", String.valueOf(data[0].getMaker_id()));

//                Log.d("MakerFragment", data[0].getMaker_product_category());
//                Log.d("MakerFragment", data[0].getMaker_key_word_lists().getKey_word_1());

                mAdapter.addAll(Arrays.asList(data));
                dialogFragment.dismiss();
            }

            @Override
            public void onFail(NetworkRequest<TradeListData<MakerData>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();

                dialogFragment.dismiss();
            }
        });

        /*mAdapter.clear();
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

        }*/

    }

}
