package com.example.dialg.projectlayouts;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class Tab3Fragment extends Fragment{
    private static final String TAG = "Tab3Fragment";
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private Button startbutton;
    private Button pausebutton;
    private Button resetbutton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stopwatch_fragment,container,false);

        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");

        startbutton = (Button) view.findViewById(R.id.StartButton);
        startbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartChronometer(v);
            }
        });

        pausebutton = (Button) view.findViewById(R.id.PauseButton);
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PauseChronometer(v);
            }
        });

        resetbutton = (Button) view.findViewById(R.id.ResetButton);
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetChronometer(v);
            }
        });


        return view;
    }
    public void StartChronometer(View v){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void PauseChronometer(View v){
        if(running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void ResetChronometer(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
