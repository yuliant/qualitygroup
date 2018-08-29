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

public class Tipe extends AppCompatActivity {

    private String URL = "http://hostofquality.000webhostapp.com/admin/tiperiverside";
    private TipeAdapter TipeAdapter;
    private RecyclerView recyclerView;
    private ArrayList<DataTipe> listdata;

    private GridLayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;

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
        ambidata();
        TipeAdapter = new TipeAdapter(this,listdata);
        recyclerView.setAdapter(TipeAdapter);
        TipeAdapter.notifyDataSetChanged();
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
