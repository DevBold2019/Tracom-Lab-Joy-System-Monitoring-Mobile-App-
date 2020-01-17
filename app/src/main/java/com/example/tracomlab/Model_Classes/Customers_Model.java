package com.example.tracomlab.Model_Classes;

public class Customers_Model {

    String customerName;
    String Country;
    String Address;
    String ContactPerson;


    public Customers_Model(String customerName, String country, String address, String contactPerson) {
        this.customerName = customerName;
        Country = country;
        Address = address;
        ContactPerson = contactPerson;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }
}
