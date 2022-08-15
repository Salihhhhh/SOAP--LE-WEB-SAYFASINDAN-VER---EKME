package com.example.hp.etso_uygulama;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hp on 12.6.2018.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<Uyeler> uyelerList;

    public ItemAdapter(List<Uyeler> uyelerList) {
        this.uyelerList = uyelerList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Uyeler uye = uyelerList.get(position);

        ///////////Uyeler clasından alınan verileri itemHolder ' a gonderiyoruz./////////////
        // holder.txtAd.setText(uye.getAd());
        holder.link_gelen = uye.getAd();

        holder.txtEmail.setText(uye.getEmail());


        /////////// CardView de haber resimlerinin  e haber başlıklarının  gosterilmesini sağlıyor////////////
        Picasso.with(holder.txtEmail.getContext()).load(uye.getProfilResim()).into(holder.profilResim);
    }

    @Override
    public int getItemCount() {
        return uyelerList.size();
    }
}