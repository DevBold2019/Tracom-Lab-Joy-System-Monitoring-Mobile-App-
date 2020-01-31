package com.example.tracomlab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.tracomlab.Fragments.Kidole_Option;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashActivity extends AppCompatActivity {

    int timeout=6000;
    Animation topAnimation,bottomAnimation;
    LottieAnimationView lottieAnimationView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler handler=new Handler();

        lottieAnimationView=findViewById(R.id.lottie);
        textView=findViewById(R.id.textView);


        topAnimation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.bottom_animation);

        lottieAnimationView.setAnimation(topAnimation);
        textView.setAnimation(bottomAnimation);


        handler.postDelayed(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {

                checkForCredentials();

            }
        },timeout);

    }

    //Check if the User had His/Her credentials saved
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkForCredentials(){

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

                    overridePendingTransition(R.anim.go_up_animation,R.anim.go_down_animation);
                    Intent intent = new Intent(getApplication(), MainUserInteface.class);
                    startActivity(intent);
                    finish();

                }else if(stringBuffer.toString().trim().equals("yes")){

                    Kidole_Option fragment = new Kidole_Option();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.splash_fragment, fragment);
                    transaction.commit();

                }else if(stringBuffer.toString().trim().equals("nothing")){

                    overridePendingTransition(R.anim.go_up_animation,R.anim.go_down_animation);
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



            overridePendingTransition(R.anim.top_animation,R.anim.bottom_animation);
            Intent intent=new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
            finish();



        }






    }
}
