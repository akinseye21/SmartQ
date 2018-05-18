package com.example.ndif_yemmanuel.smart_q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class HospitalRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  /*to remove title bar*/
        setContentView(R.layout.activity_hospital_registration);
    }
}
