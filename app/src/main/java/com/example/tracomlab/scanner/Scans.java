package com.example.tracomlab.scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tracomlab.Activities.MainUserInteface;
import com.example.tracomlab.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class Scans extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        scannerView = findViewById(R.id.zxinView);

        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
                Log.e("SCAN QR CODE", "Permission already granted!");
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    this.setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        } else {
            Toast.makeText(this, "your phone does not support barcode reader", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void handleResult(Result result) {
        final String myResult = result.getText();
        Toast.makeText(this, myResult, Toast.LENGTH_SHORT).show();
        getSharedPreferences("scanDevice", Context.MODE_PRIVATE)
                .edit()
                .putString("nameHistory", "DeviceHistory")
                .apply();

        getSharedPreferences("storeMyResult", Context.MODE_PRIVATE)
                .edit()
                .putString("results", myResult)
                .apply();

        Intent intent = new Intent(this, MainUserInteface.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);

        this.finish();

    }

    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        getSharedPreferences("storeMyResult", Context.MODE_PRIVATE)
                .edit()
                .putString("results", "empty")
                .apply();
    }
}
