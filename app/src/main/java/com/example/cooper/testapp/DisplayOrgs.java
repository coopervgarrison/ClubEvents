package com.example.cooper.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayOrgs extends AppCompatActivity {

    ExpandableListView expandableListView;
    NewExpandableListViewAdapter expandableListViewAdapter;
    String orgName;
    ArrayList<String> eventGroup, eventByOrg;
    ArrayList<ArrayList<String>> eventsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgs);
        Intent intent = getIntent();
        orgName = intent.getStringExtra("org");
        TextView title = (TextView) findViewById(R.id.orgTitle);
        title.setText(orgName);
        fillLists();
        expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
        expandableListViewAdapter = new NewExpandableListViewAdapter(this, eventGroup, eventsMap);
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    public void fillLists() {
        eventGroup = new ArrayList<>();
        eventByOrg = new ArrayList<>();
        eventsMap = new ArrayList<>();

        //Fill in orgByName, eventByOrg, and orgsMap
        eventGroup.add(" Events");
        eventByOrg.add("event1");
        eventByOrg.add("event3");
        eventByOrg.add("event4");
        eventsMap.add(eventByOrg);
    }


}
