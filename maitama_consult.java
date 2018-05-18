package com.example.ndif_yemmanuel.smart_q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class maitama_consult extends AppCompatActivity {

    TextView gopd1, gopd2, gopdfasttrack, antenatal, gynae, mopdsopd, dental, physiotherapy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maitama_consult);

        //adding back-button to the toolbar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //for GOPD 1
        gopd1 = findViewById(R.id.gopd1);
        gopd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(maitama_consult.this, gopd1.class);
                startActivity(i);
            }
        });

        //for GOPD 2
        gopd2 = findViewById(R.id.gopd2);
        gopd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(maitama_consult.this, gopd2.class);
                startActivity(i);
            }
        });

        //for GOPDFastTrack
        gopdfasttrack = findViewById(R.id.gopdfasttrack);
        gopdfasttrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(maitama_consult.this, gopdfasttrack.class);
                startActivity(i);
            }
        });

        //for antenatal
        antenatal = findViewById(R.id.antenatal);
        gynae = findViewById(R.id.gynae);
        mopdsopd = findViewById(R.id.mopdsopd);
        dental = findViewById(R.id.dental);
        physiotherapy = findViewById(R.id.physiotherapy);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
