package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dataUser extends SQLiteOpenHelper {
    public dataUser(@Nullable Context context,
                    @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE user ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addUser(user user){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",user.getName());

        db.insert("user",null,values);
    }
    public List<user> getAll(){
        List<user>  list = new ArrayList<>();
        String sql="select *from user";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                user user = new user();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                list.add(user);
            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return  list;
    }
    public int removeUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        return db.delete("User", "ID =?",
                new String[] {String.valueOf(id)});
    }

}
