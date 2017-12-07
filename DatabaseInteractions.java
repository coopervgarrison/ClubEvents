package com.example.cooper.testapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInteractions extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgs);
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.orgTitle);
        newUser();
        newEvent();
        newMember();
        eventRSVP();
    }

    public void newUser(){
        /*INSERT INTO Accounts(username, pword)
          VALUES (MainActivity(username), MainActivity(password));*/
    }

    public void newEvent(){
        /*INSERT INTO Events(eventName, hostOrg, eventDescription, startDateTime, endDateTime)
          VALUES (MakeEvent(eventName), MakeEvent(orgName), MakeEvent(eventDescription), -
            MakeEvent(startTime), MakeEvent(endTime));*/
    }

    public void newMember(){
        /*INSERT INTO OrgMemberList (orgID, username)
          VALUES (DisplayOrgs(orgID), DisplayOrgs(username);*/
    }

    public void eventRSVP(){
        /*INSERT INTO EventsRSVPd(eventID, username)
          VALUES (DisplayEvent(eventID), DisplayEvent(username);*/
    }
}
