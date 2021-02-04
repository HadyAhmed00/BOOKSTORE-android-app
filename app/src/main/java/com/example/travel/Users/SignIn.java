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

import static android.widget.Toast.LENGTH_SHORT;

public class SignIn extends AppCompatActivity {
    String                semail,
                          spassword;

    EditText               email,
                           password;

    Button                 signinbtn;

    TextView               reset_password;

    UserDatabases         db;

    public static int     c_user_id;

    public static boolean isAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signinbtn= findViewById(R.id.signin_btn2);
        email=findViewById(R.id.email_text);
        password =findViewById(R.id.pass_text);
        reset_password =findViewById(R.id.resetPssword);
        db=new UserDatabases(this);

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_valid()==false){
                    Toast.makeText(getBaseContext(),"please enter your email and password",Toast.LENGTH_SHORT ).show();
                }
                else {
                Toast.makeText(getBaseContext(),"the  password has been reseted " , LENGTH_SHORT).show();
                }
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        Intent myIntent;
        semail =email.getText().toString();
        spassword=password.getText().toString();

        if(input_valid()==false){
            Toast.makeText(getBaseContext(),"please enter you email and password",Toast.LENGTH_SHORT ).show();
        }
        if(db.login(semail,spassword)!=-1){
            isAdmin=false;
            c_user_id = db.login(semail,spassword);
            myIntent = new Intent(this, Home.class);
            startActivityForResult(myIntent, 0);
            Toast.makeText(getBaseContext(), "Welcome mr " + db.getUserByid(db.login(semail,spassword)).name, LENGTH_SHORT).show();
        }
        else {
            isAdmin=false;
            Toast.makeText(getBaseContext(), "user not found" , LENGTH_SHORT).show();
        }
    }
    public boolean input_valid(){
        if (email.getText().toString().isEmpty()
                ||password.getText().toString().isEmpty())
        {
            return false;
        }
        else {
            return true;
        }
    }
}