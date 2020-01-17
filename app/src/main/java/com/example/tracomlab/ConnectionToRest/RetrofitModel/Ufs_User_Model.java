package com.example.tracomlab.ConnectionToRest.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Ufs_User_Model {
    @SerializedName("user_id")
    public int user_id;
    @SerializedName("action_status")
    public String action_status;
    @SerializedName("creation_date")
    public String creation_date;
    @SerializedName("email")
    public String email;
    @SerializedName("full_name")
    public String full_name;
    @SerializedName("intrash")
    public String intrash;
    @SerializedName("phone_number")
    public String phone_number;
    @SerializedName("user_type")
    public int user_type;
    @SerializedName("tenant_id")
    public String tenant_id;
    @SerializedName("gender")
    public int gender;
    @SerializedName("department_id")
    public int department_id;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("action")
    public String action;
    @SerializedName("date_of_birth")
    public String date_of_birth;
    @SerializedName("status")
    public int status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getIntrash() {
        return intrash;
    }

    public void setIntrash(String intrash) {
        this.intrash = intrash;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
