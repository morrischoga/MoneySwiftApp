package com.example.moneyswiftapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class CurrenciesFragment extends Fragment {
    ListView listView;





    public static CurrenciesFragment newInstance(String param1, String param2) {
        CurrenciesFragment fragment = new CurrenciesFragment();




        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view =  inflater.inflate(R.layout.fragment_currencies, container, false);
        listView = view.findViewById(R.id.currencieslistview);





        String[] currencies = {"pln","gbp","jpy","eur","aud","zar"};

        ArrayList<Currency> currencyArrayList = new ArrayList<>();
        LinkedHashMap<String,String> balance = new LinkedHashMap<>();


        balance.put("recordID",getArguments().getString("recordID"));

//        balance.put("PLN_balance",getArguments().getString("pln_balance"));
//        balance.put("GBP_balance",getArguments().getString("gbp_balance"));
//        balance.put("EUR_balance",getArguments().getString("eur_balance"));
//        balance.put("AUD_balance",getArguments().getString("aud_balance"));
//        balance.put("JPY_balance",getArguments().getString("jpy_balance"));
//        balance.put("ZAR_balance",getArguments().getString("zar_balance"));
//        balance.put("USD_balance",getArguments().getString("usd_balance"));



        int currencyicons[] = new int[]{R.drawable.pln,R.drawable.gbp,R.drawable.yan,R.drawable.euro,R.drawable.aud,R.drawable.rand};


        for (int i = 0; i<currencies.length;i++){


            Currency currency = new Currency(currencies[i],Float.parseFloat(getArguments().getString(currencies[i])),Float.parseFloat(getArguments().getString(currencies[i])),currencyicons[i]);


            currencyArrayList.add(currency);
        }

        CurrencyExchangeBaseAdapter currencyExchangeBaseAdapter = new CurrencyExchangeBaseAdapter(getContext(),currencyArrayList,balance,getArguments().getString("recordID"),getParentFragmentManager());
        listView.setAdapter(currencyExchangeBaseAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {





            }
        });





        return  view;
    }
}