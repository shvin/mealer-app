package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Complaint_Page extends AppCompatActivity implements View.OnClickListener{
    Button btnDismiss;
    Button btnSuspend;
    Button btnBackComplaint;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);
        btnDismiss = (Button) findViewById(R.id.btnDismiss);
        btnSuspend = (Button) findViewById(R.id.btnSuspend);
        btnBackComplaint = (Button) findViewById(R.id.btnBackComplaint);
        btnBackComplaint.setOnClickListener(this);
        btnDismiss.setOnClickListener(this);
        btnSuspend.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btnBack){
            Intent intent = new Intent(this, Admin_Homepage.class);
            startActivity(intent);
        }
    }
}




