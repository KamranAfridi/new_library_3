package com.kamran.mylibrary;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyRequest extends VolleyBase{
    String url = "http://175.107.63.44:81/appServices/appServices.php?API=2833b7d244d7900e28fa29caa68720b3&_f=getMonitorProfileNew";
    ArrayList<Monitor> monitorList;
    Monitor monitor;

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
                        String ANDROID_ID = get_android_id(cr);
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

    public static String get_android_id(ContentResolver cr) {
        return Settings.Secure.getString(cr,
                Settings.Secure.ANDROID_ID);
    }

}
