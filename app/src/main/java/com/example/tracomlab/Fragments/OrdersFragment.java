package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Pick_Up_Adapter;
import com.example.tracomlab.Model_Classes.Pick_Up_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;


public class OrdersFragment extends Fragment {

 /*   TabLayout tbl;
    ViewPager viewPager;
*/
    RecyclerView recyclerView;
    List<Pick_Up_Model> list;
   Pick_Up_Adapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
         view=inflater.inflate(R.layout.fragment_orders, container, false);


        recyclerView=view.findViewById(R.id.pickUpRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);




        list=new ArrayList<>();

        for (int i=0; i<5; i++){

           Pick_Up_Model model= new Pick_Up_Model("123456789","Equity Bank","450","Karen","12:00 hrs");

            list.add(model);

        }

        adapter=new Pick_Up_Adapter(list,getContext());
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new Pick_Up_Adapter.OnItemClickListener() {

            @Override
            public void onItemClick(ImageButton imagebutton, View view, Pick_Up_Model model, int position) {

                Toast.makeText(getContext(), "Accepted", Toast.LENGTH_SHORT).show();
                list.remove(list.size()-1);

                adapter.notifyDataSetChanged();

                Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();



            }
        });

     return view;

    }

}
