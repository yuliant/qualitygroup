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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Data> listdata;
    private Activity activity;
    private Context context;

    public RecyclerAdapter(Activity activity, ArrayList<Data> listdata) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mImage.setImageResource(listdata.get(position).getThubnail());
        holder.id.setText(listdata.get(position).getId());
        holder.judul.setText(listdata.get(position).getJudul());
        holder.link.setText(listdata.get(position).getLink());
        final ViewHolder x=holder;
        Glide.with(activity)
                .load(listdata.get(position).getThubnail())
                .override(350, 350) // menambahkan ini untuk tampilan list CardView
                .into(holder.thumbnail);
        holder.id.setVisibility(View.GONE);

        holder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
//                Toast.makeText(activity, "Details "+listdata.get(position).getJudul(), Toast.LENGTH_SHORT).show();

                String judul=listdata.get(position).getJudul();
                String tgl=listdata.get(position).getLink();
                String link=listdata.get(position).getThubnail();
                String shareBody= judul+" promo baru berlaku sampai "+tgl+ " Selengkapnya cek disini "+link;

                Intent KirimData = new Intent(activity, DetailPromo.class);
                KirimData.putExtra("JDL",listdata.get(position).getJudul());
                KirimData.putExtra("GMR",listdata.get(position).getThubnail());
                KirimData.putExtra("DES",listdata.get(position).getDeskripsi());
                KirimData.putExtra("LINK",listdata.get(position).getLink());
                KirimData.putExtra("SHARE",shareBody);
                activity.startActivity(KirimData);
            }
        }));

        holder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                // ---------------------------------------------------------------------------
                // project sisa kirim gambar ke sosmed


                /*Toast.makeText(activity, "Share "+listdata.get(position).getJudul(), Toast.LENGTH_SHORT).show();
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                Uri imageuri = Uri.parse(listdata.get(position).getThubnail());
                String shareBody = listdata.get(position).getThubnail();
                String inipromo = listdata.get(position).getJudul()+" promo baru berlaku sampai "+listdata.get(position).getLink();
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("image/jpg");
                whatsappIntent.putExtra(Intent.EXTRA_STREAM, imageuri);
//                whatsappIntent.putExtra(Intent.EXTRA_TEXT, inipromo);
//                whatsappIntent.setPackage("com.whatsapp");
                activity.startActivity(Intent.createChooser(whatsappIntent,"Send image"));
                try {
                    activity.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(activity,"Whatsapp have not been installed.",Toast.LENGTH_SHORT).show();
                }*/
                // ---------------------------------------------------------------------------

                String judul=listdata.get(position).getJudul();
                String tgl=listdata.get(position).getLink();
                String link=listdata.get(position).getThubnail();
                String shareBody= judul+" promo baru berlaku sampai "+tgl+ " Selengkapnya cek disini "+link;
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView id,judul,link;
        private ImageView thumbnail;

        Button btnFavorite, btnShare;

        public ViewHolder(View v) {
            super(v);
            cv=(CardView)v.findViewById(R.id.card_view);
            id=(TextView)v.findViewById(R.id.id);
            judul=(TextView)v.findViewById(R.id.judul);
            link=(TextView)v.findViewById(R.id.link);
            thumbnail=(ImageView)v.findViewById(R.id.thumbnail);

            btnFavorite = (Button)itemView.findViewById(R.id.btn_set_favorite);
            btnShare = (Button)itemView.findViewById(R.id.btn_set_share);
        }
    }

}