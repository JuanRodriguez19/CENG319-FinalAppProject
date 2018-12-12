package com.example.dialg.projectlayouts;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.TextView;
import android.os.Bundle;


public class TempActivity extends AppCompatActivity {
        private Button btnTemp;
        private EditText temperature;
        private FirebaseDatabase database;
        private DatabaseReference myRef;

        DataStructure mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        //Back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Temperature Readings");

        //Database Functions
        findAllViews();
        getDatabase();

        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(temperature.getText());
            }
        });
    }
    private void getDatabase(){
        // TODO: Find the refernce form the database.
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");

    }

    private DataStructure createData(Editable temperature){
        // TODO: Get the timestamp
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
        return new DataStructure(String.valueOf(temperature),timestamp);

    }
    private void writeData(Editable temperature) {

        DataStructure mData = createData(temperature);
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Temperature Saved ", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Temperature Failed to Save", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Find all the views for this activity.
    private void findAllViews(){
        btnTemp = findViewById(R.id.btnTemp);
        temperature = findViewById(R.id.temperature);
    }
}

