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

public class DetailTipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tipe);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "Detail Rumah";
        this.setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView judul = (TextView) findViewById(R.id.rumah);
        String jdl = getIntent().getStringExtra("RMH");
        judul.setText(jdl);

        TextView luas = (TextView) findViewById(R.id.luas);
        String lus = getIntent().getStringExtra("LUAS");
        luas.setText(lus);

        ImageView GambarJudul = (ImageView) findViewById(R.id.thumbnail);
        String img = getIntent().getStringExtra("GMR");
        Glide.with(DetailTipe.this)
                // LOAD URL DARI INTERNET
                .load(img)
                .into(GambarJudul);
        TextView link = (TextView) findViewById(R.id.harga);
        String li = getIntent().getStringExtra("HRG");
        link.setText(li);

        WebView fasilitas = (WebView) findViewById(R.id.fasilitas);
        String fas = getIntent().getStringExtra("FAS");
        fasilitas.loadData(fas, "text/html", "UTF-8");

        WebView keterangan = (WebView) findViewById(R.id.keterangan);
        String ket = getIntent().getStringExtra("KET");
        keterangan.loadData(ket, "text/html", "UTF-8");

        Button share = (Button)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sh = getIntent().getStringExtra("SHARE");

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
        Intent intent = new Intent(DetailTipe.this, about.class);
        startActivity(intent);
    }
}
