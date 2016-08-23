package com.atto.developers.atto;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.atto.developers.atto.fragment.AttoFragment;
import com.atto.developers.atto.fragment.MakerFragment;
import com.atto.developers.atto.fragment.RealTimeTradeFragment;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getString(R.string.main_tab_one)),
                AttoFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getString(R.string.main_tab_realtrade)),
                RealTimeTradeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getString(R.string.main_tab_maker)),
                MakerFragment.class, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.notification) {
            Toast.makeText(this, "notification", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
