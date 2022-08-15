package com.example.hp.etso_uygulama;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by hp on 12.6.2018.
 */

public class Main2Activity extends Activity {

    private String url = "";

    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        Bundle bundle = getIntent().getExtras();
        String gelen = bundle.getString("mail");

        ////////////////////Ayrıştırma yaapılan kısımmm///////////////////7

        String aa[] = gelen.split("id=");
        String ff = aa[1];
        String dd[] = ff.split("&");


        url = "http://www.elazigtso.org.tr/?Git=HaberOku&id=" + dd[0];

        webView = (WebView) findViewById(R.id.webView_id);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setDefaultTextEncodingName("utf-8");

        new DetayAc().execute();


    }

    private class DetayAc extends AsyncTask<Void, Void, Void> {


        String veri = "";
        Document doc = null;


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog = new ProgressDialog(Main2Activity.this);
            progressDialog.setMessage("Yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }


        @Override
        protected Void doInBackground(Void... params) {

            try {
                doc = Jsoup.connect(url).timeout(30 * 1000).get();

                Element style = doc.head();
                Elements haber_detay = doc.select("p");


                veri += style;

                Element element;

                for (int i = 0; i < haber_detay.size(); i++) {

                    if (haber_detay.get(i).equals(null)) {


                    } else {

                        element = haber_detay.get(i);

                        if (element.toString().contains("img")) {

                        } else {

                            veri += element.outerHtml();
                            veri += "<br>";
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ////////// WebViev Yazı Stili Değiştirmek İçin Kullanıldı.  ////////////////////////////////

            String text = "<html><head>"
                    + "<style type=\"text/css\">body{color: #000000; background-color: #ffffff; font-size: 10%}"
                    + "</style></head>"
                    + "<body>"
                    + veri
                    + "</body></html>";


            webView.loadDataWithBaseURL(null, text, "text/html", "UTF-8", null);
            progressDialog.dismiss();

        }
    }

}
