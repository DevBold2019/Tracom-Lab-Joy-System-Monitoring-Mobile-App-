package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Customers_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Customers_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Customers;
import com.example.tracomlab.Model_Classes.Customers_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerFragment extends Fragment {

    RecyclerView recyclerView;
    List<Customers_Model>list;
    Customers_Model model;
    Customers_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;

        view= inflater.inflate(R.layout.fragment_customer, container, false);

        recyclerView=view.findViewById(R.id.customersRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list=new ArrayList<>();


        MainClient mainClient = new MainClient();
        Atlas_Customers_Interface atlasCustomersInterface = mainClient.getApiClient().create(Atlas_Customers_Interface.class);

        Call<List<Atlas_Customers>> call;
        call = atlasCustomersInterface.getFullList();

        call.enqueue(new Callback<List<Atlas_Customers>>() {
            @Override
            public void onResponse(Call<List<Atlas_Customers>> call, Response<List<Atlas_Customers>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Customers> atlas_customers = response.body();

                for(Atlas_Customers atlasCustomers : atlas_customers){

                    String setCustomername = atlasCustomers.getCustomer_name();
                    String setCountry = atlasCustomers.getCountry();
                    String setAddress = atlasCustomers.getAddress();
                    String setContactPerson = atlasCustomers.getContact_person();

                    model=new Customers_Model(setCustomername,setCountry,setAddress,setContactPerson);
                    list.add(model);

                }

                adapter=new Customers_Adapter(getContext(),list);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Atlas_Customers>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
