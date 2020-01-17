package com.example.tracomlab.Model_Classes;

public class Devices_Model {


    String SerialNumber;
    String Model;
    String PartNumber;
    String DeviceOwner;
    String DateCreated;


    public Devices_Model(String serialNumber, String model, String partNumber, String deviceOwner, String dateCreated) {
        SerialNumber = serialNumber;
        Model = model;
        PartNumber = partNumber;
        DeviceOwner = deviceOwner;
        DateCreated = dateCreated;
    }

    public Devices_Model() {

    }


    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getPartNumber() {
        return PartNumber;
    }

    public void setPartNumber(String partNumber) {
        PartNumber = partNumber;
    }

    public String getDeviceOwner() {
        return DeviceOwner;
    }

    public void setDeviceOwner(String deviceOwner) {
        DeviceOwner = deviceOwner;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }
}
