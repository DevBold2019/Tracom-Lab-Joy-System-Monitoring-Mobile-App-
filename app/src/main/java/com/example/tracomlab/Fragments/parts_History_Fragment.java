package com.example.tracomlab.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tracomlab.Adapters.Parts_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Parts_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Parts;
import com.example.tracomlab.Model_Classes.Parts_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class parts_History_Fragment extends Fragment {

    RecyclerView recyclerView;
    List<Parts_Model> list;
    Parts_Model model;
    Parts_Adapter adapter;
    SearchView searchView;
    LinearLayout linearLayout;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parts__history_, container, false);

        searchView = view.findViewById(R.id.searchPartsSearchView);

        recyclerView = view.findViewById(R.id.partsHistoryRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();


        checkForNetwork();


        return view;


    }



    //For connectivity check if the  wifi/network is connected to the internet
    private boolean checkForNetwork() {

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //if there's network we want to load more data
        if (netInfo != null &&  netInfo.isConnectedOrConnecting()) {


            populateParts();

            return true;
        }
        Toast.makeText(getContext(),"Check your network",Toast.LENGTH_LONG).show();

        return false;
    }

    private void populateParts() {

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

                for(Atlas_Parts parts : atlas_parts){

                    String setManufacturer = parts.getManufacturer_name();
                    String setPartModel = parts.getPart_model();
                    String setPartName = parts.getPart_name();
                    String setDescription = parts.getDescription();
                    String setPartNumber = parts.getPart_number();

                    model = new Parts_Model(setManufacturer,setPartModel,setPartName,setDescription,setPartNumber);
                    list.add(model);
                }

                adapter = new Parts_Adapter(list,getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

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
            public void onFailure(Call<List<Atlas_Parts>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });






    }


}
