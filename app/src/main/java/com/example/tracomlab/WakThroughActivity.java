package com.example.tracomlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.tracomlab.Adapters.WalkAdapter;
import com.example.tracomlab.Model_Classes.Walk_Model;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabina
 * bla bla bla
 */
public class WakThroughActivity extends AppCompatActivity {

    List<Walk_Model>list;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView textView;
    Button button,button1;
    Animation bottomAnimation,topAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walk_through_layout);

        viewPager = findViewById(R.id.vpg);
        tabLayout = findViewById(R.id.tabs);
        textView = findViewById(R.id.skip);
        button = findViewById(R.id.button3);
        button1 = findViewById(R.id.buttonGet);


        topAnimation= AnimationUtils.loadAnimation(WakThroughActivity.this,R.anim.top_animation);
       bottomAnimation= AnimationUtils.loadAnimation(WakThroughActivity.this,R.anim.bottom_animation);

       button.setAnimation(bottomAnimation);





        // when this activity is about to be launch we need to check if its opened before or not
        if (getData()) {
            Intent mainActivity = new Intent(WakThroughActivity.this, SplashActivity.class);
            startActivity(mainActivity);
            finish();

        }

        list=new ArrayList<>();

        list.add(new Walk_Model("Monitoring", "Keep close Track of Pos Terminals ", R.drawable.monitor));
        list.add(new Walk_Model("Searching", "Searching has been made Easy with the app", R.drawable.merchant));
        list.add(new Walk_Model("Accurate", "Labcom provides latest Data from the Database", R.drawable.server));

        WalkAdapter walkAdapter = new WalkAdapter(list,WakThroughActivity.this);
        viewPager.setAdapter(walkAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //GetStarted Button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savedata();

                Intent intent = new Intent(WakThroughActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //Button to go to the next Swipe
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position;

                //getting position of the current Image
                position = viewPager.getCurrentItem();
                //if position is less than maximum of the tabs we go to next
                if (position < list.size()) {

                    position++;
                    viewPager.setCurrentItem(position);
                }
                //else we skip to the last Page
                else if (position == list.size() - 1) {

                    lastPage();
                }
            }
        });
        //immediately the page reaches the end it skips to the lastPage
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == list.size() - 1) {

                    tabLayout.setSelectedTabIndicator(R.drawable.rounded_button);
                    lastPage();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void load(View view) {

    }

    public void lastPage(){

        button1.setAnimation(topAnimation);
        button1.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

    }
    //saving the swipes
    public  void savedata(){

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("loadPage",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("SavePage",true);
        editor.commit();

    }
    //check if the user has passed this welcome guide
    public  Boolean getData() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("loadPage", MODE_PRIVATE);
        Boolean isPageOpen = sharedPreferences.getBoolean("SavePage", false);

        return isPageOpen;



    }

}
