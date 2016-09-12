package com.atto.developers.atto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.atto.developers.atto.adapter.RecyclerDetailTradeAdapter;
import com.atto.developers.atto.fragment.ProgressDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.tradedata.ListData;
import com.atto.developers.atto.networkdata.tradedata.TradeListItemData;
import com.atto.developers.atto.request.DetailTradeRequest;
import com.atto.developers.atto.request.NegoCardListRequest;
import com.atto.developers.atto.view.DividerItemDecoration;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTradeActivity extends AppCompatActivity {

	private static final String TAG = DetailTradeActivity.class.getSimpleName();

	@BindView(R.id.re_list)
	RecyclerView mListView;
	RecyclerDetailTradeAdapter mAdapter;
	ProgressDialogFragment mDialogFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_trade);
		ButterKnife.bind(this);
		initToolBar();

		Intent intent = getIntent();
		int trade_Id = intent.getIntExtra("trade_id", -1);



		mDialogFragment = new ProgressDialogFragment();
		mDialogFragment.show(getSupportFragmentManager(), "detail_trade");
		mAdapter = new RecyclerDetailTradeAdapter();
		mListView.setAdapter(mAdapter);
		mListView.setLayoutManager(new LinearLayoutManager(this));
		mListView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
		mAdapter.setOnAdapterItemClickListener(new RecyclerDetailTradeAdapter.OnAdapterItemClickLIstener() {
			@Override
			public void onAdapterItemClick(View view, NegoData negoData, int position) {
				Intent intent = new Intent(DetailTradeActivity.this, DetailNegoActivity.class);
				intent.putExtra("Nego_id", negoData.getNegotiation_id());
				startActivity(intent);
			}
		});
		initData(trade_Id);
	}

	private void initData(int trade_Id) {
		checkTradeData(trade_Id);
	}

	@OnClick(R.id.btn_move_nego_register)
	public void onMoveAddNego() {
		Intent intent = new Intent(DetailTradeActivity.this, AddNegoActivity.class);
		startActivity(intent);
		finish();
	}


	private void initToolBar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
		toolbar.setTitle(R.string.activity_detail_trade);
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

	private void checkTradeData(final int trade_Id) {

		DetailTradeRequest request = new DetailTradeRequest(this, trade_Id+"", "1", "1", "1");
		NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListItemData>() {
			@Override
			public void onSuccess(NetworkRequest<TradeListItemData> request, TradeListItemData result) {
				Log.e(TAG, " Trade onSuccess 성공 : " + result);
				mAdapter.setTradeData(result);
				checkNegoData(trade_Id);
				mDialogFragment.dismiss();

			}
			@Override
			public void onFail(NetworkRequest<TradeListItemData> request, int errorCode, String errorMessage, Throwable e) {
				Log.e(TAG, "Trade onFail 실패: " + errorCode);
				mDialogFragment.dismiss();
			}
		});
	}

	private void checkNegoData(int trade_id) {

		NegoCardListRequest request = new NegoCardListRequest(this, trade_id+"", "", "10");
		NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ListData<NegoData>>() {
			@Override
			public void onSuccess(NetworkRequest<ListData<NegoData>> request, ListData<NegoData> result) {
				Log.e(TAG, "Nego onSuccess 성공 : " + result);
				NegoData[] data = result.getData();
				if(data != null) {
					if(data.length > 0) {
						mAdapter.addNego(Arrays.asList(data));
						Log.d("DetailTradeActivity", "제작자성공 : " + data[0].getNegotiation_id());
					}
				}
			}


			@Override
			public void onFail(NetworkRequest<ListData<NegoData>> request, int errorCode, String errorMessage, Throwable e) {
				Log.e(TAG, "Nego onFail 실패: " + errorCode);
			}
		});
	}
}