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
         ReceiveTracesService receiveTracesService=new ReceiveTracesService();
          receiveTracesService.ReceiveTraces();
    }

}
