package com.example.ndif_yemmanuel.smart_q;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    public static final String LOGIN_URL = "https://mobile-companion.herokuapp.com/user/login";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    private Button login1, signup1;

    private EditText email;
    private EditText password;
    TextView forgot;

    private String email1;
    private String pass;
    String message, _id, fname, lname, email2, phone;

    ProgressBar progressBar;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  /*to remove title bar*/
        setContentView(R.layout.activity_main2);


        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(getDrawable(R.drawable.login_ic));
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        login1 = findViewById(R.id.btnlogin);
        signup1 = findViewById(R.id.btnsignup);
        email = findViewById(R.id.edittxt1);
        password = findViewById(R.id.edittxt2);
        forgot = findViewById(R.id.txt3);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email1 = email.getText().toString().trim();
                pass = password.getText().toString().trim();

                if(email1.isEmpty() || email1.length()>32){
                    email.setError("Please Enter a valid name");
                }
                if(pass.isEmpty()){
                    password.setError("Please Enter your password");
                }

                login();

            }
        });

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, signup.class);
                startActivity(i);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, ForgotPassword.class);
                startActivity(i);
            }
        });
    }



    public void login(){
            progressBar.setVisibility(View.VISIBLE);
            //check DB for both email and password
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                // get JSONObject from JSON file
                                JSONObject jsonObject = new JSONObject(response);
                                message = jsonObject.getString("message");

                                JSONObject res = jsonObject.getJSONObject("result");
                                _id = res.getString("_id");
                                fname = res.getString("fname");
                                lname = res.getString("lname");
                                email2 = res.getString("email");
                                phone = res.getString("phone");


                                //pass id and other params to another activity using shared preference
                                sharedPreferences = getSharedPreferences("pass", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("_id", _id);
                                editor.putString("fname", fname);
                                editor.putString("lname", lname);
                                editor.putString("email2", email2);
                                editor.putString("phone", phone);
                                editor.commit();


                                if(message.equals("Login Successful")){
                                    onLoginSuccess();
                                    progressBar.setVisibility(View.GONE);
                                }
                                else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity2.this,"Wrong Login Details...", Toast.LENGTH_LONG).show();
                                }



                                System.out.println(jsonObject);
                                System.out.println(message);
                                System.out.println(_id);



                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity2.this, "Login Problem... Kindly check for network access on your device",Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put(KEY_EMAIL, email1);
                    map.put(KEY_PASSWORD, pass);
                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

    }

    public void onLoginSuccess(){
        //to parse the username
        String email1 = email.getText().toString();
        Toast.makeText(this,"Login Successful. Welcome", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, HomePage.class);
        i.putExtra("EMAIL",email1);
        startActivity(i);

    }


}
