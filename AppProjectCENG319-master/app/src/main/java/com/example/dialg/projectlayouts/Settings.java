package com.example.dialg.projectlayouts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

//
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.Locale;
//



public class Settings extends AppCompatActivity implements View.OnClickListener {

    SwitchCompat colorSwitch;
    boolean stateSwitch1;

    //
    private String englishLanguageCode = "en";
    private Button english,french;
    //

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.check).setOnClickListener(this);
        findViewById(R.id.aboutus).setOnClickListener(this);

        english = (Button) findViewById(R.id.english);
        french = (Button) findViewById(R.id.french);

        view= this.getWindow().getDecorView();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");

        final SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        stateSwitch1 = preferences.getBoolean("switch1", false);

        colorSwitch = (SwitchCompat) findViewById(R.id.color_switch);

        colorSwitch.setChecked(stateSwitch1);

        colorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked == false){
                    stateSwitch1 = !stateSwitch1;
                    colorSwitch.setChecked(stateSwitch1);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("switch1", stateSwitch1);
                    editor.apply();
                    view.setBackgroundResource(R.color.white);

            }else{
                    stateSwitch1 = !stateSwitch1;
                    colorSwitch.setChecked(stateSwitch1);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("switch1", stateSwitch1);
                    view.setBackgroundResource(R.color.lightBlack);
                }
                }
        });

        //Change English to French when user clicked the button.
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Apply english locale", Toast.LENGTH_LONG).show();
                String languageToLoad  = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

            }
        });
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Apply french locale", Toast.LENGTH_LONG).show();
                String languageToLoad  = "fr";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());


            }
        });

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.check:
                Toast.makeText(getApplicationContext(),"No update required", Toast.LENGTH_SHORT).show();
                break;

            case R.id.aboutus:
                startActivity(new Intent(this, AboutUs.class));

                break;
        }
    }


}
