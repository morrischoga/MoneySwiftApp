package com.example.moneyswiftapp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class WalletFragment extends Fragment {

    ArrayList barArraylist;
    ContactsFragment contactsFragment = new ContactsFragment();


    public WalletFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BarChart barChart = view.findViewById(R.id.barchart);
        getData();
        BarDataSet barDataSet = new BarDataSet(barArraylist,"Stats");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);



    }


    private void getData(){


        barArraylist = new ArrayList();
        barArraylist.add(new BarEntry(2f,10));
        barArraylist.add(new BarEntry(3f,2));
        barArraylist.add(new BarEntry(4f,30));
        barArraylist.add(new BarEntry(5f,40));
        barArraylist.add(new BarEntry(6f,50));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_wallet, container, false);
        Bundle args = new Bundle();

        TextView balance = view.findViewById(R.id.textView7);
        Button sendButton = view.findViewById(R.id.send_button);
        Button receiveButton = view.findViewById(R.id.receive_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                args.putString("objective","send");
                args.putString("balance",getArguments().getString("usd"));

                args.putString("usd",getArguments().getString("usd"));
                args.putString("M1712524",getArguments().getString("M1712524"));
                args.putString("M1712525",getArguments().getString("M1712525"));
                args.putString("M1712526",getArguments().getString("M1712526"));
                args.putString("M1712527",getArguments().getString("M1712527"));
                args.putString("M1712528",getArguments().getString("M1712528"));
                args.putString("recordID",getArguments().getString("recordID"));

                args.putString("recM1712524",getArguments().getString("recM1712524"));
                args.putString("recM1712525",getArguments().getString("recM1712525"));
                args.putString("recM1712526",getArguments().getString("recM1712526"));
                args.putString("recM1712527",getArguments().getString("recM1712527"));
                args.putString("recM1712528",getArguments().getString("recM1712528"));


                contactsFragment.setArguments(args);
                new FragmentController(contactsFragment,getParentFragmentManager()).replaceFragment();

            }
        });


        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                args.putString("objective","receive");
                contactsFragment.setArguments(args);
                new FragmentController(contactsFragment,getParentFragmentManager()).replaceFragment();

            }
        });


        if (getArguments()!=null){

            balance.setText("$"+getArguments().getString("usd"));



        }


        return view;





    }







}