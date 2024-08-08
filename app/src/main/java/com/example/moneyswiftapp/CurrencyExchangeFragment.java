package com.example.moneyswiftapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrencyExchangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrencyExchangeFragment extends Fragment {

    TextInputEditText input;



    public CurrencyExchangeFragment() {
        // Required empty public constructor
    }


    public static CurrencyExchangeFragment newInstance(String param1, String param2) {
        CurrencyExchangeFragment fragment = new CurrencyExchangeFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        API api = new API(getContext(),null);
        View view =  inflater.inflate(R.layout.fragment_currency_exchange, container, false);
        Button proceedButton = view.findViewById(R.id.currency_exchange_button);
        TextView buyOrSell = view.findViewById(R.id.buyorsell);
        TextView currency = view.findViewById(R.id.currency);
        TextView amount = view.findViewById(R.id.amount);
        input = view.findViewById(R.id.input);



        if (getArguments().getString("byingOrSelling")!=null){


            buyOrSell.setText(getArguments().getString("byingOrSelling"));
        }


//        currency.setText(getArguments().getString("currency"));
        currency.setText(getArguments().getString("currencyToBeReceived").toUpperCase());


        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buyOrSell.getText().toString().startsWith("You are buying")){

                    if (input.getText().toString().isEmpty()){Toast.makeText(getContext(), "Enter an amount", Toast.LENGTH_SHORT).show();}

                    else if(Float.parseFloat(input.getText().toString()) > Float.parseFloat(getArguments().getString("usdBalance"))){
                        Toast.makeText(getContext(), "You have insufficient amount in your USD account", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getContext(), "Successfully exchanged", Toast.LENGTH_SHORT).show();


                        Float currentUSDBalance = Float.parseFloat(getArguments().getString("usdBalance")) - Float.parseFloat(input.getText().toString());
                        Float currentForeignBalance = Float.parseFloat(getArguments().getString("foreignBalance")) + Float.parseFloat(amount.getText().toString());


                        try {
                            api.updateBalance(getArguments().getString("recordID"),currentUSDBalance,getArguments().getString("currency"),currentForeignBalance);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }

                }

                else if (buyOrSell.getText().toString().startsWith("You are selling")) {


                    if (input.getText().toString().isEmpty()){Toast.makeText(getContext(), "Enter an amount", Toast.LENGTH_SHORT).show();}

                    else if(Float.parseFloat(input.getText().toString()) > Float.parseFloat(getArguments().getString("foreignBalance"))){
                        Toast.makeText(getContext(), "You have insufficient amount in your "+ getArguments().getString("foreignCurrency") +" account", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getContext(), "Successfully exchanged", Toast.LENGTH_SHORT).show();
                        System.out.println(getArguments().getString("foreignBalance"));

                        Float currentForeignBalance = Float.parseFloat(getArguments().getString("foreignBalance")) - Float.parseFloat(input.getText().toString());
                        Float currentUSDBalance = Float.parseFloat(getArguments().getString("usdBalance")) + Float.parseFloat(amount.getText().toString());

                        try {
                            api.updateBalance(getArguments().getString("recordID"),currentUSDBalance,getArguments().getString("foreignCurrency"),currentForeignBalance);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }

                }


            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                amount.setText(String.valueOf(Integer.parseInt(input.getText().toString()) ));

                if (!input.getText().toString().isEmpty()){

                    if (buyOrSell.getText().toString().startsWith("You are buying")) {
                        amount.setText(String.valueOf(Float.parseFloat(input.getText().toString()) * Float.parseFloat(getArguments().getString("price")) ));

                    }else{
                        amount.setText(String.valueOf(Float.parseFloat(input.getText().toString()) / Float.parseFloat(getArguments().getString("price")) ));
                    }



                }






            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        return view;
    }
}