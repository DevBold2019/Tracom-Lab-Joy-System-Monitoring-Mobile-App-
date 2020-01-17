package com.example.tracomlab.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.tracomlab.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class DashFragment extends Fragment {

    private PieChart PieChart ;

  private float[] terminalNumbers = {100.5f, 200.2f, 300.2f, 600.0f};
  private   String [] Levels = {"Level 1","Level 2","Level 3","Level 4"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      View view;

    view = inflater.inflate(R.layout.fragment_dash, container, false);

    PieChart=view.findViewById(R.id.summaryPie);



        List<PieEntry> testingList=new ArrayList<>();

        for (int i=0;  i<terminalNumbers.length; i++){

            testingList.add(new PieEntry(terminalNumbers[i],Levels[i]));

        }

        PieDataSet dataSet=new PieDataSet(testingList," Repair Levels");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data=new PieData(dataSet);
        PieChart.setData(data);
        PieChart.animateX(2000);
        PieChart.invalidate();


       // populatePie();


        return view;

    }

    private void populatePie() {



    }


}
