package com.example.android.qualityjatim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
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

        TextView link = (TextView) findViewById(R.id.link);
        String li = getIntent().getStringExtra("LINK");
        link.setText(li);

        WebView deskripsi = (WebView) findViewById(R.id.deskripsi);
        String des = getIntent().getStringExtra("DES");
        deskripsi.loadData(des, "text/html", "UTF-8");

//        final Button share = (Button)findViewById(R.id.share);
        Button share = (Button)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sh = getIntent().getStringExtra("SHARE");
//                share.setText(sh);

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, sh);
                startActivity(shareIntent);
            }
        });

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
