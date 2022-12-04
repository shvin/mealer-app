package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class Search_Meals_Page extends AppCompatActivity implements View.OnClickListener{

    Button btnBack_Search;
    Button btn_Search;
    private ListView listViewSearchResults;
    private EditText searchBar;
    CheckBox nameFilter;
    CheckBox cuisineFilter;
    CheckBox typeFilter;

    Boolean nameChecked;
    Boolean cuisineChecked;
    Boolean typeChecked;

    private String clientID;
    private DatabaseReference DR;
    private ArrayList<Meal> searchResultsList;
    private ArrayAdapter<Meal> searchResultAdapter;
    String firstName;
    String lastName;
    String addressRetrieved;
    String email;
    String description;
    String ratingRetrieved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search);
        btnBack_Search = (Button) findViewById(R.id.btnBack_Search);
        btn_Search = (Button) findViewById(R.id.btn_Search);
        listViewSearchResults = (ListView) findViewById(R.id.listViewSearchResults);

        DR = FirebaseDatabase.getInstance().getReference("Meals");
        searchResultsList = new ArrayList<>();
        searchResultAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, searchResultsList);
        searchBar = (EditText) findViewById(R.id.searchBar);
        nameFilter = (CheckBox)findViewById(R.id.nameCB);
        typeFilter = (CheckBox)findViewById(R.id.typeCB);
        cuisineFilter = (CheckBox)findViewById(R.id.cuisineCB);

        firstName ="";
        lastName = "";
        addressRetrieved = "";
        email= "";
        description = "";
        ratingRetrieved = "";


        Intent intent = getIntent();
        clientID = intent.getStringExtra("clientID");

        listViewSearchResults.setAdapter(searchResultAdapter);
        btnBack_Search.setOnClickListener(this);
        btn_Search.setOnClickListener(this);


        fillSearchResultsArray();

        onItemLongClick();
    }

    /**
     * onClick listens for a click and proceeds to corresponding activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        String searchAttempt = searchBar.getText().toString().trim().toLowerCase();

        Boolean filled = searchAttempt.length() != 0;

        nameChecked = nameFilter.isChecked();
        cuisineChecked = cuisineFilter.isChecked();
        typeChecked = typeFilter.isChecked();

        if (v.getId() == R.id.btnBack_Search) {
            Intent intent = new Intent(this, Client_Homepage.class);
            intent.putExtra("clientID", clientID);
            startActivity(intent);
        }
        if (v.getId() == R.id.btn_Search) {
            if (filled) {
                searchResultsList.clear();
                if (!nameChecked && !typeChecked && !cuisineChecked) {
                    fillSearchResultsArray(searchAttempt, true, true, true);
                } else {
                    fillSearchResultsArray(searchAttempt, nameChecked, typeChecked, cuisineChecked);
                }

                searchResultAdapter.notifyDataSetChanged();
            } else {
                searchResultsList.clear();
                fillSearchResultsArray();
            }
        }
    }

    private void onItemLongClick() {
        listViewSearchResults.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meal meal = searchResultsList.get(i);
                retrieveCookInfo(meal, meal.getCookID());
                System.out.println(1);
                System.out.println(firstName);
                System.out.println(lastName);

                return true;
            }
        });
    }

    public void retrieveCookInfo(Meal meal, String cookID) {
        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Users/Cooks");
        DR1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Cook cook = dataSnapshot.getValue(Cook.class);
                if (cook.getId().equals(cookID)) {
                    firstName = cook.getFirstName();
                    lastName = cook.getLastName();
                    addressRetrieved = cook.getAddress();
                    email = cook.getEmail();
                    description = cook.getDescription();
                    ratingRetrieved = String.valueOf(cook.getAverageRating());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (ratingRetrieved.length() > 0) {
            dismissSuspendDialog(meal, firstName, lastName, addressRetrieved, email, description, ratingRetrieved);
        }
    }

    private void dismissSuspendDialog(Meal meal, String firstName2, String lastName2, String addressRetrieved2, String email2, String description2, String ratingRetrieved2){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View newView = inflater.inflate(R.layout.view_meal_dialog, null);
        alertDialog.setView(newView);

        TextView cookName = (TextView) newView.findViewById(R.id.cookName);
        TextView address = (TextView) newView.findViewById(R.id.address);
        TextView emailCook = (TextView) newView.findViewById(R.id.email);
        TextView descriptionCook = (TextView) newView.findViewById(R.id.descriptionCook);
        TextView ratings = (TextView) newView.findViewById(R.id.ratings);
        TextView mealName = (TextView) newView.findViewById(R.id.mealName);
        TextView descriptionMeal = (TextView) newView.findViewById(R.id.descriptionMeal);
        TextView mealType = (TextView) newView.findViewById(R.id.mealType);
        TextView cuisineType = (TextView) newView.findViewById(R.id.cuisineType);
        TextView price = (TextView) newView.findViewById(R.id.price);

        Button orderBtn = (Button) newView.findViewById(R.id.orderBtn);
        Button dismissBtn = (Button) newView.findViewById(R.id.dismissBtn);

        //Cook information
        cookName.setText(firstName2+ " " + lastName2);
        address.setText(addressRetrieved2);
        emailCook.setText(email2);
        descriptionCook.setText(description2);
        ratings.setText(String.valueOf(ratingRetrieved2));

        // Meal information
        mealName.setText(meal.getName());
        descriptionMeal.setText(meal.getDescription());
        mealType.setText(meal.getMealType());
        cuisineType.setText(meal.getCuisineType());
        price.setText(String.valueOf(meal.getPrice()));

        alertDialog.setTitle("Cook/Meal information");
        AlertDialog other = alertDialog.create();
        other.show();

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                other.dismiss();
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewOrder(meal);
                other.dismiss();
            }
        });
    }

    private void writeNewOrder(Meal meal){
            DatabaseReference DR2 = FirebaseDatabase.getInstance().getReference("Orders");

            UUID randID = UUID.randomUUID();
            String randIDString = randID.toString();
            Order order = new Order(randIDString, meal.getCookID(), meal.getId(), clientID, meal.getName(), true, false, false);
            DR2.child(randIDString).setValue(order);
    }



//    public void searchCook(String cookID){
//        System.out.println("3");
//        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Users/Cooks");
//        DR1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                System.out.println("4");
//                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    Cook currentCook = data.getValue(Cook.class);
//                    System.out.println("5");
//                    if (cookID.equals(currentCook.getId())) {
//                        System.out.println("HERE");
//                        writeCook(currentCook);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    public void writeCook(Cook cook){
//        this.cook = cook;
//        System.out.println(this.cook.getId());
//    }



    public void fillSearchResultsArray(){
        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

                Meal meal = dataSnapshot.getValue(Meal.class);
                if (meal.isOffered() && meal.isAvailable()) {
                    searchResultsList.add(meal);
                    searchResultAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    Boolean mealEntered = false;
    public void fillSearchResultsArray(String entered, Boolean name, Boolean type, Boolean cuisine){

        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Meal meal = dataSnapshot.getValue(Meal.class);

                if (meal.isOffered()&&meal.isAvailable()) {
                    if (name) {
                        if (meal.getName() != null) {
                            if (meal.getName().toLowerCase().contains(entered)) {
                                searchResultsList.add(meal);
                                mealEntered = true;
                            }
                        }
                    }
                    if (type) {
                        if (meal.getMealType() != null) {
                            if (meal.getMealType().toLowerCase().contains(entered) && !mealEntered) {
                                searchResultsList.add(meal);
                                mealEntered = true;
                            }
                        }
                    }

                    if (cuisine) {
                        if (meal.getCuisineType() != null) {
                            if (meal.getCuisineType().toLowerCase().contains(entered) && !mealEntered) {
                                searchResultsList.add(meal);
                                mealEntered = true;
                            }
                        }
                    }
                    mealEntered = false;
                    searchResultAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}