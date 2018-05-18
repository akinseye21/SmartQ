package com.example.ndif_yemmanuel.smart_q;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class edit_profile extends AppCompatActivity {

    private static final String REGISTER_URL = "https://mobile-companion.herokuapp.com/user/updateuser";

    public static final String KEY_FIRSTNAME = "fname";
    public static final String KEY_LASTNAME = "lname";
    public static final String KEY_PHONE = "phone";
    public  static final String KEY_ID = "_id";

    private static final int RESULT_LOAD_IMAGE = 1;
    Button uploadimage, editprofile;
    ImageView userimageupload;

    EditText txtfirstname, txtlastname, txtphone;


    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //adding back-button to the toolbar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        //get passed string using shared preference
        SharedPreferences sharedPreferences = getSharedPreferences("pass", Context.MODE_PRIVATE);
        final String _id = sharedPreferences.getString("_id", "_id");


        System.out.println(_id);


        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        txtfirstname = findViewById(R.id.txtfirstname);
        txtlastname = findViewById(R.id.txtlastname);
        txtphone = findViewById(R.id.txtphone);


        editprofile = findViewById(R.id.editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                final String fname = txtfirstname.getText().toString().trim();
                final String lname = txtlastname.getText().toString().trim();
                final String phone = txtphone.getText().toString().trim();



                StringRequest stringRequest = new StringRequest(Request.Method.PUT, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(edit_profile.this, "Profile Update Successful", Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(edit_profile.this, "Error... Profile not updated", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(KEY_ID, _id);
                        params.put(KEY_FIRSTNAME, fname);
                        params.put(KEY_LASTNAME, lname);
                        params.put(KEY_PHONE, phone);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(edit_profile.this);
                requestQueue.add(stringRequest);
            }
        });

        userimageupload = findViewById(R.id.userimageupload);

        uploadimage = findViewById(R.id.uploadimage);
        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            userimageupload.setImageURI(selectedImage);
        }
    }
}
