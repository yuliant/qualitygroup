package com.example.android.qualityjatim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TipeAdapter extends RecyclerView.Adapter<TipeAdapter.TipeViewHolder> {
    private ArrayList<DataTipe> listdata;
    private Activity activity;
    private Context context;

    public TipeAdapter(Activity activity, ArrayList<DataTipe> listdata) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public TipeAdapter.TipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tipelist, parent, false);
        TipeAdapter.TipeViewHolder vh = new TipeAdapter.TipeViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TipeAdapter.TipeViewHolder holder2, int position) {
        holder2.id.setText(listdata.get(position).getId());
        holder2.rumah.setText(listdata.get(position).getRumah());
        holder2.harga.setText(listdata.get(position).getHarga());
        final TipeAdapter.TipeViewHolder x=holder2;
        Glide.with(activity)
                .load(listdata.get(position).getThubnail())
                .override(350, 350) // menambahkan ini untuk tampilan list CardView
                .into(holder2.thumbnail);
        holder2.id.setVisibility(View.GONE);

        holder2.btnFavorite.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
//                Toast.makeText(activity, "Details "+listdata.get(position).getRumah(), Toast.LENGTH_SHORT).show();

                String judul=listdata.get(position).getRumah();
                String tgl=listdata.get(position).getHarga();
                String link=listdata.get(position).getThubnail();
                String shareBody= "Sebuah Rumah "+judul+" seharga "+tgl+ " tersedia di The Quality Riverside. Ayo cek sekarang di "+link;

                Intent KirimData = new Intent(activity, DetailTipe.class);
                KirimData.putExtra("RMH",listdata.get(position).getRumah());
                KirimData.putExtra("GMR",listdata.get(position).getThubnail());
                KirimData.putExtra("HRG",listdata.get(position).getHarga());
                KirimData.putExtra("LUAS",listdata.get(position).getLuas());
                KirimData.putExtra("FAS",listdata.get(position).getFasilitas());
                KirimData.putExtra("KET",listdata.get(position).getKeterangan());
                KirimData.putExtra("SHARE",shareBody);
                activity.startActivity(KirimData);
            }
        }));

        holder2.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
//                Toast.makeText(activity, "Share "+listdata.get(position).getRumah(), Toast.LENGTH_SHORT).show();

                String judul=listdata.get(position).getRumah();
                String tgl=listdata.get(position).getHarga();
                String link=listdata.get(position).getThubnail();
                String shareBody= "Sebuah Rumah "+judul+" seharga "+tgl+ " tersedia di The Quality Riverside. Ayo cek sekarang di "+link;

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                activity.startActivity(Intent.createChooser(shareIntent,"Share via"));

            }
        }));
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class TipeViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView id,rumah,harga;
        private ImageView thumbnail;

        Button btnFavorite, btnShare;

        public TipeViewHolder(View v) {
            super(v);
            cv=(CardView)v.findViewById(R.id.card_view);
            id=(TextView)v.findViewById(R.id.id);
            rumah=(TextView)v.findViewById(R.id.rumah);
            harga=(TextView)v.findViewById(R.id.harga);
            thumbnail=(ImageView)v.findViewById(R.id.thumbnail);

            btnFavorite = (Button)itemView.findViewById(R.id.btn_set_favorite);
            btnShare = (Button)itemView.findViewById(R.id.btn_set_share);
        }
    }
}
