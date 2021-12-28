package com.application.e_votingtif;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends Activity {

    Toolbar toolbar;
    Button votebtn,tutorbtn;
    String[] daftar;
    ListView ListView01;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("CREAted for TIF");
        getActionBar().setSubtitle("E-Voting");
        getActionBar().setIcon(R.drawable.logokecil);
        toolbar.setTitleMarginStart(100);

        votebtn=(Button)findViewById(R.id.btnvote);
        tutorbtn=(Button)findViewById(R.id.btntutor);

        ma = this;
        dbcenter = new DataHelper(this);

        votebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent votemulai=new Intent(MainActivity.this, Vote.class);
                startActivity(votemulai);
            }
        });

        tutorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/p/CIr_ugasU1Y/?utm_source=ig_web_copy_link"));
                try {
                    MainActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//        this.getDelegate().onStart();
        Toast.makeText(MainActivity.this,"Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this,"Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this,"Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this,"Stop", Toast.LENGTH_SHORT).show();
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
                Intent home=new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
                Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Dpt:
                Intent dpt=new Intent(MainActivity.this, CekDPT.class);
                startActivity(dpt);
                Toast.makeText(MainActivity.this, "Cek DPT", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Vote:
                Intent vote=new Intent(MainActivity.this, Vote.class);
                startActivity(vote);
                Toast.makeText(MainActivity.this, "Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.HasilVote:
                Intent hasil=new Intent(MainActivity.this, HasilVote.class);
                startActivity(hasil);
                Toast.makeText(MainActivity.this, "Hasil Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Kritik:
                Intent ks=new Intent(MainActivity.this, KritikSaran.class);
                startActivity(ks);
                Toast.makeText(MainActivity.this, "Kritik Saran", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Help:
                Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_LONG).show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
