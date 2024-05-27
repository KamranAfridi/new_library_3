package com.kamran.mylibrary;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class VolleyBase {
    public Context mContext;
    public RequestQueue mRequestQueue;
    public interface VolleyResponseListener {
        void onResponse(JSONObject response);
        void onError(String error);
    }


    public interface VolleyResponseFTPListener {
        FTP_Model onResponse(JSONObject response);
        void onError(String error);
    }

    public void is_allowed(final VolleyResponseListener listener){
        final String URL = "https://raw.githubusercontent.com/KamranAfridi/KamranAfridi.github.io/main/Data/library.json";
        JsonObjectRequest finalReq = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(String.valueOf(error));
            }
        });
        finalReq.setShouldCache(false);
        mRequestQueue.add(finalReq);
    }

//    public void is_allowed_FTP(final VolleyResponseFTPListener listener){
//        final String URL = "https://raw.githubusercontent.com/KamranAfridi/KamranAfridi.github.io/main/Data/library.json";
//        JsonObjectRequest finalReq = new JsonObjectRequest(Request.Method.GET, URL, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        listener.onResponse(response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onError(String.valueOf(error));
//            }
//        });
//        finalReq.setShouldCache(false);
//        mRequestQueue.add(finalReq);
//    }
}
