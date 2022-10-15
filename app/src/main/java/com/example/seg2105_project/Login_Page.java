package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Page extends AppCompatActivity implements View.OnClickListener{

    EditText usernameLogin;
    EditText passwordLogin;
    TextView registerTXT;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        registerTXT = (TextView) findViewById(R.id.registerTXT);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        registerTXT.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.registerTXT:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.loginBtn:
                break;

        }
    }
}