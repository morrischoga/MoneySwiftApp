package com.example.moneyswiftapp;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;

public class FragmentController {

    Fragment fragment;
    FragmentManager fragmentManager;

    public FragmentController(Fragment fragment,FragmentManager fragmentManager) {
        this.fragment = fragment;
        this.fragmentManager = fragmentManager;
    }

    public  void replaceFragment(){



        androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();

    }

}
