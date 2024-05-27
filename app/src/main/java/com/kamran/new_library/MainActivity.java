package com.kamran.new_library;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.kamran.mylibrary.FTP_Class;
import com.kamran.mylibrary.FTP_Model;
import com.kamran.mylibrary.Monitor;
import com.kamran.mylibrary.VolleyRequest;
import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {
    private Monitor monitor;
    private VolleyRequest mVolleyRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy =
//                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
        TextView tv = findViewById(R.id.tv);
        mVolleyRequest = new VolleyRequest(this);
        mVolleyRequest.fetchProfile(getContentResolver(), new VolleyRequest.VolleyResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int resultCode = response.getInt("resultCode");
                    if (resultCode == 1) {
                        JSONArray resultDesp = response
                                .getJSONArray("resultDesc");
                        if (resultDesp.length() > 0) {
                            for (int i = 0; i < resultDesp.length(); i++) {
                                monitor = new Monitor();
                                JSONObject person = (JSONObject) resultDesp.get(i);
                                String status = person.getString("Status");
                                String imei1 = person.getString("IMEI1");
                                String loginName = person.getString("LoginName");
                                String imei2 = person.getString("IMEI2");
                                String email = person.getString("Email");
                                String recoveryCode = person
                                        .getString("RecoveryCode");
                                String sex = person.getString("Sex");
                                String roleid = person.getString("Role_id");
                                String name = person.getString("Name");
                                String contactNo = person
                                        .getString("ContactNo");
                                String education = person
                                        .getString("Education");
                                String cnic = person.getString("CNIC");
                                String userID = person.getString("ID");
                                String session = person
                                        .getString("SessionID");
                                String Age = person.getString("Age");
                                String password = person
                                        .getString("Password");
                                String testUser = person.getString("Testuser");
                                monitor.setStatus(status);
                                monitor.setiMEI1(imei1);
                                monitor.setLoginName(loginName);
                                monitor.setiMEI2(imei2);
                                monitor.setEmail(email);
                                monitor.setRecoveryCode(recoveryCode);
                                monitor.setSex(sex);
                                monitor.setRoleId(roleid);
                                monitor.setName(name);
                                monitor.setContactNo(contactNo);
                                monitor.setEducation(education);
                                monitor.setCnic(cnic);
                                monitor.setId(userID);
                                monitor.setSession(session);
                                monitor.setAge(Age);
                                monitor.setPassword(password);
                                monitor.setTestUser(testUser);
                                tv.setText(monitor.getName());
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "No monitor found please contact to your dmo ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "You are not registered with EMA, please contact your district office", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(String error) {
                // Handle error here
                tv.setText(error);
            }
        });
        new connect_FTP().do_connect();
    }

     class connect_FTP  {

        void do_connect()
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FTP_Model ftp_model = new FTP_Class().ftpConnect("175.107.63.45", "SurveyFTP", "FTP_fkny@EMA_2023", 21);
                    Log.e("FTP", String.valueOf(ftp_model.isStatus()));
                    FTPClient ftp_client = ftp_model.getFtpClient();
                    if (ftp_client == null) {
                        Log.e("FTP", "FTP Client is null");
                    }
                }
            }).start();
        }
     }

}