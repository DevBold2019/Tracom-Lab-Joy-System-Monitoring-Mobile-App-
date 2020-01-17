package com.example.tracomlab.Model_Classes;

public class Unrepairable_Model {

    String bankName,serialNumber,PartNumber;

    public Unrepairable_Model(String bankName, String serialNumber, String partNumber) {
        this.bankName = bankName;
        this.serialNumber = serialNumber;
        PartNumber = partNumber;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPartNumber() {
        return PartNumber;
    }

    public void setPartNumber(String partNumber) {
        PartNumber = partNumber;
    }
}
