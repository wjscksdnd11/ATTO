package com.atto.developers.atto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.atto.developers.atto.data.DetailMaker;

public class AddPortActivity extends AppCompatActivity {

    ImageView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_port);

        photoView = (ImageView)findViewById(R.id.img_add_port_photo);
        Button btn = (Button)findViewById(R.id.btn_add_port_register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPortActivity.this, DetailMaker.class);
                startActivity(intent);

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

    public void onDeletePhotoButton(View view) {
        photoView.setImageDrawable(null);
    }
}
