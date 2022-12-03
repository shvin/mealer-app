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

public class Cook_Menu_Page extends AppCompatActivity implements View.OnClickListener {

    private ListView listViewMenu;
    private Button updateBtn;
    private Button offeredMealsBtn;
    private Button backBtn;

    private DatabaseReference DR;
    private ArrayList<Meal> menuList;
    private ArrayAdapter<Meal> menuAdapter;
    private String cookID;

    private Boolean removed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_menu_page);

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");

        Toast.makeText(this,"Long click on a meal item to remove it from the menu or add it to the offered meals",Toast.LENGTH_LONG).show();

        listViewMenu = (ListView) findViewById(R.id.listViewMenu);
        backBtn = (Button) findViewById(R.id.backBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        offeredMealsBtn = (Button) findViewById(R.id.offeredMealsBtn);

        DR = FirebaseDatabase.getInstance().getReference("Meals");

        menuList = new ArrayList<>();
        menuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuList);
        listViewMenu.setAdapter(menuAdapter);

        updateBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        offeredMealsBtn.setOnClickListener(this);

        addMenuList();
        onItemLongClick();
    }

    public void onClick(View v){
        if(v.getId() == R.id.updateBtn){
            if(menuList.size() != 0){
                menuList.clear();
                addMenuList();
            } else {
                menuAdapter.clear();
                menuAdapter.notifyDataSetChanged();
            }
        }
        if(v.getId() == R.id.offeredMealsBtn){
            Intent intent = new Intent(this,Offered_Meals_Page.class);
            intent.putExtra("cookID", cookID);
            startActivity(intent);
        }
        if(v.getId() == R.id.backBtn){
            Intent intent = new Intent(this,Cook_Homepage.class);
            intent.putExtra("cookID", cookID);
            startActivity(intent);
        }
    }

    public void addMenuList(){

        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Meal meal = dataSnapshot.getValue(Meal.class);
                if (meal.getCookID().equals(cookID)){
                    menuList.add(meal);
                    menuAdapter.notifyDataSetChanged();
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

    public void onItemLongClick(){
        listViewMenu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meal meal = menuList.get(i);
                addRemoveDialog(meal);

                return true;
            }
        });
    }

    private void addRemoveDialog(Meal meal){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View newView = inflater.inflate(R.layout.add_remove_dialog, null);
        alertDialog.setView(newView);

        TextView nameOfDialog = (TextView) newView.findViewById(R.id.nameOfDialog);
        Button addMeal = (Button) newView.findViewById(R.id.btnAccept);
        Button removeMeal = (Button) newView.findViewById(R.id.btnReject);
        Button dismissBtn = (Button) newView.findViewById(R.id.dismissBtn);

        nameOfDialog.setText("Add/remove meals");

        alertDialog.setTitle("Meal");
        AlertDialog other = alertDialog.create();
        other.show();

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeal(meal);
            }
        });
        removeMeal.setOnClickListener(new View.OnClickListener() {
            DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Meals").child(meal.getId());
            @Override
            public void onClick(View view) {
                if (!meal.isOffered()){
                    DR1.removeValue();
                    removed = true;
                } else {
                    removed = false;
                }
                removeMessage(removed);
            }

        });

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                other.dismiss();
            }
        });
    }

    private void addMeal(Meal meal){
        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference("Meals");
        Meal newMeal = new Meal(meal.getId(),meal.getCookID(),meal.getName(),meal.getMealType(),meal.getCuisineType(),meal.getIngredients(),meal.getAllergens(),meal.getPrice(),
                meal.getDescription(), true);
        DR1.child(meal.getId()).setValue(newMeal);
    }

    private void removeMessage(Boolean removal){
        if (removal){
            Toast.makeText(this, "The Meal has been removed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "The Meal has not been removed since it's offered", Toast.LENGTH_LONG).show();

        }
    }
}