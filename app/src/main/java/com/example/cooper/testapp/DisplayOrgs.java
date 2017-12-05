package com.example.cooper.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class DisplayOrgs extends AppCompatActivity {

    ExpandableListView expandableListView;
    NewExpandableListViewAdapter expandableListViewAdapter;
    String orgName;
    ArrayList<String> orgsByName, eventByOrg;
    ArrayList<ArrayList<String>> orgsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgs);
        Intent intent = getIntent();
        orgName = intent.getStringExtra("org");
        fillLists();
        expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
        expandableListViewAdapter = new NewExpandableListViewAdapter(this, orgsByName, orgsMap);
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    public void fillLists() {
        orgsByName = new ArrayList<>();
        eventByOrg = new ArrayList<>();
        orgsMap = new ArrayList<>();

        //Fill in orgByName, eventByOrg, and orgsMap
        orgsByName.add(orgName);
        eventByOrg.add("event1");
        eventByOrg.add("event3");
        eventByOrg.add("event4");
        orgsMap.add(eventByOrg);
    }


}
