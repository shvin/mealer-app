package com.example.seg2105_project;

public class Complaint {
    private String id;
    private String description;
    private String clientID;
    private String cookID;
    private Boolean dismissed;
    private Boolean suspended;
    private Boolean permanentlySuspended;

    public Complaint(){

    }
    public Complaint(String id, String description, String cookID) {
        this.id = id;
        this.description = description;
        this.cookID = cookID;
//        this.dismissed = dismissed;
//        this.suspended = suspended;
//        this.permanentlySuspended = permanentlySuspended;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
        this.cookID = cookID;
    }

    public String toString(){
        return "Cook ID: " + cookID + "\n" + "Description: " + description;
    }


    /*
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
    }*/

}
