package com.example.tracomlab;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracomlab.Fragments.ContactsFragment;
import com.example.tracomlab.Fragments.CustomerFragment;
import com.example.tracomlab.Fragments.DashFragment;
import com.example.tracomlab.Fragments.DeliveryFragment;
import com.example.tracomlab.Fragments.Device_History_Fragment;
import com.example.tracomlab.Fragments.DevicesFragment;
import com.example.tracomlab.Fragments.InventoryFragment;
import com.example.tracomlab.Fragments.ManufacturerFragment;
import com.example.tracomlab.Fragments.OrdersFragment;
import com.example.tracomlab.Fragments.RepairFragment;
import com.example.tracomlab.Fragments.parts_History_Fragment;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


public class MainUserInteface extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_inteface);

        toolbar=findViewById(R.id.main_user_interface_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DashBoard");


        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_layout);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        username = headerView.findViewById(R.id.username);

        //*retrieve the username*//
        try {
            FileInputStream fileInputStream = openFileInput("name.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
            }
            username.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //*retrieve the username*//

        SharedPreferences pref = getSharedPreferences("scanDevice", Context.MODE_PRIVATE);
        String DeviceHistory = pref.getString("nameHistory", null);
        if (DeviceHistory != null) {
            if (DeviceHistory != "empty") {
                Objects.requireNonNull(getSupportActionBar()).setTitle("Devices History");
                navigationView.setNavigationItemSelectedListener(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.Container, new Device_History_Fragment()).commit();

                SharedPreferences.Editor prefEdit = pref.edit();
                prefEdit.clear();
                prefEdit.apply();
            }
        } else {
            navigationView.setNavigationItemSelectedListener(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.Container, new DashFragment()).commit();
     }


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



            switch (menuItem.getItemId()) {

                case R.id.repairMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Repairs");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new RepairFragment()).commit();

                    break;

                case R.id.dashMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new DashFragment()).commit();

                    break;


                case R.id.orderMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Orders");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new OrdersFragment()).commit();

                    break;

                case R.id.deliveryMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Delivery");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new DeliveryFragment()).commit();

                    break;

                case R.id.consultationMenu:
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Contacts");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new ContactsFragment()).commit();

                    break;

                case R.id.customerOnBoardedDeviceMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Customers");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new CustomerFragment()).commit();

                    break;

                case R.id.manufacturerOnBoardedDeviceMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Manufacturer");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new ManufacturerFragment()).commit();

                    break;

                case R.id.devicesOnBoardedDeviceMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Devices");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new DevicesFragment()).commit();

                    break;

                case R.id.inventoryReportsMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Inventory Reports");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new InventoryFragment()).commit();

                    break;

                case R.id.devicesHistoryMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Devices History");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new Device_History_Fragment()).commit();

                    break;

                case R.id.partsHistoryMenu:

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Parts");
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container, new parts_History_Fragment()).commit();

                    break;


            }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_out_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.logOutMenu:
                SharedPreferences logout = getSharedPreferences("TracomLabcom", Context.MODE_PRIVATE);
                SharedPreferences.Editor logoutEdit = logout.edit();
                logoutEdit.clear();
                logoutEdit.apply();

                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }


        return true;
    }

}
