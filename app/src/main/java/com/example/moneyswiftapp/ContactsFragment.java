package com.example.moneyswiftapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.LinkedHashMap;


public class ContactsFragment extends Fragment {
    ListView listView;



    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
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




        View view =  inflater.inflate(R.layout.fragment_contacts, container, false);
        listView = view.findViewById(R.id.contactlistview);
        Bundle args = new Bundle();
        String contacts[][] = new String[5][2];
        contacts[0][0] = "M1712524";
        contacts[0][1] = "Morris choga";
        contacts[1][0] = "M1712525";
        contacts[1][1] = "Tinashe Chirisa";
        contacts[2][0] = "M1712526";
        contacts[2][1] = "Omario Chris";
        contacts[3][0] = "M1712527";
        contacts[3][1] = "Tinashe karisa";
        contacts[4][0] = "M1712528";
        contacts[4][1] = "Malvern Cipango";



        ContactsAdapter Contactsadapter = new ContactsAdapter(getContext(),contacts);
        PaymentBalanceFragment paymentBalanceFragment = new PaymentBalanceFragment();


        listView.setAdapter(Contactsadapter);
        listView.setClickable(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                args.putString("objective",getArguments().getString("objective"));
                args.putString("id",contacts[position][0]);
                args.putString("name",contacts[position][1]);
                args.putString("balance",getArguments().getString("balance"));

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


                paymentBalanceFragment.setArguments(args);
                new FragmentController(paymentBalanceFragment,getParentFragmentManager()).replaceFragment();

            }
        });




        return view;
    }
}