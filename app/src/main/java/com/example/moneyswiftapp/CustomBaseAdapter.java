package com.example.moneyswiftapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Transaction> transactionArrayList;
    LayoutInflater inflater;


    public CustomBaseAdapter(Context context,ArrayList<Transaction> transactionArrayList){
        this.context = context;
        this.transactionArrayList = transactionArrayList;
        inflater = LayoutInflater.from(context);



    }


    @Override
    public int getCount() {
        return transactionArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.history_item,null);
        ImageView senderIcon = view.findViewById(R.id.contactIcon);
        TextView senderName = view.findViewById(R.id.contactname);
        TextView date = view.findViewById(R.id.dateDetail);
        TextView amount = view.findViewById(R.id.amountDetail);


        senderIcon.setImageResource(transactionArrayList.get(i).senderIcon);
        senderName.setText(transactionArrayList.get(i).sender);
        date.setText(transactionArrayList.get(i).date);
        amount.setText(String.valueOf(transactionArrayList.get(i).amount));
        if (transactionArrayList.get(i).amount<0){
            amount.setTextColor(Color.RED);
        }



        return view;
    }
}
