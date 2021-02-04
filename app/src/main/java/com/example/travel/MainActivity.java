package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.travel.Users.SignIn;
import com.example.travel.Users.SignUp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sginin = findViewById(R.id.singin_btn);

        sginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //the action
                Intent myIntent = new Intent(v.getContext(), SignIn.class);
                startActivityForResult(myIntent, 0);
            }
        });
        TextView sigup = findViewById(R.id.sigup_text);
        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), SignUp.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }



}