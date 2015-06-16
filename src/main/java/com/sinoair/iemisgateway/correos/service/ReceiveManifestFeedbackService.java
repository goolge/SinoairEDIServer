package com.sinoair.iemisgateway.correos.service;


import com.sinoair.iemisgateway.util.ConnectionFactory;
import com.sinoair.iemisgateway.util.FileUtil;
import com.sinoair.iemisgateway.util.LogUtil;

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
  public static void DBinsert(File file,PreparedStatement updatePstm,Properties p) throws IOException, SQLException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //数据库更新操作
        String line = "";
        String errorFileName=file.getName();
        int i=0;
        while ((line = bufferedReader.readLine()) != null) {
            i=i+1;
            if(i==1){
                //如果没有ERRORES标识，以下数据不读取
                if(line.indexOf("ERRORES")==-1){
                    break;
                }else{
                //如果有错误，第一行不是数据内容，跳过
                    continue;
                }
            }
            String[] traceArray = line.split("\t");
            if(traceArray.length < 8){
                continue;
            }
             String  shipmentCode= traceArray[2];
             String errorCode=traceArray[4];
             String errorMessage="";
              try{
                   errorMessage=p.getProperty(errorCode);
            }catch (Exception e){

            }
            /*System.out.println("errorCode:"+errorCode);
            System.out.println("errorMessage:"+errorMessage);
            System.out.println("errorFileName:"+errorFileName);
            System.out.println("shipmentCode:"+shipmentCode);*/
            updatePstm.setString(1,errorMessage);
            updatePstm.setString(2,errorCode);
            updatePstm.setString(3,errorFileName);
            updatePstm.setString(4,shipmentCode);
            updatePstm.addBatch();
        }
        updatePstm.executeBatch();
        bufferedReader.close();
        fileReader.close();
    }
    public static void analysisData(String path,String backUpPath) throws Exception{
          File dir = new File(path);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
		   Connection conn= ConnectionFactory.get194Connection();
           conn.setAutoCommit(false);
           PreparedStatement updatePstm=conn.prepareStatement("update correoseawb ecm" +
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
           InputStream in = Object.class.getResourceAsStream("/corroes.properties");
           Properties p = new Properties();
           p.load(in);
                for (File file : fileList) {
                    DBinsert(file, updatePstm,p);
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

}
