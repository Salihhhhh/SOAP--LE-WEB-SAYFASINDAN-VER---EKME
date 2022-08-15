package com.example.hp.etso_uygulama;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    private String hb;
    private Button hbr, odamız, oda_organları, oda_brm, oda_yayın, hizmet;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        internetUyari();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hbr = (Button) findViewById(R.id.haberler);
        odamız = (Button) findViewById(R.id.odamız);
        oda_organları = (Button) findViewById(R.id.oda_organları);
        oda_brm = (Button) findViewById(R.id.oda_birimleri);
        oda_yayın = (Button) findViewById(R.id.oda_yayınları);
        hizmet = (Button) findViewById(R.id.hizmetler);


        hbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (InternetKontrol()) {
                    hb = "0";
                    Intent i = new Intent(getApplicationContext(), Main4Activity.class);
                    i.putExtra("send", hb);
                    startActivity(i);

                } else {
                    internetUyari();


                }
            }
        });

        oda_organları.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (InternetKontrol()) {
                    hb = "1";
                    Intent i = new Intent(getApplicationContext(), Main4Activity.class);
                    i.putExtra("send", hb);
                    startActivity(i);

                } else {
                    internetUyari();
                }
            }
        });

        odamız.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (InternetKontrol()) {
                    hb = "2";
                    Intent i = new Intent(getApplicationContext(), Main4Activity.class);
                    i.putExtra("send", hb);
                    startActivity(i);

                } else {
                    internetUyari();
                }
            }
        });


        oda_brm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (InternetKontrol()) {

                    hb = "3";
                    Intent i = new Intent(getApplicationContext(), Main4Activity.class);
                    i.putExtra("send", hb);
                    startActivity(i);

                } else {
                    internetUyari();
                }
            }
        });


        oda_yayın.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (InternetKontrol()) {
                    hb = "4";
                    Intent i = new Intent(getApplicationContext(), Main4Activity.class);
                    i.putExtra("send", hb);
                    startActivity(i);

                } else {
                    internetUyari();
                }
            }
        });


        hizmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (InternetKontrol()) {
                    hb = "5";
                    Intent i = new Intent(getApplicationContext(), Main4Activity.class);
                    i.putExtra("send", hb);
                    startActivity(i);

                } else {
                    internetUyari();
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    //////////////////////////////////////////

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //geri tuşuna basılma durumunu yakalıyoruz
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(Main3Activity.this);

            alert.setTitle("Çıkmak İstediğinizden Emin Misiniz?");

            alert.setIcon(R.drawable.uyarrr);

            //evet seçilmesi durumunda yapılacak işlemler
            alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                    android.os.Process.killProcess(android.os.Process.myPid());
                    Main3Activity.super.onDestroy();

                }
            });
            alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    //hayır seçildiginde yapılacak işlemler
                }
            });

            alert.show();
        }
        return super.onKeyDown(keyCode, event);
    }


    public boolean InternetKontrol() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isAvailable() &&
                manager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    public void internetUyari() {

        if (InternetKontrol()) {


        } else {

            AlertDialog.Builder alert = new AlertDialog.Builder(Main3Activity.this);
            alert.setTitle("Lütfen İnternetinizi Açınız!");

            alert.setIcon(R.drawable.uyarrr);

            alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            alert.show();
        }

    }
}
