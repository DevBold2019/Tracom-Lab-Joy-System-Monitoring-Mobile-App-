package com.example.tracomlab.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.Adapters.Pick_Up_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Orders_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Orders;
import com.example.tracomlab.Model_Classes.Pick_Up_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrdersFragment extends Fragment {

    /*   TabLayout tbl;
       ViewPager viewPager;
   */
    RecyclerView recyclerView;
    List<Pick_Up_Model> list;
    Pick_Up_Adapter adapter;
    public TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view = inflater.inflate(R.layout.fragment_orders, container, false);


        recyclerView = view.findViewById(R.id.pickUpRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        textView = view.findViewById(R.id.OrderID);


        list = new ArrayList<>();
        adapter = new Pick_Up_Adapter(list, getContext());

        MainClient mainClient = new MainClient();
        Atlas_Orders_Interface orders_interface = mainClient.getApiClient().create(Atlas_Orders_Interface.class);

        Call<List<Atlas_Orders>> call;
        call = orders_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Orders>>() {
            @Override
            public void onResponse(Call<List<Atlas_Orders>> call, Response<List<Atlas_Orders>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Orders> atlas_orders = response.body();

                for (Atlas_Orders orders : atlas_orders) {
                    if (!(orders.getAction_status().isEmpty())) {
                        if (orders.getAction_status().equalsIgnoreCase("Unapproved")) {
                            String setOrderId = String.valueOf(orders.getOrder_id());
                            String setQtPurchased = orders.getQt_purchased();
                            String setDescription = orders.getDescription();

                            Pick_Up_Model model = new Pick_Up_Model(setOrderId, setQtPurchased, setDescription);

                            list.add(model);
                        }
                    }
                }

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Atlas_Orders>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        adapter.setOnItemClickListener(new Pick_Up_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Button approveButton, View view, Pick_Up_Model model, int position) {
                Atlas_Orders orders = new Atlas_Orders();

                Call<Atlas_Orders> callUpdate;
                callUpdate = orders_interface.approveList(orders, Long.valueOf(model.getOrderId()));

                callUpdate.enqueue(new Callback<Atlas_Orders>() {
                    @Override
                    public void onResponse(Call<Atlas_Orders> call, Response<Atlas_Orders> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Toast.makeText(getContext(), "Approved", Toast.LENGTH_SHORT).show();

                            list.remove(list.get(position));
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Atlas_Orders> call, Throwable t) {
                        Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;

    }

}
