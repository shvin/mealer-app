package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Complaint_Page extends AppCompatActivity{

    private ListView listViewComplaints;
    private DatabaseReference DR;
    private ArrayList<Complaint> complaintsList;
    private ArrayAdapter<Complaint> complaintsAdapter;
    private Button dismissBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);
        DR = FirebaseDatabase.getInstance().getReference("Complaints");
        dismissBtn = (Button) findViewById(R.id.dismissBtn);

        complaintsList = new ArrayList<>();
        complaintsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, complaintsList);
        listViewComplaints.setAdapter(complaintsAdapter);

        //        addComplaint();

        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Complaint value = dataSnapshot.getValue(Complaint.class);
                complaintsList.add(value);

                System.out.println(complaintsList);

                complaintsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addComplaint(){
        Complaint complaint = new Complaint("123","This is a complaint", "432122");
        DR.child(complaint.getId()).setValue(complaint);

        Complaint complaint2 = new Complaint("2","This is a complaint", "55522");
        DR.child(complaint2.getId()).setValue(complaint2);

        Complaint complaint3 = new Complaint("3","This is a complaint", "99900");
        DR.child(complaint3.getId()).setValue(complaint3);
    }

}




