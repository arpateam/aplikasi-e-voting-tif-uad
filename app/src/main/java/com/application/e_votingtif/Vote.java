package com.application.e_votingtif;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
    Button voteBtn;

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

        voteBtn=(Button)findViewById(R.id.btnVote);

        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://kpump-tif.arpateam.com/vote.php"));
                try {
                    Vote.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

        dbHelper=new DataHelper(Vote.this);
    }
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