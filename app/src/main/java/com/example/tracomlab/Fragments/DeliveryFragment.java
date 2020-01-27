package com.example.tracomlab.Fragments;


import android.content.Context;
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

public DeliveryFragment(){

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;

        //vieww = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_off_layout, parent, false);

        view = inflater.inflate(R.layout.fragment_delivery, container, false);
        LinearLayout moreDetailsRepair = view.findViewById(R.id.moreDetailsRepair);

       TableLayout table_delivery = view.findViewById(R.id.table_delivery);

        context = getActivity().getApplicationContext();

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
                String setDate = "";

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
                Map<String, String> usermore = new HashMap<>();
                Map<String, String> usermd = new HashMap<>();
                Map<Integer, String> week = new HashMap<>();


                Map<Integer, String> userc = new HashMap<>();


                Map<Integer, String> userg = new HashMap<>();

                String give = "";
                String place = "";

                for (Atlas_Delivery delivery : atlas_deliveries) {

                    setCustomerName = delivery.getCustomers();
                    setDeliveryStatus = delivery.getDelivery_status();
                    setDeleveredBy = delivery.getDelivered_by();
                    setDeliveredAt = delivery.getLocation();
                    setDate = delivery.getCreation_on();

                    if (setDeliveryStatus.equals("Delivered")) {

                        if (user.keySet().contains(setCustomerName) && week.keySet().contains(delivery.getWeek()) && usermore.keySet().contains(delivery.getDelivered_by())) {

                            user.put(setCustomerName, user.get(setCustomerName) + 1);
                            usermore.put(delivery.getDelivered_by(), delivery.getLocation());


                        } else {
                            user.put(setCustomerName, 1);
                            week.put(delivery.getWeek(), setDate);
                            usermore.put(setDeleveredBy, setDeliveredAt);
                        }

                    } else {
                        if (userp.keySet().contains(setCustomerName)) {
                            userp.put(setCustomerName, userp.get(delivery.getCustomers()) + 1);
                        } else {
                            userp.put(delivery.getCustomers(), 1);
                        }
                    }

                    }



//                for (Atlas_Delivery deliveryList : atlas_deliveries) {
//                    setCustomerName = deliveryList.getCustomers();
//                    setDeliveryStatus = deliveryList.getDelivery_status();
////                               //  List<Atlas_Delivery> deliveries;
//
//
//                    if (setDeliveryStatus.equals("Delivered")) {
//
//                        if (user.keySet().contains(setCustomerName) && week.keySet().contains(deliveryList.getWeek()) && userm.keySet().contains(deliveryList.getDelivered_by())) {
//
//                            user.put(setCustomerName, user.get(setCustomerName) + 1);
//                            userm.put(deliveryList.getDelivered_by(), deliveryList.getLocation());
//
//
//                        } else {
//                            user.put(setCustomerName, 1);
//                            week.put(deliveryList.getWeek(),deliveryList.getCustomers());
//                            place = deliveryList.getLocation();
//                            userm.put(deliveryList.getDelivered_by(), deliveryList.getLocation());
//                            usermd.put(deliveryList.getDelivered_by(), deliveryList.getLocation());
//
//                            give = deliveryList.getDelivered_by();
//                            place = deliveryList.getLocation();
//
//
//                        }
//
//
//                    } else {
//                        if (userp.keySet().contains(setCustomerName)) {
//                            userp.put(setCustomerName, userp.get(deliveryList.getCustomers()) + 1);
//                        } else {
//                            userp.put(deliveryList.getCustomers(), 1);
//                        }
//                    }


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

                    //   }

                    //Log.w("results",new GsonBuilder().create().toJson(object.get("COOPERATIVE BANK AGENT").size()));

                    String game = new String();
                    List<String> Customer = new ArrayList<>();
                    List<String> userList = new ArrayList<>();
                    List<Integer> gn = new ArrayList<>();
                TableRow tableRow = new TableRow(getContext());

                    for (Map.Entry<String, String> usermm : usermore.entrySet()) {

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
                                    //, usermm.getKey(), go, usermm.getValue()
                                    ///
                                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                    tableRow.setLayoutParams(layoutParams);



                                    TextView texts = new TextView(getContext());
                                    texts.setTextColor(getResources().getColor(R.color.colorBackground));
                                    texts.setBackgroundResource(R.drawable.cellrowbody);
                                    texts.setGravity(Gravity.CENTER);
                                    texts.setText("me");
                                    tableRow.addView(texts, 0);

                                    TextView textm = new TextView(getContext());
                                    textm.setTextColor(getResources().getColor(R.color.colorBackground));
                                    textm.setBackgroundResource(R.drawable.cellrowbody);
                                    textm.setGravity(Gravity.CENTER);
                                    textm.setText("go");
                                    tableRow.addView(textm, 1);

                                    TextView textd = new TextView(getContext());
                                    textd.setTextColor(getResources().getColor(R.color.colorBackground));
                                    textd.setBackgroundResource(R.drawable.cellrowbody);
                                    textd.setGravity(Gravity.CENTER);
                                    textd.setText("where");
                                    tableRow.addView(textd, 2);

                                    TextView textr = new TextView(getContext());
                                    textr.setTextColor(getResources().getColor(R.color.colorBackground));
                                    textr.setBackgroundResource(R.drawable.cellrowbody);
                                    textr.setGravity(Gravity.CENTER);
                                    textr.setText("by");
                                    tableRow.addView(textr, 3);
                                    ///
                                    Drop_Off_Model model = new Drop_Off_Model(userme.getKey(), "Delivered");
                                    list.add(model);




                                    break;
                                }
                            }


                        }
                        continue;
                    }
               table_delivery.addView(tableRow, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT));



                    for (Map.Entry<String, Integer> usermep : userp.entrySet()) {
                        String god = Integer.toString(usermep.getValue());
                        Drop_Off_Model model = new Drop_Off_Model(usermep.getKey(), "Pending");
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


        return view;
        }



}
