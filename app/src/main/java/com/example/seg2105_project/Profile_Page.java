package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile_Page extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference DR;
    private String cookID;
    private Button backBtn;
    private TextView descriptionCook;
    private TextView mealsSold;
    private TextView ratings;
    private TextView email;
    private TextView name;
    private TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");
        System.out.println("ID" + cookID);
        backBtn = (Button) findViewById(R.id.backBtn);
        descriptionCook = (TextView) findViewById(R.id.descriptionCook);
        mealsSold = (TextView) findViewById(R.id.mealsSold);
        ratings = (TextView) findViewById(R.id.ratings);
        email = (TextView) findViewById(R.id.email);
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);


        DR = FirebaseDatabase.getInstance().getReference("Users/Cooks");

        searchCook();
        backBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        if (v.getId() == R.id.backBtn){
            Intent intent = new Intent(this,Cook_Homepage.class);
            intent.putExtra("cookID", cookID);
            startActivity(intent);
        }
    }

    private void searchCook(){
        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Cook currentCook = data.getValue(Cook.class);
                    if (cookID.equals(currentCook.getId())) {
                        displayInfo(currentCook);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());
            }
        });
    }

    private void displayInfo(Cook cook){
        descriptionCook.setText(cook.getDescription());
        mealsSold.setText(Integer.toString(cook.getMealsSold()));
        ratings.setText(String.valueOf(cook.getAverageRating()));
        email.setText(cook.getEmail());
        name.setText(cook.getFirstName() + " " + cook.getLastName());
        address.setText(cook.getAddress());
    }
}