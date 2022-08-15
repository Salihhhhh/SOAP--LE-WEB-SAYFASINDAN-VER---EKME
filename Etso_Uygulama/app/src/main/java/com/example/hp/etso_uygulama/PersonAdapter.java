package com.example.hp.etso_uygulama;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 25.6.2018.
 */

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(Context context, int resource, List<Person> items) {
            super(context, resource, items);

        }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_listview, null);
    }

        Person p = getItem(position);
        if (p != null) {
            TextView personName = (TextView) v.findViewById(R.id.textView_personname);

            if (personName != null) {
                personName.setText(p.getName());
            }
        }
        return v;
    }

}

