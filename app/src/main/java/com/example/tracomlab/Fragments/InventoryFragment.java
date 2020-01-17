package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.Adapters.Inventory_Adapter;
import com.example.tracomlab.Model_Classes.Inventory_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryFragment extends Fragment {

    RecyclerView recyclerView;
    List<Inventory_Model> list;
    Inventory_Model model;
    Inventory_Adapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;


        view=inflater.inflate(R.layout.fragment_inventory, container, false);


        recyclerView=view.findViewById(R.id.inventoryRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        list=new ArrayList<>();

        for (int i=0; i<20; i++){


            model=new Inventory_Model("Approved","IWL220","BUcomponent","189700418","Backupbattery","Ingenico");
            list.add(model);

        }


       adapter=new Inventory_Adapter(list,container.getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);





        return view;
    }

}
