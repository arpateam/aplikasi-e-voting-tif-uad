package com.application.e_votingtif;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CekDPT extends Activity {
    public static  final String url = "https://mywebsasa.000webhostapp.com/data_mahasiswa_aktif.php";
    EditText Nim;
    ImageButton Cek;
    TextView TampilNama;
    TextView TampilJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_dpt);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("CREAted for TIF");
        getActionBar().setSubtitle("E-Voting");
        getActionBar().setIcon(R.drawable.logokecil);
        toolbar.setTitleMarginStart(100);

        Nim=(EditText)findViewById(R.id.nim);
        Cek=(ImageButton)findViewById(R.id.cek);

        Cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caridpt();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(CekDPT.this,"Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(CekDPT.this,"Stop", Toast.LENGTH_SHORT).show();
    }

    public void caridpt() {
        setContentView(R.layout.caridpt);
        String nim = Nim.getText().toString();
        String url = "https://mywebsasa.000webhostapp.com/data_mahasiswa_aktif.php?NIM="+nim;

        TampilNama=(TextView)findViewById(R.id.tampilNama);
        TampilJadwal=(TextView)findViewById(R.id.tampilJadwal);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
//                            JSONArray gethasil = jsonObject.getJSONArray("hasil");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject getData = jsonArray.getJSONObject(i);
                                String nama_lengkap = getData.getString("nama_lengkap");

                                TampilNama.setText("Hai "+nama_lengkap+ " !\nAnda terdaftar sebagai DPT");
                                TampilJadwal.setText("Hari Jum'at, 02 April 2021\njangan lupa untuk memilih ya!");
                                Toast.makeText(CekDPT.this, nim,Toast.LENGTH_SHORT).show();
                            }
//                            TampilNama.setTextColor(Color.RED);
//                            TampilNama.setText("Anda tidak terdaftar sebagai DPT.\nCek NIM Anda!");
//                            TampilJadwal.setText("Hari Jum'at, 02 April 2021\njangan lupa untuk memilih ya!");

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();

                params.put("nim",nim);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

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
                Intent home=new Intent(CekDPT.this, MainActivity.class);
                startActivity(home);
                Toast.makeText(CekDPT.this, "Home", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Dpt:
                Intent dpt=new Intent(CekDPT.this, CekDPT.class);
                startActivity(dpt);
                Toast.makeText(CekDPT.this, "Cek DPT", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Vote:
                Intent vote=new Intent(CekDPT.this, Vote.class);
                startActivity(vote);
                Toast.makeText(CekDPT.this, "Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.HasilVote:
                Intent hasil=new Intent(CekDPT.this, HasilVote.class);
                startActivity(hasil);
                Toast.makeText(CekDPT.this, "Hasil Vote", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Kritik:
                Intent ks=new Intent(CekDPT.this, KritikSaran.class);
                startActivity(ks);
                Toast.makeText(CekDPT.this, "Kritik Saran", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Help:
                Toast.makeText(CekDPT.this, "Help", Toast.LENGTH_LONG).show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
