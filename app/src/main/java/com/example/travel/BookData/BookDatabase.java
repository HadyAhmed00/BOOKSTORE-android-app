package com.example.travel.BookData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.travel.ShowList.ShowListModel;

import java.util.ArrayList;

public class BookDatabase extends SQLiteOpenHelper {
    static final public String table_Name="book_data";

    static final public String id_book_col_name="book_id";
    static final public String name_book_col_name="titel";
    static final public String other_name_book_col_name="othre_name";
    static final public String img_url_book_col_name="imge_url";
    static final public String description_book_col_name ="book_description";
    //static final public String page_number_book_col_name ="page_number";
    //static final public String rate_book_col_name ="rate";

    private static final String create="CREATE TABLE "+table_Name+"("
            +id_book_col_name +" TEXT PRIMARY KEY  , "
            +name_book_col_name+" TEXT not null ,"
            + other_name_book_col_name+" TEXT not null, "
            +img_url_book_col_name+" TEXT not null, "
            +description_book_col_name+" TEXT not null ); ";




    private static int version =6;
    public BookDatabase(@Nullable Context context) {
        super(context, table_Name, null  , version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_Name);
        onCreate(db);
    }

    //inserting a fave book

    public boolean  insert(ShowListModel book)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(id_book_col_name,book.getBook_id());
        contentValues.put(name_book_col_name,book.getName());
        contentValues.put(other_name_book_col_name,book.getOuther_name());
        contentValues.put(img_url_book_col_name,book.getImg_url());
        contentValues.put(description_book_col_name,book.getDiscretion());
        //contentValues.put(page_number_book_col_name,book.getDiscretion());
       // contentValues.put(rate_book_col_name,book.getDiscretion());

        long result = db.insert(table_Name,null,contentValues);
        //if there is a data inserted will return true  else will return false
        return result !=-1 ;
    }

    public ShowListModel getBookByid(String aimid ){

        ShowListModel d;
        SQLiteDatabase db= getReadableDatabase();
        String s[];
        Cursor cursor=db.rawQuery("SELECT * FROM "+ table_Name+" WHERE "+id_book_col_name +" = ?",
                s=new String[]{aimid});

        if(cursor.moveToFirst()) {
            d = new ShowListModel(cursor.getString(cursor.getColumnIndex(id_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(name_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(other_name_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(img_url_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(description_book_col_name)));
           // d.setPage_number(cursor.getInt(cursor.getColumnIndex(page_number_book_col_name)));
           // d.setPage_number(cursor.getInt(cursor.getColumnIndex(rate_book_col_name)));
        }
        else{
            d=null;
        }
        return d;
    }

    public ArrayList<ShowListModel> getallbooks()
    {
        ArrayList<ShowListModel> ShowListModel =new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ table_Name,null );
        if(cursor.moveToFirst()){
            do{

                ShowListModel d = new ShowListModel(cursor.getString(cursor.getColumnIndex(id_book_col_name)),
                        cursor.getString(cursor.getColumnIndex(name_book_col_name)),
                        cursor.getString(cursor.getColumnIndex(other_name_book_col_name)),
                        cursor.getString(cursor.getColumnIndex(img_url_book_col_name)),
                        cursor.getString(cursor.getColumnIndex(description_book_col_name)));
              //  d.setPage_number(cursor.getInt(cursor.getColumnIndex(page_number_book_col_name)));
              //  d.setPage_number(cursor.getInt(cursor.getColumnIndex(rate_book_col_name)));
                ShowListModel.add(d);
            }
            while (cursor.moveToNext());
        }
        return ShowListModel;
    }
    public ShowListModel getBookbyname(String aimid ){

        ShowListModel d;
        SQLiteDatabase db= getReadableDatabase();
        String s[];
        Cursor cursor=db.rawQuery("SELECT * FROM "+ table_Name+" WHERE "+name_book_col_name +" = ?",
                s=new String[]{aimid});

        if(cursor.moveToFirst()) {
            d = new ShowListModel(cursor.getString(cursor.getColumnIndex(id_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(name_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(other_name_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(img_url_book_col_name)),
                    cursor.getString(cursor.getColumnIndex(description_book_col_name)));
          //  d.setPage_number(cursor.getInt(cursor.getColumnIndex(page_number_book_col_name)));
           // d.setPage_number(cursor.getInt(cursor.getColumnIndex(rate_book_col_name)));
        }
        else{
            d=null;
        }
        return d;
    }
}
