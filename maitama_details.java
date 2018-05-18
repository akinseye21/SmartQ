package com.example.ndif_yemmanuel.smart_q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class maitama_details extends AppCompatActivity {

    TextView fname_passed, lname_passed, dob_passed, phone_passed, email_passed, address_passed, hospitalid_passed;
    String fname, lname, dob, phone, email, add, hospital;

    Button paycard, paywallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maitama_details);

        //adding back-button to the toolbar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fname_passed = findViewById(R.id.fname_passed);
        lname_passed = findViewById(R.id.lname_passed);
        dob_passed = findViewById(R.id.dob_passed);
        phone_passed = findViewById(R.id.phone_passed);
        email_passed = findViewById(R.id.email_passed);
        address_passed = findViewById(R.id.address_passed);
        hospitalid_passed = findViewById(R.id.hospitalid_passed);

        fname = getIntent().getExtras().getString("firstname");
        lname = getIntent().getExtras().getString("lastname");
        dob = getIntent().getExtras().getString("dob");
        phone = getIntent().getExtras().getString("phone");
        email = getIntent().getExtras().getString("email");
        add = getIntent().getExtras().getString("address");
        hospital = getIntent().getExtras().getString("hospitalid");

        fname_passed.setText(fname);
        lname_passed.setText(lname);
        dob_passed.setText(dob);
        phone_passed.setText(phone);
        email_passed.setText(email);
        address_passed.setText(add);
        hospitalid_passed.setText(hospital);


        paycard = findViewById(R.id.paycard);
        paycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(maitama_details.this, PaymentSucessful.class);
                startActivity(i);
            }
        });

        paywallet = findViewById(R.id.paywallet);
        paywallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(maitama_details.this, PaymentSucessful.class);
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
