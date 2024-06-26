package com.kamran.mylibrary;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.json.JSONObject;

import java.io.FileInputStream;
public class FTP_Class {
    public FTPClient mFTPClient = null;



    public FTP_Model ftpConnect(String host, String username, String password,
                              int port) {


                boolean status = false;
                try {
                    mFTPClient = new FTPClient();
                    // connecting to the host
                    try {
                        mFTPClient.connect(host, port);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    // now check the reply code, if positive mean connection success
                    if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                        // login using username & password
                        status = mFTPClient.login(username, password);
                        mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                        mFTPClient.enterLocalPassiveMode();
                        status = true;
                        Log.e("FTP", "Connected");
                        return new FTP_Model(mFTPClient, status);
                    }
                } catch (Exception e) {

                }
                return new FTP_Model(mFTPClient, status);
            }





    public boolean ftpUpload(String srcFilePath, String desFileName,
                             String desDirectory, Context context) {



        boolean status = false;
        try {
            FileInputStream srcFileStream = new FileInputStream(srcFilePath);
           
            status = mFTPClient.storeFile(desFileName, srcFileStream);
           
            srcFileStream.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("FTP", "upload failed: " + e);
        }
        return status;
    }



}
