package com.example.ndif_yemmanuel.smart_q;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NotGoodResult extends AppCompatActivity {

    String dizzy, headache, stomachache, vomiting, diarrhoea, fever, bodypain, chillsandrigour, lossofappetite, epigastricpain, rightiliachfossa, cough, difficultyinbreathing, complain;

    TextView dizzy_txt, headache_txt, stomachache_txt, vomiting_txt, diarrhoea_txt, fever_txt, bodypain_txt, chillsandrigour_txt, lossofappetite_txt;
    TextView epigastricpain_txt, rightiliacfossa_txt, cough_txt, difficultyinbreathing_txt, complain_txt;

    TextView diagnosis;

    Button next;

    Bitmap img;

    ImageView complain_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_good_result);

        //adding back-button to the toolbar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //get passed values
        dizzy = getIntent().getStringExtra("message_key");
        headache = getIntent().getStringExtra("message_key2");
        stomachache = getIntent().getStringExtra("message_key3");
        vomiting = getIntent().getStringExtra("message_key4");
        diarrhoea = getIntent().getStringExtra("message_key5");
        fever = getIntent().getStringExtra("message_key6");
        bodypain = getIntent().getStringExtra("message_key7");
        chillsandrigour = getIntent().getStringExtra("message_key8");
        lossofappetite = getIntent().getStringExtra("message_key9");
        epigastricpain = getIntent().getStringExtra("message_key10");
        rightiliachfossa = getIntent().getStringExtra("message_key11");
        cough = getIntent().getStringExtra("message_key12");
        difficultyinbreathing = getIntent().getStringExtra("message_key13");
        complain = getIntent().getExtras().getString("message_key14");
        Intent intent = getIntent();
        img = intent.getParcelableExtra("image");


        System.out.println(dizzy+"\n"+headache+"\n"+stomachache+"\n"+vomiting+"\n"+diarrhoea+"\n"+fever+"\n"+bodypain+"\n"+complain+"\n"+img);

        //find views by id
        dizzy_txt = findViewById(R.id.dizzy_txt);
        headache_txt = findViewById(R.id.headache_txt);
        stomachache_txt = findViewById(R.id.stomachache_txt);
        vomiting_txt = findViewById(R.id.vomiting_txt);
        diarrhoea_txt = findViewById(R.id.diarrhoea_txt);
        fever_txt = findViewById(R.id.fever_txt);
        bodypain_txt = findViewById(R.id.bodypain_txt);
        chillsandrigour_txt = findViewById(R.id.chillsandrigour_txt);
        lossofappetite_txt = findViewById(R.id.lossofappetite_txt);
        epigastricpain_txt = findViewById(R.id.epigastricpain_txt);
        rightiliacfossa_txt = findViewById(R.id.rightstomachpain_txt);
        cough_txt = findViewById(R.id.cough_txt);
        difficultyinbreathing_txt = findViewById(R.id.difficultyinbreathing_txt);
        complain_txt = findViewById(R.id.majorcomplain_txt);
        complain_image = findViewById(R.id.complain_image);

        next = findViewById(R.id.nextbtn);

        dizzy_txt.setText(dizzy);
        headache_txt.setText(headache);
        stomachache_txt.setText(stomachache);
        vomiting_txt.setText(vomiting);
        diarrhoea_txt.setText(diarrhoea);
        fever_txt.setText(fever);
        bodypain_txt.setText(bodypain);
        chillsandrigour_txt.setText(chillsandrigour);
        lossofappetite_txt.setText(lossofappetite);
        epigastricpain_txt.setText(epigastricpain);
        rightiliacfossa_txt.setText(rightiliachfossa);
        cough_txt.setText(cough);
        difficultyinbreathing_txt.setText(difficultyinbreathing);
        complain_txt.setText(complain);
        complain_image.setImageBitmap(img);

        nextprocess();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void nextprocess(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotGoodResult.this, RegAfterComplain.class);
                startActivity(i);
            }
        });
    }
}
