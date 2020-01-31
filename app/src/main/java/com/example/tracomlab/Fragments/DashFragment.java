package com.example.tracomlab.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Delivery_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Devices_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Repair_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Shipped_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Delivery;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Devices;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Repair;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Shipped;
import com.example.tracomlab.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashFragment extends Fragment {

    private PieChart PieChart;
   private TextView onboardedDevices, ReceivedDevices, ShippedDevices, RepairedDevices, DeliveredDevice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_dash, container, false);

        PieChart = view.findViewById(R.id.summaryPie);
        onboardedDevices = view.findViewById(R.id.onboardedDevices);
        ReceivedDevices = view.findViewById(R.id.ReceivedDevices);
        ShippedDevices = view.findViewById(R.id.ShippedDevices);
        RepairedDevices = view.findViewById(R.id.RepairedDevices);
        DeliveredDevice = view.findViewById(R.id.DeliveredDevice);


        checkForNetwork();



        return view;

    }

    //Getting Generate Data to Pie chart Devices
    private void loadDataToPieChart(){

        final List<PieEntry> testingList = new ArrayList<>();

        MainClient mainClient = new MainClient();
        Atlas_Repair_Interface repair_interface = mainClient.getApiClient().create(Atlas_Repair_Interface.class);

        Call<List<Atlas_Repair>> call;
        call = repair_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Repair>>() {
            @Override
            public void onResponse(Call<List<Atlas_Repair>> call, Response<List<Atlas_Repair>> response) {

                //If response is not Sucessfull then A message should Appear
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_LONG).show();

                    return;
                }

                List<Atlas_Repair> atlas_repairs = response.body();

                int level_one = 0;
                int level_two = 0;
                int level_three = 0;
                int level_four = 0;

                for (Atlas_Repair atlasRepair : atlas_repairs) {

                    //Getting data from the model class
                    String setLevel = atlasRepair.getLevels();
                    String setTestResults = atlasRepair.getQa_test_status();
                    String setRepairedStatus = atlasRepair.getRepair_status();


                    if (setRepairedStatus != null && setRepairedStatus.equals("Repaired")) {

                        if (setTestResults != null && setTestResults.equals("Failed")) {

                            if (setLevel != null && setLevel.equals("LEVEL 1")) {
                                level_one = level_one + 1;
                            }
                            else if (setLevel != null && setLevel.equals("LEVEL 2")) {
                                level_two = level_two + 1;
                            }
                            else if (setLevel != null && setLevel.equals("LEVEL 3")) {
                                level_three = level_three + 1;
                            }
                            else if (setLevel != null && setLevel.equals("LEVEL 4")) {
                                level_four = level_four + 1;
                            }
                        }

                    } else if (setRepairedStatus != null && setRepairedStatus.equals("Pending")) {

                        if (setLevel != null && setLevel.equals("LEVEL 1")) {
                            level_one = level_one + 1;
                        }
                        else if (setLevel != null && setLevel.equals("LEVEL 2")) {
                            level_two = level_two + 1;
                        }
                        else if (setLevel != null && setLevel.equals("LEVEL 3")) {
                            level_three = level_three + 1;
                        }
                        else if (setLevel != null && setLevel.equals("LEVEL 4")) {
                            level_four = level_four + 1;
                        }
                    }

                }


                //populate the pie chart with Data got from the API call
                int[] terminalNumbers = {(int) level_one, (int) level_two, (int) level_three, (int) level_four};
                String[] Levels = {"Level 1", "Level 2", "Level 3", "Level 4"};

                for (int i = 0; i < terminalNumbers.length; i++) {

                    testingList.add(new PieEntry(terminalNumbers[i], Levels[i]));

                }

                PieDataSet dataSet = new PieDataSet(testingList, " Repair Levels");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                PieData data = new PieData(dataSet);
                PieChart.setData(data);
                PieChart.animateX(3000);
                PieChart.invalidate();
                //populate the pie chart
            }

            @Override
            public void onFailure(Call<List<Atlas_Repair>> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Getting Onboarded Devices
    private void getOnboardedDevices() {

        MainClient mainClient = new MainClient();
        Atlas_Devices_Interface atlasDevicesInterface = mainClient.getApiClient().create(Atlas_Devices_Interface.class);

        Call<List<Atlas_Devices>> call;
        call = atlasDevicesInterface.getFullList();

        call.enqueue(new Callback<List<Atlas_Devices>>() {
            @Override
            public void onResponse(Call<List<Atlas_Devices>> call, Response<List<Atlas_Devices>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Atlas_Devices> atlas_devices = response.body();

                int count = atlas_devices.size();
                onboardedDevices.setText(Integer.toString(count));
            }

            @Override
            public void onFailure(Call<List<Atlas_Devices>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Getting Received Devices
    private void getReceivedDevices() {
        MainClient mainClient = new MainClient();
        Atlas_Repair_Interface repair_interface = mainClient.getApiClient().create(Atlas_Repair_Interface.class);

        Call<List<Atlas_Repair>> call;
        call = repair_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Repair>>() {
            @Override
            public void onResponse(Call<List<Atlas_Repair>> call, Response<List<Atlas_Repair>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Atlas_Repair> atlas_repairs = response.body();

                int count = 0;
                for (Atlas_Repair repair : atlas_repairs) {
                    count = count + 1;
                }
                ReceivedDevices.setText(Integer.toString(count));
            }

            @Override
            public void onFailure(Call<List<Atlas_Repair>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Getting Shipped Devices
    private void getShippedDevices() {

        MainClient mainClient = new MainClient();
        Atlas_Shipped_Interface shipped_interface = mainClient.getApiClient().create(Atlas_Shipped_Interface.class);

        Call<List<Atlas_Shipped>> call;
        call = shipped_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Shipped>>() {
            @Override
            public void onResponse(Call<List<Atlas_Shipped>> call, Response<List<Atlas_Shipped>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Shipped> atlas_shipped = response.body();

                int count = atlas_shipped.size();

                ShippedDevices.setText(Integer.toString(count));

            }

            @Override
            public void onFailure(Call<List<Atlas_Shipped>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Getting Repaired Devices
    private void getRepairedDevices() {

        MainClient mainClient = new MainClient();
        Atlas_Repair_Interface repair_interface = mainClient.getApiClient().create(Atlas_Repair_Interface.class);

        Call<List<Atlas_Repair>> call;
        call = repair_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Repair>>() {
            @Override
            public void onResponse(Call<List<Atlas_Repair>> call, Response<List<Atlas_Repair>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Atlas_Repair> atlas_repairs = response.body();

                int count = 0;
                for (Atlas_Repair repair : atlas_repairs) {

                    if (repair.getRepair_status() != null) {
                        if (repair.getRepair_status().equals("Repaired")) {
                            count = count + 1;
                        }
                    }
                }
                RepairedDevices.setText(Integer.toString(count));
            }

            @Override
            public void onFailure(Call<List<Atlas_Repair>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Getting Delivered Devices
    private void getDeliveredDevices() {


        MainClient mainClient = new MainClient();
        Atlas_Delivery_Interface atlasDeliveryInterface = mainClient.getApiClient().create(Atlas_Delivery_Interface.class);

        Call<List<Atlas_Delivery>> call;
        call = atlasDeliveryInterface.getFullList();

        call.enqueue(new Callback<List<Atlas_Delivery>>() {
            @Override
            public void onResponse(Call<List<Atlas_Delivery>> call, Response<List<Atlas_Delivery>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Delivery> atlas_deliveries = response.body();

                int count = 0;
                for (Atlas_Delivery delivery : atlas_deliveries) {

                    String status = delivery.getDelivery_status();

                    if (status.equals("Delivered")) {
                        count = count + 1;
                    }
                }
                DeliveredDevice.setText(Integer.toString(count));
            }

            @Override
            public void onFailure(Call<List<Atlas_Delivery>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //For connectivity check if the  wifi/network is connected to the internet
    private boolean checkForNetwork() {

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //if there's network we want to load more data
        if (netInfo != null &&  netInfo.isConnectedOrConnecting()) {

            loadDataToPieChart();
            getDeliveredDevices();
            getOnboardedDevices();
            getReceivedDevices();
            getShippedDevices();
            getRepairedDevices();


            return true;
        }
        Toast.makeText(getContext(),"Check your network",Toast.LENGTH_LONG).show();

        return false;
    }

}
