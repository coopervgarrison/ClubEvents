package com.example.cooper.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DisplayEvent extends AppCompatActivity {

    ToggleButton RSVP = null;
    TextView date = null;
    TextView description = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        RSVP = (ToggleButton) findViewById(R.id.toggleButton);
        date = (TextView) findViewById(R.id.dateText);
        description = (TextView) findViewById(R.id.descrText);

        date.setText("01/12/18  11:11 AM");
        description.setText("Organization:" + "\n" + "Description of event.");
        //RSVP.toggle();
    }


}
