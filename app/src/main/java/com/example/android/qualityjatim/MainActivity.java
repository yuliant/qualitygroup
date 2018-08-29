package com.example.android.qualityjatim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String URL = "http://hostofquality.000webhostapp.com/admin/riverside";
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Data> listdata;

    private GridLayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "Promo kami";
        this.setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
//        layoutManager = new GridLayoutManager(this,2);
//        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        listdata = new ArrayList<Data>();
        ambidata();
        recyclerAdapter = new RecyclerAdapter(this,listdata);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void ambidata(){
        JsonArrayRequest arrReq = new JsonArrayRequest(URL+"/json/script.php",
        new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    for (int i = response.length(); i >= 0; i--) {
                        try {
                            JSONObject data = response.getJSONObject(i);
                            Data item = new Data();
                            item.setId(data.getString("id"));
                            item.setJudul(data.getString("judul"));
                            item.setLink(data.getString("link_website"));
                            item.setDeskripsi(data.getString("deskripsi"));
                            item.setThubnail(URL+"/images/"+data.getString("gambar"));
//                            item.setLink(data.getString("deskripsi"));
                            listdata.add(item);
                            recyclerAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Tidak ada data", Toast.LENGTH_SHORT).show();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"Aplikasi tidak meresponse", Toast.LENGTH_SHORT).show();

//                ProgressBar Pbar2 = (ProgressBar)findViewById(R.id.progress);
//                if (error != null){
//                    Pbar2.setVisibility(View.GONE);
//                }else{
//                    Pbar2.setVisibility(View.VISIBLE);
//                }

            }
        }) {
        };
        Volley.newRequestQueue(this).add(arrReq);
    }
}
