package com.example.tracomlab.Model_Classes;

public class Pick_Up_Model {

    String OrderId,Description,QtPurchased;

    public Pick_Up_Model(String orderId, String qtPurchased,  String description) {
        OrderId = orderId;
        Description = description;
        QtPurchased = qtPurchased;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQtPurchased() {
        return QtPurchased;
    }

    public void setQtPurchased(String qtPurchased) {
        QtPurchased = qtPurchased;
    }
}
