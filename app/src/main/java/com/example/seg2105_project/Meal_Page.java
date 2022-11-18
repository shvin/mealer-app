package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
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


public class Meal_Page extends AppCompatActivity implements View.OnClickListener{
    private EditText nameMeal;
    private EditText mealTypeMeal;
    private EditText cuisineTypeMeal;
    private EditText ingredientsMeal;
    private EditText allergensMeal;
    private EditText priceMeal;
    double price;
    private EditText descriptionMeal;
    private Button btnAddMeal;
    private Button btnBackMeal;
    boolean repeat = false;
    boolean goneThrough = false;
    DatabaseReference DR;
    String cookID;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_page);

        btnAddMeal = (Button) findViewById(R.id.btnAddMeal);
        btnBackMeal = (Button) findViewById(R.id.btnBackMeal);
        nameMeal = (EditText) findViewById(R.id.nameMeal);
        mealTypeMeal = (EditText) findViewById(R.id.mealTypeMeal);
        cuisineTypeMeal = (EditText) findViewById(R.id.cuisineTypeMeal);
        ingredientsMeal = (EditText) findViewById(R.id.ingredientsMeal);
        allergensMeal = (EditText) findViewById(R.id.allergensMeal);
        priceMeal = (EditText) findViewById(R.id.priceMeal);
        descriptionMeal = (EditText) findViewById(R.id.descriptionMeal);

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");

        DR = FirebaseDatabase.getInstance().getReference("Meals");

        btnAddMeal.setOnClickListener(this);
        btnBackMeal.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnAddMeal:
                checkName(nameMeal);
                break;
            case R.id.btnBackMeal:
                Intent intent = new Intent(this,Cook_Homepage.class);
                intent.putExtra("cookID", cookID);
                startActivity(intent);
                break;
        }
    }

    public boolean checkInfo() {

        boolean found = false;
        final String nameEntered = nameMeal.getText().toString();
        final String mealTypeEntered = mealTypeMeal.getText().toString();
        final String cuisineTypeEntered = cuisineTypeMeal.getText().toString();
        final String ingredientsEntered = ingredientsMeal.getText().toString();
        final String allergensEntered = allergensMeal.getText().toString();
        final String priceEntered = priceMeal.getText().toString();
        final String descriptionEntered = descriptionMeal.getText().toString();

        if(nameEntered.length() == 0 || mealTypeEntered.length() == 0 || cuisineTypeEntered.length() == 0||
                ingredientsEntered.length() == 0 || priceEntered.length() == 0 || descriptionEntered.length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Input cannot be empty",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        try
        {
            price = Double.parseDouble(priceEntered);
            if (!((priceEntered.charAt(priceEntered.length() - 3))=='.')){
                Toast toast = Toast.makeText(getApplicationContext(), "Format must be XX.XX",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }

        }
        catch(Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Your price is invalid",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
       return true;
    }

    private void checkName(EditText nameMeal){
        final String nameEntered = nameMeal.getText().toString().toLowerCase();

        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Meal meal = data.getValue(Meal.class);
                    if (nameEntered.equals(meal.getName()) && cookID == meal.getCookID()) {
                        repeatTrue();
                    }
                }
                if (repeat == false){
                    nameNotRepeated();
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

    private void nameNotRepeated() {
        goneThrough=true;
        if (checkInfo() == true) {
            try {
                writeNewUser();
                Intent intent = new Intent(this,Cook_Homepage.class);
                intent.putExtra("cookID", cookID);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeNewUser() throws IOException, ClassNotFoundException {
        final String nameEntered = nameMeal.getText().toString();
        final String mealTypeEntered = mealTypeMeal.getText().toString();
        final String cuisineTypeEntered = cuisineTypeMeal.getText().toString();
        final String ingredientsEntered = ingredientsMeal.getText().toString();
        final String allergensEntered = allergensMeal.getText().toString();
        final String descriptionEntered = descriptionMeal.getText().toString();

        UUID randID = UUID.randomUUID();
        String randIDString = randID.toString();

        Meal meal = new Meal(randIDString, cookID, nameEntered, mealTypeEntered, cuisineTypeEntered, ingredientsEntered, allergensEntered, price, descriptionEntered);

        DR.child(randIDString).setValue(meal);
    }
}
