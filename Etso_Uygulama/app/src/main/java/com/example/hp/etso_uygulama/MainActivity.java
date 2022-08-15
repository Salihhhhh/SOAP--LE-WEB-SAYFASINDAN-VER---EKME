package com.example.hp.etso_uygulama;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ////////////Yenileme işlemi için
    private SwipeRefreshLayout swipeRefreshLayout;


    //  Boolean bl=false;
    private ProgressDialog progressDialog;
    private String url, imgSrc = "";

    private ImageView imageView;
    private TextView textView;
    private TextView textView2;
    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    List<String> list3 = new ArrayList<String>();

    ////////////////////////////////////////////////////////////////////////////////////////
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ArrayList<Uyeler> uyeList;
    RecyclerView.LayoutManager mLayoutManager;
    private Button btnsol, btnsag;
    private String syfNo = "1";
    private TextView sayfa_sayi;

    ///////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///////Yenileme işleminin gerçekleştiği kod parçası
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.sw);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                uyeList.clear();
                url = "http://elazigtso.org.tr/parca/haber/haber.asp?HaberSayfa=" + syfNo;
                list1.clear();
                list2.clear();
                list3.clear();
                uyeList.clear();

                new VeriCek().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        sayfa_sayi = (TextView) findViewById(R.id.sayfa);
        btnsag = (Button) findViewById(R.id.btn_sag);
        btnsol = (Button) findViewById(R.id.btn_sol);

        url = "http://elazigtso.org.tr/parca/haber/haber.asp?HaberSayfa=" + syfNo;

        sayfa_sayi.setText(syfNo);

        btnsol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int deger = Integer.parseInt(syfNo);
                if (deger == 1) {

                } else {
                    deger--;
                }
                syfNo = String.valueOf(deger);
                syfNo += "";

                ///////////Ekranda gösterilen sayfa değeri ///////////////////
                sayfa_sayi.setText(syfNo);

                uyeList.clear();
                url = "http://elazigtso.org.tr/parca/haber/haber.asp?HaberSayfa=" + syfNo;
                list1.clear();
                list2.clear();
                list3.clear();
                uyeList.clear();
                new VeriCek().execute();

            }
        });


        btnsag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int deger = Integer.parseInt(syfNo);
                if (deger == 20) {

                } else {
                    deger++;
                }
                syfNo = String.valueOf(deger);
                syfNo += "";

                ///////////Ekranda gösterilen sayfa değeri ///////////////////
                sayfa_sayi.setText(syfNo);

                uyeList.clear();
                url = "http://elazigtso.org.tr/parca/haber/haber.asp?HaberSayfa=" + syfNo;
                list1.clear();
                list2.clear();
                list3.clear();
                uyeList.clear();
                new VeriCek().execute();


            }
        });


        imageView = (ImageView) findViewById(R.id.person_photo);
        textView2 = (TextView) findViewById(R.id.person_age);

        uyeList = new ArrayList<>();
        new VeriCek().execute();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void listeyiDoldur(ArrayList<Uyeler> uyeList) {

        try {

            for (int i = 0; i < 12; i++) {
                uyeList.add(new Uyeler(list1.get(i).toString(), list2.get(i).toString(), list3.get(i).toString()));

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HATAAAAAAA",e.getMessage());
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public class VeriCek extends AsyncTask<Void, Void, Void> {


        //Bitmap bitmap;
        String ss, as;
        String r;

        @Override
        protected Void doInBackground(Void... params) {

            try {

                Document doc = Jsoup.connect(url).timeout(30 * 1000).get();
                int j = 0;
                int sayac = 3;
                while (j < 36) {
                    sayac--;
                    Element links = doc.select("a").get(j);
                    as = links.attr("href");
                    if (sayac == 0) {
                        ss = links.text();
                        list1.add(as);
                        sayac = 3;
                    } else {

                    }
                    j++;
                }

                for (int i = 0; i < 12; i++) {
                    Element e = doc.select("b").get(i);
                    r = e.text();
                    //String aa=e.toString();
                    list2.add(r);
                }
                int k = 0;
                int sayac2 = 0;
                while (sayac2 < 12) {
                    Element img = doc.select("img").get(k);
                    imgSrc = img.absUrl("src").toString();
                    if (imgSrc.equals("http://elazigtso.org.tr/parca/haber/resimler/yeni.gif")) {

                    } else {
                        list3.add(imgSrc);
                        sayac2++;
                    }
                    k++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(MainActivity.this);
            // progressDialog.setTitle("HABERLER");
            progressDialog.setMessage("Yükleniyor..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            progressDialog.dismiss();

            listeyiDoldur(uyeList);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            itemAdapter = new ItemAdapter(uyeList);
            mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(itemAdapter);


        }
    }

}
