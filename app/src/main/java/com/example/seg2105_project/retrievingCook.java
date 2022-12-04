package com.example.seg2105_project;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;

public class retrievingCook {

    String cookId;
    Cook cook;
    DatabaseReference DR1;

    public retrievingCook(String id){
        this.cookId = id;
        searchCook(id);
        DR1 = FirebaseDatabase.getInstance().getReference().child("Users/Cooks");

    }

    public void searchCook(String cookID){
        System.out.println("9");
        DR1.addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("4");
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Cook currentCook = data.getValue(Cook.class);
                    System.out.println("5");
                    if (cookID.equals(currentCook.getId())) {
                        System.out.println("HERE");
                        writeCook(currentCook);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void writeCook(Cook cook){
        this.cook = cook;
        System.out.println(this.cook.getId());
    }

    public Cook getCook(){
        return cook;
    }
}
