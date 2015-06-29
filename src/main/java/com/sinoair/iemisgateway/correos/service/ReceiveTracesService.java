package com.sinoair.iemisgateway.correos.service;


import ch.ethz.ssh2.SFTPv3Client;
import com.sinoair.iemisgateway.util.*;
import com.sinoair.iemisgateway.util.sftp.SftpConnection;
import com.sinoair.iemisgateway.util.sftp.SftpDownload;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-4-10
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveTracesService {
    public void DBinsert(File file, PreparedStatement insertPstm, PreparedStatement selectPstm, Properties p) throws IOException, SQLException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //数据库插入操作
        String line = "";
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            i += 1;
            String[] traceArray = line.split("\t");
            if (traceArray.length < 7) {
                continue;
            }
            //西邮运单号
            String field3 = traceArray[2];
            //西邮轨迹状态代码字段
            String field5 = traceArray[4];
            if (null == field3 || "".equals(field3) || null == field5 || "".equals(field5)) {
                LogUtil.log("error " + i + " 行出现问题：shipment Code或者状态字段为空 fileName:" + file.getName());
                continue;
            }
            selectPstm.setString(1, field3);
            ResultSet rs = selectPstm.executeQuery();
            if (rs.next()) {
                field3 = rs.getString(1);
            } else {
                field3 = null;
            }
            if (field3 == null || "".equals(field3)) {
                field3 = traceArray[2];
                LogUtil.log("error " + i + " 行出现问题：" + field3 + "无效! fileName:" + file.getName());
                continue;
            }
            String EAD_CODE = p.getProperty(field5 + "EC");//todo-wxx-u
            //String EAST_CODE=traceArray[5];
            String EAST_CODE = p.getProperty(field5); //todo-wxx-u
            String EBA_E_ID_HANDLER = "1";
            String EBA_REMARK = "";
            //从第九个字段开始，把后面字段拼接起来，作为轨迹的备注字段
            for (int f = 8; f < traceArray.length; f++) {
                EBA_REMARK += traceArray[f] + "|";
            }
            //状态发生的日期  如：20140415
            String field7 = traceArray[6];
            //状态发生时间  如：13:29
            String field8 = traceArray[7];
            String SAC_ID = "SNR";
            String EBA_SAC_CODE = "GACN";
            String EBA_OCCURTIME = field7.substring(0, 4) + "-" + field7.substring(4, 6) + "-" + field7.substring(6) + " " + field8;
            /*System.out.println(EBA_OCCURTIME);*/
            String EBA_SOURCE = "CORREOS";
            String EBA_OCCURPLACE = "";
            try {
                EBA_OCCURPLACE = "";
                EBA_OCCURPLACE = "";
                String indexCD = p.getProperty(field5 + "CD");
                if (indexCD != null) {
                    Integer index = Integer.parseInt(indexCD);
                    EBA_OCCURPLACE = traceArray[index - 1];
                    EBA_OCCURPLACE = p.getProperty(traceArray[index - 1].substring(0, 2));
                }
            } catch (Exception e) {

            }

            String FLAG = "";
            String QA = "";
            insertPstm.setString(1, field3);
            insertPstm.setString(2, EAD_CODE);
            insertPstm.setString(3, EAST_CODE);
            insertPstm.setString(4, EBA_E_ID_HANDLER);
            insertPstm.setString(5, EBA_REMARK);
            insertPstm.setString(6, SAC_ID);
            insertPstm.setString(7, EBA_SAC_CODE);
            // insertPstm.setDate(8,new java.sql.Date(DateUtil.getStringToDate(EBA_OCCURTIME, "yyyy-MM-dd hh:mm").getTime()));
            insertPstm.setTimestamp(8, new Timestamp(DateUtil.getStringToDate(EBA_OCCURTIME, "yyyy-MM-dd hh:mm").getTime()));
            insertPstm.setString(9, EBA_SOURCE);
            insertPstm.setString(10, EBA_OCCURPLACE);
            insertPstm.setString(11, FLAG);
            insertPstm.setString(12, QA);
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
            PreparedStatement insertPstm = conn.prepareStatement("insert into expressbusinessactivity(EBA_SYSCODE,EAWB_PRINTCODE,EAD_CODE,EAST_CODE,EBA_E_ID_HANDLER,EBA_HANDLETIME,EBA_REMARK,SAC_ID,EBA_SAC_CODE,EBA_OCCURTIME,EBA_SOURCE,EBA_OCCURPLACE,FLAG,QA) values(" +
                    "SEQ_EXPRESSBUSINESSACTIVITY.nextval,?,?,?,?,sysdate,?,?,?,?,?,?,?,?)");
            PreparedStatement selectPstm = conn.prepareStatement("select eawb.eawb_printcode from expressairwaybill eawb where eawb.eawb_reference1=?");
            try {
                InputStream in = Object.class.getResourceAsStream("/correos.properties");
                Properties p = new Properties();
                p.load(in);
                for (File file : fileList) {
                    DBinsert(file, insertPstm, selectPstm, p);
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
        ch.ethz.ssh2.Connection connsft = SftpConnection.getSFTPConnection(PropertiesUtil.readProperty("correos", "correosUrl"), PropertiesUtil.readProperty("correos", "pfUsernameTrace"), PropertiesUtil.readProperty("correos", "keyFileTrace"));
        String  localTraceDir = historyRootPath+"/correos/in/traces/";
        String  localTraceDirCopy = historyRootPath+"/correos/bak/in/traces/";
        if (connsft != null) {
            LogUtil.log("下载轨迹反馈-连接西邮服务器成功！");
            SFTPv3Client sftPv3Client = new SFTPv3Client(connsft);
            //1.从西邮服务器上下载到本地，同时删除西邮服务器上的报文反馈文件，文件格式为zip
            SftpDownload.download(localTraceDir, PropertiesUtil.readProperty("correos", "correosTraceDir"), sftPv3Client);
            sftPv3Client.close();
            connsft.close();
            LogUtil.log("下载轨迹反馈-下载轨迹反馈成功！");
        }
        String zipPath = localTraceDir;
        File[] files = FileUtil.getFiles(zipPath);
        if (files != null && files.length > 0) {
            zipPath=zipPath.substring(0,zipPath.length()-1);
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.getName().endsWith(".zip") || file.getName().endsWith(".ZIP")) {
                    ZipUtil.unzipFile(zipPath, file.getName(), zipPath);
                    FileUtil.deleteFile(file);
                }
                LogUtil.log("下载轨迹反馈-解压轨迹反馈成功！");
            }
        }
        //在本地解析轨迹，插入轨迹信息，备份轨迹，删除本地轨迹
        analysisData(conn,localTraceDir, localTraceDirCopy);
        LogUtil.log("下载轨迹反馈-解析轨迹反馈报文、本地备份成功！");
    }

    public static void main(String[] args) throws Exception {
        Connection conn = ConnectionFactory.get200Connection();
        String historyRootPath="D:/express/SinoairEDIServerHistory";
        ReceiveTracesService receiveTracesService = new ReceiveTracesService();
        receiveTracesService.ReceiveTraces(conn,historyRootPath);
    }

}
