package br.edu.uniritter.mobile.mobile20222_1.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "BancoApp2";
    private static final Integer DB_VERSION = 4;
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_LOGIN = "userLogin";
    public static final String USER_PASSWORD = "password";
    public static final String TABLE_USERS = "users";

    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stm = "create table "+ TABLE_USERS + "("+USER_ID+" INTEGER PRIMARY KEY, "+USER_NAME+" TEXT,\n"+
                   USER_LOGIN+" TEXT, "+USER_PASSWORD+" TEXT);";
        sqLiteDatabase.execSQL(stm);

        String tdt = "create table todos (userId integer, id integer PRIMARY KEY, title text, completed integer)";
        sqLiteDatabase.execSQL(tdt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
