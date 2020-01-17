package com.example.tracomlab.Model_Classes;

public class Manufacturer_Model {

    String ManufacturerName,Email,Address,PhoneNumber,ContactPerson,CreatedOn ;


    public Manufacturer_Model(String manufacturerName, String email, String address, String phoneNumber, String contactPerson, String createdOn) {
        ManufacturerName = manufacturerName;
        Email = email;
        Address = address;
        PhoneNumber = phoneNumber;
        ContactPerson = contactPerson;
        CreatedOn = createdOn;
    }


    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        ManufacturerName = manufacturerName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
