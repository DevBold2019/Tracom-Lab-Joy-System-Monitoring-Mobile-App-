package com.example.tracomlab.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tracomlab.Model_Classes.Devices_Model;

import java.util.List;

public class DevicesRepository {




    DevicesDao devicesDao;
    LiveData<List<Devices_Model>> devices;
    DevicesDatabase database;

    DevicesRepository(Application application) {

        database= DevicesDatabase.getInstance(application);
        devicesDao=database.dao();
        devices=devicesDao.getAllDevices();


    }

    public void  add(Devices_Model model){

        new addAsyncTask(devicesDao).execute(model);
    }

    public LiveData<List<Devices_Model>> getTheDevices(){

        return devices;
    }

    public class  addAsyncTask extends AsyncTask<Devices_Model,Void,Void>{

        DevicesDao devicesDao;

        addAsyncTask(DevicesDao devicesDao) {
            this.devicesDao = devicesDao;
        }

        @Override
        protected Void doInBackground(Devices_Model... devices_models) {
            devicesDao.addDevices(devices_models[0]);
            return null;
        }
    }




}
