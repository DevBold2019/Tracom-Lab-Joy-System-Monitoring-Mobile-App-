package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.Adapters.Manufacturer_Adapter;
import com.example.tracomlab.Model_Classes.Manufacturer_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;


public class ManufacturerFragment extends Fragment {


    RecyclerView recyclerView;
    List<Manufacturer_Model> list;
    Manufacturer_Model model;
    Manufacturer_Adapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view ;

        view= inflater.inflate(R.layout.fragment_manufacturer, container, false);


        recyclerView=view.findViewById(R.id.manufacturerRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        list=new ArrayList<>();

        for (int i=0; i<20; i++){

            model=new Manufacturer_Model("EVOLIS","evolis@tracom.co.ke","SPRINGETTE","078567845","MAINA","2019-09-12");
            model=new Manufacturer_Model("INGENICO","evolis@tracom.co.ke","Italy","078567845","Giovanni Curtino","2019-09-12");

            list.add(model);



        }

        adapter=new Manufacturer_Adapter(list,container.getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);







        return view ;
    }

}
