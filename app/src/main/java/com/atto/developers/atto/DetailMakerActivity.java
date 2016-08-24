package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailMakerActivity extends AppCompatActivity {

    ListView listView;
    TextView signedView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_maker);

        signedView = (TextView)findViewById(R.id.text_detail_maker_signed);
        signedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        listView = (ListView)findViewById(R.id.rv_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if(position == 0) {
                    Intent intent = new Intent(DetailMakerActivity.this, AddPortActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DetailMakerActivity.this, AddTradeActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.back) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
