package com.example.seg2105_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cook_Requests_Page extends AppCompatActivity implements View.OnClickListener{
    private Button btnBack_Homepage;
    private Button btnUpdate;
    private ListView listViewRequests;
    private DatabaseReference DR;
    private ArrayList<Order> requestsList;
    private ArrayAdapter<Order> requestsAdapter;
    private String cookID;
    private Boolean updated = false;

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_requests_page);

        btnBack_Homepage = (Button) findViewById(R.id.btnBack_Homepage);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        listViewRequests = (ListView) findViewById(R.id.listViewRequests);

        requestsList = new ArrayList<Order>();
        requestsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, requestsList);

        listViewRequests.setAdapter(requestsAdapter);
        btnBack_Homepage.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        DR = FirebaseDatabase.getInstance().getReference("Orders");

        fillRequestsArray();

        onItemLongClick();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBack_Homepage){
            Intent intent = new Intent(this,Cook_Homepage.class);
            intent.putExtra("cookID", cookID);
            startActivity(intent);
        }
        if (v.getId() == R.id.btnUpdate){
            if(requestsList.size() != 0){
                requestsList.clear();
                fillRequestsArray();
            } else {
                requestsAdapter.clear();
                requestsAdapter.notifyDataSetChanged();
            }
        }
    }

    public void fillRequestsArray(){
        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Order order = dataSnapshot.getValue(Order.class);
                if (order.getCookId().equals(cookID)) {
                    requestsList.add(order);
                    requestsAdapter.notifyDataSetChanged();
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

    public void onItemLongClick(){
        listViewRequests.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = requestsList.get(i);
                addRemoveDialog(order);

                return true;
            }
        });
    }

    private void addRemoveDialog(Order order){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View newView = inflater.inflate(R.layout.accept_reject_dialog, null);
        alertDialog.setView(newView);


        Button btnAccept = (Button) newView.findViewById(R.id.btnAccept);
        Button btnReject = (Button) newView.findViewById(R.id.btnReject);

        alertDialog.setTitle("Order");
        AlertDialog other = alertDialog.create();
        other.show();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.isPending()){
                    Toast.makeText(getApplicationContext(), "You have accepted the order", Toast.LENGTH_LONG).show();
                    orderAccepted(order);
                } else if (order.isApproved()){
                    Toast.makeText(getApplicationContext(), "You have already accepted the order", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You have already rejected the order", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (order.isPending()) {
                    Toast.makeText(getApplicationContext(), "You have rejected the order", Toast.LENGTH_LONG).show();
                    orderRejected(order);
                } else if (order.isApproved()){
                    Toast.makeText(getApplicationContext(), "You have already accepted the order", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You have already rejected the order", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void orderAccepted(Order order){

        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Orders");
        Order newOrder = new Order(order.getId(), order.getCookId(), order.getMealId(),order.getClientId(), order.getMealName(), false, true, false);
        DR1.child(order.getId()).setValue(newOrder);


        updated = false;
        DatabaseReference DR2 = FirebaseDatabase.getInstance().getReference("Users/Cooks");
        DR2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Cook currentCook = data.getValue(Cook.class);
                    if (order.getCookId().equals(currentCook.getId()) && !updated) {

                        Cook cook = new Cook(currentCook.getId(), currentCook.getFirstName(), currentCook.getLastName(), currentCook.getEmail(),currentCook.getPassword(),
                                currentCook.getAddress(), currentCook.getDescription(), currentCook.getMealsSold() + 1, currentCook.getTotalRatings(), currentCook.getNumOfRatings(), currentCook.getSuspended(),
                                currentCook.getDaysSuspended(), currentCook.getBanned());
                        DR2.child(currentCook.getId()).setValue(cook);
                        alreadyUpdated(true);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void alreadyUpdated(Boolean bool){
        this.updated = bool;
    }

    public void orderRejected(Order order){
        DatabaseReference DR2 = FirebaseDatabase.getInstance().getReference("Orders");
        Order newOrder = new Order(order.getId(), order.getCookId(), order.getMealId(),order.getClientId(),order.getMealName(),false,false,true);
        DR2.child(order.getId()).setValue(newOrder);
    }
}