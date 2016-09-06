package com.atto.developers.atto.manager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.atto.developers.atto.MyApplication;
import com.atto.developers.atto.networkdata.dbdata.CategoryKeywordData;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class DBManager extends SQLiteOpenHelper {
    private static DBManager instance;

    private static final String CATEGORY_TABLE_CREATE = "create table category (_id integer primary key autoincrement,"+ "name text not null);";
    private static final String KEYWORD_TABLE_CREATE="create table keyword (_id integer primary key autoincrement,"+ "name text not null);";
    private static final String DATABASE_NAME = "category_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBManager";

    public static DBManager getInstance(){
        if(instance ==null){
            instance = new DBManager();
        }
        return instance;
    }


    private DBManager() {
        super(MyApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CATEGORY_TABLE_CREATE);
        db.execSQL(KEYWORD_TABLE_CREATE);


    }


    public long getCategoryId(long category_Id){
        String selection = "CREATE TABLE"+ CategoryKeywordData.Cateory._ID+"=?";
        String [] args = {""+category_Id};
        String [] columns = {CategoryKeywordData.Cateory._ID};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(CategoryKeywordData.Cateory.TABLE,columns,selection,args,null,null,null);

        return 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        Log.w(TAG, "Upgrading database from version " + old_version + " to " + new_version
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS keyword");
        onCreate(db);

    }

}

