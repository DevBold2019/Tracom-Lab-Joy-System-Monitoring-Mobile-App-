package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Devices_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Devices_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Devices;
import com.example.tracomlab.Model_Classes.Devices_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DevicesFragment extends Fragment {

    RecyclerView recyclerView;
    List<Devices_Model> list;
    Devices_Model model;
    Devices_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
       View view;


       view=  inflater.inflate(R.layout.fragment_devices, container, false);



        recyclerView=view.findViewById(R.id.devicesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);


        list=new ArrayList<>();

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

                for(Atlas_Devices atlasDevices : atlas_devices){
                    String setPartNumber = atlasDevices.part_number;
                    String setSerialNumber = atlasDevices.serial_number;
                    String setDevicemodel = atlasDevices.device_model;
                    String setDeviceOwner = atlasDevices.device_owner;
                    String setCreationDate = atlasDevices.creation_date;
                    System.err.println(setPartNumber);
                    System.err.println(setSerialNumber);
                    System.err.println(setDeviceOwner);


                    model=new Devices_Model(setSerialNumber,setDevicemodel,setPartNumber,setDeviceOwner,setCreationDate);
                    list.add(model);
                }

                adapter=new Devices_Adapter(list, getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Atlas_Devices>> call, Throwable t) {
                Toast.makeText(getContext(),""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}