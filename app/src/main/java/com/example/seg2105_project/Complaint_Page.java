package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Complaint_Page extends AppCompatActivity implements View.OnClickListener{

    private ListView listViewComplaints;
    private DatabaseReference DR;
    private ArrayList<Complaint> complaintsList;
    private ArrayAdapter<Complaint> complaintsAdapter;
    private Button backBtn;
    private Button updateBtn;
    private String clickedComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);
        DR = FirebaseDatabase.getInstance().getReference("Complaints");
        backBtn = (Button) findViewById(R.id.backBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);

        complaintsList = new ArrayList<>();
        complaintsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, complaintsList);
        listViewComplaints.setAdapter(complaintsAdapter);

        updateBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

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
        //addComplaint();
        onItemLongClick();
        banOrSuspend("97320e01-cba2-4d23-b0d8-588d8744a3cb", "30",1);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.updateBtn){
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }
        if (v.getId() == R.id.backBtn){
            startActivity(new Intent(this, Admin_Homepage.class));
        }
    }

    private void onItemLongClick() {
        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint complaint = complaintsList.get(i);
                System.out.println(complaint.toString());
                dismissSuspendDialog(complaint);
                return true;
            }
        });
    }

    private void dismissSuspendDialog(Complaint complaint){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View newView = inflater.inflate(R.layout.dismiss_suspend_dialog, null);
        alertDialog.setView(newView);

        TextView cookID = (TextView) newView.findViewById(R.id.dialog_cook_ID);
        EditText suspensionLength = (EditText) newView.findViewById(R.id.dialog_suspensionLength);
        Button suspendBtn = (Button) newView.findViewById(R.id.suspendBtn);
        Button banBtn = (Button) newView.findViewById(R.id.banBtn);
        Button dismissBtn = (Button) newView.findViewById(R.id.dismissBtn);

        cookID.setText(complaint.getCookID());

        alertDialog.setTitle("Complaint");
        AlertDialog other = alertDialog.create();
        other.show();

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteComplaint(complaint.getId());
                other.dismiss();
            }
        });
        suspendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banOrSuspend(complaint.getCookID(), suspensionLength.toString(),1);
                deleteComplaint(complaint.getId());
                other.dismiss();
            }
        });
        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banOrSuspend(complaint.getCookID(), "",0);
                deleteComplaint(complaint.getId());
                other.dismiss();
            }
        });
    }

    private void deleteComplaint(String id) {
        DatabaseReference dataBaseR = FirebaseDatabase.getInstance().getReference("Complaints").child(id);

        dataBaseR.removeValue();
        Toast.makeText(this, "The Complaint has been removed", Toast.LENGTH_LONG).show();
    }

    private void banOrSuspend(String cookID, String length, int banOrSuspend){

        if (clickedComplaint == cookID){
            return;
        } else {
            clickedComplaint = cookID;
        }

        DatabaseReference dataBaseR1 = FirebaseDatabase.getInstance().getReference("Users/Cooks");
        dataBaseR1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Cook cook = data.getValue(Cook.class);
                    System.out.println("Cook ID" + cookID);
                    System.out.println("Cook ID2" + cook.getId());

                    if(cookID.equals(cook.getId())){
                        // Bans the cook indefinitely
                        if (banOrSuspend == 0) {
                            Cook newCook = new Cook(cook.getId(),cook.getFirstName(),cook.getLastName(),cook.getEmail(),cook.getPassword(),cook.getAddress(),
                                    cook.getDescription(),cook.getMealsSold(),cook.getAverageRating(),false, 0, true);
                            dataBaseR1.child(cook.getId()).setValue(newCook);
                            break;
                        }
                        if (banOrSuspend == 1) {
                            Cook newCook = new Cook(cook.getId(),cook.getFirstName(),cook.getLastName(),cook.getEmail(),cook.getPassword(),cook.getAddress(),
                                    cook.getDescription(),cook.getMealsSold(),cook.getAverageRating(),true, 2, false);
                            dataBaseR1.child(cook.getId()).setValue(newCook);
                            break;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());
            }
            });
        }

    private void addComplaint(){
        Complaint complaint = new Complaint("123","This is a complaint", "a4a42e04-bc1b-4709-b4a8-2fe7148f5a2a");
        DR.child(complaint.getId()).setValue(complaint);

        Complaint complaint2 = new Complaint("2","This is a complaint", "e950a107-70d5-4649-90bd-bd8c30683ada");
        DR.child(complaint2.getId()).setValue(complaint2);

        Complaint complaint3 = new Complaint("3","This is a complaint", "f619f35d-7b0c-4415-8750-fc182370c288");
        DR.child(complaint3.getId()).setValue(complaint3);
    }

}




