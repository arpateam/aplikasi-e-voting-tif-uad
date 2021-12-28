package com.application.e_votingtif;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Vote extends Activity {
    protected Cursor cursor;
    String[] daftar;
    ListView ListView01;
    SQLiteDatabase sqliteDatabase;
    DataHelper dbHelper;
    Toolbar toolbar;
    Button btn;
    CheckBox checkBox, checkBox1;
//    public static Vote vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("CREAted for TIF");
        getActionBar().setSubtitle("E-Voting");
        getActionBar().setIcon(R.drawable.logokecil);
        toolbar.setTitleMarginStart(100);

        dbHelper=new DataHelper(Vote.this);
        findid();
        insertData();

//        Button btn=(Button)findViewById(R.id.kirim);
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                SQLiteDatabase db =
//                        dbHelper.getWritableDatabase();
//                db.execSQL("insert into kritik_saran(kritik) values('"+ text1.getText().toString()+"')");
//                Toast.makeText(getApplicationContext(),
//                        "Berhasil", Toast.LENGTH_LONG).show();
//
//                Intent inte = new Intent(KritikSaran.this, KritikSaran.class);
//                startActivity(inte);
//                KritikSaran.ma.RefreshList();
//                finish();
//            }
//        });
//        ma = this;
//        dbHelper = new DataHelper(this);
//        RefreshList();
    }

    private void insertData() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqliteDatabase = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                if (checkBox.isChecked())
                    contentValues.put("paslon", checkBox.getText().toString());
                if (checkBox1.isChecked())
                    contentValues.put("paslon", checkBox1.getText().toString());
                Long rec = sqliteDatabase.insert("vote", null, contentValues);
                if (rec != null) {
                    Toast.makeText(Vote.this, "Pilihan Anda tersimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Vote.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Vote.this,HasilVote.class));
            }
        });
    }


    private void findid() {
        btn=(Button) findViewById(R.id.pilih);
        checkBox=(CheckBox) findViewById(R.id.paslon1);
        checkBox1=(CheckBox) findViewById(R.id.paslon2);
    }

    //    public void RefreshList(){
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM kritik_saran",null);
//        daftar = new String[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int cc=0; cc < cursor.getCount(); cc++){
//            cursor.moveToPosition(cc);
//            daftar[cc] = cursor.getString(1).toString();
//        }
//        ListView01 = (ListView)findViewById(R.id.listView1);
//        ListView01.setAdapter(new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, daftar));
//        ListView01.setSelected(true);
//        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
//                final String selection = daftar[arg2];
//                //.getItemAtPosition(arg2).toString();
//                final CharSequence[] dialogitem = {"Hapus Kritik Saran"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(KritikSaran.this);
//                builder.setTitle("Pilihan");
//                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        switch(item){
//                            case 0 :
//                                SQLiteDatabase db =
//                                        dbHelper.getWritableDatabase();
//                                db.execSQL("delete from kritik_saran where kritik = '"+selection+"'");
//                                RefreshList();
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
//            }});
//        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
//    }
    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(Vote.this,"Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(Vote.this,"Stop", Toast.LENGTH_SHORT).show();
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
                Intent home=new Intent(Vote.this, MainActivity.class);
                startActivity(home);
                Toast.makeText(Vote.this, "Home", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Dpt:
                Intent dpt=new Intent(Vote.this, CekDPT.class);
                startActivity(dpt);
                Toast.makeText(Vote.this, "Cek DPT", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Vote:
                Intent vote=new Intent(Vote.this, Vote.class);
                startActivity(vote);
                Toast.makeText(Vote.this, "Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.HasilVote:
                Intent hasil=new Intent(Vote.this, HasilVote.class);
                startActivity(hasil);
                Toast.makeText(Vote.this, "Hasil Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Kritik:
                Intent ks=new Intent(Vote.this, KritikSaran.class);
                startActivity(ks);
                Toast.makeText(Vote.this, "Kritik Saran", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Help:
                Toast.makeText(Vote.this, "Help", Toast.LENGTH_LONG).show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}