package com.example.tracomlab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.Model_Classes.DeviceHistory_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DeviceHistory_Adapters extends RecyclerView.Adapter<DeviceHistory_Adapters.viewholder> {
    List<DeviceHistory_Model> modelList;
    Context context;
    ArrayList<DeviceHistory_Model> list;


    public DeviceHistory_Adapters(List<DeviceHistory_Model> list, Context context) {
        this.modelList = list;
        this.context = context;
        this.list = new ArrayList<>();
        this.list.addAll(modelList);
    }

    @NonNull
    @Override
    public DeviceHistory_Adapters.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_history, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHistory_Adapters.viewholder holder, int position) {
        DeviceHistory_Model model = modelList.get(position);

        holder.customer.setText(model.getCustomer());
        holder.serialnumber.setText(model.getSerialNumber());
        holder.level.setText(model.getLevel());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.linearLayout.getVisibility() != View.VISIBLE){

                    holder.linearLayout.setVisibility(View.VISIBLE);
                    holder.imageView.setImageResource(R.drawable.slideuppx);

                }else{

                    holder.linearLayout.setVisibility(View.GONE);
                    holder.imageView.setImageResource(R.drawable.downbuttonpx);

                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView customer,serialnumber,level;
        ImageView imageView;
        LinearLayout linearLayout;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            customer = itemView.findViewById(R.id.customersNameRepair);
            serialnumber = itemView.findViewById(R.id.serialNumber);
            level = itemView.findViewById(R.id.customerLevel);

            linearLayout = itemView.findViewById(R.id.moreRepair);
            imageView = itemView.findViewById(R.id.moreRepairButton);
        }
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        modelList.clear();
        if (charText.length() == 0) {
            modelList.addAll(list);
        } else {
            for (DeviceHistory_Model deviceHistory_model : list) {
                if (deviceHistory_model.getSerialNumber().toLowerCase(Locale.getDefault()).contains(charText)) {
                    modelList.add(deviceHistory_model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
