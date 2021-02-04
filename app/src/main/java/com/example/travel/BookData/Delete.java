package com.example.travel.BookData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travel.Link.LinkDatabase;
import com.example.travel.Link.LinkModel;
import com.example.travel.R;
import com.example.travel.ShowList.ShowListModel;
import com.example.travel.Users.SignIn;

public class Delete extends AppCompatActivity {

    EditText  delete ;
    Button    delete_btn;
    private   LinkDatabase linkDatabase;
    private   BookDatabase bookDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        linkDatabase =new LinkDatabase(this);
        bookDatabase=new BookDatabase(this);

        delete =findViewById(R.id.delete_fild);
        delete_btn=findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String  name = delete.getText().toString();
               delete(name);
            }
        });

    }
    private void delete(String name) {
        try {
            ShowListModel l = bookDatabase.getBookbyname(name);
            LinkModel linkModel = new LinkModel(SignIn.c_user_id, l.getBook_id());
            boolean b = linkDatabase.delete(linkModel);
            Toast.makeText(getBaseContext(),name +" Book Is Deleted Successfully ",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), name+ " Book Is Not Exist", Toast.LENGTH_SHORT).show();
        }

    }
}