package com.example.tracomlab.Model_Classes;

public class Drop_Off_Model {

  String CustomerName,DeliveredStatus,DeliveredBy,TerminalsDelivered,DeliveredAt;


    public Drop_Off_Model(String customerName, String deliveredStatus, String deliveredBy, String terminalsDelivered, String deliveredAt) {
        CustomerName = customerName;
        DeliveredStatus = deliveredStatus;
        DeliveredBy = deliveredBy;
        TerminalsDelivered = terminalsDelivered;
        DeliveredAt = deliveredAt;
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

    public String getDeliveredBy() {
        return DeliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        DeliveredBy = deliveredBy;
    }

    public String getTerminalsDelivered() {
        return TerminalsDelivered;
    }

    public void setTerminalsDelivered(String terminalsDelivered) {
        TerminalsDelivered = terminalsDelivered;
    }

    public String getDeliveredAt() {
        return DeliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        DeliveredAt = deliveredAt;
    }
}
