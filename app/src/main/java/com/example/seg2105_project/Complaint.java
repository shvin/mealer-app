package com.example.seg2105_project;

public class Complaint {
    private String id;
    private String description;
    private String cookID;

    public Complaint(){

    }
    public Complaint(String id, String description, String cookID) {
        this.id = id;
        this.description = description;
        this.cookID = cookID;
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
}
