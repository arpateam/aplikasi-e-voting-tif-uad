package com.application.e_votingtif;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "evoting.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table data_mahasiswa(Nim integer primary key, Nama_lengkap varchar (255) null);";
        Log.d("data_mahasiswa", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO data_mahasiswa(Nim, Nama_lengkap) VALUES ('1900018025', 'Elvira Putri');";
        db.execSQL(sql);

        String sql1 = "create table kritik_saran(id_kritik_saran integer PRIMARY KEY AUTOINCREMENT, kritik text not null, waktu datetime);";
        Log.d("kritik_saran", "onCreate: " + sql1);
        db.execSQL(sql1);
        sql1 = "INSERT INTO kritik_saran(kritik) VALUES ('Good Aplication');";
        db.execSQL(sql1);

        String sql2 = "create table vote(id_vote integer PRIMARY KEY AUTOINCREMENT , paslon char, waktu datetime);";
        Log.d("vote", "onCreate: " + sql2);
        sql1 = "INSERT INTO vote(paslon) VALUES ('1');";
        db.execSQL(sql2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
        // TODO Auto-generated method stub
    }
}
