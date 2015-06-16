package com.sinoair.iemisgateway.correos.service;


import com.sinoair.iemisgateway.util.ConnectionFactory;
import com.sinoair.iemisgateway.util.DateUtil;
import com.sinoair.iemisgateway.util.FileUtil;
import com.sinoair.iemisgateway.util.LogUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-4-10
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveTracesService {
  public static void DBinsert(File file,PreparedStatement insertPstm,PreparedStatement selectPstm,Properties p) throws IOException, SQLException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //数据库插入操作
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] traceArray = line.split("\t");
            if(traceArray.length<7){
                continue;
            }
            //西邮运单号
            String field3=traceArray[2];
            //西邮轨迹状态代码字段
            String field5=traceArray[4];
            if(null==field3 || "".equals(field3) || null==field5 || "".equals(field5)){
                LogUtil.log("error " + line + "shipment Code或者状态字段为空 fileName:" + file.getName());
                continue;
            }
             selectPstm.setString(1,field3);
             ResultSet rs=selectPstm.executeQuery();
             if(rs.next()){
                field3=rs.getString(1);
            }else{
              field3=null;
             }
            if(field3==null || "".equals(field3)){
                LogUtil.log("error "+line+" "+field3+"无效! fileName:"+file.getName());
                continue;
            }
            String EAD_CODE = p.getProperty(field5+"EC");//todo-wxx-u
            //String EAST_CODE=traceArray[5];
            String EAST_CODE = p.getProperty(field5); //todo-wxx-u
            String EBA_E_ID_HANDLER = "1";
            String EBA_REMARK = "";
            //从第九个字段开始，把后面字段拼接起来，作为轨迹的备注字段
            for(int f=8;f<traceArray.length;f++){
              EBA_REMARK+=traceArray[f]+"|";
            }
            //状态发生的日期  如：20140415
            String field7=traceArray[6];
            //状态发生时间  如：13:29
            String field8=traceArray[7];
            String SAC_ID = "SNR";
            String EBA_SAC_CODE = "GACN";
            String EBA_OCCURTIME = field7.substring(0,4)+"-"+field7.substring(4,6)+"-"+field7.substring(6)+ " " + field8;
            //System.out.println(EBA_OCCURTIME);
            String EBA_SOURCE = "CORREOS";
            String EBA_OCCURPLACE = "";
            try{
                   EBA_OCCURPLACE="";
                   EBA_OCCURPLACE="";
                String indexCD=p.getProperty(field5+"CD");
                if(indexCD!=null){
                   Integer index=Integer.parseInt(indexCD);
                    EBA_OCCURPLACE=traceArray[index-1];
                    EBA_OCCURPLACE=p.getProperty(traceArray[index-1].substring(0,2));
                }
            }catch (Exception e){

            }

            String FLAG = "";
            String QA = "";
            insertPstm.setString(1,field3);
            insertPstm.setString(2,EAD_CODE);
            insertPstm.setString(3,EAST_CODE);
            insertPstm.setString(4,EBA_E_ID_HANDLER);
            insertPstm.setString(5,EBA_REMARK);
            insertPstm.setString(6,SAC_ID);
            insertPstm.setString(7,EBA_SAC_CODE);
            insertPstm.setDate(8,new java.sql.Date(DateUtil.getStringToDate(EBA_OCCURTIME, "yyyy-MM-dd hh:mm").getTime()));
            insertPstm.setString(9,EBA_SOURCE);
            insertPstm.setString(10,EBA_OCCURPLACE);
            insertPstm.setString(11,FLAG);
            insertPstm.setString(12,QA);
            insertPstm.addBatch();
        }
        insertPstm.executeBatch();
        bufferedReader.close();
        fileReader.close();

    }
   public static void analysisData(String path,String backUpPath) throws Exception{
          File dir = new File(path);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
		   Connection conn= ConnectionFactory.get194Connection();
           conn.setAutoCommit(false);
           PreparedStatement insertPstm=conn.prepareStatement("insert into expressbusinessactivity(EBA_SYSCODE,EAWB_PRINTCODE,EAD_CODE,EAST_CODE,EBA_E_ID_HANDLER,EBA_HANDLETIME,EBA_REMARK,SAC_ID,EBA_SAC_CODE,EBA_OCCURTIME,EBA_SOURCE,EBA_OCCURPLACE,FLAG,QA) values(" +
                   "SEQ_EXPRESSBUSINESSACTIVITY.nextval,?,?,?,?,sysdate,?,?,?,?,?,?,?,?)");
           PreparedStatement selectPstm=conn.prepareStatement("select eawb.eawb_printcode from expressairwaybill eawb where eawb.eawb_reference1=?");
            try {
           InputStream in = Object.class.getResourceAsStream("/corroes.properties");
           Properties p = new Properties();
           p.load(in);
                for (File file : fileList) {
                    DBinsert(file, insertPstm, selectPstm,p);
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
                LogUtil.log("error "+e.getMessage());
            }
             ConnectionFactory.closeConnection(conn);
        }
    }

}
