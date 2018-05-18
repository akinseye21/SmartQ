package com.example.ndif_yemmanuel.smart_q;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NavigationHeader extends AppCompatActivity {

    TextView username_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);

        //get passed string using shared preference
        SharedPreferences sharedPreferences = getSharedPreferences("pass", Context.MODE_PRIVATE);
        final String fname = sharedPreferences.getString("fname", "fname");
        final String lname = sharedPreferences.getString("lname", "lame");

        username_home = findViewById(R.id.username_home);
        username_home.setText(fname+" "+lname);
    }
}
