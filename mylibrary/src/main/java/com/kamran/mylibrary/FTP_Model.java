package com.kamran.mylibrary;

import org.apache.commons.net.ftp.FTPClient;

public class FTP_Model {

    FTPClient ftpClient;

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    boolean status;

    public FTP_Model(FTPClient ftpClient, boolean status) {
        this.ftpClient = ftpClient;
        this.status = status;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public boolean isStatus() {
        return status;
    }

}
