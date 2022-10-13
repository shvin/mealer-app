package com.example.seg2105_project;

public class Complaint {
    private int id;
    private String description;
    private int clientID;
    private int cookID;
    private Boolean dismissed;
    private Boolean suspended;

    public Complaint(int id, String description, int clientID, int cookID) {
        this.id = id;
        this.description = description;
        this.clientID = clientID;
        this.cookID = cookID;
        this.dismissed = false;
        this.suspended = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCookID() {
        return cookID;
    }

    public void setCookID(int cookID) {
        this.cookID = cookID;
    }

    public Boolean getDismissed() {
        return dismissed;
    }

    public void setDismissed(Boolean dismissed) {
        this.dismissed = dismissed;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }
}
