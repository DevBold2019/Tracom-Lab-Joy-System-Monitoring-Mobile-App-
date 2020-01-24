package com.example.tracomlab.Fragments;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;

        view = inflater.inflate(R.layout.fragment_delivery, container, false);


        recyclerView = view.findViewById(R.id.deliveryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);


        list = new ArrayList<>();


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


                String setCustomerName = "";
                String setDeliveryStatus = "";
                String setDeleveredBy = "";
                String setTerminalsDelivered = "";
                String setDeliveredAt = "";

                int Terminalsdelivered = 0;
                String Customername = "";
                String DeliveredStatus = "";
                String DeleveredBy = "";
                String DeliveredAt = "";


                List<String> CustomerNameDelivered = new ArrayList<>();
                List<String> CustomerNameDeliveredList = new ArrayList<>();
                List<String> TerminalsDelivered = new ArrayList<>();
                List<String> check = new ArrayList<>();
                //List<Integer> week = new ArrayList<>();
                Map<String, List<Atlas_Delivery>> object = new HashMap<>();


                Map<String, Integer> user = new HashMap<>();
                Map<String, Integer> userp = new HashMap<>();
                Map<String, String> userm = new HashMap<>();
                Map<String, String> usermd = new HashMap<>();
                Map<Integer, String> week = new HashMap<>();


                Map<Integer, String> userc = new HashMap<>();


                Map<Integer, String> userg = new HashMap<>();

                String give = "";
                String place = "";
                for (Atlas_Delivery deliveryList : atlas_deliveries) {
                    setCustomerName = deliveryList.getCustomers();
                    setDeliveryStatus = deliveryList.getDelivery_status();
//                               //  List<Atlas_Delivery> deliveries;


                    if (setDeliveryStatus.equals("Delivered")) {


                        if (user.keySet().contains(setCustomerName) && week.keySet().contains(deliveryList.getWeek()) && userm.keySet().contains(deliveryList.getDelivered_by())) {

                            user.put(setCustomerName, user.get(setCustomerName) + 1);
                            userm.put(deliveryList.getDelivered_by(), deliveryList.getLocation());


                        } else {
                            user.put(setCustomerName, 1);
                            week.put(deliveryList.getWeek(),deliveryList.getCustomers());
                            place = deliveryList.getLocation();
                            userm.put(deliveryList.getDelivered_by(), deliveryList.getLocation());
                            usermd.put(deliveryList.getDelivered_by(), deliveryList.getLocation());

                            give = deliveryList.getDelivered_by();
                            place = deliveryList.getLocation();


                        }


                    } else {
                        if (userp.keySet().contains(setCustomerName)) {
                            userp.put(setCustomerName, userp.get(deliveryList.getCustomers()) + 1);
                        } else {
                            userp.put(deliveryList.getCustomers(), 1);
                        }
                    }


//
//                             if(object.get(setCustomerName) == null){
//                                    deliveries = new ArrayList<>();
//                                    if(setDeliveryStatus.equalsIgnoreCase("delivered")) {
//                                        deliveries.add(deliveryList);
//                                        object.put(setCustomerName, deliveries);
//                                    }
//                                 }
//                                 else {
//                                     deliveries = object.get(setCustomerName);
//                                     deliveries.add(deliveryList);
//                                     object.put(setCustomerName,deliveries);
//                                 }

//                                 if (setDeliveryStatus.equals("Delivered")) {
//
//                                     DeliveredStatus = "Delivered";
//
//                                     if(setCustomerName.equals(CustomerNameDelivered)){
//
//                                     }else{
//                                         CustomerNameDelivered.add(setCustomerName);
//                                     }
//
//
//                                 } else {
//
//                                     DeliveredStatus = "Pending";
//
//                                     if(setCustomerName.equals(CustomerNameDelivered)){
//
//
//                                     }else{
//                                         CustomerNameDelivered.add(setCustomerName);
//                                     }
//
//                                 }

                }

                //Log.w("results",new GsonBuilder().create().toJson(object.get("COOPERATIVE BANK AGENT").size()));

                String game = new String();
                List<String> Customer = new ArrayList<>();
                List<Integer> gn = new ArrayList<>();

                for (Map.Entry<String, String> usermm : userm.entrySet()) {

                    if (Customer.contains(usermm.getValue())) {

//
                        continue;

                    } else {
                        Customer.add(usermm.getValue());
                        for (Map.Entry<String, Integer> userme : user.entrySet()) {
                            if (gn.contains(userme.getValue())) {
                                continue;
                            } else {
                                gn.add(userme.getValue());
                                String go = Integer.toString(userme.getValue());
                                Drop_Off_Model model = new Drop_Off_Model(userme.getKey(), "Delivered", usermm.getKey(), go, usermm.getValue());
                                list.add(model);
                                break;
                            }
                        }


                    }
                    continue;
                }

                for (Map.Entry<String, Integer> usermep : userp.entrySet()) {
                    String god = Integer.toString(usermep.getValue());
                    Drop_Off_Model model = new Drop_Off_Model(usermep.getKey(), "Pending", DeleveredBy, god, DeliveredAt);
                    list.add(model);
                }


//                for (Atlas_Delivery atlasDelivery : atlas_deliveries) {
//
//                    setCustomerName = atlasDelivery.getCustomers();
//                    setDeliveryStatus = atlasDelivery.getDelivery_status();
//                    setDeleveredBy = atlasDelivery.getDelivered_by();
//                    setTerminalsDelivered = atlasDelivery.getDelivery_status();
//                    setDeliveredAt = atlasDelivery.getLocation();
//
////                    if(setDeliveryStatus.equals("Delivered")){
////                        Customername = setCustomerName;
////                        DeliveredStatus = "Delivered";
////                        if(setCustomerName.equals(Customername)){
////                            Terminalsdelivered = Terminalsdelivered + 1;
////                        }
////
////                    }else  if(setDeliveredAt != null && setDeliveredAt.equals("Pending")){
////                        Customername = setCustomerName;
////                        DeliveredStatus = "Pending";
////                        if(setCustomerName.equals(Customername)){
////                            Terminalsdelivered = Terminalsdelivered + 1;
////                        }
////                    }
//
//
//                    if(setDeliveryStatus.equals("Delivered")){
//
//                        DeliveredStatus = "Delivered";
//                        Customername = setCustomerName;
//
//                        if(Customername.equals(Customername)) {
//                            Terminalsdelivered = Terminalsdelivered + 1;
//                            DeleveredBy = setDeleveredBy;
//                            DeliveredAt = setDeliveredAt;
//                        }
//
//
//
//
//                    }else{
//
//                        DeliveredStatus = "Pending";
//                        Customername = setCustomerName;
//
//                        if(Customername.equals(Customername)) {
//                            Terminalsdelivered = Terminalsdelivered + 1;
//                            DeleveredBy = setDeleveredBy;
//                            DeliveredAt = setDeliveredAt;
//                        }
//
//
//                    }


                //}
                //}


                adapter = new Drop_Off_Adapter(list, getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Atlas_Delivery>> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
