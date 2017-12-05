package com.example.cooper.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class DisplayOrgs extends AppCompatActivity {

    ExpandableListView expandableListView;
    NewExpandableListViewAdapter expandableListViewAdapter;
    List<String> orgByName, eventByOrg;
    List<List<String>> orgsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgs);
        fillLists();
        expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
        expandableListViewAdapter = new NewExpandableListViewAdapter(this, orgByName, orgsMap);
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    public void fillLists() {
        orgByName = new ArrayList<>();
        eventByOrg = new ArrayList<>();
        orgsMap = new ArrayList<>();

        //Fill in orgByName, eventByOrg, and orgsMap
        orgByName.add("org1");
        eventByOrg.add("event1");
        eventByOrg.add("event2");
        eventByOrg.add("event3");
        eventByOrg.add("event4");
        orgsMap.add(new ArrayList<String>(eventByOrg));
        eventByOrg.clear();
        orgByName.add("org2");
        eventByOrg.add("event1");
        eventByOrg.add("event2");
        orgsMap.add(new ArrayList<String>(eventByOrg));
        eventByOrg.clear();
        orgByName.add("org3");
        eventByOrg.add("event1");
        orgsMap.add(new ArrayList<String>(eventByOrg));
        eventByOrg.clear();
        orgByName.add("org4");
        eventByOrg.add("event1");
        eventByOrg.add("event2");
        eventByOrg.add("event3");
        orgsMap.add(new ArrayList<String>(eventByOrg));
        eventByOrg.clear();
        orgByName.add("org5");
        eventByOrg.add("event1");
        eventByOrg.add("event2");
        orgsMap.add(new ArrayList<String>(eventByOrg));
        eventByOrg.clear();
        orgByName.add("org6");
        orgsMap.add(new ArrayList<String>(eventByOrg));
        eventByOrg.clear();
    }


}
