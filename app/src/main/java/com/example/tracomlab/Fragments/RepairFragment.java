package com.example.tracomlab.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Adapters.Progress_Adapter;
import com.example.tracomlab.Adapters.Unrepairable_Adapter;
import com.example.tracomlab.Model_Classes.Progress_Model;
import com.example.tracomlab.Model_Classes.Unrepairable_Model;
import com.example.tracomlab.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepairFragment extends Fragment {


    RecyclerView recyclerView,recyclerView1;
    Unrepairable_Adapter adapter;
    List<Unrepairable_Model> unrepairableModelList;
    Unrepairable_Model model;

    List<Progress_Model>progressModelList;
  Progress_Adapter progressadapter;

    Button add;
    Dialog dialog;

    float agents[]={200.1f,100.1f,300.f,200.1f};
    int [] terminalnumbers ={100, 200 ,500 ,100 };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;

        view=inflater.inflate(R.layout.fragment_repair, container, false);


        BarChart bar=view.findViewById(R.id.barchart);


        List<BarEntry>testingList=new ArrayList<>();

        for (int i=0;  i<terminalnumbers.length; i++){

            testingList.add(new BarEntry(terminalnumbers[i],agents[i]));

        }

        BarDataSet dataset=new BarDataSet(testingList,"POS perfomance");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data=new BarData(dataset);
        data.setBarWidth(30.5f);

        bar.setData(data);
        bar.animateX(2000);
        bar.animateY(1000);
        bar.setFitBars(true);
        bar.invalidate();


        dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.new_unrepairable_terminal);
        dialog.setCanceledOnTouchOutside(false);

        add=view.findViewById(R.id.add_new_btn);


        recyclerView=view.findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        recyclerView1=view.findViewById(R.id.recyclerView);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
     //   recyclerView.scrollToPosition();


        //add
        unrepairableModelList=new ArrayList<>();
        progressModelList=new ArrayList<>();

        for (int i=0; i <5; i++){

            progressModelList.add(new Progress_Model("KCB","150\t/\t500"));

        }


        for (int i=0; i <5; i++){

            model=new Unrepairable_Model("Equity","0110181746327","348282228982892");
            unrepairableModelList.add(model);

        }

        progressadapter=new Progress_Adapter(getContext(),progressModelList);
        progressadapter.notifyDataSetChanged();
        recyclerView1.scrollToPosition(progressModelList.size()-1);
        recyclerView1.setAdapter(progressadapter);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
                Button dialogsave=dialog.findViewById(R.id.dialogsavebtn);
                final EditText bnknm=dialog.findViewById(R.id.dialogBankName);
                final   EditText serial=dialog.findViewById(R.id.dialogSerialNo);
                final   EditText part=dialog.findViewById(R.id.dialogPartNo);


                dialogsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (bnknm.getText().toString().trim().isEmpty() || serial.getText().toString().trim().isEmpty() || part.getText().toString().trim().isEmpty()  ){

                            Toast.makeText(getContext(),"Cant save null values",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            return;
                        }

                       Unrepairable_Model model1=new Unrepairable_Model(bnknm.getText().toString(),serial.getText().toString(),part.getText().toString());
                        unrepairableModelList.add(model1);

                        bnknm.getText().clear();
                        serial.getText().clear();
                        part.getText().clear();

                        unrepairableModelList.add(model);


                        Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

            }
        });

        adapter=new Unrepairable_Adapter(unrepairableModelList,getContext());
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(unrepairableModelList.size()-1);
        recyclerView.setAdapter(adapter);


        return view;
    }




}
