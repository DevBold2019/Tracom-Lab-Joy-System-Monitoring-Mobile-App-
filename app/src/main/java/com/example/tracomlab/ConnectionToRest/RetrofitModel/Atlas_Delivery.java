package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Atlas_Delivery {

    @SerializedName("id")
    public int id;
    @SerializedName("action")
    public String action;
    @SerializedName("action_status")
    public String action_status;
    @SerializedName("creation_on")
    public String creation_on;
    @SerializedName("customers")
    public String customers;
    @SerializedName("delivery_status")
    public String delivery_status;
    @SerializedName("intrash")
    public String intrash;
    @SerializedName("week")
    public int week;
    @SerializedName("year")
    public int year;
    @SerializedName("creation_date")
    public String creation_date;
    @SerializedName("delivered_by")
    public String delivered_by;
    @SerializedName("location")
    public String location;
    @SerializedName("serial_number")
    public String serial_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreation_on() {
        return creation_on;
    }

    public void setCreation_on(String creation_on) {
        this.creation_on = creation_on;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getIntrash() {
        return intrash;
    }

    public void setIntrash(String intrash) {
        this.intrash = intrash;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getDelivered_by() {
        return delivered_by;
    }

    public void setDelivered_by(String delivered_by) {
        this.delivered_by = delivered_by;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }
}
