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

import com.bumptech.glide.Glide;
import com.example.tracomlab.Model_Classes.Unrepairable_Model;
import com.example.tracomlab.R;

import java.util.List;

public class Unrepairable_Adapter extends RecyclerView.Adapter<Unrepairable_Adapter.viewholder> {

    List<Unrepairable_Model> unrepairableModelList;
    Context context;

    public Unrepairable_Adapter(List<Unrepairable_Model> unrepairableModelList, Context context) {

        this.unrepairableModelList = unrepairableModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Unrepairable_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repairpageshow, parent, false);

        return new viewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Unrepairable_Adapter.viewholder holder, int position) {

        Unrepairable_Model posit = unrepairableModelList.get(position);

        holder.bankName.setText(posit.getBankName());
        holder.serialNo.setText(posit.getSerialNumber());
        holder.batchNo.setText(posit.getPartNumber());

        Glide.with(context).load(R.drawable.downbuttonpx).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.linearLayout.getVisibility() != View.VISIBLE){

                    holder.linearLayout.setVisibility(View.VISIBLE);
                    Glide.with(context).load(R.drawable.slideuppx).into(holder.imageView);

                }else{

                    holder.linearLayout.setVisibility(View.GONE);
                    Glide.with(context).load(R.drawable.downbuttonpx).into(holder.imageView);

                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return unrepairableModelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView bankName, serialNo, batchNo;
        LinearLayout linearLayout;
        ImageView imageView;


        public viewholder(@NonNull View itemView) {
            super(itemView);

            bankName = itemView.findViewById(R.id.customersRepairPage);
            serialNo = itemView.findViewById(R.id.customerSerialNumber);
            batchNo = itemView.findViewById(R.id.BatchNumber);

            linearLayout = itemView.findViewById(R.id.moreRepairPage);
            imageView = itemView.findViewById(R.id.moreRepairButtonPage);

        }
    }

}
