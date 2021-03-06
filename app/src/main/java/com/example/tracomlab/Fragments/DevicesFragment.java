package com.example.tracomlab.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Devices_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Devices_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Devices;
import com.example.tracomlab.Model_Classes.Devices_Model;
import com.example.tracomlab.R;
import com.example.tracomlab.Room.DevicesViewModel;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DevicesFragment extends Fragment {

    RecyclerView recyclerView;
    List<Devices_Model> list;
    Devices_Model model;
    Devices_Adapter adapter;
    LinearLayout linearLayout;
    ProgressBar progressBar;

    //ViewModel
    DevicesViewModel viewModel;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final View view;
        view=  inflater.inflate(R.layout.fragment_devices, container, false);

        setRetainInstance(true);

        list=new ArrayList<>();

        linearLayout=view.findViewById(R.id.error_layout);
        progressBar=view.findViewById(R.id.main_progress);

        recyclerView=view.findViewById(R.id.devicesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        adapter = new Devices_Adapter();
        recyclerView.setAdapter(adapter);

        viewModel= ViewModelProviders.of(getActivity()).get(DevicesViewModel.class);
        viewModel.getEveryDevice().observe(getActivity(), new Observer<List<Devices_Model>>() {

            @Override
            public void onChanged(List<Devices_Model> modelList) {

                //updating the recyclerview
                Toast.makeText(getActivity(),"On changed",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);

                    //modelList.remove(modelList.size()-4000);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),""+modelList.size()+"\t size ",Toast.LENGTH_LONG).show();


                adapter.add(modelList);
                Toast.makeText(getContext(),""+adapter.getItemCount()+"\t items ",Toast.LENGTH_LONG).show();



            }
        });

        list.clear();

        //isNetworkActive();
        loadForAWhile();

        return view;
    }

    //For connectivity check if the  wifi/network is connected to the internet
    private boolean isNetworkActive() {

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //if theres network we want to load more data
        if (netInfo != null &&  netInfo.isConnectedOrConnecting()) {

            progressBar.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);

            loadFromApi();


            return true;
        }

        progressBar.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

        Toast.makeText(getContext(),"Check your network",Toast.LENGTH_LONG).show();



        Button button=linearLayout.findViewById(R.id.error_btn_retry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //isNetworkActive();
                linearLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                loadForAWhile();

            }
        });


        return false;
    }




    public void  loadFromApi(){

        MainClient mainClient = new MainClient();
        Atlas_Devices_Interface atlasDevicesInterface = mainClient.getApiClient().create(Atlas_Devices_Interface.class);

        Call<List<Atlas_Devices>> call;
        call = atlasDevicesInterface.getFullList();


        call.enqueue(new Callback<List<Atlas_Devices>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Atlas_Devices>> call, Response<List<Atlas_Devices>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Devices> atlas_devices = response.body();

                for (Atlas_Devices atlasDevices : atlas_devices) {

                    String setPartNumber = atlasDevices.part_number;
                    String setSerialNumber = atlasDevices.serial_number;
                    String setDevicemodel = atlasDevices.device_model;
                    String setDeviceOwner = atlasDevices.device_owner;
                    String setCreationDate = atlasDevices.creation_date;

                    System.err.println(setPartNumber);
                    System.err.println(setSerialNumber);
                    System.err.println(setDeviceOwner);


                    model = new Devices_Model(setSerialNumber, setDevicemodel, setPartNumber, setDeviceOwner /*,setCreationDate*/);
                    list.add(model);
                    viewModel.addDevices(model);

                    //viewModel.addDevices( new Devices_Model(setSerialNumber, setDevicemodel, setPartNumber, setDeviceOwner /*,setCreationDate*/));

                }

                adapter.add(list);

                Toast.makeText(getContext(),""+adapter.getItemCount()+"\t items ",Toast.LENGTH_LONG).show();


              /* if (adapter.getItemCount() >0 ){
                    progressBar.setVisibility(View.GONE);
                }
*/


            }

            @Override
            public void onFailure(Call<List<Atlas_Devices>> call, Throwable t) {

                linearLayout.setVisibility(View.VISIBLE);

                TextView textViewError =linearLayout.findViewById(R.id.error_txt_cause);
                textViewError.setText(t.getMessage());


                Button button=linearLayout.findViewById(R.id.error_btn_retry);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        isNetworkActive();
                        loadForAWhile();

                    }
                });


            }
        });



    }

    public void loadForAWhile(){

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setVisibility(View.VISIBLE);
                isNetworkActive();

            }
        },3000);



    }

}



