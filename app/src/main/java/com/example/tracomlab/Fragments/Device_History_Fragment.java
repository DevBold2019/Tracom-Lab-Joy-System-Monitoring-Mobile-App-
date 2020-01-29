package com.example.tracomlab.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tracomlab.Adapters.DeviceHistory_Adapters;
import com.example.tracomlab.Adapters.Devices_Adapter;
import com.example.tracomlab.Adapters.Inventory_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Repair_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Repair;
import com.example.tracomlab.MainUserInteface;
import com.example.tracomlab.Model_Classes.DeviceHistory_Model;
import com.example.tracomlab.Model_Classes.Devices_Model;
import com.example.tracomlab.R;
import com.example.tracomlab.scanner.Scans;
import com.google.zxing.client.android.Intents;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Device_History_Fragment extends Fragment {
    RecyclerView recyclerView;
    List<DeviceHistory_Model> list;
    DeviceHistory_Model model;
    DeviceHistory_Adapters adapter;
    SearchView searchView;
    LinearLayout linearLayout;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_device__history_, container, false);

        searchView = view.findViewById(R.id.SearchDeviceHistorySearchView);

        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button scan_me = view.findViewById(R.id.scan_me);
        scan_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHardware();
            }
        });

        list = new ArrayList<>();

        loadFromApi();

        return view;

    }

    public void checkHardware() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(getActivity(), Scans.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            intent.putExtra("Device_History_Fragment", "Device_History_Fragment");
            startActivity(intent);

            MainUserInteface mainUserInteface = new MainUserInteface();
            mainUserInteface.finish();
        } else {
            Toast.makeText(getActivity(), "your phone can't scan", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadFromApi() {
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

                for (Atlas_Repair repair : atlas_repairs) {
                    String setCustomer = repair.getCustomers();
                    String setSerialNumber = repair.getSerial_number();
                    String setLevels = repair.getLevels();

                    model = new DeviceHistory_Model(setCustomer, setSerialNumber, setLevels);
                    list.add(model);
                }

                adapter = new DeviceHistory_Adapters(list, getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);


                SharedPreferences pref = getContext().getSharedPreferences("storeMyResult", getActivity().MODE_PRIVATE);
                String Search = pref.getString("results", null);

                if (Search != null) {
                    if (Search != "empty") {

                        searchView.setIconified(false);
                        searchView.setQuery(Search, true);
                        searchView.setFocusable(true);
                        searchView.setIconified(false);
                        searchView.requestFocusFromTouch();
                        adapter.filter(Search);
                        searchView.clearFocus();

                        SharedPreferences.Editor prefEdit = pref.edit();
                        prefEdit.clear();
                        prefEdit.apply();
                    }
                }



                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if (list.contains(query)) {
                            adapter.filter(query);
                        } else {
                            Toast.makeText(getContext(), "No Match found", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.filter(newText);
                        return false;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Atlas_Repair>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
