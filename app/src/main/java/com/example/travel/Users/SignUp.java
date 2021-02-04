package com.example.travel.Users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel.HomeList.Home;
import com.example.travel.R;

public class SignUp extends AppCompatActivity {
    private UserDatabases db;

    Users                 newUser;

    private static int    id =0;

    private String        name,
                          phone,
                          b_date,
                          email,
                          password;

    private EditText      tname,
                          tdate,
                          tphone,
                          temail,
                          tpasswoord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db =new UserDatabases(this);

        Button sinup=findViewById(R.id.sinupBtn);
        TextView sinin=findViewById(R.id.signintext);
        tname=findViewById(R.id.singup_neme);
        tdate=findViewById(R.id.singup_date);
        tphone=findViewById(R.id.singup_phone);
        temail=findViewById(R.id.singup_email);
        tpasswoord=findViewById(R.id.singup_password);

        sinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                myIntent = new Intent(v.getContext(), SignIn.class);
                startActivityForResult(myIntent, 0);
            }
        });
        sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_new_user();
            }
        });

    }


    public void insert_new_user()
    {
        name=tname.getText().toString();
        b_date=tdate.getText().toString();
        phone=tphone.getText().toString();
        email =temail.getText().toString();
        password=tpasswoord.getText().toString();
        if (name.isEmpty()
                || b_date.isEmpty()
                || phone.isEmpty()
                || email.isEmpty()
                || password.isEmpty())
        {
            Toast.makeText(getBaseContext(),"Please Enter All Fields",Toast.LENGTH_LONG ).show();
        }
        else {
            newUser = new Users(name, b_date, phone, email, password);
            boolean insert= db.insert(newUser);
            if (insert) {
                Intent myIntent = new Intent(this, SignIn.class);
                startActivityForResult(myIntent, 0);
                Toast.makeText(getBaseContext(), "you have registered successfully mr " + name , Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getBaseContext(), "data lose " + name, Toast.LENGTH_LONG).show();
            }
        }
    }
}