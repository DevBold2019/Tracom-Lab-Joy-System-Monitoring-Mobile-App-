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
import com.example.tracomlab.Model_Classes.Inventory_Model;
import com.example.tracomlab.R;

import java.util.List;

public class Inventory_Adapter  extends RecyclerView.Adapter<Inventory_Adapter.viewholder> {

    List<Inventory_Model> modelList;
    Context context;


    public Inventory_Adapter(List<Inventory_Model> modelList, Context context) {

        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Inventory_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_layout, parent, false);


        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {

        Inventory_Model model = modelList.get(position);

        holder.description.setText(model.getPartDescription());
        holder.partname.setText(model.getPartName());
        holder.partmodel.setText(model.getPartModel());
        holder.partNumber.setText(model.getPartNumber());
        holder.manufacturer.setText(model.getManufacturer());
        holder.status.setText(model.getStatus());

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

        TextView partNumber, partmodel, partname, description, status, manufacturer;
        LinearLayout linearLayout;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            partNumber = itemView.findViewById(R.id.inventoryPartNumber);
            partmodel = itemView.findViewById(R.id.inventorydeviceModel);
            partname = itemView.findViewById(R.id.inventoryPartName);
            description = itemView.findViewById(R.id.inventoryPartDescription);
            manufacturer = itemView.findViewById(R.id.inventoryManufacturer);
            status = itemView.findViewById(R.id.inventoryActionStatus);


            imageView = itemView.findViewById(R.id.moreDetailsInventoryButton);
            linearLayout = itemView.findViewById(R.id.inventorymoreDeviceDetails);

        }
    }

}
