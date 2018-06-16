package com.example.apple.apicallingdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.apicallingdemo.framework.IAsyncWorkCompletedCallback;
import com.example.apple.apicallingdemo.framework.ServiceCaller;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.apple.apicallingdemo.MainActivity.isOnline;

public class show extends AppCompatActivity {

    EditText e1;
    TextView t1,t2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        e1 = findViewById(R.id.show);
        t1 = findViewById(R.id.show_textview);
        t2 = findViewById(R.id.show_textview2);
        b1 = findViewById(R.id.show_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = e1.getText().toString().trim();
                userValidate(s);

            }
        });
    }


    private void userValidate(final String name) {
        if (isOnline(this)) {
            JSONObject object = new JSONObject();
            try {
                Log.i("send",name);
                object.put("name",name);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            final ProgressDialog progressbar = ProgressDialog.show(show.this, "", "Please wait..", true);
            progressbar.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.showData(object, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        showModel mainContent = new Gson().fromJson(result, showModel.class);
                        if (mainContent != null) {
                            if (mainContent.getStatus().equals("SUCCESS")) {

                                t1.setText(mainContent.getSecondname());
                                t2.setText(String.valueOf(mainContent.getRollno()));

                            } else {
                                Toast.makeText(show.this, "SUCCESS Not returned", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(show.this, "User Already Registered!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(show.this, "SUCCESS/FAILURE Not returned", Toast.LENGTH_SHORT).show();
                    }
                    if (progressbar.isShowing()) {
                        progressbar.dismiss();
                    }
                }
            });
        } else {
            Toast.makeText(show.this, "request not completed", Toast.LENGTH_SHORT).show();
        }
    }

}
