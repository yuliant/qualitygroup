package com.example.android.qualityjatim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailPromo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "Detail Promo";
        this.setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView judul = (TextView) findViewById(R.id.judul);
        String jdl = getIntent().getStringExtra("JDL");
        judul.setText(jdl);

        ImageView GambarJudul = (ImageView) findViewById(R.id.thumbnail);
        String img = getIntent().getStringExtra("GMR");
        Glide.with(DetailPromo.this)
                // LOAD URL DARI INTERNET
                .load(img)
                .into(GambarJudul);

//        TextView deskripsi = (TextView) findViewById(R.id.deskripsi);
//        String des = getIntent().getStringExtra("DES");
//        deskripsi.setText(des);

        WebView deskripsi = (WebView) findViewById(R.id.deskripsi);
        String des = getIntent().getStringExtra("DES");
        deskripsi.loadData(des, "text/html", "UTF-8");

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void about(View view) {
        Intent intent = new Intent(DetailPromo.this, about.class);
        startActivity(intent);
    }
}
