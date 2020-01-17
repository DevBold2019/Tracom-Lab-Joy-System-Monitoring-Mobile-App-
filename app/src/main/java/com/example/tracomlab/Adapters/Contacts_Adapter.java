package com.example.tracomlab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tracomlab.Model_Classes.Contacts_Model;
import com.example.tracomlab.R;
import java.util.List;

public class Contacts_Adapter extends RecyclerView.Adapter<Contacts_Adapter.viewHolder> {

    List<Contacts_Model>list;
    Context context;
    Contacts_Model model;

    OnItemClickListener onItemClickListener;



    public Contacts_Adapter(List<Contacts_Model> list, Context context) {

        this.list = list;
        this.context = context;

    }


    public interface OnItemClickListener {

        void onItemClick(CardView cardView , View view,Contacts_Model model, int position);
    }

    public  void setOnItemClickListener(final Contacts_Adapter.OnItemClickListener mItemClickListener) {

        this.onItemClickListener = mItemClickListener;
    }





    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_layout,parent,false);





        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {
        model=list.get(position);


        holder.contactName.setText(model.getContactName());
        holder.phoneNumber.setText(model.getPhoneNumber());
        holder.emailAddress.setText(model.getEmailAddress());


        if(model.getGender() == 1){
            Glide.with(context).load(R.drawable.man).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        }else  if (model.getGender()== 21){
            Glide.with(context).load(R.drawable.girl).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        }else{
            Glide.with(context).load(R.drawable.genderequalityone).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (onItemClickListener != null) {

                    onItemClickListener.onItemClick(holder.cardView,view,model,position);
                }


            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();

    }
    public class viewHolder extends RecyclerView.ViewHolder {

        TextView contactName,phoneNumber,emailAddress;
        CardView cardView;
        ImageView imageView;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            contactName=itemView.findViewById(R.id.contactName);
            phoneNumber=itemView.findViewById(R.id.contactNumber);
            emailAddress=itemView.findViewById(R.id.contactEmail);
            cardView=itemView.findViewById(R.id.card);
            imageView= itemView.findViewById(R.id.gender_Image);
        }
    }
}

