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

public class SearchActivity1 extends AppCompatActivity {
    private ListView lv;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    ArrayList<HashMap<String,String>> alamatList;

    String alamat[]={"cililitan","pulogadung","keong","pasar rebo","rawamangun","kampung melayu","kampung rambutan","klender","bulak ringin","terminal cililitan","pasar minggu","tamini","cilincing","kalimalang","terminal kampung melayu","pondok labu","kalisari","pinang ranti","pulo gadung","kampung melayu"};

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

                SearchActivity1.this.adapter.getFilter().filter(s);

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
                //Toast.makeText(SearchActivity1.this, alamat[position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SearchActivity1.this, MainFrom.class);
                intent.putExtra("from",adapter.getItem(position));
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
