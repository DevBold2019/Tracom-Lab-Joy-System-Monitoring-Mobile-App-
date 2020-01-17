package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Atlas_Devices {

    @SerializedName("device_id")
    public int device_id;
    @SerializedName("action")
    public String action;
    @SerializedName("action_status")
    public String action_status;
    @SerializedName("contract_expires")
    public String contract_expires;
    @SerializedName("contract_starts")
    public String contract_starts;
    @SerializedName("contract_period")
    public int contract_period;
    @SerializedName("creation_date")
    public String creation_date;
    @SerializedName("device_contact")
    public String device_contract;
    @SerializedName("device_model")
    public String device_model;
    @SerializedName("device_warranty")
    public String device_warranty;
    @SerializedName("device_owner")
    public String device_owner;
    @SerializedName("imei_number")
    public String imei_number;
    @SerializedName("intrash")
    public String intrash;
    @SerializedName("part_number")
    public String part_number;
    @SerializedName("serial_number")
    public String serial_number;
    @SerializedName("warrantyexpires")
    public String warrantyexpires;
    @SerializedName("warranty_starts")
    public String warranty_starts;
    @SerializedName("warranty_period")
    public int warranty_period;
    @SerializedName("manufacturer")
    public String manufacturer;
    @SerializedName("seller")
    public String seller;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
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

    public String getContract_expires() {
        return contract_expires;
    }

    public void setContract_expires(String contract_expires) {
        this.contract_expires = contract_expires;
    }

    public String getContract_starts() {
        return contract_starts;
    }

    public void setContract_starts(String contract_starts) {
        this.contract_starts = contract_starts;
    }

    public int getContract_period() {
        return contract_period;
    }

    public void setContract_period(int contract_period) {
        this.contract_period = contract_period;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getDevice_contract() {
        return device_contract;
    }

    public void setDevice_contract(String device_contract) {
        this.device_contract = device_contract;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_warranty() {
        return device_warranty;
    }

    public void setDevice_warranty(String device_warranty) {
        this.device_warranty = device_warranty;
    }

    public String getDevice_owner() {
        return device_owner;
    }

    public void setDevice_owner(String device_owner) {
        this.device_owner = device_owner;
    }

    public String getImei_number() {
        return imei_number;
    }

    public void setImei_number(String imei_number) {
        this.imei_number = imei_number;
    }

    public String getIntrash() {
        return intrash;
    }

    public void setIntrash(String intrash) {
        this.intrash = intrash;
    }

    public String getPart_number() {
        return part_number;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getWarrantyexpires() {
        return warrantyexpires;
    }

    public void setWarrantyexpires(String warrantyexpires) {
        this.warrantyexpires = warrantyexpires;
    }

    public String getWarranty_starts() {
        return warranty_starts;
    }

    public void setWarranty_starts(String warranty_starts) {
        this.warranty_starts = warranty_starts;
    }

    public int getWarranty_period() {
        return warranty_period;
    }

    public void setWarranty_period(int warranty_period) {
        this.warranty_period = warranty_period;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
