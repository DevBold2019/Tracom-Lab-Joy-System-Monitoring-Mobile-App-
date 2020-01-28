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
import com.example.tracomlab.Model_Classes.Parts_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Parts_Adapter extends RecyclerView.Adapter<Parts_Adapter.viewholder>{

    List<Parts_Model> modelList;
    Context context;
    ArrayList<Parts_Model> list;

    public Parts_Adapter(List<Parts_Model> list, Context context) {
        this.modelList = list;
        this.context = context;
        this.list = new ArrayList<>();
        this.list.addAll(modelList);
    }

    @NonNull
    @Override
    public Parts_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parts, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Parts_Adapter.viewholder holder, int position) {

        Parts_Model model = modelList.get(position);

        holder.manufacture.setText(model.getManufacture());
        holder.partModel.setText(model.getPartModel());
        holder.partName.setText(model.getPartName());
        holder.description.setText(model.getDescription());
        holder.partNumber.setText(model.getPartNumber());

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
        TextView manufacture,partModel,partName,description,partNumber;
        ImageView imageView;
        LinearLayout linearLayout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            manufacture = itemView.findViewById(R.id.ManufacutererName);
            partModel = itemView.findViewById(R.id.PartModel);
            partName = itemView.findViewById(R.id.PartName);
            description = itemView.findViewById(R.id.Description);
            partNumber = itemView.findViewById(R.id.PartNumber);

            imageView = itemView.findViewById(R.id.moreButtonParts);
            linearLayout = itemView.findViewById(R.id.morePartDetails);
        }
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        modelList.clear();
        if (charText.length() == 0) {
            modelList.addAll(list);
        } else {
            for (Parts_Model parts_model : list) {
                if (parts_model.getPartModel().toLowerCase(Locale.getDefault()).contains(charText)) {
                    modelList.add(parts_model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
