package com.example.seg2105_project;

import java.util.ArrayList;

public class Admin{
//    private ArrayList<Integer> complaintsToProcess = new ArrayList<>();

    String adminId;
    String adminPassword;

    Admin(){
        adminId = "111";
        adminPassword = "111";
    }

    public String getAdminId(){
        return adminId;
    }

    public String getAdminPassword(){
        return adminPassword;
    }



//    public void addToComplaints(int id){
//        this.complaintsToProcess.add(id);
//    }

//    public void removeFromComplaints(int id){
//        this.complaintsToProcess.remove(0);
//    }
}
