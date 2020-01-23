package com.example.tracomlab.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tracomlab.Model_Classes.Devices_Model;

import java.util.List;

public class DevicesViewModel extends AndroidViewModel {

    DevicesRepository repository;
     LiveData<List<Devices_Model>> allDevices;

    public DevicesViewModel(@NonNull Application application) {
        super(application);

        repository=new DevicesRepository(application);
        allDevices=repository.getTheDevices();


    }


    public void addDevices(Devices_Model model){
        repository.add(model);
    }

    public  LiveData<List<Devices_Model>> getEveryDevice(){

        return allDevices;
    }
}
