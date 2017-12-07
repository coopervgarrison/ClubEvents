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
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //private static final String HOST_ADDRESS = "127.0.0.1";
    //private static final int PORT = 6789;
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
        /*try {
            boolean task = true;
            String responseFromServer;
            Socket socket = new Socket(HOST_ADDRESS, PORT);

            while (task) {
                //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                //DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                responseFromServer = inFromServer.readLine();
                if (responseFromServer.equalsIgnoreCase("Waiting for a command.")
                        || responseFromServer.equalsIgnoreCase("Please enter key.")
                        || responseFromServer.equalsIgnoreCase("Please enter value.")
                        || responseFromServer.equalsIgnoreCase("Please enter key to retrieve its value.")) {
                    System.out.println(responseFromServer);
                    //userInput = inFromUser.readLine() + "\n";
                    //outToServer.writeBytes(userInput);
                }
                else {
                    System.out.println(responseFromServer);
                    task = false;
                    socket.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
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
