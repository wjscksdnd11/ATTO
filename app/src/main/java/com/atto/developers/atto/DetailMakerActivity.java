package com.atto.developers.atto;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.atto.developers.atto.adapter.RecyclerDetailMakerAdapter;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.networkdata.makerdata.MakerListItemData;
import com.atto.developers.atto.request.DetailMakerRequest;
import com.atto.developers.atto.view.ItemOffsetDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMakerActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView listView;

    RecyclerDetailMakerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_maker);
        ButterKnife.bind(this);
        initToolBar();

        mAdapter = new RecyclerDetailMakerAdapter();
        listView.setAdapter(mAdapter);
        final GridLayoutManager manager = new GridLayoutManager(this, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.isHeader(position) ? manager.getSpanCount() : 1;
            }
        });


        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        listView.addItemDecoration(itemDecoration);
        listView.setLayoutManager(manager);

        mAdapter.setOnAdapterItemClickListener(new RecyclerDetailMakerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, MakerData makerData, int position) {

                Toast.makeText(DetailMakerActivity.this, "position : " + position, Toast.LENGTH_SHORT).show();

            }
        });

        initData();

    }

    private void initData() {

        String tid = "1";
        DetailMakerRequest request = new DetailMakerRequest(this, tid);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<MakerListItemData>() {
            @Override
            public void onSuccess(NetworkRequest<MakerListItemData> request, MakerListItemData result) {
                MakerData data = result.getData();
                Toast.makeText(DetailMakerActivity.this, "성공 result : " + data.getMaker_id(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFail(NetworkRequest<MakerListItemData> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(DetailMakerActivity.this, "실패" + errorCode, Toast.LENGTH_LONG).show();


            }
        });

        for (int i = 0; i < 20; i++) {
            MakerData makerData = new MakerData();
            makerData.setMader_representation_img("http://cfile227.uf.daum.net/image/251FB64752FA49772D6348");
            mAdapter.add(makerData);
        }
    }


    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.activity_detail_maker);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
