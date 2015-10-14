package com.sinoair.iemisgateway.royalMail.service;

import ch.ethz.ssh2.SFTPv3Client;
import com.sinoair.iemisgateway.royalMail.domain.RoyalMailManifest;
import com.sinoair.iemisgateway.royalMail.domain.RoyalMailTrace;
import com.sinoair.iemisgateway.util.*;
import com.sinoair.iemisgateway.util.sftp.SftpConnection;
import com.sinoair.iemisgateway.util.sftp.SftpDownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-2
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveRmTracesService {
    public void DBinsert(File file, PreparedStatement insertPstm, PreparedStatement selectPstm) throws IOException, SQLException {
        LogUtil.log("开始处理轨迹文件filename:" + file.getName());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String rmTrack_OK=PropertiesUtil.readProperty("royalMail", "rmTrack_OK");
        String rmTrack_DEF=PropertiesUtil.readProperty("royalMail", "rmTrack_DEF");
        String rmTrack_RM=PropertiesUtil.readProperty("royalMail", "rmTrack_RM");
        String rmTrack_ADPO=PropertiesUtil.readProperty("royalMail", "rmTrack_ADPO");
        //数据库插入操作
        String line = "";
        int i = 0;
        //从第二行开始读数据，第一行是表头,最后一行是表尾
        while ((line = bufferedReader.readLine()) != null) {
            i += 1;
            String sourceStr=line;
            line=line.replaceAll("\"","");
            String[] traceArray = line.split(",");
            //如果是表尾，则不再读取数据
            if (traceArray==null ||traceArray.length<11 || !"D".equals(traceArray[0])) {
                continue;
            }
            RoyalMailTrace royalMailTrace=new RoyalMailTrace(traceArray);
            String eawb_printcode = null;
            selectPstm.setString(1, royalMailTrace.getB3_Item_code());
            ResultSet rs = selectPstm.executeQuery();
            if (rs.next()) {
                eawb_printcode = rs.getString(1);
            }
            if (eawb_printcode == null || "".equals(eawb_printcode)) {
                eawb_printcode = royalMailTrace.getB3_Item_code();
                LogUtil.log("error " + i + " 行出现问题：" + eawb_printcode + "无效! fileName:" + file.getName());
                continue;
            }
            String EAD_CODE = "INTERNATIONAL";
            String EAST_CODE="OT";
            if(rmTrack_OK.contains(royalMailTrace.getB7_Eventcode()+",")){
             EAD_CODE = "DELIVERY";
             EAST_CODE="OK";
            }else if(rmTrack_DEF.contains(royalMailTrace.getB7_Eventcode()+",")){
             EAD_CODE = "INTERNATIONAL";
             EAST_CODE="DEF";
            }else if(rmTrack_RM.contains(royalMailTrace.getB7_Eventcode()+",")){
             EAD_CODE = "INTERNATIONAL";
             EAST_CODE="RM";
            }else if(rmTrack_ADPO.contains(royalMailTrace.getB7_Eventcode()+",")){
             EAD_CODE = "INTERNATIONAL";
             EAST_CODE="ADPO";
            }
            String EBA_E_ID_HANDLER = "1";
            String EBA_REMARK = "";//如果是“ES8XX”时，显示错误原因，取自，其他显显示空；
            String SAC_ID = "SNR";
            String EBA_SAC_CODE = "GACN";
            String EBA_OCCURTIME = royalMailTrace.getB9_Logdate().substring(0, 4) + "-" + royalMailTrace.getB9_Logdate().substring(4, 6) + "-" + royalMailTrace.getB9_Logdate().substring(6) + " " + royalMailTrace.getB10_Logtime().substring(0,2)+":"+royalMailTrace.getB10_Logtime().substring(2,4)+":"+royalMailTrace.getB10_Logtime().substring(4);
            /*BaseLogger.info(EBA_OCCURTIME);*/
            String EBA_OCCURPLACE = royalMailTrace.getB6_Eventlocation();

            String FLAG = "";
            String QA = "";
            insertPstm.setString(1, eawb_printcode);
            insertPstm.setString(2, EAD_CODE);
            insertPstm.setString(3, EAST_CODE);
            insertPstm.setString(4, EBA_E_ID_HANDLER);
            insertPstm.setString(5, EBA_REMARK);
            insertPstm.setString(6, SAC_ID);
            insertPstm.setString(7, EBA_SAC_CODE);
            insertPstm.setTimestamp(8, new Timestamp(DateUtil.getStringToDate(EBA_OCCURTIME, "yyyy-MM-dd hh:mm").getTime()));
            insertPstm.setString(9, RoyalMailManifest.PARTNER_CODE);
            insertPstm.setString(10, EBA_OCCURPLACE);
            insertPstm.setString(11, FLAG);
            insertPstm.setString(12, QA);
            insertPstm.setString(13, royalMailTrace.getB7_Eventcode());
            insertPstm.setString(14, sourceStr);
            insertPstm.addBatch();
        }
        insertPstm.executeBatch();
        bufferedReader.close();
        fileReader.close();

    }

    public void analysisData(Connection conn,String path, String backUpPath) throws Exception {
        File dir = new File(path);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
            conn.setAutoCommit(false);
            PreparedStatement insertPstm = conn.prepareStatement("insert into expressbusinessactivity(EBA_SYSCODE,EAWB_PRINTCODE,EAD_CODE,EAST_CODE,EBA_E_ID_HANDLER,EBA_HANDLETIME,EBA_REMARK,SAC_ID,EBA_SAC_CODE,EBA_OCCURTIME,EBA_SOURCE,EBA_OCCURPLACE,FLAG,QA,EAT_partner_ACTIVITY_CODE,EAT_PARTNER_ORIGIN,EAT_PARTNER_ID) values(" +
                    "SEQ_EXPRESSBUSINESSACTIVITY.nextval,?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?,'"+RoyalMailManifest.PARTNER_CODE+"')");
            PreparedStatement selectPstm = conn.prepareStatement("select eawb.eawb_printcode from expressairwaybill eawb where eawb.eawb_reference1=? ");
            try {

                for (File file : fileList) {
                    DBinsert(file, insertPstm, selectPstm);
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

    public void ReceiveTraces(Connection conn,String historyRootPath) throws Exception {

        String  localTraceDir =     historyRootPath+"/royalMail/in/traces/";
        String  localTraceDirCopy = historyRootPath+"/royalMail/bak/in/traces/";
        ch.ethz.ssh2.Connection connsft = SftpConnection.getSFTPConnectionWithPassword(RoyalMailManifest.RMURL, RoyalMailManifest.USERNAME, RoyalMailManifest.PASSWORD, RoyalMailManifest.PROTNUM);
        if (connsft != null) {
            LogUtil.log("下载轨迹反馈-连接英邮服务器成功！");
            SFTPv3Client sftPv3Client = new SFTPv3Client(connsft);
            //1.从英邮服务器上下载到本地，同时删除英邮服务器上的轨迹文件
            SftpDownload.download(localTraceDir, PropertiesUtil.readProperty("royalMail", "rmTrackDir"), sftPv3Client);
            sftPv3Client.close();
            connsft.close();
            LogUtil.log("下载轨迹反馈-下载英邮轨迹反馈成功！");
        }
        //在本地解析轨迹，插入轨迹信息，备份轨迹，删除本地轨迹
        analysisData(conn,localTraceDir, localTraceDirCopy);
        LogUtil.log("下载轨迹反馈-解析英邮轨迹反馈报文、本地备份成功！");
    }

    public static void main(String[] args) throws Exception {
      Connection conn = ConnectionFactory.get194Connection();
        String historyRootPath="D:/express/SinoairEDIServerHistory";
        ReceiveRmTracesService receiveTracesService = new ReceiveRmTracesService();
        receiveTracesService.ReceiveTraces(conn,historyRootPath);
       /* String  localTraceDir =     historyRootPath+"/royalMail/in/traces/";
        String  localTraceDirCopy = historyRootPath+"/royalMail/bak/in/traces/";
        receiveTracesService.analysisData(conn,localTraceDir,localTraceDirCopy);*/
    }
}
