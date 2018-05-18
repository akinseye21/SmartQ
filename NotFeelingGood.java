package com.example.ndif_yemmanuel.smart_q;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NotFeelingGood extends AppCompatActivity {
Button open_cam, next;
ImageView uploadimg;
EditText complain;

String complain1, S_dizzy, S_headache, S_stomachache, S_vomiting, S_diarrhoea, S_fever, S_bodypain, S_chillsandrigour, S_lossofappetite, S_epigastricpain;
String S_rightiliacfossa, S_cough, S_difficultyinbreathing;

RadioGroup dizzy_rgroup, headache_rgroup, stomachache_rgroup, vomiting_rgroup, diarrhoea_rgroup, fever_rgroup, bodypain_rgroup, chillsandrigour_rgroup;
RadioGroup lossofappetite_rgroup, epigastricpain_rgroup, rightiliacfossa_rgroup, cough_rgroup, difficultyinbreathing_rgroup;

RadioButton dizzy, headache, stomachache, vomiting, diarrhoea, fever, bodypain, chillsandrigour, lossofappetite;
RadioButton epigastricpain, rightiliacfossa, cough, difficultyinbreathing;

    Bitmap bp;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_feeling_good);

        //adding back-button to the toolbar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        dizzy_rgroup = findViewById(R.id.dizzy_rgroup);
        headache_rgroup = findViewById(R.id.headache_rgroup);
        stomachache_rgroup = findViewById(R.id.stomachache_rgroup);
        vomiting_rgroup = findViewById(R.id.vomiting_rgroup);
        diarrhoea_rgroup = findViewById(R.id.diarrhoea_rgroup);
        fever_rgroup = findViewById(R.id.fever_rgroup);
        bodypain_rgroup = findViewById(R.id.bodypain_rgroup);
        chillsandrigour_rgroup = findViewById(R.id.chillsandrigour_rgroup);
        lossofappetite_rgroup = findViewById(R.id.lossofappetite_rgroup);
        epigastricpain_rgroup = findViewById(R.id.epigastricpain_rgroup);
        rightiliacfossa_rgroup = findViewById(R.id.rightiliacfossa_rgroup);
        cough_rgroup = findViewById(R.id.cough_rgroup);
        difficultyinbreathing_rgroup = findViewById(R.id.difficultyinbreathing_rgroup);




        next = findViewById(R.id.next);
        open_cam = findViewById(R.id.open_cam);
        uploadimg = findViewById(R.id.uploadimg);
        open_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseopen();
            }
        });
    }

    public void open(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        bp = (Bitmap) data.getExtras().get("data");
        uploadimg.setImageBitmap(bp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    public void dizzy_rbclick(View view) {
        int radiobuttonid = dizzy_rgroup.getCheckedRadioButtonId();
        dizzy = findViewById(radiobuttonid);

        S_dizzy = (String) dizzy.getText();
    }

    public void headache_rbclick(View view) {
        int radiobuttonid = headache_rgroup.getCheckedRadioButtonId();
        headache = findViewById(radiobuttonid);

        S_headache = (String) headache.getText();
    }

    public void stomachache_rbclick(View view) {
        int radiobuttonid = stomachache_rgroup.getCheckedRadioButtonId();
        stomachache = findViewById(radiobuttonid);

        S_stomachache = (String) stomachache.getText();
    }

    public void vomiting_rbclick(View view) {
        int radiobuttonid = vomiting_rgroup.getCheckedRadioButtonId();
        vomiting = findViewById(radiobuttonid);

        S_vomiting = (String) vomiting.getText();
    }

    public void diarrhoea_rbclick(View view) {
        int radiobuttonid = diarrhoea_rgroup.getCheckedRadioButtonId();
        diarrhoea = findViewById(radiobuttonid);

        S_diarrhoea = (String) diarrhoea.getText();
    }

    public void fever_rbclick(View view) {
        int radiobuttonid = fever_rgroup.getCheckedRadioButtonId();
        fever = findViewById(radiobuttonid);

        S_fever = (String) fever.getText();
    }

    public void bodypain_rbclick(View view) {
        int radiobuttonid = bodypain_rgroup.getCheckedRadioButtonId();
        bodypain = findViewById(radiobuttonid);

        S_bodypain = (String) bodypain.getText();
    }

    public void chills_rbclick(View view) {
        int radiobuttonid = chillsandrigour_rgroup.getCheckedRadioButtonId();
        chillsandrigour = findViewById(radiobuttonid);

        S_chillsandrigour = (String) chillsandrigour.getText();
    }

    public void lossofappetite_rbclick(View view) {
        int radiobuttonid = lossofappetite_rgroup.getCheckedRadioButtonId();
        lossofappetite = findViewById(radiobuttonid);

        S_lossofappetite = (String) lossofappetite.getText();
    }

    public void epigastricpain_rbclick(View view) {
        int radiobuttonid = epigastricpain_rgroup.getCheckedRadioButtonId();
        epigastricpain = findViewById(radiobuttonid);

        S_epigastricpain = (String) epigastricpain.getText();
    }

    public void rightiliacfossa_rbclick(View view) {
        int radiobuttonid = rightiliacfossa_rgroup.getCheckedRadioButtonId();
        rightiliacfossa = findViewById(radiobuttonid);

        S_rightiliacfossa = (String) rightiliacfossa.getText();
    }

    public void cough_rbclick(View view) {
        int radiobuttonid = cough_rgroup.getCheckedRadioButtonId();
        cough = findViewById(radiobuttonid);

        S_cough = (String) cough.getText();
    }

    public void difficultyinbreathing_rbclick(View view) {
        int radiobuttonid = difficultyinbreathing_rgroup.getCheckedRadioButtonId();
        difficultyinbreathing = findViewById(radiobuttonid);

        S_difficultyinbreathing = (String) difficultyinbreathing.getText();
    }

    public void parseopen(){
        complain = findViewById(R.id.complain);
        complain1 = complain.getText().toString().trim();

        Intent i = new Intent(this, NotGoodResult.class);
        i.putExtra("message_key",S_dizzy);
        i.putExtra("message_key2",S_headache);
        i.putExtra("message_key3",S_stomachache);
        i.putExtra("message_key4",S_vomiting);
        i.putExtra("message_key5",S_diarrhoea);
        i.putExtra("message_key6",S_fever);
        i.putExtra("message_key7",S_bodypain);
        i.putExtra("message_key8",S_chillsandrigour);
        i.putExtra("message_key9",S_lossofappetite);
        i.putExtra("message_key10",S_epigastricpain);
        i.putExtra("message_key11",S_rightiliacfossa);
        i.putExtra("message_key12",S_cough);
        i.putExtra("message_key13",S_difficultyinbreathing);
        i.putExtra("message_key14", complain1);
        i.putExtra("image", bp);
        startActivity(i);


       /*** Intent i = new Intent(this, NotGoodResult.class);
        i.putExtra("Complain", complain1);
        i.putExtra("Dizzy", S_dizzy);
        i.putExtra("Headache", S_headache);
        i.putExtra("Stomachache", S_stomachache);
        i.putExtra("Vomiting", S_vomiting);
        i.putExtra("Diarrhoea", S_diarrhoea);
        i.putExtra("Fever", S_fever);
        i.putExtra("Bodypain", S_bodypain);
        i.putExtra("Chillsandrigour", S_chillsandrigour);
        i.putExtra("Lossofappetite", S_lossofappetite);
        i.putExtra("Epigastricpain", S_epigastricpain);
        i.putExtra("Rightiliacfossa", S_rightiliacfossa);
        i.putExtra("Cough", S_cough);
        i.putExtra("Difficultyinbreathing", S_difficultyinbreathing);
        startActivity(i);
        ***/

    }


}
