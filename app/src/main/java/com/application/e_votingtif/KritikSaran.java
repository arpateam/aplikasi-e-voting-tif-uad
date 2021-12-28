package com.application.e_votingtif;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

public class KritikSaran extends Activity {
    protected Cursor cursor;
    String[] daftar;
    ListView ListView01;
    DataHelper dbHelper;
    Button ton1;
    EditText text1;
    public static KritikSaran ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kritik_saran);

        text1 = (EditText) findViewById(R.id.kritiksaran);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("CREAted for TIF");
        getActionBar().setSubtitle("E-Voting");
        getActionBar().setIcon(R.drawable.logokecil);
        toolbar.setTitleMarginStart(100);

        Button btn=(Button)findViewById(R.id.kirim);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();
                db.execSQL("insert into kritik_saran(kritik) values('"+ text1.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),
                        "Berhasil", Toast.LENGTH_LONG).show();

                Intent inte = new Intent(KritikSaran.this, KritikSaran.class);
                startActivity(inte);
                KritikSaran.ma.RefreshList();
                finish();
            }
        });
        ma = this;
        dbHelper = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kritik_saran",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Hapus Kritik Saran"};
                AlertDialog.Builder builder = new AlertDialog.Builder(KritikSaran.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                SQLiteDatabase db =
                                        dbHelper.getWritableDatabase();
                                db.execSQL("delete from kritik_saran where kritik = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(KritikSaran.this,"Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(KritikSaran.this,"Stop", Toast.LENGTH_SHORT).show();
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
                Intent home=new Intent(KritikSaran.this, MainActivity.class);
                startActivity(home);
                Toast.makeText(KritikSaran.this, "Home", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Dpt:
                Intent dpt=new Intent(KritikSaran.this, CekDPT.class);
                startActivity(dpt);
                Toast.makeText(KritikSaran.this, "Cek DPT", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Vote:
                Intent vote=new Intent(KritikSaran.this, Vote.class);
                startActivity(vote);
                Toast.makeText(KritikSaran.this, "Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.HasilVote:
                Intent hasil=new Intent(KritikSaran.this, HasilVote.class);
                startActivity(hasil);
                Toast.makeText(KritikSaran.this, "Hasil Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Kritik:
                Intent ks=new Intent(KritikSaran.this, KritikSaran.class);
                startActivity(ks);
                Toast.makeText(KritikSaran.this, "Kritik Saran", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Help:
                Toast.makeText(KritikSaran.this, "Help", Toast.LENGTH_LONG).show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}