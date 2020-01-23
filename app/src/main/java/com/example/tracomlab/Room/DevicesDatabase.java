package com.example.tracomlab.Room;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.os.AsyncTask;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.tracomlab.Model_Classes.Devices_Model;

@Database(entities = {Devices_Model.class},version = 1 , exportSchema = false)
abstract class DevicesDatabase extends RoomDatabase {

    static DevicesDatabase myInstance;
    abstract DevicesDao dao();

    static synchronized DevicesDatabase getInstance(Context context) {
        if (myInstance == null) {

            myInstance = Room.databaseBuilder(context.getApplicationContext(), DevicesDatabase.class, "Devices_Db").fallbackToDestructiveMigration().addCallback(callback).build();

        }


        return myInstance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           new populateDevice(myInstance).execute();

           System.out.println("ON create Activated............................................////////////////////////////////////////////////////////////////////////////////////////");



        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            System.out.println("ON OPEN#################################################################################################################################################");

        }
    };


    public static class populateDevice extends AsyncTask<Void, Void, Void> {

        DevicesDao dao;

        populateDevice(DevicesDatabase DB) {

            dao=DB.dao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));
            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));
            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));
            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));
            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));
            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));
            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));
            dao.addDevices(new Devices_Model("123456","IWL20","356890","KCB "));

            return null;


        }
    }

}
