package com.example.tracomlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordActivity extends AppCompatActivity {

    TextInputLayout textInputLayout,textInputLayout1;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        dialog= new ProgressDialog(ResetPasswordActivity.this,R.style.loginDialog);
        dialog.setTitle("Saving Password");

        textInputLayout=findViewById(R.id.resetPassword);
        textInputLayout1=findViewById(R.id.confirmResetPassword);
    }

    public void GoToMainScreen(View view) {

        String password =textInputLayout.getEditText().getText().toString().trim();
        String confirmPassword =textInputLayout1.getEditText().getText().toString().trim();


        if ( password.equals(confirmPassword)) {

            confirmPassword();

            return;

        }

        Toast.makeText(getApplicationContext(),"Passwords Don't Match",Toast.LENGTH_LONG).show();
        textInputLayout.getEditText().setError("");
        textInputLayout1.getEditText().setError("");


    }



    public  void confirmPassword(){
        dialog.show();
        dialog.setMessage("A  while.....");

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {


                Intent intent=new Intent(ResetPasswordActivity.this, MainUserInteface.class);
                startActivity(intent);
                finish();


            }
        },4000);


    }



}
