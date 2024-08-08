package com.example.moneyswiftapp;

public class Transaction {

    String sender,paymentMethod,date;
    int amount,senderIcon;

    public Transaction(String sender, String paymentMethod, String date,int senderIcon, int amount) {
        this.sender = sender;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.senderIcon = senderIcon;
        this.amount = amount;
    }
}
