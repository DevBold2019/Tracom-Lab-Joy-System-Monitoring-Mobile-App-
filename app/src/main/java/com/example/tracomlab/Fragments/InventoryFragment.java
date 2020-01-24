package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.Adapters.Inventory_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Parts_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Parts;
import com.example.tracomlab.Model_Classes.Inventory_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryFragment extends Fragment {

    RecyclerView recyclerView;
    List<Inventory_Model> list;
    Inventory_Model model;
    Inventory_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;


        view = inflater.inflate(R.layout.fragment_inventory, container, false);


        recyclerView = view.findViewById(R.id.inventoryRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        list = new ArrayList<>();

        MainClient mainClient = new MainClient();
        Atlas_Parts_Interface parts_interface = mainClient.getApiClient().create(Atlas_Parts_Interface.class);

        Call<List<Atlas_Parts>> call;
        call = parts_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Parts>>() {
            @Override
            public void onResponse(Call<List<Atlas_Parts>> call, Response<List<Atlas_Parts>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Parts> atlas_parts = response.body();

                for (Atlas_Parts parts : atlas_parts) {

                    String setStatus = parts.getAction_status();
                    String setPartModel = parts.getPart_model();
                    String setPartName = parts.getPart_name();
                    String setPartNumber = parts.getPart_number();
                    String setPartDescription = parts.getDescription();
                    String setManufacturer = parts.getManufacturer_name();

                    model = new Inventory_Model(setStatus, setPartModel.toUpperCase(), setPartName, setPartNumber, setPartDescription, setManufacturer);
                    list.add(model);

                }
                adapter = new Inventory_Adapter(list, container.getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Atlas_Parts>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
