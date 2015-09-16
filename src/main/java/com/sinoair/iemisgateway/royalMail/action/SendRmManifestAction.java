package com.sinoair.iemisgateway.royalMail.action;

import com.sinoair.iemisgateway.royalMail.service.SendRmManifestService;
import com.sinoair.iemisgateway.util.ConnectionFactory;
import com.sinoair.iemisgateway.util.PropertiesUtil;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-7-30
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 */
public class SendRmManifestAction {
        public static void main(String[] args) throws Exception{
        SendRmManifestService generateInfo = new SendRmManifestService();
        generateInfo.sendManifest(ConnectionFactory.getConnectionInProperties(), PropertiesUtil.readProperty("common", "historyRootPath"));
       }
}
