package com.sinoair.iemisgateway.util.sftp;

import ch.ethz.ssh2.Connection;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-20
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class SftpConnection {
    public static Connection getSFTPConnection(String hostname,String username,String fileName){
        System.out.println("hostname = " + hostname);
        System.out.println("username = " + username);
        System.out.println("fileName = " + fileName);
        Connection conn=null;
        try{
          if(fileName==null || "".equals(fileName)){
            return null;
          }

//            System.out.println("---------------------------keyFile.getAbsolutePath() = " + keyFile.getAbsolutePath());
            URL url = SftpConnection.class.getResource("/"+fileName);
            String path = url.getPath();
            System.out.println("path = " + path);
            File keyFile = new File(path);
            System.out.println("keyFile.getAbsolutePath() = " + keyFile.getAbsolutePath());
           conn = new Connection(hostname);
          conn.connect();
          boolean isAuthenticated = conn.authenticateWithPublicKey(username, keyFile, "");
           if (isAuthenticated == false){throw new IOException("Authentication failed.");}
        }catch(Exception e){
            e.printStackTrace();
        }
       return conn;
    }


}
