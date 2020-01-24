package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Atlas_Parts {

    @SerializedName("parts_id")
    public int parts_id;
    @SerializedName("action")
    public String action;
    @SerializedName("action_status")
    public String action_status;
    @SerializedName("created_on")
    public String created_on;
    @SerializedName("description")
    public String description;
    @SerializedName("intrash")
    public String intrash;
    @SerializedName("manufacturer_name")
    public String manufacturer_name;
    @SerializedName("part_model")
    public String part_model;
    @SerializedName("part_name")
    public String part_name;
    @SerializedName("part_number")
    public String part_number;

    public int getParts_id() {
        return parts_id;
    }

    public void setParts_id(int parts_id) {
        this.parts_id = parts_id;
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

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPart_model() {
        return part_model;
    }

    public void setPart_model(String part_model) {
        this.part_model = part_model;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public String getPart_number() {
        return part_number;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }
}
