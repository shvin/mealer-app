package com.example.seg2105_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cook_Menu_Page extends AppCompatActivity implements View.OnClickListener {

    private ListView listViewMenu;
    private Button updateBtn;
    private Button offeredMealsBtn;
    private Button backBtn;

    private DatabaseReference DR;
    private ArrayList<Meal> menuList;
    private ArrayAdapter<Meal> menuAdapter;
    private String clickedMeal;
    private String cookID;

    private Boolean removed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_menu_page);

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");

        System.out.println("MENU: " + cookID);

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
            // offered meals page redirect
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
                System.out.println("MEAL: " + meal.getCookID());
                System.out.println("COOK ID:" + cookID);
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

        System.out.println(meal.getId());

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View newView = inflater.inflate(R.layout.add_remove_dialog, null);
        alertDialog.setView(newView);

        TextView nameOfDialog = (TextView) newView.findViewById(R.id.nameOfDialog);
        Button addMeal = (Button) newView.findViewById(R.id.add_meal);
        Button removeMeal = (Button) newView.findViewById(R.id.remove_meal);
        Button dismissBtn = (Button) newView.findViewById(R.id.dismissBtn);

        nameOfDialog.setText("Add/remove meals");

        alertDialog.setTitle("Meal");
        AlertDialog other = alertDialog.create();
        other.show();

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If meal not in offeredList, adds to the offeredList
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
    private void removeMessage(Boolean removal){
        if (removal){
            Toast.makeText(this, "The Complaint has been removed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "The Complaint has not been removed since it's offered", Toast.LENGTH_LONG).show();

        }
    }

//    private void deleteMeal(String mealID){
//
//        DatabaseReference databaseR = FirebaseDatabase.getInstance().getReference("Meals");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot data : dataSnapshot.getChildren()){
//                    Meal meal = data.getValue(Meal.class);
//
//                    if (mealID.equals(meal.getId()) && !meal.isOffered()){
//                        databaseR.removeValue();
//                        removed = true;
//                        break;
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());
//            }
//        };
//        if (removed){
//            Toast.makeText(this, "The meal has been removed", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "The meal has not been removed because it is offered", Toast.LENGTH_LONG).show();
//        }
//
//    }

}