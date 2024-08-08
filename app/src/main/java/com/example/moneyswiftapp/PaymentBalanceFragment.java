package com.example.moneyswiftapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;


public class PaymentBalanceFragment extends Fragment {


    Button pay;
    BottomSheetDialog dialog;


    public static PaymentBalanceFragment newInstance(String param1, String param2) {
        PaymentBalanceFragment fragment = new PaymentBalanceFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_balance, container, false);
        TextView name = view.findViewById(R.id.pbname);
        TextView id = view.findViewById(R.id.pbid);
        EditText input = view.findViewById(R.id.editText);

        pay = view.findViewById(R.id.payorrequest_button);
        dialog = new BottomSheetDialog(getContext());

//        createDialog(am);


        name.setText(getArguments().getString("name"));
        id.setText(getArguments().getString("id"));


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(getArguments().getString("objective").equals("send")){
                    if ( Float.parseFloat(input.getText().toString()) > Float.parseFloat(getArguments().getString("balance")) ){
                        Toast.makeText(getContext(), "You have insufficient amount in your usd account", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        try {
                            createDialog(input);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        dialog.show();

                    }
                }
                else {
                    try {
                        createDialog(input);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    dialog.show();


                }




            }
        });

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        return view;


    }

    private void createDialog(EditText am) throws JSONException {

        View view = getLayoutInflater().inflate(R.layout.button_sheet, null,false);

        TextView sendOrReceive = view.findViewById(R.id.sendto);
        TextView id = view.findViewById(R.id.idvalue);
        TextView name = view.findViewById(R.id.naime);
        TextView amount = view.findViewById(R.id.textView16);

        Button sendOrRequest = view.findViewById(R.id.send);

        name.setText(getArguments().getString("name"));
        id.setText(getArguments().getString("id"));
        amount.setText(am.getText().toString());
        API api = new API(getContext(),getParentFragmentManager());
//        System.out.println(getArguments().getString("M1712525"));
//        System.out.println(getArguments().getString("recM1712525"));




        if(getArguments().getString("objective").equals("send")){
            sendOrReceive.setText("Send To");
            sendOrRequest.setText("Send");

            id.setText(getArguments().getString("id"));


            sendOrRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {


                        api.updateBalance(getArguments().getString("recordID"),Float.parseFloat(getArguments().getString("balance")) - Float.parseFloat(am.getText().toString()),null,null);
                        api.updateBalance(getArguments().getString("rec"+getArguments().getString("id")),Float.parseFloat(getArguments().getString(getArguments().getString("id"))) + Float.parseFloat(am.getText().toString()),null,null);
                        Toast.makeText(getContext(), "Money has been transferred successfully", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                    }
                }
            });







        }
        else if(getArguments().getString("objective").equals("receive")){
            sendOrReceive.setText("Receive From");
            sendOrRequest.setText("Request");
            sendOrRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Request sent!", Toast.LENGTH_SHORT).show();
                }
            });

        }


        dialog.setContentView(view);

    }
}