package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register_Login_Page extends AppCompatActivity implements View.OnClickListener{

    Button btnClient;
    Button btnCook;
    Button btnLoginClient;
    Button btnLoginCook;
    Button btnLoginAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClient = (Button) findViewById(R.id.btnClient);
        btnCook = (Button) findViewById(R.id.btnCook);
        btnLoginClient = (Button) findViewById(R.id.btnLoginClient);
        btnLoginCook = (Button) findViewById(R.id.btnLoginCook);
        btnLoginAdmin = (Button) findViewById(R.id.btnLoginAdmin);

        btnClient.setOnClickListener(this);
        btnCook.setOnClickListener(this);
        btnLoginClient.setOnClickListener(this);
        btnLoginCook.setOnClickListener(this);
        btnLoginAdmin.setOnClickListener(this);

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnClient:
                startActivity(new Intent(this,Client_Registration.class));
                break;
            case R.id.btnCook:
                startActivity(new Intent(this,Cook_Registration.class));
                break;
            case R.id.btnLoginClient:
                startActivity(new Intent(this,Client_Login_Page.class));
                break;
            case R.id.btnLoginCook:
                startActivity(new Intent(this,Cook_Login_Page.class));
                break;
            case R.id.btnLoginAdmin:
                startActivity(new Intent(this, Admin_Login_Page.class));
                break;

        }
    }
}