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
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_off_layout, parent, false);


        return new Drop_Off_Adapter.viewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {

        Drop_Off_Model model = modelList.get(position);


            holder.t1.setText(model.getCustomerName());
            holder.t2.setText(model.getDeliveredStatus());
            holder.t3.setText(model.getDate());
            holder.t4.setText(model.getLocation());
            holder.t5.setText(model.getPerson());
            holder.t6.setText(model.getSerialNumber());

            if(model.getDeliveredStatus().equals("Delivered")){
                holder.t2.setBackgroundResource(R.drawable.blue_btn);
            }else if(model.getDeliveredStatus().equals("Pending")){
                holder.t2.setBackgroundResource(R.drawable.badred);
            }



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.linearLayout.getVisibility() != View.VISIBLE) {

                    holder.linearLayout.setVisibility(View.VISIBLE);
                    Glide.with(context).load(R.drawable.slideuppx).into(holder.imageView);

                } else {

                    holder.linearLayout.setVisibility(View.GONE);
                    Glide.with(context).load(R.drawable.downbuttonpx).into(holder.imageView);

                }


            }
        });


    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView t1, t2, t3, t4, t5,t6;
        LinearLayout linearLayout;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.CustomerName);
            t2 = itemView.findViewById(R.id.DeliveredStatus);
            t3 = itemView.findViewById(R.id.dropDate);
            t4 = itemView.findViewById(R.id.dropLocation);
            t5 = itemView.findViewById(R.id.dropDelivered);
            t6 = itemView.findViewById(R.id.dropSerial);


            imageView = itemView.findViewById(R.id.moreDetailsDeliveryButton);
            linearLayout = itemView.findViewById(R.id.moreDetailsRepair);

        }
    }


}
