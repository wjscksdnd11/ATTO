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
import android.widget.Toast;

import com.atto.developers.atto.adapter.RecyclerDetailTradeAdapter;
import com.atto.developers.atto.fragment.ProgressDialogFragment;
import com.atto.developers.atto.manager.NetworkManager;
import com.atto.developers.atto.manager.NetworkRequest;
import com.atto.developers.atto.networkdata.negodata.NegoData;
import com.atto.developers.atto.networkdata.tradedata.TradeData;
import com.atto.developers.atto.networkdata.tradedata.TradeListData;
import com.atto.developers.atto.networkdata.tradedata.TradeListItemData;
import com.atto.developers.atto.request.DetailTradeRequest;
import com.atto.developers.atto.request.NegoCardListRequest;
import com.atto.developers.atto.view.DividerItemDecoration;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailTradeActivity extends AppCompatActivity {

	private Unbinder mUnbinder;

	@BindView(R.id.re_list)
	RecyclerView mListView;
	RecyclerDetailTradeAdapter mAdapter;
	ProgressDialogFragment mDialogFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_trade);
		mUnbinder = ButterKnife.bind(this);

		init();
	}

	private void init() {

		Intent intent = getIntent();
		int tradeId = intent.getIntExtra("trade_id", -1);

		initToolBar();
		mDialogFragment = new ProgressDialogFragment();
		mDialogFragment.show(getSupportFragmentManager(), "detail_trade");
		mAdapter = new RecyclerDetailTradeAdapter();
		mListView.setAdapter(mAdapter);
		mListView.setLayoutManager(new LinearLayoutManager(this));
		mListView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));

		initData(tradeId);
	}

	private void initData(int tradeId) {
		checkTradeData(tradeId);
		checkNegoData(tradeId);
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

	private void checkTradeData(int tradeId) {
		DetailTradeRequest request = new DetailTradeRequest(this, "1", "1", "10", "10");
		NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListItemData>() {
			@Override
			public void onSuccess(NetworkRequest<TradeListItemData> request, TradeListItemData result) {
				TradeData data = result.getData();

				if (data != null) {
					mAdapter.setTradeData(data);
					mDialogFragment.dismiss();
				}else{
					Log.d("DetailtradeActivity","실패");
				}
			}

			@Override
			public void onFail(NetworkRequest<TradeListItemData> request, int errorCode, String errorMessage, Throwable e) {
				Log.d("DetailTradeActivity", "실패: " + errorCode);
				Toast.makeText(DetailTradeActivity.this, "실패 : " + errorCode, Toast.LENGTH_SHORT).show();
				mDialogFragment.dismiss();
			}
		});
	}

	private void checkNegoData(final int tradeId) {
		NegoCardListRequest request = new NegoCardListRequest(this, "1", "10", "10");
		NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<TradeListData<NegoData>>() {
			@Override
			public void onSuccess(NetworkRequest<TradeListData<NegoData>> request, TradeListData<NegoData> result) {
				NegoData[] data = result.getData();

				if(data != null) {
					if(data.length > 0) {
						mAdapter.addNego(Arrays.asList(data));
						Log.d("DetailTradeActivity", "제작자성공 : " + data[0].getNegotiation_id());

					}
				}

			}

			@Override
			public void onFail(NetworkRequest<TradeListData<NegoData>> request, int errorCode, String errorMessage, Throwable e) {
				Toast.makeText(getApplicationContext(), "실패 : " + errorCode, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mUnbinder.unbind();
	}
}