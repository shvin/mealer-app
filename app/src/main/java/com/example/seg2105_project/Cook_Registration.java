package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


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

public class Cook_Registration extends AppCompatActivity implements View.OnClickListener{

    Button btnRegisterCook;
    Button btnBackCookReg;
    EditText firstNameCook;
    EditText lastNameCook;
    EditText emailAddressCook;
    EditText passwordCook;
    EditText addressNumCook;
    EditText addressNameCook;
    EditText descriptionCook;
    boolean repeat = false;
    boolean goneThrough = false;

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
        addressNumCook = (EditText) findViewById(R.id.addressNumCook);
        addressNameCook = (EditText) findViewById(R.id.addressNameCook);
        descriptionCook = (EditText) findViewById(R.id.descriptionCook);

        DR = FirebaseDatabase.getInstance().getReference("Users/Cooks");

        btnRegisterCook.setOnClickListener( this);
        btnBackCookReg.setOnClickListener( this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRegisterCook:

                checkEmail(emailAddressCook);
                repeat = false;

                break;
            case R.id.btnBackCookReg:
                startActivity(new Intent(this, Register_Login_Page.class));
                break;
        }
    }

    public boolean checkInfo() {

        boolean found = false;
        final String firstNameEntered = firstNameCook.getText().toString();
        final String lastNameEntered = lastNameCook.getText().toString();
        final String emailEntered = (emailAddressCook.getText().toString()).toLowerCase();
        final String addressNumEntered = addressNumCook.getText().toString();
        final String addressNameEntered = addressNameCook.getText().toString();
        final String passwordEntered = passwordCook.getText().toString();
        final String descriptionEntered = descriptionCook.getText().toString();

        if(firstNameEntered.length() == 0 || lastNameEntered.length() == 0 || emailEntered.length() == 0||
                addressNumEntered.length() == 0 || addressNameEntered.length() == 0 || passwordEntered.length() == 0 || descriptionEntered.length() == 0){
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

        for (int i = 0;i < addressNumEntered.length(); i++){
            if (!Character.isDigit(addressNumEntered.charAt(i))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your street number must only contain numbers",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        for (int i = 0;i < addressNameEntered.length(); i++){
            if (!Character.isLetter(addressNameEntered.charAt(i)) && addressNameEntered.charAt(i) != ' ') {
                Toast toast = Toast.makeText(getApplicationContext(), "Your street name must only contain letters and spaces",Toast.LENGTH_SHORT);
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

    private void checkEmail(EditText email){
        final String emailEntered = email.getText().toString().toLowerCase();

        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //System.out.println(data);
                    Cook cook = data.getValue(Cook.class);
                    if (emailEntered.equals(cook.getEmail())) {
                        repeatTrue();
                        emailRepeated();
                        System.out.println("2");
                        System.out.println(repeat);


                    }
                }
                System.out.println("3");
                System.out.println(repeat);

                if (repeat == false){
                    System.out.println("4");

                    emailNotRepeated();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());

            }
        });


    }
    private void repeatTrue(){
        repeat = true;
    }

    private void emailRepeated(){
        if(goneThrough == false) {
            Toast.makeText(getApplicationContext(), "Email is already registered", Toast.LENGTH_SHORT).show();
        }
    }

    private void emailNotRepeated() {
        System.out.println("5");
        System.out.println("2");
        goneThrough=true;
        if (checkInfo() == true) {
            try {
                writeNewUser();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, Cook_Homepage.class));
        }
    }

    public void writeNewUser() throws IOException, ClassNotFoundException {
        final String firstNameEntered = firstNameCook.getText().toString();
        final String lastNameEntered = lastNameCook.getText().toString();
        final String emailEntered = (emailAddressCook.getText().toString()).toLowerCase();
        final String passwordEntered = passwordCook.getText().toString();
        final String addressNumEntered = addressNumCook.getText().toString();
        final String addressNameEntered = addressNameCook.getText().toString();
        final String descriptionEntered = descriptionCook.getText().toString();

        UUID randID = UUID.randomUUID();
        String randIDString = randID.toString();
        ArrayList<Integer> menu = new ArrayList<>();

        Cook cook = new Cook(randIDString, firstNameEntered, lastNameEntered, emailEntered, passwordEntered, addressNumEntered + " " + addressNameEntered, descriptionEntered);

        DR.child(randIDString).setValue(cook);
    }




}
