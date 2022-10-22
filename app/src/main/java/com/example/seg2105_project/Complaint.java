package com.example.seg2105_project;

public class Complaint {
    private int id;
    private String description;
    private String clientID;
    private String cookID;
    private Boolean dismissed;
    private Boolean suspended;

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

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
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

    public Complaint(int id, String description, String clientID, String cookID, Boolean dismissed, Boolean suspended) {
        this.id = id;
        this.description = description;
        this.clientID = clientID;
        this.cookID = cookID;
        this.dismissed = dismissed;
        this.suspended = suspended;
    }

    public Complaint(){
    }



}
