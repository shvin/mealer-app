package com.example.seg2105_project;

import java.util.ArrayList;

public class Admin extends User{
    private ArrayList<Integer> complaintsToProcess = new ArrayList<>();
    public Admin(int id, String firstName, String lastName, String email, String password, String address) {
        super(id, firstName, lastName, email, password, address);
    }

    public void addToComplaints(int id){
        this.complaintsToProcess.add(id);
    }

    public void removeFromComplaints(int id){
        this.complaintsToProcess.remove(0);
    }
}
