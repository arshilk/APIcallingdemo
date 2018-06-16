package com.example.apple.apicallingdemo.framework;

/**
 * Created by Neeraj on 7/25/2017.
 */
public interface IAsyncWorkCompletedCallback {
    public void onDone(String workName, boolean isComplete);
}
