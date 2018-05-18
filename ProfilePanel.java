package com.example.ndif_yemmanuel.smart_q;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfilePanel extends AppCompatActivity {

    Button editprofile;
    TextView txtfirstname, txtlastname, txtemail, txtphone, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  /*to remove title bar*/
        setContentView(R.layout.activity_profile_panel);

        //adding back-button to the toolbar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //get passed string using shared preference
        SharedPreferences sharedPreferences = getSharedPreferences("pass", Context.MODE_PRIVATE);
        final String fname = sharedPreferences.getString("fname", "fname");
        final String lname = sharedPreferences.getString("lname", "lame");
        final String email = sharedPreferences.getString("email2", "email2");
        final String phone = sharedPreferences.getString("phone", "phone");

        txtfirstname = findViewById(R.id.txtfirstname);
        txtfirstname.setText(fname);

        txtlastname = findViewById(R.id.txtlastname);
        txtlastname.setText(lname);

        txtemail = findViewById(R.id.txtemail);
        txtemail.setText(email);

        txtphone = findViewById(R.id.txtphone);
        txtphone.setText(phone);

        username = findViewById(R.id.username);
        username.setText(fname+" "+lname);

        editprofile = findViewById(R.id.editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfilePanel.this, edit_profile.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
