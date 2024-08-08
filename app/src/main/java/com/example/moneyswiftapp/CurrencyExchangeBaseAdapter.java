package com.example.moneyswiftapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CurrencyExchangeBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Currency> currencyArrayList;
    LayoutInflater inflater;
    String recordID;
    FragmentManager fragmentManager;
    LinkedHashMap<String,String> balance;





    public CurrencyExchangeBaseAdapter(Context context, ArrayList<Currency> currencyArrayList, LinkedHashMap<String,String> balance,String recordID,FragmentManager fragmentManager){
        this.context = context;
        this.balance = balance;
        this.recordID = recordID;
        this.fragmentManager = fragmentManager;
        this.currencyArrayList = currencyArrayList;
        inflater = LayoutInflater.from(context);



    }

    @Override
    public int getCount() {
        return currencyArrayList.size();
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
        view = inflater.inflate(R.layout.currencies_item,null);
        TextView  currencyB = view.findViewById(R.id.currency_b);
        TextView  buy = view.findViewById(R.id.textView11);
        TextView  sell = view.findViewById(R.id.aabc);
        TextView  buyprice = view.findViewById(R.id.buyprice);
        TextView  sellprice = view.findViewById(R.id.sellprice);
        ImageView currencyIconB = view.findViewById(R.id.currencyicon_b);
        buy.setText("BUY "+currencyArrayList.get(i).name);
        sell.setText("SELL "+currencyArrayList.get(i).name);
        buyprice.setText(String.valueOf(currencyArrayList.get(i).buy));
        sellprice.setText(String.valueOf(currencyArrayList.get(i).sell));
        currencyB.setText(currencyArrayList.get(i).name);
        currencyIconB.setImageResource(currencyArrayList.get(i).icon);
        CardView buyCard = view.findViewById(R.id.buycard);
        CardView sellCard = view.findViewById(R.id.sellcard);
        Bundle args = new Bundle();





        buyCard.setOnClickListener(new View.OnClickListener() {
            API api = new API(context,fragmentManager);
            @Override
            public void onClick(View view) {

                try {
                    api.exchange("You are buying " +currencyArrayList.get(i).name,currencyArrayList.get(i).name,recordID,String.valueOf(currencyArrayList.get(i).sell),currencyArrayList.get(i).name,currencyArrayList.get(i).name);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


//                args.putString("buying", "You are buying "+currencyArrayList.get(i).name);
//                args.putString("currency",currencyArrayList.get(i).name);
//                args.putString("price",String.valueOf(currencyArrayList.get(i).buy));
//                args.putString("recordID",recordID);

//                for (String currency: balance.keySet()) {
//
//                    args.putString(currency, balance.get(currency));
//
//
//                }
//                CurrencyExchangeFragment currencyExchangeFragment = new CurrencyExchangeFragment();
//                currencyExchangeFragment.setArguments(args);
//
//                new FragmentController(currencyExchangeFragment,fragmentManager).replaceFragment();

            }
        });

        sellCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API api = new API(context,fragmentManager);
                try {
                    api.exchange("You are selling " + currencyArrayList.get(i).name,currencyArrayList.get(i).name,recordID,String.valueOf(currencyArrayList.get(i).sell),currencyArrayList.get(i).name,"usd");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

//                args.putString("buying", "You are selling "+currencyArrayList.get(i).name);
//                args.putString("currency","USD");
//                args.putString("recordID",recordID);
//                args.putString("foreignCurrency",currencyArrayList.get(i).name);
//                args.putString("price",String.valueOf(currencyArrayList.get(i).sell));

//                for (String currency: balance.keySet()) {
//                    args.putString(currency, balance.get(currency));
//
//                }

//                CurrencyExchangeFragment currencyExchangeFragment = new CurrencyExchangeFragment();
//                currencyExchangeFragment.setArguments(args);

//                new FragmentController(currencyExchangeFragment,fragmentManager).replaceFragment();



            }
        });





        return view;
    }
}
