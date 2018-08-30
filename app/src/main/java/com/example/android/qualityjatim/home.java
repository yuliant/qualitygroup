package com.example.android.qualityjatim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {

    private LinearLayout about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "The Quality Riverside";
        this.setTitle(title);
    }

    public void about(View view) {
        Intent intent = new Intent(home.this, about.class);
        startActivity(intent);
    }

    public void rumah(View view) {
        Intent intent = new Intent(home.this, Tipe.class);
        startActivity(intent);
    }

    public void promo(View view) {
        Intent intent = new Intent(home.this, MainActivity.class);
        startActivity(intent);
    }

    public void peta(View view) {
        Intent intent = new Intent(home.this, MapsActivity.class);
        startActivity(intent);
    }
}
