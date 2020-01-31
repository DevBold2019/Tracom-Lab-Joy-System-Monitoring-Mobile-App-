package com.example.tracomlab.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.example.tracomlab.Fragments.Kidole_Option;
import com.example.tracomlab.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashActivity extends AppCompatActivity {

    int timeout=3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {

                SharedPreferences pref = getSharedPreferences("TracomLabcom", Context.MODE_PRIVATE);
                String username = pref.getString("username", null);
                String password = pref.getString("password", null);

                if(username != null && password != null){
                    //*retrieve the status*//
                    try {
                        FileInputStream fileInputStream = openFileInput("status.txt");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuffer stringBuffer = new StringBuffer();

                        String lines;
                        while ((lines = bufferedReader.readLine()) != null) {
                            stringBuffer.append(lines + "\n");
                        }

                        if(stringBuffer.toString().trim().equals("no")){

                            Intent intent = new Intent(getApplication(), MainUserInteface.class);
                            startActivity(intent);
                            finish();

                        }else if(stringBuffer.toString().trim().equals("yes")){

                            Kidole_Option fragment = new Kidole_Option();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.splash_fragment, fragment);
                            transaction.commit();

                        }else if(stringBuffer.toString().trim().equals("nothing")){
                            Intent intent=new Intent(getApplication(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //*retrieve the status*//
                }else{
                    Intent intent=new Intent(getApplication(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },timeout);

    }
}
