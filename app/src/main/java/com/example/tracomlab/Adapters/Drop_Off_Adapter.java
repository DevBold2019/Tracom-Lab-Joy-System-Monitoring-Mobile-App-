package com.example.tracomlab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Model_Classes.Drop_Off_Model;
import com.example.tracomlab.R;

import java.util.List;

public class Drop_Off_Adapter extends RecyclerView.Adapter<Drop_Off_Adapter.viewholder> {

    List<Drop_Off_Model> modelList;
    Context context;


    public Drop_Off_Adapter(List<Drop_Off_Model> modelList, Context context) {

        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Drop_Off_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_off_layout,parent,false);


        return new Drop_Off_Adapter.viewholder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        Drop_Off_Model model=modelList.get(position);

        holder.t1.setText(model.getEngineerName());
        holder.t2.setText(model.getRequestId());
        holder.t3.setText(model.getNumberOfTerminals());
        holder.t4.setText(model.getDeliveryTime());


    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView t1,t2,t3,t4;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.engineerName);
            t2=itemView.findViewById(R.id.RequestId);
            t3=itemView.findViewById(R.id.TerminalNumber);
            t4=itemView.findViewById(R.id.DropTime);




        }
    }




}
