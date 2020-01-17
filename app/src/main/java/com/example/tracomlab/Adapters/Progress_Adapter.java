package com.example.tracomlab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracomlab.Model_Classes.Progress_Model;
import com.example.tracomlab.R;

import java.util.List;

public class Progress_Adapter extends RecyclerView.Adapter<Progress_Adapter.viewholder> {

    Context context;
    List<Progress_Model>progressModelList;

    public Progress_Adapter(Context context, List<Progress_Model> progressModelList) {

        this.context = context;
        this.progressModelList = progressModelList;
    }

    @NonNull
    @Override
    public Progress_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;

       view= LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_design_view,parent,false);

       return  new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Progress_Adapter.viewholder holder, int position) {

      Progress_Model model=progressModelList.get(position);

        holder.client.setText(model.getClientName());
        holder.progress.setText(model.getProgress());

    }

    @Override
    public int getItemCount() {
        return progressModelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView client,progress;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            client=itemView.findViewById(R.id.textView3);
            progress=itemView.findViewById(R.id.TotalTT);


        }
    }
}
