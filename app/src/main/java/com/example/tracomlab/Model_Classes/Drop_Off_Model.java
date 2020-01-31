package com.example.tracomlab.Model_Classes;

public class Drop_Off_Model {

  String CustomerName,DeliveredStatus,SerialNumber,Location,Date,Person;


    public Drop_Off_Model(String customerName, String deliveredStatus, String serialNumber, String location, String date, String person) {
        CustomerName = customerName;
        DeliveredStatus = deliveredStatus;
        SerialNumber = serialNumber;
        Location = location;
        Date = date;
        Person = person;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getDeliveredStatus() {
        return DeliveredStatus;
    }

    public void setDeliveredStatus(String deliveredStatus) {
        DeliveredStatus = deliveredStatus;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }
}
