package com.example.cooper.testapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    protected static final int GROUPCAST_PORT = 20000;
    protected static final String GROUPCAST_SERVER = "10.0.2.2";
    Socket mSocket = null;
    BufferedReader mIn = null;
    PrintWriter mOut = null;
    boolean mConnected = false;

    Button logIn = null;
    EditText username = null;
    EditText password = null;
    Map<String, String> logInCreds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = (Button) findViewById(R.id.logIn);
        username = (EditText) findViewById(R.id.editUN);
        password = (EditText) findViewById(R.id.editPW);
        //Fill in logInCreds
        logInCreds = new HashMap<>();
        logInCreds.put("default", "default");
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logInCreds.get(username.getText().toString()).equals(password.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, FrontPageActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid username/password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
