package com.example.ndif_yemmanuel.smart_q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {

    EditText newp_word, confirmnewp_word, email;
    String pword, cpword, em;

    Button passwordsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        newp_word = findViewById(R.id.newp_word);
        confirmnewp_word = findViewById(R.id.confirmnewp_word);
        email = findViewById(R.id.emailadd);
        passwordsubmit = findViewById(R.id.passwordsubmit);

        pword = newp_word.getText().toString().trim();
        cpword = confirmnewp_word.getText().toString().trim();
        em = email.getText().toString();

        passwordsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
