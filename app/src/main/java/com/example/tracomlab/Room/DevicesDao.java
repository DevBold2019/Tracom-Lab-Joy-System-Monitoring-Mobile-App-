package com.example.tracomlab.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tracomlab.Model_Classes.Devices_Model;

import java.util.List;

@Dao
public interface DevicesDao {

    @Insert
    void  addDevices(Devices_Model model);

    @Query("Select * FROM Devices_table")
    LiveData<List<Devices_Model>> getAllDevices();





}
