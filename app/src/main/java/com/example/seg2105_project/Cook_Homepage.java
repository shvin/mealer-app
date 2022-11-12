package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Cook homepage lets you know you're logged in as a cook and lets you back out to the register/login page
 */
public class Cook_Homepage extends AppCompatActivity  implements View.OnClickListener{
    private Button btnLogout;
    private Button viewMenuBtn;
    private Button addMealBtn;
    private TextView typeOfSuspension;
    private TextView suspensionLength;
    private String cookID;
    private DatabaseReference DR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_homepage);
        typeOfSuspension = (TextView) findViewById(R.id.typeOfSuspension);
        suspensionLength = (TextView) findViewById(R.id.suspensionLength);
        typeOfSuspension.setTextColor(Color.RED);
        suspensionLength.setTextColor(Color.RED);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        viewMenuBtn = (Button) findViewById(R.id.viewMenuBtn);
        addMealBtn = (Button) findViewById(R.id.addMealBtn);


        DR = FirebaseDatabase.getInstance().getReference("Users/Cooks");

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");


        viewMenuBtn.setOnClickListener(this);
        addMealBtn.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    /**
     * onClick listens for a button to click and starts the corresponding activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLogout){
            Intent intent = new Intent(this, Register_Login_Page.class);
            startActivity(intent);
        }if (v.getId() == R.id.viewMenuBtn){
            Intent intent = new Intent(this,Cook_Menu_Page.class);
            intent.putExtra("cookID", cookID);
            startActivity(intent);
        }if (v.getId() == R.id.addMealBtn){
            Intent intent = new Intent(this,Meal_Page.class);
            intent.putExtra("cookID", cookID);
            startActivity(intent);
        }
    }



}