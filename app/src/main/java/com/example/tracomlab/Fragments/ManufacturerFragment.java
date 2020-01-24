package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.Adapters.Manufacturer_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Manufacturers_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Manufacturers;
import com.example.tracomlab.Model_Classes.Manufacturer_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManufacturerFragment extends Fragment {


    RecyclerView recyclerView;
    List<Manufacturer_Model> list;
    Manufacturer_Model model;
    Manufacturer_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;

        view = inflater.inflate(R.layout.fragment_manufacturer, container, false);

        recyclerView = view.findViewById(R.id.manufacturerRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();

        MainClient mainClient = new MainClient();
        Atlas_Manufacturers_Interface manufacturers_interface = mainClient.getApiClient().create(Atlas_Manufacturers_Interface.class);

        Call<List<Atlas_Manufacturers>> call;
        call = manufacturers_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Manufacturers>>() {
            @Override
            public void onResponse(Call<List<Atlas_Manufacturers>> call, Response<List<Atlas_Manufacturers>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Manufacturers> atlas_manufacturers = response.body();

                for (Atlas_Manufacturers atlasManufacturers : atlas_manufacturers) {

                    String setManufacturerName = atlasManufacturers.getManufacturer_name();
                    String setEmail = atlasManufacturers.getEmail_address();
                    String setAddress = atlasManufacturers.getAddress();
                    String setPhoneNumber = atlasManufacturers.getPhone_number();
                    String setContactPerson = atlasManufacturers.getContact_person();
                    String setCreatedOn = atlasManufacturers.getCreation_date();

                    model = new Manufacturer_Model(setManufacturerName, setEmail, setAddress, setPhoneNumber, setContactPerson, setCreatedOn);
                    list.add(model);

                }

                adapter = new Manufacturer_Adapter(list, getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Atlas_Manufacturers>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
