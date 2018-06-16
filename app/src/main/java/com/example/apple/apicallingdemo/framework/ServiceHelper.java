package com.example.apple.apicallingdemo.framework;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Neeraj on 7/25/2017.
 */
public class ServiceHelper implements IServiceHelper {

    @Override
    public void callService(String url, JSONObject jsonInputObj, final IServiceSuccessCallback c) {
        final String callerUrl = url;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonInputObj,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (Contants.IS_DEBUG_LOG) {
                            Log.d(Contants.LOG_TAG, "successfully called " + callerUrl);
                        }
                        c.onDone(callerUrl, response.toString(), null);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "error at ServiceHelper: callService " + callerUrl + ": " + error.getMessage());
                }
                c.onDone(callerUrl, null, error.getMessage());
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, 1.0f));
        AppController.getInstance().addToRequestQueue(jsonObjReq);//, tag_json_obj);
    }

    public void cancelPendingRequests() {
    }

    @Override
    public void callJsonArryService(String url, final Map<String, String> params, final IServiceSuccessCallback c) {
        final String callerUrl = url;
        JsonArrayRequest jsonarray = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if (Contants.IS_DEBUG_LOG) {
                            Log.d(Contants.LOG_TAG, "successfully called " + callerUrl);
                        }
                        c.onDone(callerUrl, response.toString(), null);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "error at ServiceHelper: callService " + callerUrl + ": " + error.getMessage());
                }
                c.onDone(callerUrl, null, error.getMessage());
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        // Adding request to request queue
        jsonarray.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
        AppController.getInstance().addToRequestQueue(jsonarray);//, tag_json_obj);

    }

    @Override
    public String doStuff(String input) {
        return null;
    }

    @Override
    public void callGetService(String url, JSONObject jsonInputObj, final IServiceSuccessCallback c) {
        final String callerUrl = url;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (Contants.IS_DEBUG_LOG) {
                            Log.d(Contants.LOG_TAG, "successfully called " + callerUrl);
                        }
                        c.onDone(callerUrl, response.toString(), null);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "error at ServiceHelper: callService " + callerUrl + ": " + error.getMessage());
                }
                c.onDone(callerUrl, null, error.getMessage());
            }
        }
        ) {
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
        AppController.getInstance().addToRequestQueue(jsonObjReq);//, tag_json_obj);
    }
}
