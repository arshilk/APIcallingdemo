package com.example.apple.apicallingdemo.framework;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Neeraj on 7/25/2017.
 */
public interface IServiceHelper {
    //--this method runs an AsyncTask and calls supplied Callback object's methods when done
    public void callService(String url, JSONObject jsonInputObj, IServiceSuccessCallback c);

    //--this method just processes input string locally and returns it
    public String doStuff(String input);

    public void callGetService(String url, JSONObject jsonInputObj, IServiceSuccessCallback c);

    public void callJsonArryService(String url, Map<String, String> params, IServiceSuccessCallback c);
}

