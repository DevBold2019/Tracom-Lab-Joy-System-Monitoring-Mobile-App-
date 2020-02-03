package com.example.tracomlab.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.Adapters.Manufacturer_Adapter;
import com.example.tracomlab.Adapters.Unrepairable_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Repair_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Repair;
import com.example.tracomlab.Model_Classes.Manufacturer_Model;
import com.example.tracomlab.Model_Classes.Unrepairable_Model;
import com.example.tracomlab.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepairFragment extends Fragment {

    View view;
    CardView cardProgress, cardRepair;
    FloatingActionButton repairBack, progressBack;
    ConstraintLayout dashRepairPage, progressPage, RepairPage;
    TableLayout table_progress, table_repair;
    Context context = null;
    RecyclerView recyclerView;
    List<Unrepairable_Model> list;
    Unrepairable_Model model;
    Unrepairable_Adapter adapter;
    int addCount = 0;
    TableRow tableRow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_repair, container, false);

        cardProgress = view.findViewById(R.id.cardProgress);
        cardRepair = view.findViewById(R.id.cardRepair);

        progressBack = view.findViewById(R.id.progressBack);
        repairBack = view.findViewById(R.id.repairBack);

        dashRepairPage = view.findViewById(R.id.dashRepairPage);
        progressPage = view.findViewById(R.id.progressPage);
        RepairPage = view.findViewById(R.id.RepairPage);

        table_progress = view.findViewById(R.id.table_progress);

        recyclerView = view.findViewById(R.id.recyclerViewRepair);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        context = getActivity().getApplicationContext();

        cardProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goProgress();
            }
        });

        cardRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRepair();
            }
        });

        progressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backProgress();
            }
        });

        repairBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backRepair();
            }
        });

        checkForNetwork();



        return view;
    }

    private void goProgress() {

        if (dashRepairPage.getVisibility() == View.VISIBLE) {
            dashRepairPage.setVisibility(View.GONE);
            progressPage.setVisibility(View.VISIBLE);

            if(addCount == 0){
                loadProgressData();
                addCount = addCount + 1;
            }else{
                addCount = addCount + 1;
            }

        }
    }

    private void goRepair() {

        if (dashRepairPage.getVisibility() == View.VISIBLE) {
            dashRepairPage.setVisibility(View.GONE);
            RepairPage.setVisibility(View.VISIBLE);
            loadRepairData();

        }
    }

    private void backProgress() {

        if (dashRepairPage.getVisibility() != View.VISIBLE) {
            progressPage.setVisibility(View.GONE);
            dashRepairPage.setVisibility(View.VISIBLE);
        }
    }

    private void backRepair() {

        if (dashRepairPage.getVisibility() != View.VISIBLE) {
            RepairPage.setVisibility(View.GONE);
            dashRepairPage.setVisibility(View.VISIBLE);
        }
    }

    private void loadProgressData() {

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

                Map<String, Integer> pending = new HashMap<>();
                Map<String, Integer> delivered = new HashMap<>();

                for (Atlas_Repair repair : atlas_repairs) {

                    String ClientName = repair.getCustomers();
                    String RepairStatus = repair.getRepair_status();

                    if (RepairStatus != null) {
                        if (RepairStatus.equals("Pending")) {

                            if (pending.keySet().contains(ClientName)) {
                                pending.put(ClientName, pending.get(ClientName) + 1);

                            } else {
                                pending.put(ClientName, 1);
                            }

                        } else {

                            if (delivered.keySet().contains(ClientName)) {
                                delivered.put(ClientName, delivered.get(ClientName) + 1);

                            } else {
                                delivered.put(ClientName, 1);
                            }
                        }
                    }

                }

                int i = 1;

                for (Map.Entry<String, Integer> deliveryList : delivered.entrySet()) {


                    for (Map.Entry<String, Integer> pendingList : pending.entrySet()) {

                        if (deliveryList.getKey().equalsIgnoreCase(pendingList.getKey())) {
                            tableRow = new TableRow(context);

                            if (i % 2 == 0) {
                                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                tableRow.setLayoutParams(layoutParams);

                                String sum = Integer.toString(pendingList.getValue() + deliveryList.getValue());
                                String delv = Integer.toString(deliveryList.getValue());

                                TextView textCustomer = new TextView(getContext());
                                textCustomer.setTextColor(getResources().getColor(R.color.colorWhite));
                                textCustomer.setBackgroundResource(R.drawable.cellrowheader);
                                textCustomer.setGravity(Gravity.LEFT);
                                textCustomer.setPadding(10,10,10,10);
                                textCustomer.setText(deliveryList.getKey());
                                tableRow.addView(textCustomer, 0);

                                TextView textm = new TextView(getContext());
                                textm.setPadding(10,10,10,10);
                                textm.setTextColor(getResources().getColor(R.color.colorWhite));
                                textm.setBackgroundResource(R.drawable.cellrowheader);
                                textm.setGravity(Gravity.RIGHT);
                                textm.setText(delv + "/" + sum);
                                tableRow.addView(textm, 1);

                                table_progress.addView(tableRow);

                                i = i + 1;
                            } else {
                                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                tableRow.setLayoutParams(layoutParams);

                                String sum = Integer.toString(pendingList.getValue() + deliveryList.getValue());
                                String delv = Integer.toString(deliveryList.getValue());

                                TextView textCustomer = new TextView(getContext());
                                textCustomer.setPadding(10,10,10,10);
                                textCustomer.setTextColor(getResources().getColor(R.color.colorBackground));
                                textCustomer.setBackgroundResource(R.drawable.cellrowbody);
                                textCustomer.setGravity(Gravity.LEFT);
                                textCustomer.setText(deliveryList.getKey());
                                tableRow.addView(textCustomer, 0);

                                TextView Count = new TextView(getContext());
                                Count.setPadding(10,10,10,10);
                                Count.setTextColor(getResources().getColor(R.color.colorBackground));
                                Count.setBackgroundResource(R.drawable.cellrowbody);
                                Count.setGravity(Gravity.RIGHT);
                                Count.setText(delv + "/" + sum);
                                tableRow.addView(Count, 1);

                                table_progress.addView(tableRow);

                                i = i + 1;
                            }

                        }
                    }

                }


            }

            @Override
            public void onFailure(Call<List<Atlas_Repair>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadRepairData() {

        MainClient mainClient = new MainClient();
        Atlas_Repair_Interface repair_interface = mainClient.getApiClient().create(Atlas_Repair_Interface.class);

        list = new ArrayList<>();

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

                    String customer = repair.getCustomers();
                    String serial = repair.getSerial_number();
                    String batch = repair.getBatch_number();
                    String status = repair.getRepair_status();

                    if (status != null) {
                        if (status.equals("Pending")) {

                            model = new Unrepairable_Model(customer, serial, batch);
                            list.add(model);
                        }
                    }
                }
                adapter = new Unrepairable_Adapter(list, getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Atlas_Repair>> call, Throwable t) {
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


            goProgress();
            goProgress();
            backProgress();
            backRepair();
            loadRepairData();


            return true;
        }
        Toast.makeText(getContext(),"Check your network",Toast.LENGTH_LONG).show();

        return false;
    }


}
