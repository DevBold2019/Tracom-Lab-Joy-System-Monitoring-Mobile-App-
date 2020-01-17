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
import com.example.tracomlab.Model_Classes.Customers_Model;
import com.example.tracomlab.R;

import java.util.List;

public class Customers_Adapter extends RecyclerView.Adapter<Customers_Adapter.viewholder> {

        Context context;
        List<Customers_Model> customerModelist;

        public Customers_Adapter(Context context, List<Customers_Model> customerModelist) {
            this.context = context;
            this.customerModelist = customerModelist;
        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;

            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_layout,parent,false);

            return new  viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final viewholder holder, int position) {

            Customers_Model model =customerModelist.get(position);

            holder.contactPerson.setText(model.getContactPerson());
            holder.name.setText(model.getCustomerName());
            holder.country.setText(model.getCountry());
            holder.Address.setText(model.getAddress());
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

               /* if(holder.imageView.getDrawable().equals(R.drawable.slideuppx)){

                    Glide.with(view).load(R.drawable.downbuttonpx).into(holder.imageView);
                    holder.linearLayout.setVisibility(View.GONE);

                }*/



                }
            });



        }

        @Override
        public int getItemCount() {

            return customerModelist.size();
        }

        public class viewholder extends RecyclerView.ViewHolder {

            TextView name,country,contactPerson,Address;
            LinearLayout linearLayout;
            ImageView imageView;

            public viewholder(@NonNull View itemView) {
                super(itemView);

                name=itemView.findViewById(R.id.customersName);
                Address=itemView.findViewById(R.id.customerAddress);
                contactPerson=itemView.findViewById(R.id.contactPerson);
                country=itemView.findViewById(R.id.customerCountry);

                imageView=itemView.findViewById(R.id.moreDetailsButton);
                linearLayout=itemView.findViewById(R.id.moreDetails);

            }
        }




}
