package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Drop_Off_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Delivery_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Delivery;
import com.example.tracomlab.Model_Classes.Drop_Off_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dropOffFragment extends Fragment {

    RecyclerView recyclerView;
    List<Drop_Off_Model>list;
    Drop_Off_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;

        view=inflater.inflate(R.layout.fragment_drop_off, container, false);


        recyclerView=view.findViewById(R.id.dropOffRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);


        list=new ArrayList<>();


        for (int i=0; i< 50; i++){

         Drop_Off_Model model=new Drop_Off_Model("12345678","Engineer Ephantus");

            list.add(model);


        }

        adapter=new Drop_Off_Adapter(list,getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);



        return view;
    }

}
