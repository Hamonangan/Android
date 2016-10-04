package com.angkotan.dot.angkotan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {
    private ListView lv;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    ArrayList<HashMap<String,String>> alamatList;

    String alamat[]={"arundina cibubur","pasar rebo","tamini","bambu apus","kayu tinggi","ciracas","condet","cilangkap","jambore","pinang ranti","rawa kuning","lampiri","pangkalan jati","pulogebang","transito","kranji jakarta","klender","rorotan","gempol","stasiun cakung","pondok kopi","klender jakarta","kalimalang","pulogebang","pulogadung","cililitan","pondok gede","pondok kelapa jakarta","kampung rambutan","munjul jakarta","gandaria","pisangan","ciganjur","jatinegara"," kayumanis jakarta","rawasari jakarta","cipinang","cilangkap","bendungan melayu jakarta","pondok kelapa","jambore jakarta","kalisari ","pondok bambu","taman bunga","kampung melayu","mekarsari jakarta","setu","duren sawit","perumnas klender","cawang","cibubur","rawamangun","harapan jaya","pekayon","cakung","cipayung","kompleks pwi jakarta","keong","klender","setu jakarta","cijantung","pasar kebayoran lama","klapa dua wetan","kota wisata","chandra","bj benteng","pondok rangon","kramat jati","condet","kramatjati","bulak ringin","komp halim","arundina","suci","matraman","utan kayu","pinang ranti","lebak bulus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv=(ListView) findViewById(R.id.list_view);
        inputSearch=(EditText) findViewById(R.id.input_search);

        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.list_alamat,alamat);
        lv.setAdapter(adapter);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                SearchActivity.this.adapter.getFilter().filter(s);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //pada saat listview di click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //inputSearch.setText(position);
                //Toast.makeText(SearchActivity.this,alamat[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, MainFrom.class);
                intent.putExtra("to",adapter.getItem(position));

                Intent intents=getIntent();


                    String stringData1 = intents.getStringExtra("from2");
                    String stringData2 = intents.getStringExtra("to2");
                    intent.putExtra("from",stringData1);



                    startActivity(intent);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
