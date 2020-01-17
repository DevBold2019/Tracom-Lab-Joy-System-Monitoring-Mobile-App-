package com.example.tracomlab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unrepairable_design_view, parent, false);

        return new viewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Unrepairable_Adapter.viewholder holder, int position) {

        Unrepairable_Model posit = unrepairableModelList.get(position);

        holder.bankName.setText(posit.getBankName());
        holder.serialNo.setText(posit.getSerialNumber());
        holder.partNo.setText(posit.getPartNumber());


    }

    @Override
    public int getItemCount() {
        return unrepairableModelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView bankName, serialNo, partNo;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            bankName = itemView.findViewById(R.id.textView15);
            serialNo = itemView.findViewById(R.id.textView16);
            partNo = itemView.findViewById(R.id.textView18);

        }
    }

}
