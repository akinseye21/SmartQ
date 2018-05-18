package com.example.ndif_yemmanuel.smart_q;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String Email, message;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    private  Toolbar mToolbar;

    Button prof, wallet, reg, consult, buttonhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  /*to remove title bar*/
        setContentView(R.layout.activity_home_page);

        /***pass the value from the input to the new activity
        Email = getIntent().getExtras().getString("EMAIL");
        TextView namepassed =  findViewById(R.id.namepassed);
        namepassed.setText(Email);
         ***/

        //get passed string using shared preference
        SharedPreferences sharedPreferences = getSharedPreferences("pass", Context.MODE_PRIVATE);
        final String fname = sharedPreferences.getString("fname", "fname");
        final String lname = sharedPreferences.getString("lname", "lame");
        TextView namepassed =  findViewById(R.id.namepassed);
        namepassed.setText(fname+" "+lname);


        //creating alert dialogue
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
        builder.setCancelable(true);
        builder.setTitle("Welcome "+fname+" !!!");
        builder.setMessage("How are you feeling today?");
        builder.setIcon(R.drawable.welcome);


        builder.setPositiveButton("Feel Good", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
                AlertDialog.Builder j = new AlertDialog.Builder(HomePage.this);

                j.setCancelable(false);
                j.setTitle("Thank you "+fname);
                j.setMessage("Thank you for your feedback on how you feel today... " +
                        "\nYou can also register for hospital services and make payment for different services. " +
                        "\nKindly click the 'OK' button to exit this window and access other services.");
                j.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                j.show();

            }
        });



        builder.setNeutralButton("Not Good", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent z = new Intent(HomePage.this, NotFeelingGood.class);
                startActivity(z);
            }
        });
        builder.show();



        getSupportActionBar().setDisplayShowTitleEnabled(true);
        
        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        prof = (Button)findViewById(R.id.btnprofile);
        wallet = (Button)findViewById(R.id.btnwallet);
        reg = (Button)findViewById(R.id.btnaccesshealthcare);
        consult = (Button)findViewById(R.id.btnaccessconsultation);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, select_hospital.class);
                startActivity(i);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, select_clinic.class);
                startActivity(i);
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, WalletPanel.class);
                startActivity(i);
            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, ProfilePanel.class);
                startActivity(i);
            }
        });






        naviview  = findViewById(R.id.navigationview);
        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home_id:
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.transaction_id:
                        Intent i = new Intent(HomePage.this, TransactionPanel.class);
                        startActivity(i);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.wallet_id:
                        Intent a = new Intent(HomePage.this, WalletPanel.class);
                        startActivity(a);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.profile_id:
                        Intent w = new Intent(HomePage.this, ProfilePanel.class);
                        startActivity(w);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.change_password_id:
                        Intent x = new Intent(HomePage.this, ChangePassword.class);
                        startActivity(x);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.logout_id:
                        final AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
                        builder.setMessage("Are you sure you want to exit?");
                        builder.setCancelable(true);
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                Toast.makeText(HomePage.this,"Thank you for using SmartQ, kindly Login again to access available services.", Toast.LENGTH_LONG).show();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;
                }

                return true;
            }
        });


        
    }

    /**custom popup
    public void display(){
        TextView close;
        Button good, notgood;
        myDialog.setContentView(R.layout.custompopup);
        close = findViewById(R.id.close);
        good = findViewById(R.id.feelgood);
        notgood = findViewById(R.id.notgood);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    ***/

    private void setSupportActionBar(Toolbar mToolbar) {
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void change(View view) {

        Intent i = new Intent(getBaseContext(), NavigationHeader.class);
        startActivity(i);
    }
}
