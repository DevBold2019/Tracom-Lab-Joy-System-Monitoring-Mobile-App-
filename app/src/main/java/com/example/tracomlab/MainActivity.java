package com.example.tracomlab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tracomlab.ConnectionToRest.RetrofitClient.ServiceGenerator;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Ufs_Authentication_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_Authentication_Model;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    RadioButton RememberMeKidole;
    RadioButton RememberMe;
    ProgressDialog progressDialog;
    TextInputEditText login_email_edt;
   TextInputEditText login_pass_edt;

    String getMail;
    String getPass;
    Dialog dialog;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        progressDialog= new ProgressDialog(this,R.style.loginDialog);
        progressDialog.setMessage("A moment please");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //Email EditText
        login_email_edt = findViewById(R.id.login_email_edt);
        //Password EditText
        login_pass_edt = findViewById(R.id.login_pass_edt);

        //Doesn't login when user logs in the app again
        RememberMe = findViewById(R.id.RememberMe);
        //Next time time user logs in using fingerprint
        RememberMeKidole = findViewById(R.id.RememberMeKidole);


        //*check if the FingerPrint Option will be applicable*//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                RememberMeKidole.setEnabled(false);
                RememberMeKidole.setVisibility(View.GONE);
            } else if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                RememberMeKidole.setEnabled(false);
                RememberMeKidole.setVisibility(View.GONE);
            } else if (!keyguardManager.isKeyguardSecure()) {
                RememberMeKidole.setEnabled(false);
                RememberMeKidole.setVisibility(View.GONE);
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                RememberMeKidole.setEnabled(false);
                RememberMeKidole.setVisibility(View.GONE);
            }

        } else {
            RememberMeKidole.setEnabled(false);
            RememberMeKidole.setVisibility(View.GONE);
        }

    }

    public void NavigateToMainInterface(View view) {

       getMail = login_email_edt.getText().toString();
        getPass = login_pass_edt.getText().toString();

        if (login_email_edt.getText().toString().trim().isEmpty() || login_pass_edt.getText().toString().trim().isEmpty() ){

            Toast.makeText(getApplicationContext(),"Can't Login with null values",Toast.LENGTH_LONG).show();

            login_email_edt.setError("Email is Required");
            login_pass_edt.setError("Password is Required");

            return;
        }

        isNetworkActive();


    }
    //For connectivity check if the  wifi/network is connected to the internet
    private boolean isNetworkActive() {

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //if theres network we want to load more data
        if (netInfo != null &&  netInfo.isConnectedOrConnecting()) {


            loginUser();

            return true;
        }

        Toast.makeText(getApplicationContext(),"check Your network Settings",Toast.LENGTH_LONG).show();
        progressDialog.dismiss();

        return false;
    }

    public void  loginUser(){

        progressDialog.show();

        Ufs_Authentication_Interface ufsAuthenticationInterface = ServiceGenerator.createService(Ufs_Authentication_Interface.class, "captain", "edins");

        Call<Ufs_Authentication_Model> call = ufsAuthenticationInterface.getAuth("password", getMail, getPass);

        call.enqueue(new Callback<Ufs_Authentication_Model>() {
            @Override
            public void onResponse(Call<Ufs_Authentication_Model> call, final Response<Ufs_Authentication_Model> response) {

                if (response.isSuccessful()) {

                    progressDialog.setMessage("Wait a while.....");
                    checkRadioButtons();


                }else if(response.code() == 400){
                    progressDialog.setMessage("wrong username or email");
                    progressDialog.dismiss();


                } else {

                    isNetworkActive();
                    progressDialog.setTitle("An error occurred");
                    progressDialog.setMessage(""+response.code());
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Ufs_Authentication_Model> call, Throwable t) {

                progressDialog.setTitle("An error occured");
                progressDialog.setMessage("Please check your network\n"+t.getMessage());
                progressDialog.dismiss();

            }
        });


    }

    public void checkRadioButtons(){

        String textToSaveEmail = login_email_edt.getText().toString();
        String textToSavePassword = login_pass_edt.getText().toString();

        //*enabling remember me password*//
        if (RememberMe.isChecked()) {

            try {

                //*storing the username*//
                FileOutputStream fileOut = openFileOutput("name.txt", Context.MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
                outputWriter.write(textToSaveEmail);
                outputWriter.close();
            } catch (Exception e) {
                e.printStackTrace();

            }

            getSharedPreferences("TracomLabcom", Context.MODE_PRIVATE)
                    .edit()
                    .putString("username", textToSaveEmail)
                    .putString("password", textToSavePassword)
                    .apply();

            //*enabling remember me password*//

            //*storing the username*//
            try {

                FileOutputStream fileOut = openFileOutput("status.txt", Context.MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
                outputWriter.write("no");
                outputWriter.close();

            } catch (Exception e) {

                e.printStackTrace();

            }
            //*storing the username*//

        } else if (RememberMeKidole.isChecked()) {

            //*enabling remember me password*//
            getSharedPreferences("TracomLabcom", Context.MODE_PRIVATE)
                    .edit()
                    .putString("username", textToSaveEmail)
                    .putString("password", textToSavePassword)
                    .apply();
            //*enabling remember me password*//

            //*storing the username*//
            try {

                FileOutputStream fileOut = openFileOutput("status.txt", Context.MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
                outputWriter.write("yes");
                outputWriter.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            //*storing the username*//
        } else {

            //*storing the username*//
            try {
                FileOutputStream fileOut = openFileOutput("status.txt", Context.MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
                outputWriter.write("nothing");
                outputWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //*storing the username*//
        }

        //*open the application*//
        Intent intent = new Intent(getApplication(), MainUserInteface.class);
        startActivity(intent);
        finish();

    }

    public void GetOtp(View view) {

        Intent intent=new Intent(MainActivity.this,Email_Verification_Activity.class);
        startActivity(intent);
        finish();



    }
}