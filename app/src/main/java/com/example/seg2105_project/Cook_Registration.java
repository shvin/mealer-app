package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cook_Registration extends AppCompatActivity implements View.OnClickListener{

    Button btnRegisterCook;
    EditText firstNameCook;
    EditText lastNameCook;
    EditText emailAddressCook;
    EditText passwordCook;
    EditText addressCook;
    EditText descriptionCook;

    boolean correctFirstName = true;
    boolean correctLastName = true;
    boolean correctEmail = true;
    boolean correctPassword = true;
    boolean correctAddress = true;

    DatabaseReference DR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);

        btnRegisterCook = (Button) findViewById(R.id.btnRegisterCook);
        firstNameCook = (EditText) findViewById(R.id.firstNameCook);
        lastNameCook = (EditText) findViewById(R.id.lastNameCook);
        emailAddressCook = (EditText) findViewById(R.id.emailAddressCook);
        passwordCook = (EditText) findViewById(R.id.passwordCook);
        addressCook = (EditText) findViewById(R.id.addressCook);
        descriptionCook = (EditText) findViewById(R.id.descriptionCook);

        DR = FirebaseDatabase.getInstance().getReference();

        btnRegisterCook.setOnClickListener( this);
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
                    startActivity(new Intent(this, Login_Page.class));
                }
        }
    }


    public boolean checkInfo() {
        boolean correct = true;
        final String firstNameEntered = firstNameCook.getText().toString();
        final String lastNameEntered = lastNameCook.getText().toString();
        final String emailEntered = emailAddressCook.getText().toString();
        final String passwordEntered = passwordCook.getText().toString();
        final String addressEntered = addressCook.getText().toString();
        final String descriptionEntered = descriptionCook.getText().toString();
        for (int i = 0; i < firstNameEntered.length(); i++) {
            if ((Character.isLetter(firstNameEntered.charAt(i)) == false)) {
                correctFirstName = false;
            }
        }
        for (int i = 0; i < lastNameEntered.length(); i++) {
            if ((Character.isLetter(lastNameEntered.charAt(i)) == false)) {
                correctLastName = false;
            }
        }
        for (int i = 0; i < emailEntered.length(); i++) {
            boolean found = false;
            if ((emailEntered.charAt(i) == ' ')) {
                correctEmail = false;
            }
            if (emailEntered.charAt(i) == '@' == true) {
                found = true;
                for (int j = i + 1; j < emailEntered.length(); j++) {
                    if (emailEntered.charAt(j) == '@') {
                        correctEmail = false;
                    }
                }
                if (i == emailEntered.length() - 1 && found == false) {
                    correctEmail = false;
                }
            }
        }
        if (passwordEntered.length() < 8) {
            correctPassword = false;
        }



        /*System.out.println((correctFirstName == false || correctLastName == false || correctEmail == false || correctPassword == false || correctAddress == false || correctCardNum == false || correctMonthYear == false || correctCVV == false));
        if (correctFirstName == false || correctLastName == false || correctEmail == false || correctPassword == false || correctAddress == false || correctCardNum == false || correctMonthYear == false || correctCVV == false)
            return false;*/
        return true;
    }

    public void writeNewUser() throws IOException, ClassNotFoundException {
        final String firstNameEntered = firstNameCook.getText().toString();
        final String lastNameEntered = lastNameCook.getText().toString();
        final String emailEntered = emailAddressCook.getText().toString();
        final String passwordEntered = passwordCook.getText().toString();
        final String addressEntered = addressCook.getText().toString();
        final String descriptionEntered = descriptionCook.getText().toString();
        Runner run = Runner.getInstance();
        final int tempId = run.randomIdGenerator();
        ArrayList<Integer> menu = new ArrayList<>();
        menu.add(2);
        Cook cook = new Cook(tempId, firstNameEntered, lastNameEntered, emailEntered, passwordEntered, addressEntered, descriptionEntered);

        DR.child("Users").child("Cooks").child(Integer.toString(tempId)).setValue(cook);
    }


}
