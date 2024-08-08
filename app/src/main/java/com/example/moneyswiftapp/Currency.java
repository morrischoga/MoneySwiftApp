package com.example.moneyswiftapp;

public class Currency {

    String name;
    int icon;
    float buy,sell;


    public Currency(String name, float buy, float sell, int icon) {
        this.name = name;
        this.buy = buy;
        this.sell = sell;
        this.icon = icon;
    }
}
