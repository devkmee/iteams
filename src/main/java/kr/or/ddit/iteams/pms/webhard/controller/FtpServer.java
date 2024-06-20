package kr.or.ddit.iteams.pms.webhard.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FtpServer {
    public static void main(String[] args) throws IOException
    {String ftpsServer = "192.168.0.123";
    int ftpsPort = 21;
    String ftpsUser = "user01";
    String ftpsPass = "java";

    try{
        FTPSClient ftpClient = new FTPSClient();
        ftpClient.connect(ftpsServer,ftpsPort);
        // check FTP connection
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)){
            ftpClient.disconnect();
            System.err.println("FTP server refused connection.");
            System.exit(1);
            } else {
                System.out.println("Connected to " + ftpsServer + ":" + ftpsPort + ".");
            }
        // check FTP login
        if (ftpClient.login(ftpsUser, ftpsPass)){
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("Logged into FTP server successfully");
        } else {
            System.out.println("Failed log into FTP server");
            ftpClient.logout();
            ftpClient.disconnect();
            return;
        }

        File localFile = new File("C:/test/history.txt");
        InputStream inputStream = new FileInputStream(localFile);
        boolean uploaded = ftpClient.storeFile("/wwwroot/preview/history.txt", inputStream);
        if(!uploaded) {
            System.err.println("Can't upload File! Reply from Server: " + ftpClient.getReplyString());
        } else {
            System.out.println("Upload successful");
        }
        inputStream.close();
        ftpClient.logout();
        ftpClient.disconnect();

    }catch(Exception e){
        log.error("Exception when merging Reminderlist is: ", e);

    }
    }
}