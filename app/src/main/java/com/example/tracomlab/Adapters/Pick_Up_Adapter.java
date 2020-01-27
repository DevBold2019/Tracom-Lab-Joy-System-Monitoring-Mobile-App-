package com.example.tracomlab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tracomlab.Model_Classes.Pick_Up_Model;
import com.example.tracomlab.R;

import java.util.List;

public class Pick_Up_Adapter extends RecyclerView.Adapter<Pick_Up_Adapter.viewholder> {

    List<Pick_Up_Model>modelList;
    Context context;

    public OnItemClickListener mOnItemClickListener;


    public Pick_Up_Adapter(List<Pick_Up_Model> modelList, Context context) {

        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Pick_Up_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pick_up_layout,parent,false);


        return new viewholder(view);


    }

    public interface OnItemClickListener {

        void onItemClick(Button approveButton, View view, Pick_Up_Model model, int position);

    }

    public  void setOnItemClickListener(final OnItemClickListener mItemClickListener) {

        this.mOnItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final Pick_Up_Adapter.viewholder holder, final int position) {

        final Pick_Up_Model model=modelList.get(position);

        holder.t1.setText(model.getOrderId());
        holder.t2.setText(model.getQtPurchased());

        holder.t3.setText(model.getDescription());

        holder.approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.approveButton,view,model,position);
                }

            }
        });

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

    public class viewholder extends RecyclerView.ViewHolder {

        TextView t1,t2,t3;
        ImageView imageView;
        Button approveButton;
        LinearLayout linearLayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.OrderID);
            t2=itemView.findViewById(R.id.qtPurchased);
            t3=itemView.findViewById(R.id.description);


            approveButton = itemView.findViewById(R.id.ButtonApprove);
            imageView=itemView.findViewById(R.id.moreDetailsOrderButton);
            linearLayout = itemView.findViewById(R.id.moreOrderDetails);



        }
    }
}
