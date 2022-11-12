package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Cook login page lets the log in as Cook. If their information is incorrect it will not let them log in, asking them to enter correct information
 */
public class Cook_Login_Page extends AppCompatActivity implements View.OnClickListener {

    EditText usernameLogin;
    EditText passwordLogin;
    Button loginBtn;
    Button backBtn;
    DatabaseReference DR;
    Boolean exists;
    List<Cook> cooks;

    Boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_login_page);

        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        backBtn = (Button) findViewById(R.id.backBtn);
        exists = false;
        cooks = new ArrayList<>();
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Cooks");
        loginBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

    }

    /**
     * onClick listens for a click and proceeds to corresponding activity/method
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkForExistingUser(usernameLogin, passwordLogin);

                break;
            case R.id.backBtn:
                startActivity(new Intent(this, Register_Login_Page.class));

                break;
        }
    }

    /**
     * Checks if the user information corresponds to a Client in the Database.
     * @param user
     * @param pass
     */
    private void checkForExistingUser(EditText user, EditText pass) {

        String userCheck = (user.getText().toString()).toLowerCase();
        String userPass = pass.getText().toString();
        //Adding eventListener to reference
        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //System.out.println(data);
                    Cook cook = data.getValue(Cook.class);
                    if (userCheck.equals(cook.getEmail()) && userPass.equals(cook.getPassword())) {

                        returnTrueValue();
                        if(cook.getBanned()==true||cook.getSuspended()==true){
                            System.out.println("123");
                            optionTrueBanned(cook.getId());

                        } else{
                            optionTrue(cook.getId());
                        }
                    }
                }
                if (valid == false) {
                    optionFalse();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());

            }
        });

    }

    /**
     * Sets boolean valid to true
     */
    private void returnTrueValue() {
        valid = true;
    }

    /**
     * Starts the Cook Homepage Activity and lets the user know they are signed in
     */
    private void optionTrue(String cookId){
        Intent intent = new Intent(this,Cook_Homepage.class);
        intent.putExtra("cookID", cookId);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "LOGGED IN", Toast.LENGTH_SHORT).show();
    }

    private void optionTrueBanned(String cookId){
        Intent intent = new Intent(this,Cook_Banned_Homepage.class);
        intent.putExtra("cookID", cookId);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "LOGGED IN", Toast.LENGTH_SHORT).show();
    }

    /**
     * Lets the user know that their information is incorrect
     */
    private void optionFalse(){
        Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_SHORT).show();

    }

}