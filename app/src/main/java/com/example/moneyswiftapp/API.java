package com.example.moneyswiftapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class API {

    String accountsURL = "https://api.airtable.com/v0/appoPo5Tmgyb2wecv/tblybQssUBwGe4X9k";
    String key = "patt7LIbltAkrn7PE.a8d09e091241a0910f46c86701b3b59112b875e552eef519aa1000292b49724a";
    Context context;
    RequestQueue requestQueue;
    FragmentManager fragmentManager;
    String  pln_balance,gbp_balance,aud_balance,eur_balance,zar_balance,usd_balance,jpy_balance,recordID;

    public API(Context context,FragmentManager fragmentManager){
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.requestQueue = Volley.newRequestQueue(context);


    }


    public  boolean userAuthentication(String userID, String pass){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(accountsURL,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray accounts = response.getJSONArray("records");
                    boolean userExists = false;

                    for (int a=0; a<accounts.length(); a++){

                        JSONObject account = (JSONObject) accounts.get(a);
                        JSONObject accountFields = (JSONObject) account.get("fields");

                        String id = (String) accountFields.get("id");
                        String password = (String) accountFields.get("password");
                        String recordID = (String) account.get("id");
                        String[] accountsIDs = new String[]{"M1712524","M1712525","M1712526","M1712527","M1712528"};





//                        if (true){
                        if (userID.equals(id)){
                            userExists = true;
                            Intent intent = new Intent(context,MainActivity.class);


                            intent.putExtra("usd",String.valueOf(Float.parseFloat(accountFields.getString("usd"))));


                            intent.putExtra("recordID",recordID);

                            for (int x=0; x<accounts.length(); x++){

                                /////////////////////////////////////////////////////////
                                JSONObject balance = (JSONObject) accounts.get(x);
                                JSONObject fields = (JSONObject) balance.get("fields");
                                String recID = balance.getString("id");
                                intent.putExtra(fields.getString("id"),String.valueOf(Float.parseFloat(fields.get("usd").toString())));
                                intent.putExtra("rec"+fields.getString("id"),recID);







                            }



//                            if (true){ startActivity(context,intent,null);} else{
                            if (pass.equals(password)){ startActivity(context,intent,null);} else{

                                Toast.makeText(context, "Password incorrect", Toast.LENGTH_SHORT).show();}

                        }


                    }
                    if (userExists==false){Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show();}


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + key);
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);





        return true;

    }


    public void getCurrencies(){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://api.freecurrencyapi.com/v1/latest?apikey=FujDg96sWDGEri64cmeUq59AD3u5cspLgOF9FhSr",  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Bundle args = new Bundle();
                CurrenciesFragment currenciesFragment =  new CurrenciesFragment();



                try {
                    JSONObject currencies = response.getJSONObject("data");

                    String pln = currencies.getString("PLN");
                    String gbp = currencies.getString("GBP");
                    String euro = currencies.getString("EUR");
                    String aud = currencies.getString("AUD");
                    String jpy = currencies.getString("JPY");
                    String zar = currencies.getString("ZAR");

                    args.putString("recordID", recordID);


                    args.putString("pln", pln);
                    args.putString("gbp", gbp);
                    args.putString("eur", euro);
                    args.putString("aud", aud);
                    args.putString("jpy", jpy);
                    args.putString("zar", zar);

//                    args.putString("pln_balance", pln_balance);
//                    args.putString("gbp_balance", gbp_balance);
//                    args.putString("eur_balance", eur_balance);
//                    args.putString("aud_balance", aud_balance);
//                    args.putString("jpy_balance", jpy_balance);
//                    args.putString("zar_balance", zar_balance);
//                    args.putString("usd_balance", usd_balance);



                    currenciesFragment.setArguments(args);

                    new FragmentController(currenciesFragment,fragmentManager).replaceFragment();






                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });

        requestQueue.add(jsonObjectRequest);


    }

    public void updateBalance(String recordID,Float usd, String foreign, Float balance) throws JSONException {




        JSONObject body = new JSONObject();
        JSONObject fields = new JSONObject();

        if (foreign==null){

            fields.put("usd", usd);


            body.put("fields",fields);
            String requestBody = body.toString();
        }
        else{
            fields.put("usd", usd);
            fields.put(foreign, balance);
            body.put("fields",fields);
            String requestBody = body.toString();
        }








        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH,accountsURL+"/"+recordID, body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("this is an error "+error.toString());

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + key);
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);


    }

    public void exchange(String byingOrSelling,String currency,String recordID,String price,String foreignCurrency,String currencyToBeReceived) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("byingOrSelling",byingOrSelling);
        data.put("currency",currency);
        data.put("recordID",recordID);
        data.put("price",price);
        data.put("foreignCurrency",foreignCurrency);
        data.put("currencyToBeReceived",currencyToBeReceived);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,accountsURL, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray accounts = response.getJSONArray("records");

                    for (int a=0; a<accounts.length(); a++){
                        Bundle args = new Bundle();
                        JSONObject account = (JSONObject) accounts.get(a);


                        if (recordID.equals(account.get("id"))){
                            JSONObject accountFields = (JSONObject) account.get("fields");

                            String usd = accountFields.get("usd").toString();
                            String foreign = accountFields.get(data.getString("foreignCurrency")).toString();



                            args.putString("byingOrSelling",data.getString("byingOrSelling"));
                            args.putString("currency",data.getString("currency"));
                            args.putString("recordID",data.getString("recordID"));
                            args.putString("foreignCurrency",data.getString("foreignCurrency"));
                            args.putString("price",data.getString("price"));
                            args.putString("currencyToBeReceived",data.getString("currencyToBeReceived"));
                            args.putString("usdBalance",usd);
                            args.putString("foreignBalance",foreign);






                            CurrencyExchangeFragment currencyExchangeFragment = new CurrencyExchangeFragment();
                            currencyExchangeFragment.setArguments(args);
                            new FragmentController(currencyExchangeFragment,fragmentManager).replaceFragment();


                        }


                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + key);
                return headers;
            }
        };




        requestQueue.add(jsonObjectRequest);


    }


//    public void alterBalance(){
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH,accountsURL,  new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                try {
//                    JSONArray accounts = response.getJSONArray("records");
//
//
//
//
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println(error);
//
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization", "Bearer " + key);
//                return headers;
//            }
//        };
//
//        requestQueue.add(jsonObjectRequest);
//
//    }







}

