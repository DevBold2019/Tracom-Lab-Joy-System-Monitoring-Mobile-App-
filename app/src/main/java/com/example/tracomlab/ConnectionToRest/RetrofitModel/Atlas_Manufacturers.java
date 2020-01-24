package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Atlas_Manufacturers {

    @SerializedName("manufacturer_id")
    public int manufacturer_id;
    @SerializedName("action")
    public String action;
    @SerializedName("action_status")
    public String action_status;
    @SerializedName("address")
    public String address;
    @SerializedName("comments")
    public String comments;
    @SerializedName("contact_person")
    public String contact_person;
    @SerializedName("creation_date")
    public String creation_date;
    @SerializedName("email_address")
    public String email_address;
    @SerializedName("intrash")
    public String intrash;
    @SerializedName("manufacturer_name")
    public String manufacturer_name;
    @SerializedName("phone_number")
    public String phone_number;

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getIntrash() {
        return intrash;
    }

    public void setIntrash(String intrash) {
        this.intrash = intrash;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
