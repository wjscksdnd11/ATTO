package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.atto.developers.atto.adapter.RecyclerDetailMakerAdapter;
import com.atto.developers.atto.fragment.MakerFragment;
import com.atto.developers.atto.fragment.ProgressDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.makerdata.MakerData;
import com.atto.developers.atto.networkdata.makerdata.MakerListItemData;
import com.atto.developers.atto.networkdata.portfoliodata.PortfolioData;
import com.atto.developers.atto.networkdata.portfoliodata.PortfolioListitemData;
import com.atto.developers.atto.request.DetailMakerRequest;
import com.atto.developers.atto.request.DetailPortfolioRequest;
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

        Intent intent = getIntent();
        int maker_id = intent.getIntExtra(MakerFragment.MAKER_ID, -1);
        Log.d("DetailMakerActivity", "makerId : " + maker_id);


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

                Intent intent = new Intent(DetailMakerActivity.this, DetailPortActivity.class);
                startActivity(intent);

            }
        });

        initData(maker_id);

    }

    ProgressDialogFragment dialogFragment = new ProgressDialogFragment();

    private void initData(int maker_id) {

        dialogFragment.show(getSupportFragmentManager(), "progress");
        mAdapter.clear();
        detailMakerRequest(maker_id);
        detailPortFolioData(maker_id);
    }

    private void detailMakerRequest(int tid) {

        DetailMakerRequest request = new DetailMakerRequest(this, String.valueOf(tid));
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<MakerListItemData>() {
            @Override
            public void onSuccess(NetworkRequest<MakerListItemData> request, MakerListItemData result) {
                MakerData data = result.getData();
                if(data != null) mAdapter.add(data);
                Log.d("DetailMakerActivity", "성공 result : " + data.getMaker_id());
                dialogFragment.dismiss();
            }
            @Override
            public void onFail(NetworkRequest<MakerListItemData> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("DetailMakerActivity", "실패 : " + errorMessage);
                dialogFragment.dismiss();

            }
        });

    }

    private void detailPortFolioData(int tid) {
        DetailPortfolioRequest request = new DetailPortfolioRequest(this, String.valueOf(tid));
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<PortfolioListitemData>() {
            @Override
            public void onSuccess(NetworkRequest<PortfolioListitemData> request, PortfolioListitemData result) {

                PortfolioData portfolioData = result.getData();
                if(portfolioData != null) {
                    mAdapter.add(portfolioData);
                    Log.d("DetailMakerActivity", portfolioData.getPortfolio_img());
                }
            }

            @Override
            public void onFail(NetworkRequest<PortfolioListitemData> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("DetailMakerActivity", "실패 : " + errorMessage);
            }
        });


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
