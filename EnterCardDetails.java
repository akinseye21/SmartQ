package com.example.ndif_yemmanuel.smart_q;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.exceptions.ExpiredAccessCodeException;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class EnterCardDetails extends AppCompatActivity {

    String backend_url = "https://infinite-peak-60063.herokuapp.com";

    String paystack_public_key = "pk_test_19f6f5aa1a9d106020e4ceeb14cbd5d8abf9a812";

    EditText card_Num, expireMonth, expireYear, cvv;
    TextView mTextError, mTextBackendMessage;
    Button pay1;

    ProgressDialog dialog;
    private TextView mTextReference;
    private Charge charge;
    private Transaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_card_details);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(getDrawable(R.drawable.carddetail));
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        PaystackSdk.setPublicKey(paystack_public_key);

        card_Num = findViewById(R.id.cardNum);
        expireMonth = findViewById(R.id.expireMonth);
        expireYear = findViewById(R.id.expireYear);
        cvv = findViewById(R.id.cvv);
        mTextError = findViewById(R.id.mTextError);
        mTextReference = findViewById(R.id.textview_reference);
        mTextBackendMessage = findViewById(R.id.textview_backend_message);

        pay1 = findViewById(R.id.pay1);

        PaystackSdk.initialize(getApplicationContext());

        pay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startAFreshCharge(false);
                } catch (Exception e) {
                    EnterCardDetails.this.mTextError.setText(String.format("An error occurred while charging card: %s %s", e.getClass().getSimpleName(), e.getMessage()));

                }
            }
        });
    }

    private void startAFreshCharge(boolean local) {
        // initialize the charge
        charge = new Charge();
        charge.setCard(loadCardFromForm());

        dialog = new ProgressDialog(EnterCardDetails.this);
        dialog.setMessage("Performing transaction... please wait");
        dialog.show();

        if (local) {
            // Set transaction params directly in app (note that these params
            // are only used if an access_code is not set. In debug mode,
            // setting them after setting an access code would throw an exception

            charge.setAmount(50);
            charge.setEmail("abayomi.akinseye@gmail.com");
            charge.setReference("ChargedFromAndroid_" + Calendar.getInstance().getTimeInMillis());
            try {
                charge.putCustomField("Charged From", "Android SDK");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            chargeCard();
        } else {
            // Perform transaction/initialize on our server to get an access code
            // documentation: https://developers.paystack.co/reference#initialize-a-transaction
            new fetchAccessCodeFromServer().execute(backend_url + "/new-access-code");
        }
    }

    private Card loadCardFromForm() {
        //validate fields
        Card card;

        String cardNum = card_Num.getText().toString().trim();

        //build card object with ONLY the number, update the other fields later
        card = new Card.Builder(cardNum, 0, 0, "").build();
        String cvc = cvv.getText().toString().trim();
        //update the cvc field of the card
        card.setCvc(cvc);

        //validate expiry month;
        String sMonth = expireMonth.getText().toString().trim();
        int month = 0;
        try {
            month = Integer.parseInt(sMonth);
        } catch (Exception ignored) {
        }

        card.setExpiryMonth(month);

        String sYear = expireYear.getText().toString().trim();
        int year = 0;
        try {
            year = Integer.parseInt(sYear);
        } catch (Exception ignored) {
        }
        card.setExpiryYear(year);

        return card;
    }

    @Override
    public void onPause() {
        super.onPause();

        if ((dialog != null) && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
    }

    private void chargeCard() {
        transaction = null;
        PaystackSdk.chargeCard(EnterCardDetails.this, charge, new Paystack.TransactionCallback() {
            // This is called only after transaction is successful
            @Override
            public void onSuccess(Transaction transaction) {
                dismissDialog();
                EnterCardDetails.this.transaction = transaction;
                mTextError.setText(" ");
                Toast.makeText(EnterCardDetails.this, transaction.getReference(), Toast.LENGTH_LONG).show();
                updateTextViews();
                new verifyOnServer().execute(transaction.getReference());
            }

            // This is called only before requesting OTP
            // Save reference so you may send to server if
            // error occurs with OTP
            // No need to dismiss dialog
            @Override
            public void beforeValidate(Transaction transaction) {
                EnterCardDetails.this.transaction = transaction;
                Toast.makeText(EnterCardDetails.this, transaction.getReference(), Toast.LENGTH_LONG).show();
                updateTextViews();
            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                // If an access code has expired, simply ask your server for a new one
                // and restart the charge instead of displaying error
                EnterCardDetails.this.transaction = transaction;
                if (error instanceof ExpiredAccessCodeException) {
                    EnterCardDetails.this.startAFreshCharge(false);
                    EnterCardDetails.this.chargeCard();
                    return;
                }

                dismissDialog();

                if (transaction.getReference() != null) {
                    Toast.makeText(EnterCardDetails.this, transaction.getReference() + " concluded with error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    mTextError.setText(String.format("%s  concluded with error: %s %s", transaction.getReference(), error.getClass().getSimpleName(), error.getMessage()));
                    new verifyOnServer().execute(transaction.getReference());
                } else {
                    Toast.makeText(EnterCardDetails.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    mTextError.setText(String.format("Error: %s %s", error.getClass().getSimpleName(), error.getMessage()));
                }
                updateTextViews();
            }

        });
    }

    private void dismissDialog() {
        if ((dialog != null) && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void updateTextViews() {
        if (transaction.getReference() != null) {
            mTextReference.setText(String.format("Reference: %s", transaction.getReference()));
        } else {
            mTextReference.setText("No transaction");
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.length() < 1;
    }

    private class fetchAccessCodeFromServer extends AsyncTask<String, Void, String> {
        private String error;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                charge.setAccessCode(result);
                chargeCard();
            } else {
                EnterCardDetails.this.mTextBackendMessage.setText(String.format("There was a problem getting a new access code form the backend: %s", error));
                dismissDialog();
            }
        }

        @Override
        protected String doInBackground(String... ac_url) {
            try {
                URL url = new URL(ac_url[0]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));

                String inputLine;
                inputLine = in.readLine();
                in.close();
                return inputLine;
            } catch (Exception e) {
                error = e.getClass().getSimpleName() + ": " + e.getMessage();
            }
            return null;
        }
    }

    private class verifyOnServer extends AsyncTask<String, Void, String> {
        private String reference;
        private String error;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                EnterCardDetails.this.mTextBackendMessage.setText(String.format("Gateway response: %s", result));

            } else {
                EnterCardDetails.this.mTextBackendMessage.setText(String.format("There was a problem verifying %s on the backend: %s ", this.reference, error));
                dismissDialog();
            }
        }

        @Override
        protected String doInBackground(String... reference) {
            try {
                this.reference = reference[0];
                URL url = new URL(backend_url + "/verify/" + this.reference);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));

                String inputLine;
                inputLine = in.readLine();
                in.close();
                return inputLine;
            } catch (Exception e) {
                error = e.getClass().getSimpleName() + ": " + e.getMessage();
            }
            return null;
        }
    }
}
