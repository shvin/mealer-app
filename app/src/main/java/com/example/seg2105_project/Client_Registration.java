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

public class Client_Registration extends AppCompatActivity implements View.OnClickListener {

    Button btnRegisterClient;
    Button btnBack;
    EditText firstNameClient;
    EditText lastNameClient;
    EditText emailAddressClient;
    EditText passwordClient;
    EditText addressNumClient;
    EditText addressNameClient;
    EditText cardNumberClient;
    EditText monthYearClient;
    EditText cvvClient;

    DatabaseReference DR;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);

        btnRegisterClient = (Button) findViewById(R.id.btnRegisterClient);
        btnBack = (Button) findViewById(R.id.btnBack);
        firstNameClient = (EditText) findViewById(R.id.firstNameClient);
        lastNameClient = (EditText) findViewById(R.id.lastNameClient);
        emailAddressClient = (EditText) findViewById(R.id.emailAddressClient);
        passwordClient = (EditText) findViewById(R.id.passwordClient);
        addressNumClient = (EditText) findViewById(R.id.addressNumClient);
        addressNameClient = (EditText) findViewById(R.id.addressNameClient);
        cardNumberClient = (EditText) findViewById(R.id.cardNumberClient);
        monthYearClient = (EditText) findViewById(R.id.monthYearClient);
        cvvClient = (EditText) findViewById(R.id.cvvClient);



        DR = FirebaseDatabase.getInstance().getReference();

        btnRegisterClient.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRegisterClient:
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
            case R.id.btnBack:
                startActivity(new Intent(this, Register_Login_Page.class));
                break;
        }
    }


    public boolean checkInfo() {

        boolean found = false;
        final String firstNameEntered = firstNameClient.getText().toString();
        final String lastNameEntered = lastNameClient.getText().toString();
        final String emailEntered = emailAddressClient.getText().toString();
        final String passwordEntered = passwordClient.getText().toString();
        final String addressNumEntered = addressNumClient.getText().toString();
        final String addressNameEntered = addressNameClient.getText().toString();
        final String cardNumEntered = (cardNumberClient.getText().toString()).replace(" ","");
        final String monthYearEntered = monthYearClient.getText().toString();
        final String cvvEntered = cvvClient.getText().toString();

        if(firstNameEntered.length() == 0 || lastNameEntered.length() == 0 || emailEntered.length() == 0||
                passwordEntered.length() == 0 || addressNumEntered.length() == 0 ||
                addressNameEntered.length() == 0 || cardNumEntered.length() == 0 || monthYearEntered.length() == 0 || cvvEntered.length() == 0){
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

        for (int i = 0; i < cardNumEntered.length(); i++) {

            if (!Character.isDigit(cardNumEntered.charAt(i))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your card number must only contain numbers",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }
        if (cardNumEntered.length() != 16){
            Toast toast = Toast.makeText(getApplicationContext(), "Your card number must be 16 digits",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        if (monthYearEntered.length() != 4){
            Toast toast = Toast.makeText(getApplicationContext(), "Your credit card expiration date must be four digits", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
//        else {
//            for (int i = 0; i < monthYearEntered.length(); i++) {
//                if (!Character.isDigit(monthYearEntered.charAt(i))) {
//                    Toast toast = Toast.makeText(getApplicationContext(), "Your credit card expiration date must only contain digits", Toast.LENGTH_SHORT);
//                    toast.show();
//                    return false;
//                }
//            }
//            if (monthYearEntered.charAt(0) != '0' && monthYearEntered.charAt(0) != '1'){
//                Toast toast = Toast.makeText(getApplicationContext(), "Your credit card expiration month cannot be over 12",Toast.LENGTH_SHORT);
//                toast.show();
//                return false;
//            }
//            if (monthYearEntered.charAt(0) == '1' && Character.getNumericValue(monthYearEntered.charAt(1)) > 2){
//                Toast toast = Toast.makeText(getApplicationContext(), "Your credit card expiration month cannot be over 12",Toast.LENGTH_SHORT);
//                toast.show();
//                return false;
//            }
//            if (monthYearEntered.charAt(3) != '2' && Character.getNumericValue(monthYearEntered.charAt(4)) < 2){
//                Toast toast = Toast.makeText(getApplicationContext(), "Your credit card expiration year must be over 2022",Toast.LENGTH_SHORT);
//                toast.show();
//                return false;
//            }
//        }

        for(int i = 0; i < cvvEntered.length(); i++){
            if (!Character.isDigit(cvvEntered.charAt(i))){
                Toast toast = Toast.makeText(getApplicationContext(), "Your cvv must only contain digits",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }
        if (cvvEntered.length() != 3){
            Toast toast = Toast.makeText(getApplicationContext(), "Your cvv must be 3 digits",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }

    public void writeNewUser() throws IOException, ClassNotFoundException {
        final String firstNameEntered = firstNameClient.getText().toString();
        final String lastNameEntered = lastNameClient.getText().toString();
        final String emailEntered = emailAddressClient.getText().toString();
        final String passwordEntered = passwordClient.getText().toString();
        final String addressNumEntered = addressNumClient.getText().toString();
        final String addressNameEntered = addressNameClient.getText().toString();
        final String cardNumEntered = (cardNumberClient.getText().toString()).replace(" ","");
        final String monthYearEntered = monthYearClient.getText().toString();
        final String cvvEntered = cvvClient.getText().toString();

        int tempId = (int) (Math.random()*10000);
        ArrayList<Integer> orderHistory = new ArrayList<>();
        Client client = new Client(tempId, firstNameEntered, lastNameEntered, emailEntered, passwordEntered, addressNumEntered + " " + addressNameEntered, cardNumEntered, monthYearEntered, cvvEntered,orderHistory);

        DR.child("Users").child("Clients").child(Integer.toString(tempId)).setValue(client);
    }
}