package com.example.seg2105_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Search_Meals_Page extends AppCompatActivity implements View.OnClickListener{

    Button btnBack_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search);
        btnBack_Search = (Button) findViewById(R.id.btnBack_Search);


        btnBack_Search.setOnClickListener(this);
    }

    /**
     * onClick listens for a click and proceeds to corresponding activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBack_Search){
            Intent intent = new Intent(this, Client_Homepage.class);
            startActivity(intent);
        }
    }
}