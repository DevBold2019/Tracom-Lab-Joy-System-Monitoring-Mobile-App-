package com.example.tracomlab.Model_Classes;

public class Progress_Model {

    String clientName,progress;

    public Progress_Model(String clientName, String progress) {
        this.clientName = clientName;
        this.progress = progress;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
