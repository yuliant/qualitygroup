package com.example.android.qualityjatim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {

    private LinearLayout about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

//        String title = "Quality Group - Krian";
//        setActionBarTitle(title);

    }
//    private void setActionBarTitle(String title){
//        getSupportActionBar().setTitle(title);
//    }

    public void about(View view) {
        Intent intent = new Intent(home.this, about.class);
        startActivity(intent);
    }

    public void peta(View view) {
        Intent intent = new Intent(home.this, MapsActivity.class);
        startActivity(intent);
    }
}
