package com.sinoair.iemisgateway.royalMail.action;

import com.sinoair.iemisgateway.royalMail.service.ReceiveRmTracesService;
import com.sinoair.iemisgateway.util.ConnectionFactory;
import com.sinoair.iemisgateway.util.PropertiesUtil;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-15
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveRmTracesAction {
    public static void main(String[] args) throws Exception{
          ReceiveRmTracesService receiveRmTracesService=new ReceiveRmTracesService();
          receiveRmTracesService.ReceiveTraces(ConnectionFactory.getConnectionInProperties(), PropertiesUtil.readProperty("common", "historyRootPath"));
    }

}
