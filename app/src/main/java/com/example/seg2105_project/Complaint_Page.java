package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

public class Complaint_Page extends AppCompatActivity implements View.OnClickListener{

    private ListView listViewComplaints;
    private DatabaseReference DR;
    private ArrayList<Complaint> complaintsList;
    private ArrayAdapter<Complaint> complaintsAdapter;
    private Button backBtn;
    private Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);
        DR = FirebaseDatabase.getInstance().getReference("Complaints");
        backBtn = (Button) findViewById(R.id.backBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);

        complaintsList = new ArrayList<>();
        complaintsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, complaintsList);
        listViewComplaints.setAdapter(complaintsAdapter);

        updateBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        DR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Complaint value = dataSnapshot.getValue(Complaint.class);
                complaintsList.add(value);

                System.out.println(complaintsList);

                complaintsAdapter.notifyDataSetChanged();
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
//        addComplaint();
        onItemLongClick();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.updateBtn){
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }
        if (v.getId() == R.id.backBtn){
            startActivity(new Intent(this, Admin_Homepage.class));
        }
    }

    private void onItemLongClick() {
        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint complaint = complaintsList.get(i);
                System.out.println(complaint.toString());
                dismissSuspendDialog(complaint);
                return true;
            }
        });
    }

    private void dismissSuspendDialog(Complaint complaint){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View newView = inflater.inflate(R.layout.dismiss_suspend_dialog, null);
        alertDialog.setView(newView);

        TextView cookName = (TextView) newView.findViewById(R.id.dialog_cook_name);
        TextView cookID = (TextView) newView.findViewById(R.id.dialog_cook_ID);
        EditText suspensionLength = (EditText) newView.findViewById(R.id.dialog_suspensionLength);
        Button suspendBtn = (Button) newView.findViewById(R.id.suspendBtn);
        Button banBtn = (Button) newView.findViewById(R.id.banBtn);
        Button dismissBtn = (Button) newView.findViewById(R.id.dismissBtn);

       cookName.setText("Tmp name");
       cookID.setText(complaint.getCookID());

        alertDialog.setTitle("Complaint");
        AlertDialog other = alertDialog.create();
        other.show();

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteComplaint(complaint.getId());
                other.dismiss();
            }
        });
        suspendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void deleteComplaint(String id) {
        DatabaseReference dataBaseR = FirebaseDatabase.getInstance().getReference("Complaints").child(id);

        dataBaseR.removeValue();
        Toast.makeText(this, "The Complaint has been dismissed", Toast.LENGTH_LONG).show();
    }

    private void suspend(String complaintID, String cookID, TextView length){

    }

    private void ban(){

    }

    private void addComplaint(){
        Complaint complaint = new Complaint("123","This is a complaint", "432122");
        DR.child(complaint.getId()).setValue(complaint);

        Complaint complaint2 = new Complaint("2","This is a complaint", "55522");
        DR.child(complaint2.getId()).setValue(complaint2);

        Complaint complaint3 = new Complaint("3","This is a complaint", "99900");
        DR.child(complaint3.getId()).setValue(complaint3);
    }

}




