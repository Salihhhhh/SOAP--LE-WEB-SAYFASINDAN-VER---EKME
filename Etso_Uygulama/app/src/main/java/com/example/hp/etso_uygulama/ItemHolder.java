package com.example.hp.etso_uygulama;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hp on 12.6.2018.
 */
public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtEmail;
    public ImageView profilResim;
    public String link_gelen;

     // public TextView txtAd;



    @Override
    public void onClick(View v) {

        try {
            Intent i = new Intent(v.getContext(), Main2Activity.class);
            i.putExtra("mail", link_gelen);
            v.getContext().startActivity(i);
        } catch (Exception e) {
            Log.i("hataaa", e.getMessage());
        }

    }


    public ItemHolder(final View view) {
        super(view);
        // txtAd = (TextView) view.findViewById(R.id.person_name);
        txtEmail = (TextView) view.findViewById(R.id.person_age);
        profilResim = (ImageView) view.findViewById(R.id.person_photo);
        view.setOnClickListener(this);
    }
}
