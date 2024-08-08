package com.example.moneyswiftapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;

public class ContactsAdapter extends BaseAdapter {
    String[][] contacts;
    LayoutInflater inflater;

    public ContactsAdapter(Context context, String[][] contacts) {
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contacts.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.contact_item,null);
        TextView contactName = view.findViewById(R.id.contactname);



        contactName.setText(contacts[position][1]);



        return view;
    }
}
