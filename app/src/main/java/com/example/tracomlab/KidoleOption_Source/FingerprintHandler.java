package com.example.tracomlab.KidoleOption_Source;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.tracomlab.R;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    public FingerprintHandler(Context context){

        this.context = context;

    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("There was an Auth Error. " + errString, false);

    }

    @Override
    public void onAuthenticationFailed() {

        this.update("Auth Failed. ", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error: " + helpString, false);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        this.update("done", true);


    }

    public void update(String s, boolean b) {

        final TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.text_kidole);
        final ConstraintLayout icon_kidole = ((Activity)context).findViewById(R.id.icon_kidole);
        if(paraLabel != null && icon_kidole != null) {
            paraLabel.setText(s);
            if (b == false) {

                //*******//
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                        paraLabel.setText("Place your Finger on the Scanner to Proceed");
                        paraLabel.setTypeface(paraLabel.getTypeface(), Typeface.BOLD);
                        icon_kidole.setBackgroundResource(R.drawable.kidole);
                    }
                }, 500);
                //*******//

                paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
                paraLabel.setTypeface(paraLabel.getTypeface(), Typeface.BOLD);
                icon_kidole.setBackgroundResource(R.drawable.fingerprint_error);
            } else {
                paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                paraLabel.setTypeface(paraLabel.getTypeface(), Typeface.BOLD);
                icon_kidole.setBackgroundResource(R.drawable.kidole_check);
            }
        }

    }

}

