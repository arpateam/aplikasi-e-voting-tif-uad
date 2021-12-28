package com.application.e_votingtif;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class HasilVote extends Activity {
    protected Cursor cursor;
    String[] daftar;
    int[]id;
    ListView ListView01;
    DataHelper dbHelper;
    SQLiteDatabase sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_vote);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("CREAted for TIF");
        getActionBar().setSubtitle("E-Voting");
        getActionBar().setIcon(R.drawable.logokecil);
        toolbar.setTitleMarginStart(100);

        dbHelper=new DataHelper(HasilVote.this);
        findid();
        displayData();
    }

    private void displayData() {
        sqliteDatabase=dbHelper.getReadableDatabase();
        cursor = sqliteDatabase.rawQuery("select*from vote",null );
        if (cursor.getCount()>0){
            id=new int[cursor.getCount()];
            daftar=new String[cursor.getCount()];

            int i=0;

            while (cursor.moveToNext()){
                id[i]=cursor.getInt(0);
                daftar[i]=cursor.getString(1);
                i++;
            }
            Custom custom=new Custom();
            ListView01.setAdapter(custom);
        }
    }

    private void findid() {
        ListView01=findViewById(R.id.listView1);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(HasilVote.this,"Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(HasilVote.this,"Stop", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Home:
                Intent home=new Intent(HasilVote.this, MainActivity.class);
                startActivity(home);
                Toast.makeText(HasilVote.this, "Home", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Dpt:
                Intent dpt=new Intent(HasilVote.this, CekDPT.class);
                startActivity(dpt);
                Toast.makeText(HasilVote.this, "Cek DPT", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Vote:
                Intent vote=new Intent(HasilVote.this, Vote.class);
                startActivity(vote);
                Toast.makeText(HasilVote.this, "Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.HasilVote:
                Intent hasil=new Intent(HasilVote.this, HasilVote.class);
                startActivity(hasil);
                Toast.makeText(HasilVote.this, "Hasil Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Kritik:
                Intent ks=new Intent(HasilVote.this, KritikSaran.class);
                startActivity(ks);
                Toast.makeText(HasilVote.this, "Kritik Saran", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Help:
                Toast.makeText(HasilVote.this, "Help", Toast.LENGTH_LONG).show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }


    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView,textView1;
            return null;
        }
    }
}