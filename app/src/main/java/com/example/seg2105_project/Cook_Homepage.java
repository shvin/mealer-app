package com.example.seg2105_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Cook_Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_homepage);
    }

    //onClick for logoutButtonHomepage in activity cook homepage
    public void logoutButtonHomepage(View view) {
        Intent intent = new Intent(this, Login_Page.class);
        startActivity(intent);
    }
}
