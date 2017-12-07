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

    // TAG for logging
    private static final String TAG = "Main";

    // server to connect to
    protected static final int GROUPCAST_PORT = 20000;
    protected static final String GROUPCAST_SERVER = "10.0.2.2";

    // networking
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
        logInCreds = new HashMap<>();
        //Should read in all log-in credentials
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
        connect();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy called");
        disconnect();
        super.onDestroy();
    }

    void connect() {
        new AsyncTask<Void, Void, String>() {
            String errorMsg = null;

            @Override
            protected String doInBackground(Void... args) {
                Log.i(TAG, "Connect task started");
                try {
                    mConnected = false;
                    mSocket = new Socket(GROUPCAST_SERVER, GROUPCAST_PORT);
                    Log.i(TAG, "Socket created");
                    mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                    mOut = new PrintWriter(mSocket.getOutputStream());

                    mConnected = true;
                    Log.i(TAG, "Input and output streams ready");

                } catch (UnknownHostException e1) {
                    errorMsg = e1.getMessage();
                } catch (IOException e1) {
                    errorMsg = e1.getMessage();
                    try {
                        if (mOut != null) {
                            mOut.close();
                        }
                        if (mSocket != null) {
                            mSocket.close();
                        }
                    } catch (IOException ignored) {
                    }
                }
                Log.i(TAG, "Connect task finished");
                return errorMsg;
            }

            @Override
            protected void onPostExecute(String errorMsg) {
                if (errorMsg == null) {
                    Toast.makeText(getApplicationContext(), "Connected to server", Toast.LENGTH_SHORT).show();

                    // start receiving
                    receive();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error: " + errorMsg, Toast.LENGTH_SHORT).show();
                    // can't connect: close the activity
                    finish();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    void receive() {
        new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... args) {
                Log.i(TAG, "Receive task started");
                try {
                    while (mConnected) {
                        String msg = mIn.readLine();
                        if (msg == null) { // other side closed the connection
                            break;
                        }
                        publishProgress(msg);
                    }
                } catch (UnknownHostException e1) {
                    Log.i(TAG, "UnknownHostException mIn receive task");
                } catch (IOException e1) {
                    Log.i(TAG, "IOException mIn receive task");
                } finally {
                    mConnected = false;
                    try {
                        if (mOut != null) {
                            mOut.close();
                        }
                        if (mSocket != null) {
                            mSocket.close();
                        }
                    } catch (IOException e) {
                    }
                }
                Log.i(TAG, "Receive task finished");
                return null;
            }

            @Override
            protected void onProgressUpdate(String... lines) {
                // the message received from the server is
                // guaranteed to be not null
                String msg = lines[0];

                if (msg.startsWith("+OK,NAME")) {
                    return;
                }

                if (msg.startsWith("+ERROR,NAME")) {
                    Toast.makeText(getApplicationContext(), msg.substring("+ERROR,NAME,".length()), Toast.LENGTH_SHORT).show();
                    return;
                }

                // if we haven't returned yet, tell the user that we have an unhandled message
                Toast.makeText(getApplicationContext(), "Unhandled message: " + msg, Toast.LENGTH_SHORT).show();
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    void disconnect() {
        new Thread() {
            @Override
            public void run() {
                if (mConnected) {
                    mConnected = false;
                }
                // make sure that we close the output, not the input
                if (mOut != null) {
                    mOut.print("BYE");
                    mOut.flush();
                    mOut.close();
                }
                // mIn some rare cases, mOut can be null, so we need to close the mSocket itself
                if (mSocket != null) {
                    try {
                        mSocket.close();
                    } catch(IOException ignored) {}
                }


                Log.i(TAG, "Disconnect task finished");
            }
        }.start();
    }

    boolean send(String msg) {
        if (!mConnected) {
            Log.i(TAG, "can't send: not mConnected");
            return false;
        }

        new AsyncTask<String, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(String... msg) {
                Log.i(TAG, "sending: " + msg[0]);
                mOut.println(msg[0]);
                return mOut.checkError();
            }

            @Override
            protected void onPostExecute(Boolean error) {
                if (!error) {
                    Toast.makeText(getApplicationContext(), "Message sent to server", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error sending message to server", Toast.LENGTH_SHORT).show();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, msg);

        return true;
    }

}
