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

    /**
     * onClick listens for a click and proceeds to corresponding activity/method
     * @param v
     */
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

    /**
     * Checks to see if provided info is correct or not. If all conditions are met, then the Client can be registered
     * E.g, first name can only contain letters
     * @return
     */
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

        /*
        inputs must be at least of length 1
         */
        if(firstNameEntered.length() == 0 || lastNameEntered.length() == 0 || emailEntered.length() == 0||
                passwordEntered.length() == 0 || addressNumEntered.length() == 0 ||
                addressNameEntered.length() == 0 || cardNumEntered.length() == 0 || monthYearEntered.length() == 0 || cvvEntered.length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Input cannot be empty",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        /*
        first name cannot contain anything but letters
         */
        for (int i = 0; i < firstNameEntered.length(); i++) {
            if (!(Character.isLetter(firstNameEntered.charAt(i)))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your first name must only contain letters",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        /*
        first name cannot contain anything but letters
         */
        for (int i = 0; i < lastNameEntered.length(); i++) {
            if (!(Character.isLetter(lastNameEntered.charAt(i)))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your last name must only contain letters",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        /*
        email cannot have any spaces and must not contain more than one '@'
         */
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

        /*
        email must contain a '@'
         */
        if (found == false) {
            Toast toast = Toast.makeText(getApplicationContext(), "Your email must have a '@'", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        /*
        password must be at least 8 characters in length
         */
        if (passwordEntered.length() < 8) {
            Toast toast = Toast.makeText(getApplicationContext(), "Your password must be at least 8 characters",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        /*
        address number must only contain numbers
         */
        for (int i = 0;i < addressNumEntered.length(); i++){
            if (!Character.isDigit(addressNumEntered.charAt(i))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your street number must only contain numbers",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        /*
        address name must only contain letters and spaces
         */
        for (int i = 0;i < addressNameEntered.length(); i++){
            if (!Character.isLetter(addressNameEntered.charAt(i)) && addressNameEntered.charAt(i) != ' ') {
                Toast toast = Toast.makeText(getApplicationContext(), "Your street name must only contain letters and spaces",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        /*
        card number must only contain numbers
         */
        for (int i = 0; i < cardNumEntered.length(); i++) {
            if (!Character.isDigit(cardNumEntered.charAt(i))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your card number must only contain numbers",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        /*
        card number must be 16 characters long
         */
        if (cardNumEntered.length() != 16){
            Toast toast = Toast.makeText(getApplicationContext(), "Your card number must be 16 digits",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        /*
        length of month/year must be 4 characters 'mm/yy'
         */
        if (monthYearEntered.length() != 4){
            Toast toast = Toast.makeText(getApplicationContext(), "Your credit card expiration date must be four digits", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        /*
        CVV pin must only contain numbers
         */
        for(int i = 0; i < cvvEntered.length(); i++){
            if (!Character.isDigit(cvvEntered.charAt(i))){
                Toast toast = Toast.makeText(getApplicationContext(), "Your cvv must only contain digits",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        }

        /*
        the length of the CVV pin must be 3 characters
         */
        if (cvvEntered.length() != 3){
            Toast toast = Toast.makeText(getApplicationContext(), "Your cvv must be 3 digits",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }

    /**
     * Writes a new Client to the Database with the information input to the page
     * @throws IOException
     * @throws ClassNotFoundException
     */
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