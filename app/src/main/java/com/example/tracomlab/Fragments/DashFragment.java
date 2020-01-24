package com.example.tracomlab.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tracomlab.ConnectionToRest.RetrofitClient.MainClient;
import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Repair_Interface;
import com.example.tracomlab.ConnectionToRest.RetrofitModel.Atlas_Repair;
import com.example.tracomlab.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashFragment extends Fragment {

    private PieChart PieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;

        view = inflater.inflate(R.layout.fragment_dash, container, false);

        PieChart = view.findViewById(R.id.summaryPie);

        final List<PieEntry> testingList = new ArrayList<>();

        MainClient mainClient = new MainClient();
        Atlas_Repair_Interface repair_interface = mainClient.getApiClient().create(Atlas_Repair_Interface.class);

        Call<List<Atlas_Repair>> call;
        call = repair_interface.getFullList();

        call.enqueue(new Callback<List<Atlas_Repair>>() {
            @Override
            public void onResponse(Call<List<Atlas_Repair>> call, Response<List<Atlas_Repair>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Atlas_Repair> atlas_repairs = response.body();

                int level_one = 0;
                int level_two = 0;
                int level_three = 0;
                int level_four = 0;

                for (Atlas_Repair atlasRepair : atlas_repairs) {

                    String setLevel = atlasRepair.getLevels();
                    String setTestResults = atlasRepair.getQa_test_status();
                    String setRepairedStatus = atlasRepair.getRepair_status();



                    if (setRepairedStatus != null && setRepairedStatus.equals("Repaired")) {
                        if (setTestResults != null && setTestResults.equals("Failed")) {
                            if (setLevel != null && setLevel.equals("LEVEL 1")) {
                                level_one = level_one + 1;
                            } else if (setLevel != null && setLevel.equals("LEVEL 2")) {
                                level_two = level_two + 1;
                            } else if (setLevel != null && setLevel.equals("LEVEL 3")) {
                                level_three = level_three + 1;
                            } else if (setLevel != null && setLevel.equals("LEVEL 4")) {
                                level_four = level_four + 1;
                            }
                        }
                    } else if (setRepairedStatus != null && setRepairedStatus.equals("Pending")) {
                        if (setLevel != null && setLevel.equals("LEVEL 1")) {
                            level_one = level_one + 1;
                        } else if (setLevel != null && setLevel.equals("LEVEL 2")) {
                            level_two = level_two + 1;
                        } else if (setLevel != null && setLevel.equals("LEVEL 3")) {
                            level_three = level_three + 1;
                        } else if (setLevel != null && setLevel.equals("LEVEL 4")) {
                            level_four = level_four + 1;
                        }
                    }

                    //populate the pie chart


                }

                int[] terminalNumbers = {(int) level_one, (int) level_two, (int) level_three, (int) level_four};
                String[] Levels = {"Level 1", "Level 2", "Level 3", "Level 4"};

                for (int i = 0; i < terminalNumbers.length; i++) {

                    testingList.add(new PieEntry(terminalNumbers[i], Levels[i]));

                }

                PieDataSet dataSet = new PieDataSet(testingList, " Repair Levels");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                PieData data = new PieData(dataSet);
                PieChart.setData(data);
                PieChart.animateX(2000);
                PieChart.invalidate();
                //populate the pie chart
            }

                @Override
                public void onFailure (Call < List < Atlas_Repair >> call, Throwable t){
                    Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        return view;

        }

    }
