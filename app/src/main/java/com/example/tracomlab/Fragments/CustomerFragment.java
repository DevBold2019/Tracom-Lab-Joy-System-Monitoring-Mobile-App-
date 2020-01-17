package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Customers_Adapter;
import com.example.tracomlab.Model_Classes.Customers_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;


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


        for (int i=0; i<20; i++){


            model=new Customers_Model("COOPERATIVE BANK MERCHANT","KENYA","Card Business","Francis Kinyajui");

            list.add(model);



        }

        adapter=new Customers_Adapter(getContext(),list);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);



        return view;
    }

}
