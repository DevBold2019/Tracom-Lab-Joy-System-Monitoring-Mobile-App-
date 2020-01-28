package com.example.tracomlab.Fragments;


import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.tracomlab.R;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

/**
 * A simple {@link Fragment} subclass.
 */
public class Scanner extends Fragment implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;

    public Scanner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        /// getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        View view = inflater.inflate(R.layout.fragment_scanner, container, false);


        scannerView = view.findViewById(R.id.zxscan);
        scannerView.forceLayout();


        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
                Log.e("SCAN QR CODE", "Permission already granted!");
            } else {
                requestPermission();
            }
        }
//        scannerView = new ZXingScannerView(getActivity().getApplicationContext());
//
//        scannerView.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));
//        scan_launch.addView(scannerView);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(getActivity());
                    getActivity().setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        } else {
            Toast.makeText(getActivity(), "your phone does not support barcode reader", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getActivity(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void handleResult(Result result) {
        final String myResult = result.getText();
        Toast.makeText(getActivity(), myResult, Toast.LENGTH_SHORT).show();
        scannerView.removeAllViews();
        scannerView.stopCamera();

    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }
}
