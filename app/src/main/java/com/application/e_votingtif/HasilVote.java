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

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HasilVote extends Activity {
    protected Cursor cursor;
    String[] daftar;
    int[] id;
    ListView ListView01;
    DataHelper dbHelper;
    SQLiteDatabase sqliteDatabase;

    TextView rincianVotePaslon01, rincianVotePaslon02, rincianVoteGolput, pemenangnya, rincianVotePemenang, presentasKemenangan;

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
        rincianVotePaslon01 = (TextView) findViewById(R.id.rincianVotePaslon01);
        rincianVotePaslon02 = (TextView) findViewById(R.id.rincianVotePaslon02);
        rincianVoteGolput = (TextView) findViewById(R.id.rincianVoteGolput);
        pemenangnya = (TextView) findViewById(R.id.pemenangnya);
        rincianVotePemenang = (TextView) findViewById(R.id.rincianVotePemenang);
        presentasKemenangan = (TextView) findViewById(R.id.presentasKemenangan);
        displayData();
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

    private void displayData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://www.kpump-tif.arpateam.com/data-hasil-vote.php";

        JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // menaruh data JSON kkedalam variabel JSON Object
                            JSONObject jsonPost = new JSONObject(response.toString());

                            //men set data ke dalam tampilan
                            rincianVotePaslon01.setText(jsonPost.getString("rincianVotePaslon01"));
                            rincianVotePaslon02.setText(jsonPost.getString("rincianVotePaslon02"));
                            rincianVoteGolput.setText(jsonPost.getString("rincianVoteGolput"));
                            pemenangnya.setText(jsonPost.getString("pemenangnya"));
                            rincianVotePemenang.setText(jsonPost.getString("rincianVotePemenang"));
                            presentasKemenangan.setText(jsonPost.getString("presentasKemenangan"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}