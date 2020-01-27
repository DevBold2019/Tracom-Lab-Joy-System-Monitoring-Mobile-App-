package com.example.tracomlab.Model_Classes;

public class Drop_Off_Model {

  String CustomerName,DeliveredStatus;


    public Drop_Off_Model(String customerName, String deliveredStatus) {
        CustomerName = customerName;
        DeliveredStatus = deliveredStatus;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getDeliveredStatus() {
        return DeliveredStatus;
    }

    public void setDeliveredStatus(String deliveredStatus) {
        DeliveredStatus = deliveredStatus;
    }

}
