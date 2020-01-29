package com.example.tracomlab.Model_Classes;

public class Parts_Model {


    String manufacture,partModel,partName,description,partNumber;

    public Parts_Model(String manufacture, String partModel, String partName, String description, String partNumber) {
        this.manufacture = manufacture;
        this.partModel = partModel;
        this.partName = partName;
        this.description = description;
        this.partNumber = partNumber;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getPartModel() {
        return partModel;
    }

    public void setPartModel(String partModel) {
        this.partModel = partModel;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }
}
