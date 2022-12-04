package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class Client_Purchase_Requests_Page extends AppCompatActivity implements View.OnClickListener{

    private Button btnBack_Homepage;
    private Button btnUpdate;
    private ListView listViewRequests;
    private DatabaseReference DR;
    private ArrayList<Order> requestsList;
    private ArrayAdapter<Order> requestsAdapter;
    private String clientID;
    private Boolean updated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_purchase_requests_page);

        Intent intent = getIntent();
        clientID = intent.getStringExtra("clientID");

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

    public void onClick(View v){
        if(v.getId() == R.id.btnBack_Homepage){
            Intent intent = new Intent(this,Client_Homepage.class);
            intent.putExtra("clientID", clientID);
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
                if (order.getClientId().equals(clientID)) {
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
        View newView = inflater.inflate(R.layout.complaint_rate_cook, null);
        alertDialog.setView(newView);

        EditText ratingNum = (EditText) newView.findViewById(R.id.ratingNum);
        Button rateBtn = (Button) newView.findViewById(R.id.rateBtn);
        EditText complaintDesc = (EditText) newView.findViewById(R.id.complaintDesc);
        Button complaintBtn = (Button) newView.findViewById(R.id.complaintBtn);

        alertDialog.setTitle("Order");
        AlertDialog other = alertDialog.create();
        other.show();

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    isValid(Double.parseDouble(ratingNum.getText().toString()));
                    if (!order.isApproved()) {
                        Toast.makeText(getApplicationContext(), "You cannot rate the meal since you have not received it yet", Toast.LENGTH_LONG).show();
                    }
                    else if (order.isApproved() && isValid(Double.parseDouble(ratingNum.getText().toString()))){
                        rateMeal(order, Double.parseDouble(ratingNum.getText().toString()));
                        other.dismiss();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Make sure your input is a number that resembles (x.xx)", Toast.LENGTH_LONG).show();
                }
            }
        });

        complaintBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!order.isApproved()) {
                    Toast.makeText(getApplicationContext(), "You cannot complain about the meal since you have not received it yet", Toast.LENGTH_LONG).show();
                }
                else if (complaintDesc.getText().toString().length() != 0){
                    complain(order, complaintDesc.getText().toString());
                    other.dismiss();
                }
            }
        });
    }

    public Boolean isValid(Double rating){
        if (rating instanceof Double){
            if (rating >= 0 && rating <= 5){
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Enter a number between 0 and 5", Toast.LENGTH_LONG).show();

                return false;
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Make sure your input is a number that resembles (x.xx)", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void rateMeal (Order order, double rating){
        updated = false;
        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Users/Cooks");
        DR1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Cook currentCook = data.getValue(Cook.class);
                    if (order.getCookId().equals(currentCook.getId()) && !updated) {
                        System.out.println("HERE");
                        Cook cook = new Cook(currentCook.getId(), currentCook.getFirstName(), currentCook.getLastName(), currentCook.getEmail(),currentCook.getPassword(),
                                currentCook.getAddress(), currentCook.getDescription(), currentCook.getMealsSold(), currentCook.getTotalRatings() + rating, currentCook.getNumOfRatings() + 1, currentCook.getSuspended(),
                                currentCook.getDaysSuspended(), currentCook.getBanned());
                        DR1.child(currentCook.getId()).setValue(cook);
                        alreadyUpdated(true);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Toast.makeText(getApplicationContext(), "You have rated the meal " + rating + "/5", Toast.LENGTH_LONG).show();
    }

    public void alreadyUpdated(Boolean bool){
        this.updated = bool;
    }

    public void complain (Order order, String description){
        DatabaseReference DR = FirebaseDatabase.getInstance().getReference("Complaints");

        UUID randID = UUID.randomUUID();
        String randIDString = randID.toString();

        Complaint complaint = new Complaint(randIDString, description, order.getCookId());

        DR.child(randIDString).setValue(complaint);
    }
}