package com.example.tracomlab.TableData;

public class DataTable {
    String phoneNumber,emailAddress,contactName,gp;

    public DataTable(String phoneNumber, String emailAddress, String contactName, String gp) {
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.contactName = contactName;
        this.gp = gp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getGp() {
        return gp;
    }

    public void setGp(String gp) {
        this.gp = gp;
    }
}
