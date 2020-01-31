package com.example.tracomlab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tracomlab.Model_Classes.Walk_Model;
import com.example.tracomlab.R;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class WalkAdapter extends PagerAdapter {

    List<Walk_Model>list;
    Context context;

    public WalkAdapter(List<Walk_Model> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);

        View view=layoutInflater.inflate(R.layout.walklayout,null);

        TextView textView=view.findViewById(R.id.Title);
        TextView textView1=view.findViewById(R.id.desc);
        ImageView imageView=view.findViewById(R.id.pic);

        textView.setText(list.get(position).getTitle());
        textView1.setText(list.get(position).getDescription());
        imageView.setImageResource(list.get(position).getImage());



        container.addView(view);
        return view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }



}
