package com.example.hp.etso_uygulama;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main5Activity extends AppCompatActivity {

    private WebView webView;
    private String url, okunacak;

    private ProgressDialog progressDialog;
    private String gelenId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        webView = (WebView) findViewById(R.id.webView_id);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setDefaultTextEncodingName("utf-8");


        Bundle bundle = getIntent().getExtras();
        gelenId = bundle.getString("gönder");

        if (gelenId.equals("102") || gelenId.equals("103")) {

            okunacak = "FONT";
        } else if (gelenId.equals("27")) {

            okunacak = "ul";
        } else if (gelenId.equals("21")) {

            okunacak = "td a";
        } else if (gelenId.equals("114")) {

            okunacak = "ul";
        } else {
            okunacak = "p";
        }

        url = "http://www.elazigtso.org.tr/?Git=VeriOku&id=" + gelenId;
        new VeriGetir().execute();

    }


    //////////////////////////////////////////////////////////////////////////////////////////////

    private class VeriGetir extends AsyncTask<Void, Void, Void> {


        String data = "";


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog = new ProgressDialog(Main5Activity.this);
            progressDialog.setMessage("Yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }


        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect(url).timeout(30 * 1000).get();
                Elements elements = doc.select(okunacak);
                Element as = doc.head();
                data += as;
                Element element;
                for (int i = 0; i < elements.size(); i++) {
                    element = elements.get(i);

                    String aa = element.toString();
                    String dizi[] = aa.split("<");
                    String ss = dizi[2];
                    String dizi2[] = ss.split(">");
                    String zz = dizi2[0];

                    String ye[] = aa.split(">");
                    String y2 = ye[1];
                    String rr[] = y2.split("<");
                    String so = rr[0];


                    if (zz.equals("br") || so.equals("&nbsp;")) {

                    } else {
                        data += element.outerHtml();
                        data += "<br>";
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

            String text = "<html><head>"
                    + "<style type=\"text/css\">strong,b{color: #000000}"
                    + "body{color: #000000; background-color: #ffffff; font-size: 90%}"
                    + "</style></head>"
                    + "<body>"
                    + data
                    + "</body></html>";

            ////  body{color: #000000; background-color: #808080; font-size: 90%}
            /////# 908f8f   808080  /// Gri Renk Kodu

            webView.loadDataWithBaseURL(null, text, "text/html", "UTF-8", null);
            progressDialog.dismiss();

        }
    }
}
