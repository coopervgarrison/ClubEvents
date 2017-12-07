package com.example.cooper.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.ArrayList;

public class FrontPageActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListViewAdapter expandableListViewAdapter;
    ArrayList<String> orgByName, eventByDate, clientEventByDate, keys;
    ArrayList<ArrayList<String>> frontpageMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        fillLists();
        expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
        expandableListViewAdapter = new ExpandableListViewAdapter(this, keys, frontpageMap);
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    public void fillLists() {
        orgByName = new ArrayList<>();
        eventByDate = new ArrayList<>();
        clientEventByDate = new ArrayList<>();
        keys = new ArrayList<>();
        frontpageMap = new ArrayList<>();

        //Should read in the organization list, event list, and RSVP'd event list from database via server

        //Fill in the orgByName, eventByDate, clientEventByDate, and keys lists
        orgByName.add("org1");
        orgByName.add("org2");
        orgByName.add("org3");
        orgByName.add("org4");
        orgByName.add("org5");
        eventByDate.add("event1");
        eventByDate.add("event2");
        eventByDate.add("event3");
        eventByDate.add("event4");
        clientEventByDate.add("event2");
        keys.add(" Organizations");
        keys.add(" All Events");
        keys.add(" Calendar");

        //Fill in the map
        frontpageMap.add(orgByName);
        frontpageMap.add(eventByDate);
        frontpageMap.add(clientEventByDate);
    }


}
