package com.example.tracomlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Ufs_Authentication_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Ufs_Otp_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_Authentication_Model;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_Otp;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Email_Verification_Activity extends AppCompatActivity {

    Button button;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__verification_);


        button = findViewById(R.id.sendButton);
        textInputLayout = findViewById(R.id.recoveryTextInput);




        button.setOnClickListener(view -> {

            String getEmail = textInputLayout.getEditText().getText().toString();

            if (textInputLayout.getEditText().getText().toString().trim().isEmpty()) {


                textInputLayout.getEditText().setError("");

                Toast.makeText(Email_Verification_Activity.this, "Can't send Email on \n Null Email Address", Toast.LENGTH_LONG).show();
                textInputLayout.getEditText().setError("Blank Email");

                return;
            } else {
                checkEmail();

            }


        });


    }

    public void checkEmail() {
        String getEmail = textInputLayout.getEditText().getText().toString();

        MainClient mainClient = new MainClient();
        Ufs_Authentication_Interface ufsAuthenticationInterface = mainClient.getApiClient().create(Ufs_Authentication_Interface.class);

        Call<Ufs_Authentication_Model> call;
        call = ufsAuthenticationInterface.findUsername(getEmail);

        call.enqueue(new Callback<Ufs_Authentication_Model>() {
            @Override
            public void onResponse(Call<Ufs_Authentication_Model> call, Response<Ufs_Authentication_Model> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Email_Verification_Activity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Ufs_Authentication_Model auth = response.body();

                if (getEmail.equals(auth.getUsername())) {

                    getSharedPreferences("otpEmail", Context.MODE_PRIVATE)
                            .edit()
                            .putString("email", getEmail)
                            .apply();

                    generateOtp();

                    Toast.makeText(Email_Verification_Activity.this, "" + getEmail, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Email_Verification_Activity.this, OneTimePasswordActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Email_Verification_Activity.this, "your email does not exist", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Ufs_Authentication_Model> call, Throwable t) {
                Toast.makeText(Email_Verification_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void generateOtp() {
        MainClient mainClient = new MainClient();

        Ufs_Otp otp = new Ufs_Otp();

        Ufs_Otp_Interface ufsOtpInterface = mainClient.getApiClient().create(Ufs_Otp_Interface.class);

        Call<List<Ufs_Otp>> call;
        call = ufsOtpInterface.getFullList(otp);

        call.enqueue(new Callback<List<Ufs_Otp>>() {
            @Override
            public void onResponse(Call<List<Ufs_Otp>> call, Response<List<Ufs_Otp>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Email_Verification_Activity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<Ufs_Otp>> call, Throwable t) {
                Toast.makeText(Email_Verification_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.go_up_animation,R.anim.go_down_animation);
        Intent intent = new Intent(Email_Verification_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
