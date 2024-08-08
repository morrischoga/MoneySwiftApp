package com.example.moneyswiftapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class TransactionDetailsFragment extends Fragment {

    public static TransactionDetailsFragment newInstance(String param1, String param2) {
        TransactionDetailsFragment fragment = new TransactionDetailsFragment();
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


        View view = inflater.inflate(R.layout.fragment_transaction_details, container, false);
        TextView sender = view.findViewById(R.id.contactname);
        TextView amountDetail = view.findViewById(R.id.amountDetail);
        TextView monthDetail = view.findViewById(R.id.monthDetail);
        TextView dateDetail = view.findViewById(R.id.dateDetail);
        TextView accountID = view.findViewById(R.id.accountID);
        TextView senderName = view.findViewById(R.id.senderName);
        TextView transactionID = view.findViewById(R.id.transactionID);
        ImageView senderIconDetail  = view.findViewById(R.id.senderIconDetail);



        sender.setText(getArguments().getString("senderName"));
        amountDetail.setText(getArguments().getString("amount"));
        monthDetail.setText(getArguments().getString("date"));
        senderIconDetail.setImageResource(getArguments().getInt("sendericon"));





        return view;
    }
}