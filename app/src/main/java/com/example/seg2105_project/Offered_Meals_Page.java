package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Offered_Meals_Page extends AppCompatActivity implements View.OnClickListener{

    private ListView listViewOffered;
    private Button updateBtn;
    private Button backBtn;

    private DatabaseReference DR;
    private ArrayList<Meal> offeredList;
    private ArrayAdapter<Meal> offeredAdapter;
    private String cookID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_meals_page);

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");

        Toast.makeText(this,"Long click on a meal item to remove it from the offered meals list",Toast.LENGTH_LONG).show();

        listViewOffered = (ListView) findViewById(R.id.listViewOffered);
        backBtn = (Button) findViewById(R.id.backBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);

        DR = FirebaseDatabase.getInstance().getReference("Meals");

        offeredList = new ArrayList<>();
        offeredAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, offeredList);
        listViewOffered.setAdapter(offeredAdapter);

        updateBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        addOfferedList();
        onItemLongClick();
    }

    public void onClick(View v){
        if(v.getId() == R.id.updateBtn){
            if(offeredList.size() != 0){
                offeredList.clear();
                addOfferedList();
            } else {
                offeredAdapter.clear();
                offeredAdapter.notifyDataSetChanged();
            }
        }
        if(v.getId() == R.id.backBtn){
            Intent intent = new Intent(this,Cook_Menu_Page.class);
            intent.putExtra("cookID", cookID);
            startActivity(intent);
        }
    }

    private void addOfferedList() {

        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Meal meal = dataSnapshot.getValue(Meal.class);

                if(meal.getCookID().equals(cookID) && meal.isOffered()){
                    offeredList.add(meal);
                    offeredAdapter.notifyDataSetChanged();
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

    private void onItemLongClick() {
        listViewOffered.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meal meal = offeredList.get(i);
                removeSelectedDialog(meal);
                return true;
            }
        });
    }

    private void removeSelectedDialog(Meal meal){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View newView = inflater.inflate(R.layout.remove_dialog, null);
        alertDialog.setView(newView);

        TextView nameOfDialog = (TextView) newView.findViewById(R.id.nameOfDialog);
        Button removeMeal = (Button) newView.findViewById(R.id.remove_meal);
        Button dismissBtn = (Button) newView.findViewById(R.id.dismissBtn);

        nameOfDialog.setText("Add/remove meals");

        alertDialog.setTitle("Offered Meal");
        AlertDialog other = alertDialog.create();
        other.show();

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                other.dismiss();
            }
        });

        removeMeal.setOnClickListener(new View.OnClickListener() {
            DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Meals").child(meal.getId());
            @Override
            public void onClick(View view) {
                updateMeal(meal);
                removeMessage();
            }
        });
    }

    private void updateMeal(Meal meal){
        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Meals");
        Meal newMeal = new Meal(meal.getId(),meal.getCookID(),meal.getName(),meal.getMealType(),meal.getCuisineType(),meal.getIngredients(),meal.getAllergens(),meal.getPrice(),
                meal.getDescription(), false);
        DR1.child(meal.getId()).setValue(newMeal);
    }

    private void removeMessage(){
        Toast.makeText(this, "The Complaint has been removed", Toast.LENGTH_LONG).show();
    }
}