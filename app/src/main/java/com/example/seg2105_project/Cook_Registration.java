package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cook_Registration extends AppCompatActivity implements View.OnClickListener{

    Button btnRegisterCook;
    Button btnBackCookReg;
    EditText firstNameCook;
    EditText lastNameCook;
    EditText emailAddressCook;
    EditText passwordCook;
    EditText addressCook;
    EditText descriptionCook;

    DatabaseReference DR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);

        btnRegisterCook = (Button) findViewById(R.id.btnRegisterCook);
        btnBackCookReg = (Button) findViewById(R.id.btnBackCookReg);
        firstNameCook = (EditText) findViewById(R.id.firstNameCook);
        lastNameCook = (EditText) findViewById(R.id.lastNameCook);
        emailAddressCook = (EditText) findViewById(R.id.emailAddressCook);
        passwordCook = (EditText) findViewById(R.id.passwordCook);
        addressCook = (EditText) findViewById(R.id.addressCook);
        descriptionCook = (EditText) findViewById(R.id.descriptionCook);

        DR = FirebaseDatabase.getInstance().getReference();

        btnRegisterCook.setOnClickListener( this);
        btnBackCookReg.setOnClickListener( this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRegisterCook:
                if (checkInfo() == true) {
                    try {
                        writeNewUser();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(this, Client_Login_Page.class));
                }
                break;
            case R.id.btnBackCookReg:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public boolean checkInfo() {

        boolean found = false;
        final String firstNameEntered = firstNameCook.getText().toString();
        final String lastNameEntered = lastNameCook.getText().toString();
        final String emailEntered = emailAddressCook.getText().toString();
        final String passwordEntered = passwordCook.getText().toString();
        final String descriptionEntered = descriptionCook.getText().toString();

        if(firstNameEntered.length() == 0 || lastNameEntered.length() == 0 || emailEntered.length() == 0||
                passwordEntered.length() == 0 || descriptionEntered.length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Input cannot be empty",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        for (int i = 0; i < firstNameEntered.length(); i++) {
            if (!(Character.isLetter(firstNameEntered.charAt(i)))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your first name must only contain letters",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        for (int i = 0; i < lastNameEntered.length(); i++) {
            if (!(Character.isLetter(lastNameEntered.charAt(i)))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your last name must only contain letters",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        for (int i = 0; i < emailEntered.length(); i++) {
            if ((emailEntered.charAt(i) == ' ')) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your email must have no spaces",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (emailEntered.charAt(i) == '@' ) {
                found = true;
                for (int j = i + 1; j < emailEntered.length(); j++) {
                    if (emailEntered.charAt(j) == '@') {
                        Toast toast = Toast.makeText(getApplicationContext(), "Your email cannot have more than one '@'",Toast.LENGTH_SHORT);
                        toast.show();
                        return false;
                    }
                }
            }
        }
        if (found == false) {
            Toast toast = Toast.makeText(getApplicationContext(), "Your email must have a '@'", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (passwordEntered.length() < 8) {
            Toast toast = Toast.makeText(getApplicationContext(), "Your password must be at least 8 characters",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }

    public void writeNewUser() throws IOException, ClassNotFoundException {
        final String firstNameEntered = firstNameCook.getText().toString();
        final String lastNameEntered = lastNameCook.getText().toString();
        final String emailEntered = emailAddressCook.getText().toString();
        final String passwordEntered = passwordCook.getText().toString();
        final String addressEntered = addressCook.getText().toString();
        final String descriptionEntered = descriptionCook.getText().toString();

        int tempId = (int) (Math.random()*10000);
        ArrayList<Integer> menu = new ArrayList<>();

        Cook cook = new Cook(tempId, firstNameEntered, lastNameEntered, emailEntered, passwordEntered, addressEntered, descriptionEntered);

        DR.child("Users").child("Cooks").child(Integer.toString(tempId)).setValue(cook);
    }


}
