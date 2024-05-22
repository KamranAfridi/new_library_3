//package com.kamran.mylibrary;
//
//import android.app.Activity;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.provider.Settings;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.JsonObjectRequest;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.concurrent.CountDownLatch;
//
//public class DownloadProfile extends Activity {
//    String Profile = "http://175.107.63.44:81/appServices/appServices.php?API=2833b7d244d7900e28fa29caa68720b3&_f=getMonitorProfileNew";
//    ArrayList<Monitor> monitorList;
//    Monitor monitor;
//
//    public interface ResponseListener {
//        void onResponse(String response);
//        void onError(String error);
//    }
//
//    public Monitor makeJsonArrayRequest(ContentResolver cr, Context context) {
//        String ANDROID_ID = Settings.Secure.getString(cr,
//                Settings.Secure.ANDROID_ID);
//        final CountDownLatch latch = new CountDownLatch(1);
//
//        final String URL = Profile + "&imei1=" + ANDROID_ID + "&imei2=" + ANDROID_ID;
//        Log.e("Profile URL", URL);
//        monitorList = new ArrayList<Monitor>();
//        JsonObjectRequest req = new JsonObjectRequest(URL, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            int resultCode = response.getInt("resultCode");
//                            if (resultCode == 1) {
//                                JSONArray resultDesp = response
//                                        .getJSONArray("resultDesc");
//                                if (resultDesp.length() > 0) {
//                                    for (int i = 0; i < resultDesp.length(); i++) {
//                                        monitor = new Monitor();
//                                        JSONObject person = (JSONObject) resultDesp.get(i);
//                                        String status = person.getString("Status");
//                                        String imei1 = person.getString("IMEI1");
//                                        String loginName = person.getString("LoginName");
//                                        String imei2 = person.getString("IMEI2");
//                                        String email = person.getString("Email");
//                                        String recoveryCode = person
//                                                .getString("RecoveryCode");
//                                        String sex = person.getString("Sex");
//                                        String roleid = person.getString("Role_id");
//                                        String name = person.getString("Name");
//                                        String contactNo = person
//                                                .getString("ContactNo");
//                                        String education = person
//                                                .getString("Education");
//                                        String cnic = person.getString("CNIC");
//                                        String userID = person.getString("ID");
//                                        String session = person
//                                                .getString("SessionID");
//                                        String Age = person.getString("Age");
//                                        String password = person
//                                                .getString("Password");
//                                        String testUser = person.getString("Testuser");
//
//                                        monitor.setStatus(status);
//                                        monitor.setiMEI1(imei1);
//                                        monitor.setLoginName(loginName);
//                                        monitor.setiMEI2(imei2);
//                                        monitor.setEmail(email);
//                                        monitor.setRecoveryCode(recoveryCode);
//                                        monitor.setSex(sex);
//                                        monitor.setRoleId(roleid);
//                                        monitor.setName(name);
//                                        monitor.setContactNo(contactNo);
//                                        monitor.setEducation(education);
//                                        monitor.setCnic(cnic);
//                                        monitor.setId(userID);
//                                        monitor.setSession(session);
//                                        monitor.setAge(Age);
//                                        monitor.setPassword(password);
//                                        monitor.setTestUser(testUser);
//
//                                        System.out.println("Monitor: "+monitor.getName());
//                                    }
//                                } else {
//                                    Toast.makeText(context, "No monitor found please contact to your dmo ", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(context, "You are not registered with EMA, please contact your district office", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } finally {
//                            Log.e("count_down", "I am here!!!");
//                            latch.countDown();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.e("Error: ", error.getMessage());
//                latch.countDown();
//            }
//        });
//
//        req.setShouldCache(false);
//        App.getInstance().addToRequestQueue(req);
//
//        try {
//            latch.await(); // Wait for the response
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return monitor;
//    }
//
//    public Monitor getMonitor() {
//
//        if (monitor != null) {
//            return monitor;
//        }
//        return monitor;
//    }
//
//}
