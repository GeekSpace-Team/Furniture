package com.geekspace.a3decommerce.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavDB extends SQLiteOpenHelper {
    private static final String DB_NAME="db_name";
    private static final String TB_NAME="tb_name";
    public FavDB(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TB_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,product_id TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
        onCreate(db);
    }

    public boolean insertData(String product_id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("product_id",product_id);
        long result=db.insert(TB_NAME,null,values);
        if(result==-1){
            return false;
        } else{
            return true;
        }
    }

    public Cursor getAll(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TB_NAME, null);
        return cursor;
    }

    public Cursor getSelect(String product_id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TB_NAME+" WHERE product_id = '"+product_id+"'", null);
        return cursor;
    }

    public boolean updateData(String product_id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("product_id",product_id);
        db.update(TB_NAME,values,"product_id=?",new String[]{product_id});
        return true;
    }


    public Integer deleteData(String product_id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TB_NAME,"product_id=?",new String[]{product_id});

    }

    public void truncate(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
        onCreate(db);
    }
}
