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
import com.example.tracomlab.Model_Classes.Devices_Model;
import com.example.tracomlab.R;

import java.util.ArrayList;
import java.util.List;

public class Devices_Adapter extends RecyclerView.Adapter<Devices_Adapter.viewholder> {

    List<Devices_Model> modelList= new ArrayList<>();
    Context context;
    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;


  /*  public Devices_Adapter(List<Devices_Model> modelList, Context context) {

        this.modelList = modelList;
        this.context = context;
    }*/

    @NonNull
    @Override
    public Devices_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.devices_layout, parent, false);


        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {


        Devices_Model model = modelList.get(position);

        holder.part.setText(model.getPartNumber());
        //holder.date.setText(model.getDateCreated());
        holder.model.setText(model.getModel());
        holder.serial.setText(model.getSerialNumber());
        holder.owner.setText(model.getDeviceOwner());
        holder.imageView.setImageResource(R.drawable.downbuttonpx);

        //Glide.with(context).load(R.drawable.downbuttonpx).into(holder.imageView);

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


    class viewholder extends RecyclerView.ViewHolder {

        TextView serial, model, owner, date, part;
        LinearLayout linearLayout;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            serial = itemView.findViewById(R.id.deviceSerialNumber);
            owner = itemView.findViewById(R.id.deviceOwnerName);
            model = itemView.findViewById(R.id.deviceModel);
            date = itemView.findViewById(R.id.deviceCreationDate);
            part = itemView.findViewById(R.id.devicePartNumber);


            imageView = itemView.findViewById(R.id.moreDetailsDeviceButton);

            linearLayout = itemView.findViewById(R.id.moreDeviceDetails);

        }
    }

    public void add(List<Devices_Model> modelist) {
        this.modelList=modelist;

        notifyItemInserted(modelList.size()-1);
        notifyDataSetChanged();
    }

}

