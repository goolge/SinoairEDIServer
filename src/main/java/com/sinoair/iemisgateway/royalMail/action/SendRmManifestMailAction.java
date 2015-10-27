package com.sinoair.iemisgateway.royalMail.action;

import com.sinoair.iemisgateway.royalMail.service.SendRmManifestMailService;
import com.sinoair.iemisgateway.util.ConnectionFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-7-30
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 */
public class SendRmManifestMailAction {
        public static void main(String[] args) throws Exception{
        SendRmManifestMailService sendRmManifestMailService = new SendRmManifestMailService();
        sendRmManifestMailService.sendEmail(ConnectionFactory.getConnectionInProperties());
       }
}
