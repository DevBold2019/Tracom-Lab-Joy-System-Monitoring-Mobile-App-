package com.example.tracomlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Email_Verification_Activity extends AppCompatActivity {

    Button button;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__verification_);


        button=findViewById(R.id.sendButton);
       textInputLayout=findViewById(R.id.recoveryTextInput);


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String getEmail = textInputLayout.getEditText().getText().toString();

               if ( textInputLayout.getEditText().getText().toString() != null){

                   Toast.makeText(Email_Verification_Activity.this,""+getEmail,Toast.LENGTH_LONG).show();

                   Intent intent=new Intent(Email_Verification_Activity.this,OneTimePasswordActivity.class);
                   startActivity(intent);
                   finish();

                   return;
               }

               Toast.makeText(Email_Verification_Activity.this,"Can't send Email on \n Null Email Address",Toast.LENGTH_LONG).show();
               textInputLayout.getEditText().setError("Blank Email");



           }
       });


    }
}
