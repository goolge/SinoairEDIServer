package com.sinoair.iemisgateway.correos.action;


import ch.ethz.ssh2.SFTPv3Client;
import com.sinoair.iemisgateway.correos.service.SendManifestService;
import com.sinoair.iemisgateway.util.ConnectionFactory;
import com.sinoair.iemisgateway.util.FileUtil;
import com.sinoair.iemisgateway.util.LogUtil;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import com.sinoair.iemisgateway.util.sftp.SftpConnection;
import com.sinoair.iemisgateway.util.sftp.SftpUpload;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-11
 * Time: 下午1:48
 * To change this template use File | Settings | File Templates.
 */
public class SendManifestAction {
    public static void main(String[] args) throws Exception{
        //组织西邮报文数据，在本地存一份备份，然后放到西邮服务器，同时更新运单状态为发送成功

          ArrayList arrayList=null;
          Connection conn= ConnectionFactory.get194Connection();
          SendManifestService generateInfo=new SendManifestService();
         try{

          //1.获取需要发送预报的运单数据；
          arrayList=generateInfo.getInfoList(conn);
          //2.更新数据状态为已发送
          generateInfo.updateSuccess(arrayList,conn);
          conn.commit();
          conn.setAutoCommit(true);
         }catch (Exception e){
             conn.rollback();
			 e.printStackTrace();
         }
          ConnectionFactory.closeConnection(conn);
          //3.在本地生成西邮报文
          int sendNum=arrayList==null?0:arrayList.size();
        LogUtil.log(" 发送报文-获取发预报的数据条数：" + sendNum);
          generateInfo.generateInfoCorreos(arrayList,PropertiesUtil.readProperty("corroes", "localpfileDir"));

          //4.向西邮发送预报,同时备份本地文件
        File[] files= FileUtil.getFiles(PropertiesUtil.readProperty("corroes", "localpfileDir"));
        if(files!=null && files.length>0){
          ch.ethz.ssh2.Connection connsft= SftpConnection.getSFTPConnection(PropertiesUtil.readProperty("corroes", "corroesUrl"), PropertiesUtil.readProperty("corroes", "pfUsername"), PropertiesUtil.readProperty("corroes", "keyFileManifest"));
        if(connsft!=null){
            LogUtil.log(" 发送报文-连接西邮服务器成功");
            SFTPv3Client sftPv3Client=new SFTPv3Client(connsft);
            SftpUpload.upload(PropertiesUtil.readProperty("corroes", "localpfileDir"), PropertiesUtil.readProperty("corroes", "correospfDir"), sftPv3Client, PropertiesUtil.readProperty("corroes", "localpfileDirCopy"));
            sftPv3Client.close();
            connsft.close();
             LogUtil.log(" 发送报文-上传西邮服务器报文成功！");
         }

        }
       }
    }
