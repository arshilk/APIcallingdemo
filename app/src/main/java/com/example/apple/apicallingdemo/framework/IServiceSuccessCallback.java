package com.example.apple.apicallingdemo.framework;

/**
 * Created by Neeraj on 7/25/2017.
 */
public interface IServiceSuccessCallback {
    public void onDone(String callerUrl, String result, String error);
}