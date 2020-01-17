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
import com.example.tracomlab.Model_Classes.Manufacturer_Model;
import com.example.tracomlab.R;

import java.util.List;

public class Manufacturer_Adapter extends RecyclerView.Adapter<Manufacturer_Adapter.viewholder> {

    List<Manufacturer_Model> modelList;
    Context context;


    public Manufacturer_Adapter(List<Manufacturer_Model> modelList, Context context) {

        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Manufacturer_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manufacturer_layout, parent, false);


        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Manufacturer_Adapter.viewholder holder, int position) {

        Manufacturer_Model model = modelList.get(position);

        holder.address.setText(model.getAddress());
        holder.contactPerson.setText(model.getContactPerson());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhoneNumber());
        holder.name.setText(model.getManufacturerName());


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
        return modelList.size();
    }


    class viewholder extends RecyclerView.ViewHolder {

        TextView name, email, address, phone, contactPerson;
        LinearLayout linearLayout;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.manufacturersName);
            phone = itemView.findViewById(R.id.manufacturerPhoneNumber);
            email = itemView.findViewById(R.id.manufacturerEmail);
            address = itemView.findViewById(R.id.manufacturerAddress);
            contactPerson = itemView.findViewById(R.id.contactPersonManufacturer);
            imageView = itemView.findViewById(R.id.moreDetailsManuButton);

            linearLayout = itemView.findViewById(R.id.moreDetailsManufacturer);

        }
    }


}