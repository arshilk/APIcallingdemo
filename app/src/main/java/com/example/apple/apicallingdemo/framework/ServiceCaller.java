package com.example.apple.apicallingdemo.framework;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Neeraj on 7/25/2017.
 */
public class ServiceCaller {
    Context context;

    public ServiceCaller(Context context) {
        this.context = context;
    }

    public void insertData(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url = Contants.SERVICE_BASE_URL + Contants.submit;
        new ServiceHelper().callService(url, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("SignUpService done", false);
                }
            }
        });
    }

}
