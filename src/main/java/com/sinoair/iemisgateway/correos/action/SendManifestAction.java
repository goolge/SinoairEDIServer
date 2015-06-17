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
        SendManifestService generateInfo = new SendManifestService();
        generateInfo.sendManifest(ConnectionFactory.getConnectionInProperties(),PropertiesUtil.readProperty("common", "historyRootPath"));
       }
    }
