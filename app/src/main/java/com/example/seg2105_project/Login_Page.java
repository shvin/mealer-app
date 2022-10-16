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
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login_Page extends AppCompatActivity implements View.OnClickListener{

    EditText usernameLogin;
    EditText passwordLogin;
    TextView registerTXT;
    Button loginBtn;
    DatabaseReference DR;
    Boolean exists;
    List<Client> clients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        registerTXT = (TextView) findViewById(R.id.registerTXT);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        exists = false;
        clients = new ArrayList<>();
        DR = FirebaseDatabase.getInstance().getReference().child("Users");;
        registerTXT.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.registerTXT:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.loginBtn:
                //FOR ADMIN LOG IN

                if (checkForExistingUser(usernameLogin)){
                    startActivity(new Intent(this,Client_Homepage.class));
                }
                Toast.makeText(getApplicationContext(),"Username or Password is incorrect",Toast.LENGTH_SHORT).show();

                break;

        }
    }


    private boolean checkForExistingUser(EditText user) {
        //Adding eventListener to reference
        DR.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //Getting the string value of that node
                    System.out.println(data);
                    Client client = data.getValue(Client.class);
                    Toast.makeText(getApplicationContext(), "Data Received: username: " + client.getEmail(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage() );

            }
        });

        return true;
    }
}