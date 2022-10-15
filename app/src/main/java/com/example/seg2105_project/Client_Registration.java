package com.example.seg2105_project;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Client_Registration extends AppCompatActivity implements View.OnClickListener {

    Button btnRegisterClient;
    EditText firstNameClient;
    EditText lastNameClient;
    EditText emailAddressClient;
    EditText passwordClient;
    EditText addressClient;
    EditText cardNumberClient;
    EditText monthYearClient;
    EditText cvvClient;

    boolean correctFirstName = true;
    boolean correctLastName = true;
    boolean correctEmail = true;
    boolean correctPassword = true;
    boolean correctAddress = true;
    boolean correctCardNum = true;
    boolean correctMonthYear = true;
    boolean correctCVV = true;


    DatabaseReference DR;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);

        btnRegisterClient = (Button) findViewById(R.id.btnRegisterClient);
        firstNameClient = (EditText) findViewById(R.id.firstNameClient);
        lastNameClient = (EditText) findViewById(R.id.lastNameClient);
        emailAddressClient = (EditText) findViewById(R.id.emailAddressClient);
        passwordClient = (EditText) findViewById(R.id.passwordClient);
        addressClient = (EditText) findViewById(R.id.addressClient);
        cardNumberClient = (EditText) findViewById(R.id.cardNumberClient);
        monthYearClient = (EditText) findViewById(R.id.monthYearClient);
        cvvClient = (EditText) findViewById(R.id.cvvClient);

        DR = FirebaseDatabase.getInstance().getReference();

        btnRegisterClient.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRegisterClient:
               if (checkInfo() == true) {

                   try {
                       System.out.println("hi");
                       writeNewUser();
                   } catch (ClassNotFoundException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   //startActivity(new Intent(this, Client_Homepage.class));
               }
        }
    }



    public boolean checkInfo() {
        boolean correct = true;
        final String firstNameEntered = firstNameClient.getText().toString();
        final String lastNameEntered = lastNameClient.getText().toString();
        final String emailEntered = emailAddressClient.getText().toString();
        final String passwordEntered = passwordClient.getText().toString();
        final String addressEntered = addressClient.getText().toString();
        final String cardNumEntered = (cardNumberClient.getText().toString()).replace(" ","");
        final String monthYearEntered = monthYearClient.getText().toString();
        final String cvvEntered = cvvClient.getText().toString();
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

        for (int i = 0; i < cardNumEntered.length(); i++) {

            if (!Character.isDigit(cardNumEntered.charAt(i))) {
                correctCardNum = false;
            }
            if (i > 15){
                correctCardNum = false;
            }
        }
        if (monthYearEntered.length() != 5){
            correctMonthYear = false;
        } else {
            for (int i = 0; i < monthYearEntered.length(); i++) {
                switch(i){
                    case 0:
                    if (!Character.isDigit(monthYearEntered.charAt(0))){
                        correctMonthYear = false;
                    } else {
                        if (monthYearEntered.charAt(0) != '0'||monthYearEntered.charAt(0) != '1'){
                            correctMonthYear = false;

                        }
                    }
                    case 1:
                        if (!Character.isDigit(monthYearEntered.charAt(1))){
                            correctMonthYear = false;
                        }
                    case 2:
                        if (monthYearEntered.charAt(2) != '/'){
                            correctMonthYear = false;
                        }
                    case 3:
                        if (!Character.isDigit(monthYearEntered.charAt(0))){
                            correctMonthYear = false;
                        }
                    case 4:
                        if (!Character.isDigit(monthYearEntered.charAt(0))){
                            correctMonthYear = false;
                        }
                }

            }

        }

        for(int i = 0; i < cvvEntered.length(); i++){
            if (!Character.isDigit(cvvEntered.charAt(i))){
                correctCVV = false;
            }
            if (i > 2){
                correctCVV = false;
            }
        }

        /*System.out.println((correctFirstName == false || correctLastName == false || correctEmail == false || correctPassword == false || correctAddress == false || correctCardNum == false || correctMonthYear == false || correctCVV == false));
        if (correctFirstName == false || correctLastName == false || correctEmail == false || correctPassword == false || correctAddress == false || correctCardNum == false || correctMonthYear == false || correctCVV == false)
            return false;*/
        return true;
    }

    public void writeNewUser() throws IOException, ClassNotFoundException {
        Runner run = Runner.getInstance();
        final String firstNameEntered = firstNameClient.getText().toString();
        final String lastNameEntered = lastNameClient.getText().toString();
        final String emailEntered = emailAddressClient.getText().toString();
        final String passwordEntered = passwordClient.getText().toString();
        final String addressEntered = addressClient.getText().toString();
        final String cardNumEntered = (cardNumberClient.getText().toString()).replace(" ","");
        final String monthYearEntered = monthYearClient.getText().toString();
        final String cvvEntered = cvvClient.getText().toString();
        final int tempId = run.randomIdGenerator();
        Client client = new Client(tempId, firstNameEntered, lastNameEntered, emailEntered, passwordEntered, addressEntered, cardNumEntered, monthYearEntered, cvvEntered);

        DR.child("Clients").child(Integer.toString(tempId)).setValue(client);
    }
}