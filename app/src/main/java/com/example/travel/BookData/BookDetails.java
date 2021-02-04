package com.example.travel.BookData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel.Link.LinkDatabase;
import com.example.travel.Link.LinkModel;
import com.example.travel.R;
import com.example.travel.ShowList.ShowList;
import com.example.travel.ShowList.ShowListModel;
import com.example.travel.Users.SignIn;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookDetails extends AppCompatActivity {

    //variables
    private ShowListModel t;
    private BookDatabase bookDatabase;
    private LinkDatabase linkDatabase;
    private LinkModel linkModel;
    TextView t_name,t_discip;
    ImageView book_img;
    Button book_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //declaration
        linkDatabase =new LinkDatabase(this);
        bookDatabase =new BookDatabase(this);
        t= ShowList.curnt_Trip;

        book_button=findViewById(R.id.subment);
        t_name=findViewById(R.id.name_show);
        t_discip=findViewById(R.id.dis_book);
        book_img=findViewById(R.id.book_img);

        // fill the text views by data
        t_name.setText(t.getName());
        t_discip.setText(t.getDiscretion());
        Picasso.get().load(t.getImg_url()).into(book_img);

        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bookDatabase.insert(ShowList.curnt_Trip);
               linkModel=new LinkModel(SignIn.c_user_id, ShowList.curnt_Trip.getBook_id());
                ArrayList<LinkModel> linkModels=new ArrayList<>();
                linkModels= linkDatabase.getallLink();
                boolean chick = true;
                for (int i =0;i<linkModels.size();i++ )
                {
                    if (linkModels.get(i).getUsr_id()==linkModel.getUsr_id() && linkModels.get(i).getBook_id().equals(linkModel.getBook_id())){
                            chick=false;
                    }
                }
                if (chick) {
                    linkDatabase.insert(linkModel);
                    Toast.makeText(getBaseContext(),"You Added This Book to your Favorite List :)",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(),"This Book is Already Exist :)",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}