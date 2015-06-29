package com.sinoair.iemisgateway.correos.service;


import ch.ethz.ssh2.SFTPv3Client;
import com.sinoair.iemisgateway.util.*;
import com.sinoair.iemisgateway.util.sftp.SftpConnection;
import com.sinoair.iemisgateway.util.sftp.SftpDownload;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-4-10
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveManifestFeedbackService {
    public void DBinsert(File file, PreparedStatement updatePstm, Properties p) throws IOException, SQLException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //数据库更新操作
        String line = "";
        String errorFileName = file.getName();
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            i = i + 1;
            if (i == 1) {
                //如果没有ERRORES标识，以下数据不读取
                if (line.indexOf("ERROR") == -1) {
                    break;
                } else {
                    //如果有错误，第一行不是数据内容，跳过
                    continue;
                }
            }
            String[] traceArray = line.split("\t");
            if (traceArray.length < 8) {
                continue;
            }
            String shipmentCode = traceArray[2];
            String errorCode = traceArray[4];
            String errorMessage = "";
            try {
                errorMessage = p.getProperty(errorCode);
            } catch (Exception e) {

            }
            /*System.out.println("errorCode:"+errorCode);
            System.out.println("errorMessage:"+errorMessage);
            System.out.println("errorFileName:"+errorFileName);
            System.out.println("shipmentCode:"+shipmentCode);*/
            updatePstm.setString(1, errorMessage);
            updatePstm.setString(2, errorCode);
            updatePstm.setString(3, errorFileName);
            updatePstm.setString(4, shipmentCode);
            updatePstm.addBatch();
        }
        updatePstm.executeBatch();
        bufferedReader.close();
        fileReader.close();
    }

    public void analysisData(Connection conn,String path, String backUpPath) throws Exception {
        File dir = new File(path);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
            conn.setAutoCommit(false);
            PreparedStatement updatePstm = conn.prepareStatement("update express_correos_manifest ecm" +
                    " set ecm.ecm_status     = 'ERROR'," +
                    " ecm.ecm_comments   = ecm.ecm_comments ||' '|| ?," +
                    " ecm.ecm_errorcode     = ecm.ecm_errorcode ||' '|| ?," +
                    " ecm.ecm_errorfilename = ecm.ecm_errorfilename ||' '|| ?," +
                    " ecm.ecm_handletime = sysdate" +
                    " where ecm.eawb_printcode =" +
                    " (select eawb.eawb_printcode" +
                    " from expressairwaybill eawb" +
                    " where eawb.eawb_reference1 = ?) ");
            try {
                InputStream in = Object.class.getResourceAsStream("/correos.properties");
                Properties p = new Properties();
                p.load(in);
                for (File file : fileList) {
                    DBinsert(file, updatePstm, p);
                    //文件备份删除操作
                    FileUtil.copyFile(file, backUpPath);
                    FileUtil.deleteFile(file);
                }
                conn.commit();
                conn.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                conn.setAutoCommit(true);
                LogUtil.log("error " + e.getMessage());
            }
            ConnectionFactory.closeConnection(conn);
        }
    }

    public void ReceiveManifestFeedback(Connection conn,String historyRootPath) throws Exception {
        //1.从西邮服务器上下载到本地，同时删除西邮服务器上的报文反馈文件，文件格式为zip
        ch.ethz.ssh2.Connection connsft = SftpConnection.getSFTPConnection(PropertiesUtil.readProperty("correos", "correosUrl"), PropertiesUtil.readProperty("correos", "pfUsername"), PropertiesUtil.readProperty("correos", "keyFileManifest"));
         String  localPfFeedbackDir = historyRootPath+"/correos/in/manifestFeedback/";
         String  localPfFeedbackDirCopy = historyRootPath+"/correos/bak/in/manifestFeedback/";
        if (connsft != null) {
            LogUtil.log("下载报文反馈-连接西邮服务器成功！");
            SFTPv3Client sftPv3Client = new SFTPv3Client(connsft);
            SftpDownload.download(localPfFeedbackDir, PropertiesUtil.readProperty("correos", "correosPfFeedbackDir"), sftPv3Client);
            sftPv3Client.close();
            connsft.close();
            LogUtil.log("下载报文反馈-下载报文反馈成功！");
        }
        //2.解压文件zip，解压完成后，删除文件
        String zipPath = localPfFeedbackDir;
        File[] files = FileUtil.getFiles(zipPath);
        if (files != null && files.length > 0) {
            zipPath=zipPath.substring(0,zipPath.length()-1);
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.getName().endsWith(".zip") || file.getName().endsWith(".ZIP")) {
                    ZipUtil.unzipFile(zipPath, file.getName(), zipPath);
                    FileUtil.deleteFile(file);
                }
                LogUtil.log("下载报文反馈-解压报文反馈成功！");
            }
        }
        //2.在本地解析预报反馈，更新运单状态为ERROR,备份预报反馈
        analysisData(conn,localPfFeedbackDir, localPfFeedbackDirCopy);
        LogUtil.log("下载报文反馈-解析报文反馈报文、本地备份成功！");
    }

    public static void main(String[] args) throws Exception {
        Connection conn = ConnectionFactory.get200Connection();
        String historyRootPath="D:/express/SinoairEDIServerHistory";
        ReceiveManifestFeedbackService receiveManifestFeedbackService = new ReceiveManifestFeedbackService();
        receiveManifestFeedbackService.ReceiveManifestFeedback(conn,historyRootPath);
    }

}
