package com.sinoair.iemisgateway.correos.action;

import ch.ethz.ssh2.SFTPv3Client;
import com.sinoair.iemisgateway.correos.service.ReceiveManifestFeedbackService;
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
public class ReceiveManifestFeedbackAction {
    public static void main(String[] args) throws Exception{
      //1.从西邮服务器上下载到本地，同时删除西邮服务器上的报文反馈文件，文件格式为zip
      ch.ethz.ssh2.Connection connsft= SftpConnection.getSFTPConnection(PropertiesUtil.readProperty("corroes", "corroesUrl"),PropertiesUtil.readProperty("corroes", "pfUsername"),PropertiesUtil.readProperty("corroes", "keyFileManifest"));
        if(connsft!=null){
            LogUtil.log("下载报文反馈-连接西邮服务器成功！");
            SFTPv3Client sftPv3Client=new SFTPv3Client(connsft);
            SftpDownload.download(PropertiesUtil.readProperty("corroes","localPfFeedbackDir"),PropertiesUtil.readProperty("correos","PfFeedbackDir"),sftPv3Client);
            sftPv3Client.close();
            connsft.close();
             LogUtil.log("下载报文反馈-下载报文反馈成功！");
        }
       //2.解压文件zip，解压完成后，删除文件
       String zipPath=PropertiesUtil.readProperty("corroes","localPfFeedbackDir");
       File[] files= FileUtil.getFiles(zipPath);
        if(files != null && files.length>0){

              for(int i=0;i<files.length;i++){
               File file=files[i];
               if(file.getName().endsWith(".zip")){
                   ZipUtil.unzipFile(zipPath,file.getName(),zipPath);
                   FileUtil.deleteFile(file);
               }
              LogUtil.log("下载报文反馈-解压报文反馈成功！");
           }
        }
       //2.在本地解析预报反馈，更新运单状态为ERROR,备份预报反馈
      ReceiveManifestFeedbackService.analysisData(PropertiesUtil.readProperty("corroes", "localPfFeedbackDir"), PropertiesUtil.readProperty("corroes", "localPfFeedbackDirCopy"));
         LogUtil.log("下载报文反馈-解析报文反馈报文、本地备份成功！");
    }

}
