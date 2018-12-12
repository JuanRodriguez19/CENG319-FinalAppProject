package com.example.dialg.projectlayouts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TempHistory extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    DataStructure mData;

    private TextView temperature;
    private TextView timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_history);

        //Back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Previous Saves");

        //Reads from Database Functions
        getDatabase();
        findAllViews();
        retrieveData();

    }


    private void findAllViews(){
        temperature = findViewById(R.id.readTemperature);
        timestamp = findViewById(R.id.readtimestamp);
    }

    private void getDatabase(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
    }

    private void retrieveData(){
      myRef.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              DataStructure ds = dataSnapshot.getValue(DataStructure.class);
              temperature.setText("Previous Temperature (in celsius): " +ds.getTemperature());

              timestamp.setText(convertTimestamp(ds.getTimestamp()));
          }

          private String convertTimestamp(String timestamp){

              long yourSeconds = Long.valueOf(timestamp);
              Date mDate = new Date(yourSeconds * 1000);
              DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
              return df.format(mDate);
          }


          @Override
          public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              DataStructure ds = dataSnapshot.getValue(DataStructure.class);
              temperature.setText("Temperature:   "+ds.getTemperature());

              timestamp.setText(convertTimestamp(ds.getTimestamp()));

          }

          @Override
          public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

          }

          @Override
          public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataStructure> arraylist = new ArrayList<>();

                if (dataSnapshot !=null && dataSnapshot.getValue() !=null){

                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        DataStructure dataStructure = new DataStructure();
                        dataStructure.setTemperature(a.getValue(DataStructure.class).getTemperature());
                        dataStructure.setTimestamp(a.getValue(DataStructure.class).getTimestamp());


                        arraylist.add(dataStructure);
                        Log.d("MapleLeaf", "dataStructure " + dataStructure.getTimestamp());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MapleLeaf", "Data Loading Cancelled/Failed.", databaseError.toException());
            }

        });

    }


}
