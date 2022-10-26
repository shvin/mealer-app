package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
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
        btnLogout.setOnClickListener(this);

        DR = FirebaseDatabase.getInstance().getReference("Users/Cooks");

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");

        getCook();

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
        }
    }

    private void checkIfBanOrSuspend(Cook cook){
        System.out.println(cook.getBanned());
        System.out.println(cook.getSuspended());
        if (cook.getBanned()){
            typeOfSuspension.setText("You have been banned indefinitely");
            suspensionLength.setText("You can no longer use the application");
            typeOfSuspension.setTextColor(Color.RED);
            suspensionLength.setTextColor(Color.RED);
        } else if (cook.getSuspended()) {
            typeOfSuspension.setText("You have been suspended");
            suspensionLength.setText("Your suspension will end in " + cook.getDaysSuspended() + " days");
        }
    }

    private void getCook(){
        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Cook currentCook = data.getValue(Cook.class);
                    if (cookID.equals(currentCook.getId())) {
                        checkIfBanOrSuspend(currentCook);
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
}