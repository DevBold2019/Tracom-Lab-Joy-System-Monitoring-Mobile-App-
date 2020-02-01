package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Ufs_Otp {

    @SerializedName("otp_id")
    public int otp_id;
    @SerializedName("creation_date")
    public Date creation_date;
    @SerializedName("otp")
    public String otp;
    @SerializedName("user_id")
    public String user_id;
    @SerializedName("otp_category")
    public int otp_category;

    public int getOtp_id() {
        return otp_id;
    }

    public void setOtp_id(int otp_id) {
        this.otp_id = otp_id;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getOtp_category() {
        return otp_category;
    }

    public void setOtp_category(int otp_category) {
        this.otp_category = otp_category;
    }
}
