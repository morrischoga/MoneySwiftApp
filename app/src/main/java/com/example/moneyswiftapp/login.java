package com.example.moneyswiftapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;


public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText id = findViewById(R.id.user_id);
        EditText password = findViewById(R.id.user_password);

        Button loginButton = findViewById(R.id.button);
        API api = new API(getApplicationContext(),null);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                api.userAuthentication(id.getText().toString(),password.getText().toString());
//                startActivity(new Intent(login.this,MainActivity.class));
            }
        });









    }
}