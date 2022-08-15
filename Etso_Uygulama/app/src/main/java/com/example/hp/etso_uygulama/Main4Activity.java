package com.example.hp.etso_uygulama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    private String gelen;
    private int a;
    private ListView listView;
    String[] liste;
    String[] listeId;

    ArrayList<Person> persons = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        listView = (ListView) findViewById(R.id.lst);

        Bundle bundle = getIntent().getExtras();
        gelen = bundle.getString("send");

        int sayı = Integer.parseInt(gelen);

        switch (sayı) {

            case 0:

                a = 1;

                Intent ii = new Intent(Main4Activity.this, MainActivity.class);


                startActivity(ii);
                finish();


                break;
            case 1:
                a = 0;
                listeId = new String[]{"102", "103"};
                //  liste=new String[]{"Oda Meclisi","Oda Yönetim Kurulu"};
                persons.add(new Person("Oda Meclisi"));
                persons.add(new Person("Oda Yönetim Kurulu"));


                break;
            case 2:
                a = 0;

                listeId = new String[]{"9", "59", "60", "61", "63", "64", "66", "67", "68", "70"};
                //   liste= new String[]{"Oda Organları", "Tarihçe", "Kalite Politikamız", "Vizyonumuz ve Misyonumuz"};

                persons.add(new Person("Genel Sekreterlik"));
                persons.add(new Person("Ticaret Sicil Müdürlüğü"));
                persons.add(new Person("Oda Sicil"));
                persons.add(new Person("Mali İşler"));
                persons.add(new Person("Piyasa İstihbarat"));
                persons.add(new Person("Sanayi Birimi"));
                persons.add(new Person("Basın ve Halkla İlişkiler "));
                persons.add(new Person("Kalite Yönetim Temsilcisi"));
                persons.add(new Person("Sanal Evrak Kayıt"));
                persons.add(new Person("Yardımcı Hizmetler"));

                break;

            case 3:

                a = 0;

                listeId = new String[]{"4", "39", "56", "57"};
                //    liste=new String[]{"Genel Sekreterlik","Ticaret Sicil Müdürlüğü","Oda Sicil","Mali işler","Piyasa istihbarat","Sanayi Birimi",
                //    "Basın ve Halkla İlişkiler","Kalite Yönetim Temsilcisi","Santral Evrak Kayıt","Yardımcı Hizmetler"};

                persons.add(new Person("Oda Organları"));
                persons.add(new Person("Tarihçe"));
                persons.add(new Person("Kalite  Politikamız"));
                persons.add(new Person("Vİzyonumuz ve Misyonumuz"));

                break;

            case 4:
                a = 0;
                listeId = new String[]{"27", "99", "100", "115"};
                //    liste=new String[]{"Faaliyet Raporları","Fizibilitie Raporları","Fırsatlar Şehri Elazığ",
                //          "Yönetici El Kitabı"};


                persons.add(new Person("Faaliyet Raporları"));
                persons.add(new Person("Fizibilite Raporları"));
                persons.add(new Person("Fırsatlar Şehri Elazığ"));
                persons.add(new Person("Yönetici El Kitabı"));


                break;

            case 5:
//114 hizmetler
                a = 1;

                String es = "114";
                Intent i = new Intent(Main4Activity.this, Main5Activity.class);
                i.putExtra("gönder", es);

                startActivity(i);
                finish();
                break;

        }
        if (a == 0) {

            PersonAdapter personAdapter = new PersonAdapter(this, R.layout.layout_listview, persons);

            //   ArrayAdapter<String> veriAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, liste);
            //   listView.setAdapter(veriAdapter);
            ListView listviewPerson = (ListView) findViewById(R.id.lst);
            if (listviewPerson != null) {
                listviewPerson.setAdapter(personAdapter);
            }

        } else {

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i =   new Intent(getApplicationContext(), Main5Activity.class);
                i.putExtra("gönder", listeId[position]);
                startActivity(i);
            }


        });
    }
}
