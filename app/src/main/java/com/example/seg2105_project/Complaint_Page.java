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
import java.util.UUID;

/**
 * Complaint Page lists the complaints issued by clients towards cooks. The admin can ban, suspend, or dismiss the complaints
 */
public class Complaint_Page extends AppCompatActivity implements View.OnClickListener{

    private ListView listViewComplaints;
    private DatabaseReference DR;
    private DatabaseReference DR2;
    private ArrayList<Complaint> complaintsList;
    private ArrayAdapter<Complaint> complaintsAdapter;
    private ArrayList<Meal> unavailable;
    private Button backBtn;
    private Button updateBtn;
    private String clickedComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        Toast.makeText(this,"Long click on a complaint  to remove it from the complaints list",Toast.LENGTH_LONG).show();

        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);
        DR = FirebaseDatabase.getInstance().getReference("Complaints");
        DR2 = FirebaseDatabase.getInstance().getReference("Meals");
        backBtn = (Button) findViewById(R.id.backBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);

        complaintsList = new ArrayList<>();
        unavailable = new ArrayList<>();
        complaintsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, complaintsList);
        listViewComplaints.setAdapter(complaintsAdapter);


        updateBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        addComplaintList();

        onItemLongClick();
    }

    /**
     * Adds and removes the complaints listed on the page
     */
    public void addComplaintList(){

        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Complaint value = dataSnapshot.getValue(Complaint.class);
                complaintsList.add(value);

                complaintsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * When its called, performs the action to either update the page, or go back
     * @param v
     */
    public void onClick(View v) {
        if (v.getId() == R.id.updateBtn){
            if(complaintsList.size() != 0){
                complaintsList.clear();
                addComplaintList();
            } else {
                complaintsAdapter.clear();
                complaintsAdapter.notifyDataSetChanged();
            }

        }
        if (v.getId() == R.id.backBtn){
            startActivity(new Intent(this, Admin_Homepage.class));
        }
    }

    /**
     * When an item is clicked on for a long time, a new view pops up on the page
     */
    private void onItemLongClick() {
        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint complaint = complaintsList.get(i);
                dismissSuspendDialog(complaint);
                return true;
            }
        });
    }

    /**
     * Actions that can be performed on the pop up page. You can dismiss, suspend, or ban.
     * @param complaint
     */
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
                if (suspensionLength.getText().toString().equals("")){
                    banOrSuspend(complaint.getCookID(), 0,1);
                } else {
                    banOrSuspend(complaint.getCookID(), Integer.parseInt(suspensionLength.getText().toString()),1);
                }

                deleteComplaint(complaint.getId());
                other.dismiss();
            }
        });
        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banOrSuspend(complaint.getCookID(), 1,0);
                deleteComplaint(complaint.getId());
                other.dismiss();
            }
        });
    }

    /**
     * Deletes the complaint from the database/list
     * @param id
     */
    private void deleteComplaint(String id) {
        DatabaseReference dataBaseR = FirebaseDatabase.getInstance().getReference("Complaints").child(id);
        dataBaseR.removeValue();
        Toast.makeText(this, "The Complaint has been removed", Toast.LENGTH_LONG).show();
    }

    /**
     * Bans or suspends a cook based on the parameters input
     * @param cookID
     * @param length
     * @param banOrSuspend
     */
    private void banOrSuspend(String cookID, int length, int banOrSuspend){

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

                    if(cookID.equals(cook.getId())){
                        // Bans the cook indefinitely
                        if (banOrSuspend == 0) {
                            Cook newCook = new Cook(cook.getId(),cook.getFirstName(),cook.getLastName(),cook.getEmail(),cook.getPassword(),cook.getAddress(),
                                    cook.getDescription(),cook.getMealsSold(), cook.getTotalRatings(), cook.getNumOfRatings(), false, 0, true);
                            dataBaseR1.child(cook.getId()).setValue(newCook);
                            setMealsUnavailable(cook.getId());
                            goThroughList();
                            break;
                        }
                        if (banOrSuspend == 1) {
                            Cook newCook = new Cook(cook.getId(),cook.getFirstName(),cook.getLastName(),cook.getEmail(),cook.getPassword(),cook.getAddress(),
                                    cook.getDescription(),cook.getMealsSold(), cook.getTotalRatings(), cook.getNumOfRatings(), false, 0, true);
                            dataBaseR1.child(cook.getId()).setValue(newCook);
                            setMealsUnavailable(cook.getId());
                            goThroughList();
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

    /**
     * If a cook is banned, set the cook's meals to unavailable
     * @param cookID
     */
    public void setMealsUnavailable(String cookID){

        DR2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Meal meal = dataSnapshot.getValue(Meal.class);
                if (meal.getCookID().equals(cookID)){
                    updateMeal(meal);
                }
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

    private void goThroughList(){
        for(Meal m:unavailable){
            updateMeal(m);
            System.out.println("5");
        }
    }

    /**
     * Updates the meal if cook is banned (unavailable)
     * @param meal
     */
    private void updateMeal(Meal meal){
        System.out.println("2231");
        DatabaseReference DR3 = FirebaseDatabase.getInstance().getReference("Meals");
        Meal newMeal = new Meal(meal.getId(),meal.getCookID(),meal.getName(),meal.getMealType(),meal.getCuisineType(),meal.getIngredients(),meal.getAllergens(),meal.getPrice(),
                meal.getDescription(), false, false);
        DR3.child(meal.getId()).setValue(newMeal);
    }


}




