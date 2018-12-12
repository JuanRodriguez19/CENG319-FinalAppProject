package com.example.dialg.projectlayouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private Calendar current;
    private Button btnTEST;
    private Spinner spinner;
    private TextView timezone, txtCurrentTime, txtTimezoneTime;
    private long milliseconds;
    private ArrayAdapter<String> idAdapter;
    private SimpleDateFormat sdf;
    private Date resultDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clock_fragment,container,false);

        spinner = (Spinner) view.findViewById(R.id.spinner_timezone);
        timezone = (TextView) view.findViewById(R.id.timezone);
        txtCurrentTime = (TextView) view.findViewById(R.id.txtCurrentTime);
        txtTimezoneTime = (TextView) view.findViewById(R.id.txtTimezone);

        String[] idArray = TimeZone.getAvailableIDs();

        sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");

        idAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, idArray);

        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);

        GetGMT();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GetGMT();
                String selectedID = (String) (parent.getItemAtPosition(position));

                TimeZone timeZone = TimeZone.getTimeZone(selectedID);
                String TimeZoneName = timeZone.getDisplayName();

                int TimeZoneOffset = timeZone.getRawOffset() / (60 * 1000);
                int hrs = TimeZoneOffset / 60;
                int mins = TimeZoneOffset % 60;

                milliseconds = milliseconds + timeZone.getRawOffset();

                resultDate = new Date(milliseconds);
                System.out.println(sdf.format(resultDate));

                timezone.setText(TimeZoneName + " : GMT " + hrs + ":" + mins);
                txtTimezoneTime.setText("" + sdf.format(resultDate));
                milliseconds = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void GetGMT(){
        current = Calendar.getInstance();
        txtCurrentTime.setText("" + current.getTime());

        milliseconds = current.getTimeInMillis();

        TimeZone tzCurrent = current.getTimeZone();
        int offset = tzCurrent.getRawOffset();

        if(tzCurrent.inDaylightTime(new Date())){
            offset = offset + tzCurrent.getDSTSavings();
        }

        milliseconds = milliseconds - offset;
        resultDate = new Date(milliseconds);
        System.out.println(sdf.format(resultDate));
    }
}