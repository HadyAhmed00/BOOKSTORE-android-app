package com.example.travel.Link;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LinkDatabase extends SQLiteOpenHelper {


    static final public String table_Name="link_table";

    static final public String user_id_col_name="user_id";
    static final public String book_id_col_name ="book_id";

    private static final String create="CREATE TABLE "+table_Name+"("
            + user_id_col_name+" INTEGER not null  , "
            +book_id_col_name+" TEXT not null ) ";
    private static final int version=4;
    public LinkDatabase(@Nullable Context context ) {
        super(context ,table_Name, null, version);
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

    public boolean  insert(LinkModel model)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(user_id_col_name,model.getUsr_id());
        contentValues.put(book_id_col_name,model.getBook_id());
        long result = db.insert(table_Name,null,contentValues);
        //if there is a data inserted will return true  else will return false
        return result !=-1 ;
    }

    public ArrayList<String> getfavebooks (int aimid ){

        ArrayList<String> t=new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        String s[];
        Cursor cursor=db.rawQuery("SELECT * FROM "+ table_Name+" WHERE "+user_id_col_name +" = ?"
                ,s=new String[]{""+aimid});

        if(cursor.moveToFirst()){
            do{
               String string = cursor.getColumnName(cursor.getColumnIndex(book_id_col_name));
                t.add(string);
            }
            while (cursor.moveToNext());
        }
        else {
            t=null;
        }

        return t;
    }

    public ArrayList<LinkModel> getallLink()
    {
        ArrayList<LinkModel> listdata =new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ table_Name,null );
        if(cursor.moveToFirst()){
            do{

                LinkModel  d = new LinkModel(cursor.getInt(cursor.getColumnIndex(user_id_col_name)),
                        cursor.getString(cursor.getColumnIndex(book_id_col_name)));
                listdata.add(d);
            }
            while (cursor.moveToNext());
        }
        return listdata;
    }
    public boolean delete(LinkModel link ){

        SQLiteDatabase db=getWritableDatabase();
        String s[];
        int i = db.delete(table_Name,book_id_col_name+" = ? and "+ user_id_col_name +" =  ?"
                ,s=new String[]{link.getBook_id(),""+link.getUsr_id()});
        return i >0;

    }
}
