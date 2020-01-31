package com.example.tracomlab.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveryFragment extends Fragment {

    RecyclerView recyclerView;
    List<Drop_Off_Model> list;
    Drop_Off_Adapter adapter;
    Context context = null;
    TextView t3, t4, t5;

    public DeliveryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;

        view = inflater.inflate(R.layout.fragment_delivery, container, false);

        context = getActivity().getApplicationContext();

        recyclerView = view.findViewById(R.id.deliveryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        list = new ArrayList<>();


        checkForNetwork();


        return view;
    }



    private boolean checkForNetwork() {

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //if there's network we want to load more data
        if (netInfo != null &&  netInfo.isConnectedOrConnecting()) {

            LoadDelivery();

            return true;
        }
        Toast.makeText(getContext(),"Check your network",Toast.LENGTH_LONG).show();

        return false;
    }

    public void LoadDelivery(){

        MainClient mainClient = new MainClient();
        Atlas_Delivery_Interface atlasDeliveryInterface = mainClient.getApiClient().create(Atlas_Delivery_Interface.class);

        Call<List<Atlas_Delivery>> call;
        call = atlasDeliveryInterface.getFullList();

        call.enqueue(new Callback<List<Atlas_Delivery>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Atlas_Delivery>> call, Response<List<Atlas_Delivery>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Delivery> atlas_deliveries = response.body();


                for (Atlas_Delivery delivery : atlas_deliveries) {

                    Drop_Off_Model model = new Drop_Off_Model(delivery.getCustomers(),delivery.getDelivery_status(),delivery.getSerial_number(),delivery.getLocation(),delivery.getCreation_on(),delivery.getDelivered_by());
                    list.add(model);
                }

                adapter = new Drop_Off_Adapter(list, getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure (Call < List < Atlas_Delivery >> call, Throwable t){
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }


}
