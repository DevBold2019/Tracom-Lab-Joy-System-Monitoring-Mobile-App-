package com.example.tracomlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tracomlab.ConnectionToRest.RetrofitClient.ServiceGenerator;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Ufs_Authentication_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_Authentication_Model;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    RadioButton RememberMeKidole;
    RadioButton RememberMe;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Login In Progress");
        progressDialog.setMessage("Wait a while");

        RememberMeKidole = findViewById(R.id.RememberMeKidole);
        //*check if the Kidole Option will be applicable*//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                RememberMeKidole.setEnabled(false);
            } else if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                RememberMeKidole.setEnabled(false);
            } else if (!keyguardManager.isKeyguardSecure()) {
                RememberMeKidole.setEnabled(false);
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                RememberMeKidole.setEnabled(false);
            }

        } else {
            RememberMeKidole.setEnabled(false);
        }
        //*check if the Kidole Option will be applicable*//

    }

    public void NavigateToMainInterface(View view) {

        final TextInputEditText login_email_edt = findViewById(R.id.login_email_edt);
        final TextInputEditText login_pass_edt = findViewById(R.id.login_pass_edt);

        RememberMe = findViewById(R.id.RememberMe);
        RememberMeKidole = findViewById(R.id.RememberMeKidole);

        String getMail = login_email_edt.getText().toString();
        String getPass = login_pass_edt.getText().toString();

        if (login_email_edt.getText().toString().trim().isEmpty() || login_pass_edt.getText().toString().trim().isEmpty() ){

            Toast.makeText(getApplicationContext(),"Cant Login with null values",Toast.LENGTH_LONG).show();

            login_email_edt.setError("Required");
            login_pass_edt.setError("Required");

            return;
        }

        progressDialog.show();

        Ufs_Authentication_Interface ufsAuthenticationInterface = ServiceGenerator.createService(Ufs_Authentication_Interface.class, "captain", "edins");

        Call<Ufs_Authentication_Model> call = ufsAuthenticationInterface.getAuth("password", getMail, getPass);

        call.enqueue(new Callback<Ufs_Authentication_Model>() {
            @Override
            public void onResponse(Call<Ufs_Authentication_Model> call, final Response<Ufs_Authentication_Model> response) {
                if (response.isSuccessful()) {

                    String textToSaveEmail = login_email_edt.getText().toString();
                    String textToSavePassword = login_pass_edt.getText().toString();

                    //*storing the username*//
                    try {
                        FileOutputStream fileOut = openFileOutput("name.txt", Context.MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
                        outputWriter.write(textToSaveEmail);
                        outputWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //*storing the username*//

                    //*radio button action listener*//
                    if (RememberMe.isChecked()) {

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
                    //*radio button action listener*//

                    //*open the application*//
                    Intent intent = new Intent(getApplication(), MainUserInteface.class);
                    startActivity(intent);
                    finish();
                    //*open the application*//
                } else {

                    Toast.makeText(getApplication(), "" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Ufs_Authentication_Model> call, Throwable t) {
                Toast.makeText(getApplication(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                progressDialog.setMessage("Network error\n Please check your network");

            }
        });
    }
}
