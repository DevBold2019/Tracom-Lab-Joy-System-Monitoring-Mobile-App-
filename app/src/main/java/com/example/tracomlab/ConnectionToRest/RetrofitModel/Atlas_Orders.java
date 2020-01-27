package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Atlas_Orders {

    @SerializedName("order_id")
    public Long order_id;
    @SerializedName("action")
    public String action;
    @SerializedName("action_status")
    public String action_status;
    @SerializedName("creation_date")
    public String creation_date;
    @SerializedName("description")
    public String description;
    @SerializedName("dt_purchased")
    public String dt_purchased;
    @SerializedName("intrash")
    public String intrash;
    @SerializedName("part_no")
    public String part_no;
    @SerializedName("po_number")
    public String po_number;
    @SerializedName("qt_purchased")
    public String qt_purchased;
    @SerializedName("date_received")
    public String date_received;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
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

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDt_purchased() {
        return dt_purchased;
    }

    public void setDt_purchased(String dt_purchased) {
        this.dt_purchased = dt_purchased;
    }

    public String getIntrash() {
        return intrash;
    }

    public void setIntrash(String intrash) {
        this.intrash = intrash;
    }

    public String getPart_no() {
        return part_no;
    }

    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }

    public String getPo_number() {
        return po_number;
    }

    public void setPo_number(String po_number) {
        this.po_number = po_number;
    }

    public String getQt_purchased() {
        return qt_purchased;
    }

    public void setQt_purchased(String qt_purchased) {
        this.qt_purchased = qt_purchased;
    }

    public String getDate_received() {
        return date_received;
    }

    public void setDate_received(String date_received) {
        this.date_received = date_received;
    }
}
