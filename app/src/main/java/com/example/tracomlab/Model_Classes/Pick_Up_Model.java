package com.example.tracomlab.Model_Classes;

public class Pick_Up_Model {

    String requestId,ClientName,NumberOfTerminal,Location,pickUpTime;


    public Pick_Up_Model(String requestId, String clientName, String numberOfTerminal, String location, String pickUpTime) {
        this.requestId = requestId;
        ClientName = clientName;
        NumberOfTerminal = numberOfTerminal;
        Location = location;
        this.pickUpTime = pickUpTime;
    }


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getNumberOfTerminal() {
        return NumberOfTerminal;
    }

    public void setNumberOfTerminal(String numberOfTerminal) {
        NumberOfTerminal = numberOfTerminal;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
}
