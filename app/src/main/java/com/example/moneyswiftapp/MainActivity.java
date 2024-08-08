package com.example.moneyswiftapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moneyswiftapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        WalletFragment walletFragment =  new WalletFragment();
        String[] accountsIDs = new String[]{"M1712524","M1712525","M1712526","M1712527","M1712528",};


//
        Bundle args = new Bundle();
        args.putString("usd",getIntent().getStringExtra("usd"));
        args.putString("M1712524",getIntent().getStringExtra("M1712524"));
        args.putString("M1712525",getIntent().getStringExtra("M1712525"));
        args.putString("M1712526",getIntent().getStringExtra("M1712526"));
        args.putString("M1712527",getIntent().getStringExtra("M1712527"));
        args.putString("M1712528",getIntent().getStringExtra("M1712528"));


        args.putString("recM1712524",getIntent().getStringExtra("recM1712524"));
        args.putString("recM1712525",getIntent().getStringExtra("recM1712525"));
        args.putString("recM1712526",getIntent().getStringExtra("recM1712526"));
        args.putString("recM1712527",getIntent().getStringExtra("recM1712527"));
        args.putString("recM1712528",getIntent().getStringExtra("recM1712528"));


//
        args.putString("recordID",getIntent().getStringExtra("recordID"));


        walletFragment.setArguments(args);





        FragmentManager fragmentManager = getSupportFragmentManager();
        new FragmentController(walletFragment,fragmentManager).replaceFragment();

        replaceFragment(walletFragment);
        binding.bottomNavigationView2.setSelectedItemId(R.id.wallet_icon);
        API api = new API(getApplicationContext(),fragmentManager);


        binding.bottomNavigationView2.setOnItemReselectedListener(item -> {





            if (item.getItemId() == R.id.currencies_icon){



//                api.gbp_balance = getIntent().getStringExtra("gbp");
//                api.pln_balance = getIntent().getStringExtra("pln");
//                api.aud_balance = getIntent().getStringExtra("aud");
//                api.zar_balance = getIntent().getStringExtra("zar");
                api.usd_balance = getIntent().getStringExtra("usd");
//                api.jpy_balance = getIntent().getStringExtra("jpy");
//                api.eur_balance = getIntent().getStringExtra("eur");
                api.recordID = getIntent().getStringExtra("recordID");
                api.getCurrencies();
            }


            else if (item.getItemId() == R.id.wallet_icon){ new FragmentController(walletFragment,fragmentManager).replaceFragment();}


            else if (item.getItemId() == R.id.history_icon){new FragmentController(new HistoryFragment(),fragmentManager).replaceFragment();}




        });




    }


    public  void replaceFragment(Fragment fragment){


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();

    }


}