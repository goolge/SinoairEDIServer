package com.sinoair.iemisgateway.correos.service;

import com.sinoair.iemisgateway.util.ConnectionFactory;
import com.sinoair.iemisgateway.util.FileUtil;
import com.sinoair.iemisgateway.util.LogUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-15
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveTracesServiceTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDBinsert() throws Exception {
        ReceiveTracesService receiveTracesService=new ReceiveTracesService();
        String path = "C:/ftp/correos/trace";
        String backUpPath = "C:/ftp/correos/bak/trace";
       /* String name = "cmis";//todo-wxx-n 生产
        String password = "cmis";//todo-wxx-n 生产
        String url = "jdbc:oracle:thin:@172.17.0.194:1521:orcl";//todo-wxx-n 生产*/
        File dir = new File(path);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
		   Connection conn= ConnectionFactory.get194Connection();
           conn.setAutoCommit(false);
           PreparedStatement insertPstm=conn.prepareStatement("insert into expressbusinessactivity(EBA_SYSCODE,EAWB_PRINTCODE,EAD_CODE,EAST_CODE,EBA_E_ID_HANDLER,EBA_HANDLETIME,EBA_REMARK,SAC_ID,EBA_SAC_CODE,EBA_OCCURTIME,EBA_SOURCE,EBA_OCCURPLACE,FLAG,QA) values(" +
                   "SEQ_EXPRESSBUSINESSACTIVITY.nextval,?,?,?,?,sysdate,?,?,?,?,?,?,?,?)");
           PreparedStatement selectPstm=conn.prepareStatement("select eawb.eawb_printcode from expressairwaybill eawb where eawb.eawb_reference1=?");
            try {
           InputStream in = Object.class.getResourceAsStream("/correos.properties");
           Properties p = new Properties();
           p.load(in);
                for (File file : fileList) {
                    receiveTracesService.DBinsert(file, insertPstm, selectPstm,p);
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

    @Test
    public void testAnalysisData() throws Exception {

    }

}
