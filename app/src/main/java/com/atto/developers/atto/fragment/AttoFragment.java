package com.atto.developers.atto.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.atto.developers.atto.DetailPortActivity;
import com.atto.developers.atto.R;
import com.atto.developers.atto.asymmetricgridview.DefaultListAdapter;
import com.atto.developers.atto.asymmetricgridview.DemoAdapter;
import com.atto.developers.atto.asymmetricgridview.DemoItem;
import com.atto.developers.atto.asymmetricgridview.DemoUtils;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttoFragment extends Fragment implements AdapterView.OnItemClickListener {
    AsymmetricGridView listView;
    DemoAdapter adapter;

    private final DemoUtils demoUtils = new DemoUtils();


    public AttoFragment() {
        // Required empty public constructor
    }

    public static AttoFragment newInstance() {

        AttoFragment fragment = new AttoFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atto, container, false);

        listView = (AsymmetricGridView) view.findViewById(R.id.listView);

        // Choose your own preferred column width

        final List<DemoItem> items = new ArrayList<>();
        // initialize your items array
        adapter = new DefaultListAdapter(getContext(), demoUtils.moarItems(0));
        adapter.setItems(items);

        init();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void init() {

        demoUtils.currentOffset = 0;
        adapter.setItems(demoUtils.moarItems(30));
        adapter.appendItems(demoUtils.moarItems(30));
        listView.setRequestedColumnCount(3);
        listView.setRequestedHorizontalSpacing(Utils.dpToPx(getContext(), 3));
        listView.setDebugging(true);
        listView.setOnItemClickListener(this);
        setNumColumns(3);
    }

    private AsymmetricGridViewAdapter getNewAdapter() {
        return new AsymmetricGridViewAdapter(getContext(), listView, adapter);
    }


    private void setNumColumns(int numColumns) {
        listView.setRequestedColumnCount(numColumns);
        listView.determineColumns();
        listView.setAdapter(getNewAdapter());
    }



    @Override
    public void onItemClick(@NotNull AdapterView<?> parent, @NotNull View view, int position, long id) {
        Toast.makeText(getContext(), "item : " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), DetailPortActivity.class);
        startActivity(intent);
    }

}
