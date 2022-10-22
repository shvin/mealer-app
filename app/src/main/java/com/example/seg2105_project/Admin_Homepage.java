package com.example.seg2105_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Admin homepage lets you know you're logged in as an admin and lets you back out to the register/login page
 */
public class Admin_Homepage extends AppCompatActivity  implements View.OnClickListener{
    Button btnLogout;
    Button btnComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnComplaint = (Button) findViewById(R.id.btnComplaint);
        btnComplaint.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

    }

    /**
     * onClick listens for a button to click and starts the correct activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLogout){
            Intent intent = new Intent(this, Register_Login_Page.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.btnComplaint){
            Intent intent = new Intent (this, Complaint_Page.class);
            startActivity(intent);

        }
    }
}
