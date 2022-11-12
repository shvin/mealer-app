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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cook_Menu_Page extends AppCompatActivity implements View.OnClickListener {

    ListView listViewMenu;
    Button updateBtn;
    Button offeredMealsBtn;
    Button backBtn;

    DatabaseReference DR;
    ArrayList<Meal> menuList;
    ArrayAdapter<Meal> menuAdapter;
    String clickedMeal;
    String cookID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_menu_page);

        Intent intent = getIntent();
        cookID = intent.getStringExtra("cookID");
        // print
        System.out.println(cookID);

        listViewMenu = (ListView) findViewById(R.id.listViewMenu);
        backBtn = (Button) findViewById(R.id.backBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        offeredMealsBtn = (Button) findViewById(R.id.offeredMealsBtn);

        DR = FirebaseDatabase.getInstance().getReference("Users/Cooks");
        // goes to the cooks menu
        DR.child(cookID).child("menu");

        menuList = new ArrayList<>();
        menuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuList);
        listViewMenu.setAdapter(menuAdapter);



        updateBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        offeredMealsBtn.setOnClickListener(this);




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

        }
        if(v.getId() == R.id.backBtn){
            startActivity(new Intent(this,Cook_Homepage.class));
        }
    }

    public void addMenuList(){

        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Meal meal = dataSnapshot.getValue(Meal.class);
                menuList.add(meal);
                menuAdapter.notifyDataSetChanged();
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

        TextView nameOfDialog = (TextView) findViewById(R.id.nameOfDialog);
        Button addMeal = (Button) findViewById(R.id.add_meal);
        Button removeMeal = (Button) findViewById(R.id.remove_meal);
        Button dismissBtn = (Button) findViewById(R.id.dismissBtn);
    }


    // NEED TO CHECK IF THE MEAL IS IN THE OFFERED MENU LIST FIRST
//    private void deleteMeal(String id) {
//        DatabaseReference databaseR = FirebaseDatabase.getInstance().getReference("Users/Cooks").child(cookID).child("menu");
//
//    }

}