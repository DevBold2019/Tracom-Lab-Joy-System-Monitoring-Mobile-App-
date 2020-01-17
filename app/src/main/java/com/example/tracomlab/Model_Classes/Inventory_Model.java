package com.example.tracomlab.Model_Classes;

public class Inventory_Model {

    String Status;
    String PartModel;
    String PartName;
    String PartNumber;
    String PartDescription;
    String Manufacturer;


    public Inventory_Model(String status, String partModel, String partName, String partNumber, String partDescription, String manufacturer) {
        Status = status;
        PartModel = partModel;
        PartName = partName;
        PartNumber = partNumber;
        PartDescription = partDescription;
        Manufacturer = manufacturer;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPartModel() {
        return PartModel;
    }

    public void setPartModel(String partModel) {
        PartModel = partModel;
    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String partName) {
        PartName = partName;
    }

    public String getPartNumber() {
        return PartNumber;
    }

    public void setPartNumber(String partNumber) {
        PartNumber = partNumber;
    }

    public String getPartDescription() {
        return PartDescription;
    }

    public void setPartDescription(String partDescription) {
        PartDescription = partDescription;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }
}
