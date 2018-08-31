package com.example.android.qualityjatim;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tipe extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String URL = "http://hostofquality.000webhostapp.com/admin/tiperiverside";
    private TipeAdapter TipeAdapter;
    private RecyclerView recyclerView;
    private ArrayList<DataTipe> listdata;

    private GridLayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipe);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "Tipe Rumah";
        this.setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        listdata = new ArrayList<DataTipe>();
//        ambidata();
        TipeAdapter = new TipeAdapter(this,listdata);
        recyclerView.setAdapter(TipeAdapter);
        TipeAdapter.notifyDataSetChanged();

        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                ambidata();
            }
        });
    }

    // ---------------------------------------------------------------------------->
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_bar,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView  = new SearchView(Tipe.this);
        //searchView.setBackgroundColor(getResources().getColor(R.color.putih));
        searchView.setQueryHint("Cari Sesuatu....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
                nextText = nextText.toLowerCase();
                ArrayList<DataTipe> dataFilter = new ArrayList<>();
                for(DataTipe data : listdata){
                    String nama = data.getRumah().toLowerCase();
                    String harga = data.getHarga().toLowerCase();
                    if(nama.contains(nextText)){
                        dataFilter.add(data);
                    }
                    if(harga.contains(nextText)){
                        dataFilter.add(data);
                    }
                }
                TipeAdapter.setFilter(dataFilter);
                return true;
            }
        });
        searchItem.setActionView(searchView);

        return true;
    }

    //<------------------------------------------------------------------------------------------//


    @Override
    public void onRefresh() {

        if(TipeAdapter != null){
            listdata.clear();
            TipeAdapter.notifyDataSetChanged();
            ambidata();
        }else {
            ambidata();
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void ambidata(){
        mSwipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest arrReq = new JsonArrayRequest(URL+"/json/script.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            for (int i = response.length(); i >= 0; i--) {
                                try {
                                    JSONObject data = response.getJSONObject(i);
                                    DataTipe item = new DataTipe();
                                    item.setId(data.getString("id"));
                                    item.setRumah(data.getString("nama_rumah"));
                                    item.setLuas(data.getString("luas"));
                                    item.setFasilitas(data.getString("fasilitas"));
                                    item.setHarga(data.getString("harga"));
                                    item.setKeterangan(data.getString("keterangan"));
                                    item.setThubnail(URL+"/images/"+data.getString("gambar"));
//                            item.setLink(data.getString("deskripsi"));
                                    listdata.add(item);
                                    TipeAdapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Tidak ada data", Toast.LENGTH_SHORT).show();
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
        };
        Volley.newRequestQueue(this).add(arrReq);
    }
}
