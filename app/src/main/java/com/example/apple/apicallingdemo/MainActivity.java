package com.example.apple.apicallingdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.apicallingdemo.framework.Contants;
import com.example.apple.apicallingdemo.framework.IAsyncWorkCompletedCallback;
import com.example.apple.apicallingdemo.framework.ServiceCaller;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText name,second_name,roll_no;
    Button submit,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        second_name = findViewById(R.id.second_name);
        roll_no  = findViewById(R.id.rollno);
        submit = findViewById(R.id.submit);
        show = findViewById(R.id.show);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = name.getText().toString().trim();
                String Second_Name = second_name.getText().toString().trim();
                String rollno = roll_no.getText().toString().trim();
                userValidate(Name,Second_Name,rollno);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,show.class));
            }
        });

    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager;
        boolean connected = false;
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;


        } catch (Exception e) {
            //System.out.println("CheckConnectivity Exception: " + e.getMessage());
            //Log.v("connectivity", e.toString());
        }
        return connected;
    }



        private void userValidate(final String name,final String Second_Name,final String rollno) {
            if (isOnline(this)) {
                JSONObject object = new JSONObject();
                try {
                    object.put("name", name);
                    object.put("secondname", Second_Name);
                    object.put("rollno", rollno);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                final ProgressDialog progressbar = ProgressDialog.show(MainActivity.this, "", "Please wait..", true);
                progressbar.show();
                ServiceCaller serviceCaller = new ServiceCaller(this);
                serviceCaller.insertData(object, new IAsyncWorkCompletedCallback() {
                    @Override
                    public void onDone(String result, boolean isComplete) {
                        if (isComplete) {
                            Model mainContent = new Gson().fromJson(result, Model.class);
                            if (mainContent != null) {
                                if (mainContent.getStatus().equals("SUCCESS")) {

                                } else {
                                    Toast.makeText(MainActivity.this, "SUCCESS Not returned", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "User Already Registered!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "SUCCESS/FAILURE Not returned", Toast.LENGTH_SHORT).show();
                        }
                        if (progressbar.isShowing()) {
                            progressbar.dismiss();
                        }
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "request not completed", Toast.LENGTH_SHORT).show();
            }
        }
    }


