package com.kamran.mylibrary;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class VolleyRequest {
    String url = "http://175.107.63.44:81/appServices/appServices.php?API=2833b7d244d7900e28fa29caa68720b3&_f=getMonitorProfileNew";
    ArrayList<Monitor> monitorList;
    Monitor monitor;
    private Context mContext;
    private RequestQueue mRequestQueue;
    public interface VolleyResponseListener {
        void onResponse(JSONObject response);
        void onError(String error);
    }
    public VolleyRequest(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
    }
    public Monitor fetchProfile(ContentResolver cr, final VolleyResponseListener listener) {
        is_allowed(new VolleyResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int resultCode = response.getInt("resultCode");
                    if(resultCode==1)
                    {
                        String ANDROID_ID = Settings.Secure.getString(cr,
                                Settings.Secure.ANDROID_ID);
                        final String URL = url + "&imei1=" + ANDROID_ID + "&imei2=" + ANDROID_ID;
                        monitorList = new ArrayList<Monitor>();
                        JsonObjectRequest req = new JsonObjectRequest(URL, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        listener.onResponse(response);
                                    }
                                }, error -> listener.onError(String.valueOf(error)));
                        mRequestQueue.add(req);
                    }else {
                        Toast.makeText(mContext.getApplicationContext(), "Library Not Allowed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });

        return monitor;
    }
    private void is_allowed(final VolleyResponseListener listener){
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
}
