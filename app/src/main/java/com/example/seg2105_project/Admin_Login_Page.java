package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The login page of the Admin lets the user login in if they have the correct username and password
 */
public class Admin_Login_Page extends AppCompatActivity implements View.OnClickListener {
    EditText usernameLogin;
    EditText passwordLogin;
    Button loginBtn;
    Button backBtn;
    Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        admin = new Admin();

        loginBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    /**
     * onClick listens for a click and proceeds to the correct activity
     * @param v
     */
    public void onClick(View v) {
        String userCheck = usernameLogin.getText().toString();
        String userPass = passwordLogin.getText().toString();
        switch (v.getId()) {
            case R.id.loginBtn:
                if (userCheck.equals(admin.getAdminId()) && userPass.equals(admin.getAdminPassword()) ){
                    startActivity(new Intent(this, Admin_Homepage.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.backBtn:
                //FOR ADMIN LOG IN
                startActivity(new Intent(this, Register_Login_Page.class));

                break;
        }
    }
}