package com.example.tracomlab.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Contacts_Adapter;
import com.example.tracomlab.ConnectionToRest.RetrofitClient.Ufs_User_Client;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Ufs_User_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Ufs_User_Model;
import com.example.tracomlab.Model_Classes.Contacts_Model;
import com.example.tracomlab.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactsFragment extends Fragment {

    RecyclerView recyclerView;
    List<Contacts_Model> list;
    Contacts_Adapter adapter;
    Contacts_Model model;
    BottomSheetDialog bottomSheetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;

        view = inflater.inflate(R.layout.fragment_communication, container, false);

        recyclerView=view.findViewById(R.id.contactRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        bottomSheetDialog=new BottomSheetDialog(getContext());
        View layout=LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_layout,container,false);
        bottomSheetDialog.setContentView(layout);

        list=new ArrayList<>();

        Ufs_User_Client ufs_user_client = new Ufs_User_Client();
        Ufs_User_Interface ufs_user_interface = ufs_user_client.getApiClient().create(Ufs_User_Interface.class);

        Call<List<Ufs_User_Model>> called;
        called = ufs_user_interface.getFullList();

        called.enqueue(new Callback<List<Ufs_User_Model>>() {
            @Override
            public void onResponse(Call<List<Ufs_User_Model>> call, Response<List<Ufs_User_Model>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Ufs_User_Model> ufs_user_model = (List<Ufs_User_Model>) response.body();

                for(Ufs_User_Model ufs_user_model1 : ufs_user_model){
                    String setPhone = ufs_user_model1.phone_number;
                    String setMail = ufs_user_model1.email;
                    String setName = ufs_user_model1.full_name;
                    int setGender = ufs_user_model1.gender;

                    model = new Contacts_Model(setPhone,setMail,setName,setGender);
                    list.add(model);
                }

                adapter=new Contacts_Adapter(list,getContext());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new Contacts_Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CardView cardView, View view, final Contacts_Model model, int position) {

                        bottomSheetDialog.show();
                        TextView call=bottomSheetDialog.findViewById(R.id.callEng);
                        TextView email=bottomSheetDialog.findViewById(R.id.emailEng);
                        TextView sms=bottomSheetDialog.findViewById(R.id.smsEng);

                        final TextView contactEmail = view.findViewById(R.id.contactEmail);
                        final TextView contactNumber = view.findViewById(R.id.contactNumber);

                        if (call != null){
                            call.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:"+contactNumber.getText().toString()));
                                    startActivity(intent);
                                    bottomSheetDialog.dismiss();
                                }
                            });
                        }

                        if (email != null){
                            email.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("mailto:"+contactEmail.getText().toString()));
                                    startActivity(intent);
                                    bottomSheetDialog.dismiss();
                                }
                            });
                        }

                        if (sms != null){
                            sms.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                                    intent.setData(Uri.parse("smsto:"+contactNumber.getText().toString()));
                                    startActivity(intent);
                                    bottomSheetDialog.dismiss();
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Ufs_User_Model>> call, Throwable t) {
                Toast.makeText(getContext(),""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
