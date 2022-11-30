package com.example.seg2105_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Client homepage lets you know you're logged in as a client and lets you back out to the register/login page
 */
public class Client_Homepage extends AppCompatActivity  implements View.OnClickListener{
    Button btnLogout;
    Button btnSearch;
    Button btnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_homepage);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnStatus = (Button) findViewById(R.id.btnStatus);

        btnSearch.setOnClickListener(this);
        btnStatus.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    /**
     * onClick listens for a click and proceeds to corresponding activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLogout){
            Intent intent = new Intent(this, Register_Login_Page.class);
            startActivity(intent);
        } else if(v.getId() == R.id.btnSearch){
            Intent intent = new Intent(this, Search_Meals_Page.class);
            startActivity(intent);
        }
    }
}
