package com.example.tracomlab.Model_Classes;

public class DeviceHistory_Model {
    String customer,serialNumber,level;

    public DeviceHistory_Model(String customer, String serialNumber, String level) {
        this.customer = customer;
        this.serialNumber = serialNumber;
        this.level = level;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
