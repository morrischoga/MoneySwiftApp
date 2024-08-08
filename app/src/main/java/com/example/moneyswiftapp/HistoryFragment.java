package com.example.moneyswiftapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moneyswiftapp.databinding.ActivityHistoryBinding;
import com.example.moneyswiftapp.databinding.FragmentHistoryBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {



    FragmentHistoryBinding binding;
    ListView listView;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        listView = view.findViewById(R.id.listview);

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = view.findViewById(R.id.listview);

        String[] senders = {"Google","Amazom","WSPA","Tinashe","Malvern","Ghona","BBBBB","CCCC","DDDD"};

        String[] currenciesIcon = {};

        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        Intent intent = new Intent(getContext(),TransactionDetailsFragment.class);

        for (int i = 0; i<senders.length;i++){
            Transaction transaction = new Transaction(senders[i],"card","15 June",R.drawable.store,-20);
            transactionArrayList.add(transaction);
        }

        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getContext(),transactionArrayList);



        listView.setAdapter(customBaseAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle args = new Bundle();
                args.putString("senderName",transactionArrayList.get(i).sender);

                args.putString("date",transactionArrayList.get(i).date);
                args.putInt("amount",transactionArrayList.get(i).amount);
                args.putInt("sendericon",transactionArrayList.get(i).senderIcon);


                TransactionDetailsFragment transactionDetailsFragment = new TransactionDetailsFragment();
                transactionDetailsFragment.setArguments(args);

                new FragmentController(transactionDetailsFragment,getParentFragmentManager()).replaceFragment();

            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}