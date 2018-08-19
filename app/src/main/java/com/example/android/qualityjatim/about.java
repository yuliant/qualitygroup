package com.example.android.qualityjatim;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class about extends AppCompatActivity {
    String smsNumber = "+6282143056858";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "Bantuan";
        this.setTitle(title);

        // berfungsi untuk menampilkan icon back (kembali) di pojok kiri atas
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView email = (TextView) findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: masrizal04@gmail.com")); //hanya email app yang bisa menangani ini
                intent.putExtra(Intent.EXTRA_SUBJECT, "PESAN DARI APLIKASI");
                intent.putExtra(Intent.EXTRA_TEXT, "Assaalamualiakum Qulaity Group");
                startActivity(intent);
            }
        });


        TextView telpon = (TextView) findViewById(R.id.telpon);
        telpon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", smsNumber, null));
                startActivity(intent);
            }
        });

        TextView sms = (TextView) findViewById(R.id.sms);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", smsNumber, null)));
            }
        });

        TextView whatapp = (TextView) findViewById(R.id.whatsapp);
        whatapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smsText = "Hallo";
                Uri uri = Uri.parse("smsto:" + smsNumber);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", smsText);
                i.setPackage("com.whatsapp");
                startActivity(i);
            }
        });

        TextView instagram = (TextView) findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/masrizaleka/"));
                startActivity(intent);
            }
        });

        TextView twitter = (TextView) findViewById(R.id.twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/masrizal_ey"));
                startActivity(intent);
            }
        });

        TextView facebook = (TextView) findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/rizal.ekayulianto"));
                startActivity(intent);
            }
        });

    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
