package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Search_Meals_Page extends AppCompatActivity implements View.OnClickListener{

    Button btnBack_Search;
    Button btn_Search;
    private ListView listViewSearchResults;
    private EditText searchBarName;
    private EditText searchBarType;
    private EditText searchBarCuisine;


    private DatabaseReference DR;
    private ArrayList<Meal> searchResultsList;
    private ArrayAdapter<Meal> searchResultAdapter;


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
        searchBarName = (EditText) findViewById(R.id.searchBarName);
        searchBarType = (EditText) findViewById(R.id.searchBarType);
        searchBarCuisine = (EditText) findViewById(R.id.searchBarCuisine);

        listViewSearchResults.setAdapter(searchResultAdapter);
        btnBack_Search.setOnClickListener(this);
        btn_Search.setOnClickListener(this);

        fillSearchResultsArray();
    }

    /**
     * onClick listens for a click and proceeds to corresponding activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        String searchAttemptName = searchBarName.getText().toString().trim().toLowerCase();
        String searchAttemptType = searchBarType.getText().toString().trim().toLowerCase();
        String searchAttemptCuisine = searchBarCuisine.getText().toString().trim().toLowerCase();

        Boolean nameFilled = searchAttemptName.length()!=0;
        Boolean typeFilled = searchAttemptType.length()!=0;
        Boolean cuisineFilled = searchAttemptCuisine.length()!=0;

        if(v.getId() == R.id.btnBack_Search){
            Intent intent = new Intent(this, Client_Homepage.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btn_Search){
            if (nameFilled ||typeFilled || cuisineFilled){
                searchResultsList.clear();
                fillSearchResultsArray(searchAttemptName,searchAttemptType,searchAttemptCuisine,nameFilled,typeFilled,cuisineFilled);
                searchResultAdapter.notifyDataSetChanged();
            } else {
                searchResultsList.clear();
                fillSearchResultsArray();
            }
        }
    }

    public void fillSearchResultsArray(){
        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                System.out.println(2);
                Meal meal = dataSnapshot.getValue(Meal.class);
                if (meal.isOffered()) {
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
    public void fillSearchResultsArray(String enteredName, String enteredType, String enteredCuisine, Boolean name, Boolean type, Boolean cuisine){
        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Meal meal = dataSnapshot.getValue(Meal.class);

                if (meal.isOffered()) {

                    if (name) {
                        if (meal.getName() != null) {
                            if (meal.getName().toLowerCase().contains(enteredName)) {
                                searchResultsList.add(meal);
                                mealEntered = true;
                            }
                        }
                    }
                    if (type) {
                        if (meal.getMealType() != null) {
                            if (meal.getMealType().toLowerCase().contains(enteredType) && !mealEntered) {
                                searchResultsList.add(meal);
                                mealEntered = true;
                            }
                        }
                    }

                    if (cuisine) {
                        if (meal.getCuisineType() != null) {
                            if (meal.getCuisineType().toLowerCase().contains(enteredCuisine) && !mealEntered) {
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