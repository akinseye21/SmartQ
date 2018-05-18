package com.example.ndif_yemmanuel.smart_q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class maitama_reg extends AppCompatActivity {

    EditText fname, lname, dob, phone, email, address, hospitalid;

    String fname1, lname1, dob1, phone1, email1, address1, hospitalid1;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maitama_reg);

        //adding back-button to the toolbar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fname = findViewById(R.id.input_f_name);
        lname = findViewById(R.id.input_l_name);
        dob = findViewById(R.id.input_dob);
        phone = findViewById(R.id.input_phone);
        email = findViewById(R.id.input_emailadd);
        address = findViewById(R.id.input_address);
        submit = findViewById(R.id.submit_reg_btn);
        hospitalid = findViewById(R.id.input_hospitalid);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passsend();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void passsend(){
        fname1 = fname.getText().toString();
        lname1 = lname.getText().toString();
        dob1 = dob.getText().toString();
        phone1 = phone.getText().toString();
        email1 = email.getText().toString();
        address1 = address.getText().toString();
        hospitalid1 = hospitalid.getText().toString();

        Intent i = new Intent(maitama_reg.this, maitama_details.class);
        i.putExtra("firstname", fname1);
        i.putExtra("lastname", lname1);
        i.putExtra("dob", dob1);
        i.putExtra("phone", phone1);
        i.putExtra("email", email1);
        i.putExtra("address", address1);
        i.putExtra("hospitalid", hospitalid1);
        startActivity(i);
    }
}
