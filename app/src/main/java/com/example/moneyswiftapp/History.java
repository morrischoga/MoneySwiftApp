//package com.example.moneyswiftapp;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.example.moneyswiftapp.databinding.ActivityHistoryBinding;
//import com.example.moneyswiftapp.databinding.ActivityMainBinding;
//
//import java.util.ArrayList;
//
//public class History extends AppCompatActivity {
//
//
//    ActivityHistoryBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_history);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        String[] senders = {"Google","Amazom","WSPA","Tinashe","Malvern","Ghona"};
//        String date = "18 May";
//        int amount = 100;
//        int senderIcon = 10;
//
//
//        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
//
//        for (int i=0; i<senders.length;i++){
//            Transaction transaction = new Transaction(senders[i],"card",date,R.drawable.store,amount);
//        }
//
//        ListAdapter listAdapter = new ListAdapter(History.this,transactionArrayList);
//
//        binding.listview.setAdapter(listAdapter);
//        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
//
//
//
//
//
//    }
//}