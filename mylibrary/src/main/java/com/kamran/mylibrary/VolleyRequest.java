package com.kamran.mylibrary;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

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


        String ANDROID_ID = Settings.Secure.getString(cr,
                Settings.Secure.ANDROID_ID);
        final CountDownLatch latch = new CountDownLatch(1);

        final String URL = url + "&imei1=" + ANDROID_ID + "&imei2=" + ANDROID_ID;
        Log.e("Profile URL", URL);
        monitorList = new ArrayList<Monitor>();
        JsonObjectRequest req = new JsonObjectRequest(URL, null,
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

        mRequestQueue.add(req);



        return monitor;
    }
}
