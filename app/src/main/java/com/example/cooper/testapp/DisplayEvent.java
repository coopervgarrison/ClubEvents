package com.example.cooper.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DisplayEvent extends AppCompatActivity {

    ToggleButton RSVP = null;
    TextView dateTimeTitle = null;
    TextView description = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Intent intent = getIntent();
        String eventID = intent.getStringExtra("event");
        RSVP = (ToggleButton) findViewById(R.id.toggleButton);
        dateTimeTitle = (TextView) findViewById(R.id.eventTitle);
        description = (TextView) findViewById(R.id.descrText);
        if (intent.getBooleanExtra("RSVP", false))
            RSVP.toggle();
        dateTimeTitle.setText(eventID);
        description.setText("Organization:" + "\n" + "Description of event.");
    }


}
