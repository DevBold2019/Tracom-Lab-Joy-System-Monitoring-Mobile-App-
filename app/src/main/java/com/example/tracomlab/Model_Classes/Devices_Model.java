package com.example.tracomlab.Model_Classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "devices_table")
  public class Devices_Model {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String serialNumber ;
    private String model;
    private String partNumber;
    private String deviceOwner ;


    public Devices_Model(String serialNumber, String model, String partNumber, String deviceOwner) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.partNumber = partNumber;
        this.deviceOwner = deviceOwner;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDeviceOwner() {
        return deviceOwner;
    }

    public void setDeviceOwner(String deviceOwner) {
        this.deviceOwner = deviceOwner;
    }
}
