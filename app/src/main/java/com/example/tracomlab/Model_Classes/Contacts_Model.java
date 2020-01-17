package com.example.tracomlab.Model_Classes;

public class Contacts_Model {

        String phoneNumber,emailAddress,contactName;
        int gender;

        public Contacts_Model(String phoneNumber, String emailAddress, String contactName, int gender) {
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
            this.contactName = contactName;
            this.gender = gender;
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
