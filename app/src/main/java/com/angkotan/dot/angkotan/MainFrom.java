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
import android.widget.TextView;

public class MainFrom extends AppCompatActivity {
    private EditText input1,input2;
    private Button btn1,btn2,btnxit;
    private TextView textOrigin,textDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_from);
        EditText from;
        input1=(EditText) findViewById(R.id.editText2);
        input2=(EditText) findViewById(R.id.input_form2);
        btn1=(Button) findViewById(R.id.btn_signup);
        btn2=(Button) findViewById(R.id.button3);
        btnxit=(Button) findViewById(R.id.button5);
        textOrigin=(TextView) findViewById(R.id.textView);
        textDestination=(TextView) findViewById(R.id.textView2);
        textOrigin.setText("From");
        textDestination.setText("Destination");
        int X=0,Y=0;
        Intent intent=getIntent();
        if(null !=intent){

            String stringData1=intent.getStringExtra("from");
            String stringData2=intent.getStringExtra("to");

            from=(EditText) findViewById(R.id.editText2);
            from.setText(stringData1);


            from=(EditText) findViewById(R.id.input_form2);
            from.setText(stringData2);

        }


        //if one of from click
        input1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFrom.this, SearchActivity1.class);
                intent.putExtra("from2",input1.getText().toString());
                intent.putExtra("to2",input2.getText().toString());
                startActivity(intent);


            }
        });

        input2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFrom.this, SearchActivity.class);
                intent.putExtra("from2",input1.getText().toString());
                intent.putExtra("to2",input2.getText().toString());
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=input1.getText().toString();
                String y=input2.getText().toString();

                //Toast.makeText(MainFrom.this, x+" "+y, Toast.LENGTH_SHORT).show();

                Intent intent_Map = new Intent(MainFrom.this, MapsActivity.class);
                intent_Map.putExtra("from2",x);
                intent_Map.putExtra("to2",y);
                startActivity(intent_Map);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentx = new Intent(MainFrom.this, About.class);
                startActivity(intentx);
            }



    });

        btnxit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutup();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_from, menu);
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

    public void tutup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainFrom.this.finish();
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
