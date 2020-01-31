package com.example.tracomlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.goodiebag.pinview.Pinview;

public class OneTimePasswordActivity extends AppCompatActivity {

    Pinview pinview;
    TextView textView;
    TextView textView1;
    Button button;
    String OTP;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_time_password);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dialog= new ProgressDialog(OneTimePasswordActivity.this,R.style.loginDialog);
        dialog.setTitle("Verifying OTP");


        button=findViewById(R.id.verifyButton);
        pinview=findViewById(R.id.pinview);
        textView=findViewById(R.id.textViewTimer);
        textView1=findViewById(R.id.textViewGoToGmail);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pinview.getValue().trim() == null){

                    Toast.makeText(OneTimePasswordActivity.this,"Null OTP",Toast.LENGTH_LONG).show();
                    textView.setText("Resend OTP");

                    return;

                }

                if (pinview.getValue().trim().equals("1234")){

                    verifyOTP();
                    Toast.makeText(OneTimePasswordActivity.this,"OTP  is \t"+pinview.getValue(),Toast.LENGTH_LONG).show();

                    return;

                }

                Toast.makeText(OneTimePasswordActivity.this,"OTP  appears To be Invalid \t"+pinview.getValue(),Toast.LENGTH_LONG).show();

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textView.getText().toString().trim().equalsIgnoreCase("Resend OTP")) {

                    timerOTP();

                    return;
                }
               Trial();

                Toast.makeText(OneTimePasswordActivity.this,"OTP is being Sent",Toast.LENGTH_LONG).show();

            }
        });



        timerOTP();

    }


    public  void verifyOTP(){
        dialog.show();
        dialog.setMessage("A Second please");

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {


                Intent intent=new Intent(OneTimePasswordActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
                finish();

            }
        },6000);


    }


    public void  timerOTP(){

        new CountDownTimer(40000, 1000) {

            public void onTick(long millisUntilFinished) {

                textView.setText("Sending OTP in : " + millisUntilFinished / 1000);
                textView1.setVisibility(View.GONE);


            }

            public void onFinish() {

                textView.setText("Resend OTP ");
                textView1.setVisibility(View.VISIBLE);


            }

        }.start();


    }

    public void incomingNotification(){

       Notification notification = new Notification.Builder(OneTimePasswordActivity.this)
                .setContentText("Password Reset OTP")
                .setContentTitle("Input this OTP")
                .setSmallIcon(R.drawable.girl)
                .setStyle(new Notification.BigTextStyle().bigText("1234"))
                .build();

    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(OneTimePasswordActivity.this);
    notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationManagerCompat.notify(1000, notification);

    }

    public void Trial(){


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.girl)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }


}
