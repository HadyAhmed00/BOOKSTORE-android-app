package com.example.travel.HomeList;

import android.content.Intent;
import android.os.Bundle;

import com.example.travel.BookData.BookDatabase;
import com.example.travel.BookData.Delete;
import com.example.travel.Link.LinkDatabase;
import com.example.travel.Link.LinkModel;
import com.example.travel.R;
import com.example.travel.ShowList.ShowList;
import com.example.travel.ShowList.ShowListModel;
import com.example.travel.Users.SignIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements HomeListAdapter.onhomelisner {
    public static boolean fave;
    private LinkDatabase linkDatabase;
    private BookDatabase bookDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linkDatabase =new LinkDatabase(this);
        bookDatabase=new BookDatabase(this);
        RecyclerView homerec;
        homerec=findViewById(R.id.reclist);
        ArrayList<HomeListModel> catogrys=new ArrayList<>() ;
        //array list data
        catogrys.add(new HomeListModel("Sports" ,R.drawable.sports));
        catogrys.add(new HomeListModel("Life",R.drawable.life));
        catogrys.add(new HomeListModel("Love",R.drawable.love));
        catogrys.add(new HomeListModel("Time",R.drawable.time));
        catogrys.add(new HomeListModel("Favorite Books",R.drawable.favorite));
        catogrys.add(new HomeListModel("delete",R.drawable.delete));
        HomeListAdapter HomeListAdapter =new HomeListAdapter(catogrys,this);
        homerec.setLayoutManager(new GridLayoutManager(this,2));
        homerec.setAdapter(HomeListAdapter);
        homerec.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onhomeclik(int pos) {
        Intent intent;

        if (pos==0) {
            ShowList.data.clear();
            fave=false;
            ShowList.setUrl("https://run.mocky.io/v3/f393cb45-4081-4e33-a45e-c2966a90f2f8");
            intent = new Intent(this, ShowList.class);
            startActivity(intent);
        }
        else if (pos==1){
            ShowList.data.clear();
            fave=false;
            ShowList.setUrl("https://run.mocky.io/v3/b5adf54a-9e8a-47eb-a055-7ba3249aae9c");
            intent = new Intent(this, ShowList.class);
            startActivity(intent);
        }
        else if (pos==2){
            ShowList.data.clear();
            fave=false;
            ShowList.setUrl("https://run.mocky.io/v3/a1f58cce-5dc9-44e7-a5a8-1daa0824250e");
            intent = new Intent(this, ShowList.class);
            startActivity(intent);

        }
        else if (pos==3){
            ShowList.data.clear();
            fave=false;
            ShowList.setUrl("https://run.mocky.io/v3/e36cc68e-5e78-4f11-950b-fc6570bc5d03");
            intent = new Intent(this, ShowList.class);
            startActivity(intent);

        }
        else if (pos==4) {
            try {
                ShowList.data.clear();
                fave = true;
                ShowList.data = fav();
                intent = new Intent(this, ShowList.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
        else{
            intent = new Intent(this, Delete.class);
            startActivity(intent);
        }
    }

    private ArrayList<ShowListModel> fav(){
        ArrayList<ShowListModel>f=new ArrayList<>();
        ArrayList<LinkModel> linkModels=new ArrayList<>();
        linkModels= linkDatabase.getallLink();

        for (int i = 0; i < linkModels.size(); i++) {
            if(linkModels.get(i).getUsr_id() == SignIn.c_user_id) {
                    f.add(bookDatabase.getBookByid(linkModels.get(i).getBook_id()));
            }
        }

        return f;
    }


}