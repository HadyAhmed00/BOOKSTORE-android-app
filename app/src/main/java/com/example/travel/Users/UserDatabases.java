package com.example.travel.Users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDatabases extends SQLiteOpenHelper {


    public static final String  tableName ="USERS";

    public static final String  id_col_name="id";
    public static final String  name_col_name="name";
    public static final String  email_col_name="email";
    public static final String  phone_col_name="phone";
    public static final String  password_col_name="password";
    public static final String  birthdate_col_name="birthdate";

    public static final int version =1;

    private static final String create="CREATE TABLE "+tableName+"("
            +id_col_name +" INTEGER PRIMARY KEY AUTOINCREMENT , "
            + name_col_name+" TEXT not null, "
            +birthdate_col_name+" TEXT not null, "
            +phone_col_name+" TEXT not null, "
            +email_col_name+" TEXT not null, "
            + password_col_name+" TEXT not null);";

    public UserDatabases(@Nullable Context context) {
        super(context, tableName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }

    public boolean  insert(Users user)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name_col_name,user.getName());
        contentValues.put(birthdate_col_name,user.getB_date());
        contentValues.put(phone_col_name,user.phone);
        contentValues.put(email_col_name,user.getEmail());
        contentValues.put(password_col_name,user.password);
        long result = db.insert(tableName,null,contentValues);
        //if there is a data inserted will return true  else will return false
        return result !=-1 ;
    }
    public boolean Update (Users user ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name_col_name,user.getName());
        contentValues.put(birthdate_col_name,user.getB_date());
        contentValues.put(phone_col_name,user.phone);
        contentValues.put(email_col_name,user.getEmail());
        contentValues.put(password_col_name,user.password);
        String s[];
        int i =db.update(tableName,contentValues
                ,id_col_name+" =?"
                ,s= new String[]{String.valueOf(user.getId())});
        //if there is a update then will return true
        // else will return false
        return i > 0;
    }
    public boolean delete(Users user ){
        SQLiteDatabase db=getWritableDatabase();
        String s[];
        int i = db.delete(tableName
                ,id_col_name+" = ?"
                ,s=new String[]{String.valueOf(user.getId())});
        return i >0;

    }

    public  ArrayList<Users> getAllusers()
    {
        ArrayList<Users> users=new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ tableName
                ,null );
        //to chick whither the table have values or not
        if(cursor.moveToFirst()){
            do{
                Users u=new Users(cursor.getInt(cursor.getColumnIndex(id_col_name)),
                        cursor.getString(cursor.getColumnIndex(name_col_name)),
                        cursor.getString(cursor.getColumnIndex(birthdate_col_name)),
                        cursor.getString(cursor.getColumnIndex(phone_col_name)),
                        cursor.getString(cursor.getColumnIndex(email_col_name)),
                        cursor.getString(cursor.getColumnIndex(password_col_name)));
                users.add(u);
            }
            while (cursor.moveToNext());
        }
        else {

        }
        return users;
    }
    public Users getUserByid( int aimid ){

        Users u;
        SQLiteDatabase db= getReadableDatabase();
        String s[];
        Cursor cursor=db.rawQuery("SELECT * FROM "+ tableName+" WHERE "+id_col_name +" = ?"

                ,s=new String[]{""+aimid});
        if(cursor.moveToFirst()) {
            u = new Users(cursor.getInt(cursor.getColumnIndex(id_col_name)),
                    cursor.getString(cursor.getColumnIndex(name_col_name)),
                    cursor.getString(cursor.getColumnIndex(birthdate_col_name)),
                    cursor.getString(cursor.getColumnIndex(phone_col_name)),
                    cursor.getString(cursor.getColumnIndex(email_col_name)),
                    cursor.getString(cursor.getColumnIndex(password_col_name)));
        }
        else{
            u=null;
        }
        return u;
    }

    public int login(String email,String passwoed){
        ArrayList<Users> Allusers= getAllusers();
        int userid=-1;
        for (int i =0;i<Allusers.size();i++){
            if (email.equals(Allusers.get(i).email)&&passwoed.equals(Allusers.get(i).password))
            {
                userid = Allusers.get(i).id;
                break;
            }
            else {
                userid=-1;
            }

        }
        return userid;
    }


}
