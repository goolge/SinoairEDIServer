package com.sinoair.iemisgateway.correos.action;


import ch.ethz.ssh2.SFTPv3Client;
import com.sinoair.iemisgateway.correos.service.ReceiveTracesService;
import com.sinoair.iemisgateway.util.FileUtil;
import com.sinoair.iemisgateway.util.LogUtil;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import com.sinoair.iemisgateway.util.ZipUtil;
import com.sinoair.iemisgateway.util.sftp.SftpConnection;
import com.sinoair.iemisgateway.util.sftp.SftpDownload;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-4-22
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveTracesAction {
    public static void main(String[] args) throws Exception{
        ch.ethz.ssh2.Connection connsft= SftpConnection.getSFTPConnection(PropertiesUtil.readProperty("corroes", "corroesUrl"), PropertiesUtil.readProperty("corroes", "pfUsernameTrace"), PropertiesUtil.readProperty("corroes", "keyFileTrace"));
        if(connsft!=null){
             LogUtil.log("下载轨迹反馈-连接西邮服务器成功！");
            SFTPv3Client sftPv3Client=new SFTPv3Client(connsft);
            //1.从西邮服务器上下载到本地，同时删除西邮服务器上的报文反馈文件，文件格式为zip
            SftpDownload.download(PropertiesUtil.readProperty("corroes", "localTraceDir"), PropertiesUtil.readProperty("corroes", "correosTraceDir"), sftPv3Client);
            sftPv3Client.close();
            connsft.close();
             LogUtil.log("下载轨迹反馈-下载轨迹反馈成功！");
        }
       String zipPath=PropertiesUtil.readProperty("corroes", "localTraceDir");
       File[] files= FileUtil.getFiles(zipPath);
        if(files != null && files.length>0){
              for(int i=0;i<files.length;i++){
               File file=files[i];
               if(file.getName().endsWith(".zip")){
                   ZipUtil.unzipFile(zipPath, file.getName(), zipPath);
                   FileUtil.deleteFile(file);
               }
              LogUtil.log("下载轨迹反馈-解压轨迹反馈成功！");
           }
        }
       //在本地解析轨迹，插入轨迹信息，备份轨迹，删除本地轨迹
      ReceiveTracesService.analysisData(PropertiesUtil.readProperty("corroes", "localTraceDir"), PropertiesUtil.readProperty("corroes", "localTraceDirCopy"));
      LogUtil.log("下载轨迹反馈-解析轨迹反馈报文、本地备份成功！");
    }

}
