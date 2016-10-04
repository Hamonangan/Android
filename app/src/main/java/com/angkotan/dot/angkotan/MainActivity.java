package com.angkotan.dot.angkotan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {
    private EditText input1, input2;
    private Button btn1,btn2,btnxit;
    public static final String REQUEST_TAG = "MainVolleyActivity";
    private RequestQueue mQueue;
    private TextView mTextView;
    Spinner spins,spins2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (Button) findViewById(R.id.btn_signup);
        btn2 = (Button) findViewById(R.id.button2);
        btnxit=(Button) findViewById(R.id.ButtonExit);
        spins=(Spinner) findViewById(R.id.spinner);
        spins2=(Spinner) findViewById(R.id.spinner2);






        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=String.valueOf(spins.getSelectedItem());
                String y=String.valueOf(spins2.getSelectedItem());

                if (x.isEmpty() || y.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Data belum valid", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent_Map = new Intent(MainActivity.this, MapsActivity.class);
                    intent_Map.putExtra("from2",x);
                    intent_Map.putExtra("to2",y);
                    startActivity(intent_Map);

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Map = new Intent(MainActivity.this, About.class);
                startActivity(intent_Map);
            }
        });

        btnxit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
tutup();
            }
        });
/*
   Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        startActivity(intent
       */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tutup(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }

                 //   public void onCLick(DialogInterface dialog,int id){
                  //      MainActivity.this.finish();

                   // }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }



           // public void onClick(DialogInterface dialog, int id) {
            //    dialog.cancel();
           // }
        }).show();


    }






}
