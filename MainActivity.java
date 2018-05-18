package com.example.ndif_yemmanuel.smart_q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    protected static final int Timer_Runtime=3000;
    protected boolean mbActive;
    protected ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  /*to remove title bar*/
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("SmartQ-Mobile Health Companion");
        getSupportActionBar().setIcon(getDrawable(R.drawable.splash_ic));
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        final Thread timerThread = new Thread(){
            @Override
            public void run(){
                mbActive = true;
                try{
                    int waited = 0;
                    while(mbActive && (waited < Timer_Runtime)){
                        sleep(200);
                        if (mbActive){
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                }catch(InterruptedException e){
                    //error
                }finally{
                    onContinue();
                }
            }
        };
        timerThread.start();
    }

    public void updateProgress(final int timePassed){
        if (null != mProgressBar){

            final int progress = mProgressBar.getMax() * timePassed / Timer_Runtime;
            mProgressBar.setProgress(progress);
        }
    }

    public void onContinue(){
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);

    }
}
