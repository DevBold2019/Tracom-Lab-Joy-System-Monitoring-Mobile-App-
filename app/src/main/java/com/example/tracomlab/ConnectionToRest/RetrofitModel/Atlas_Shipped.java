package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Atlas_Shipped {

    @SerializedName("id")
    public int id;
    @SerializedName("action")
    public String action;
    @SerializedName("action_status")
    public String action_status;
    @SerializedName("board_defect")
    public String board_defect;
    @SerializedName("bt_address")
    public String bt_address;
    @SerializedName("creation_on")
    public String creation_on;
    @SerializedName("intrash")
    public String intrash;
    @SerializedName("mac_id")
    public String mac_id;
    @SerializedName("note")
    public String note;
    @SerializedName("pcba_pn")
    public String pcba_pn;
    @SerializedName("pcba_sn")
    public String pcba_sn;
    @SerializedName("pki_version")
    public String pki_version;
    @SerializedName("printer_type")
    public String printer_type;
    @SerializedName("wifi")
    public String wifi;
    @SerializedName("creation_date")
    public String creation_date;
    @SerializedName("serial_number")
    public String serial_number;
    @SerializedName("shipped_status")
    public String shipped_status;

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

    public String getBoard_defect() {
        return board_defect;
    }

    public void setBoard_defect(String board_defect) {
        this.board_defect = board_defect;
    }

    public String getBt_address() {
        return bt_address;
    }

    public void setBt_address(String bt_address) {
        this.bt_address = bt_address;
    }

    public String getCreation_on() {
        return creation_on;
    }

    public void setCreation_on(String creation_on) {
        this.creation_on = creation_on;
    }

    public String getIntrash() {
        return intrash;
    }

    public void setIntrash(String intrash) {
        this.intrash = intrash;
    }

    public String getMac_id() {
        return mac_id;
    }

    public void setMac_id(String mac_id) {
        this.mac_id = mac_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPcba_pn() {
        return pcba_pn;
    }

    public void setPcba_pn(String pcba_pn) {
        this.pcba_pn = pcba_pn;
    }

    public String getPcba_sn() {
        return pcba_sn;
    }

    public void setPcba_sn(String pcba_sn) {
        this.pcba_sn = pcba_sn;
    }

    public String getPki_version() {
        return pki_version;
    }

    public void setPki_version(String pki_version) {
        this.pki_version = pki_version;
    }

    public String getPrinter_type() {
        return printer_type;
    }

    public void setPrinter_type(String printer_type) {
        this.printer_type = printer_type;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getShipped_status() {
        return shipped_status;
    }

    public void setShipped_status(String shipped_status) {
        this.shipped_status = shipped_status;
    }
}
