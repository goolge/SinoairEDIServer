package com.sinoair.iemisgateway.service;

import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.DateUtil;
import com.sinoair.iemisgateway.util.XMLUtil;
import com.sinoair.iemisgateway.util.iemisWSClient.updateOrder.UpdateOrderClient;
import org.dom4j.Document;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-7
 * Time: 下午1:57
 * 上传运单
 */

public class UpdateOrder {
    public String updateOrder(String xmlStr) {
        BaseLogger.info("----wxx---xmlStr = --" + xmlStr + "----");
        String xmlPath = "sinoairEDIServerXML" + File.separator + "SECOND" + File.separator + "update" + DateUtil.getDateWith() + ".xml";
        String xsdPath = (Thread.currentThread().getContextClassLoader().getResource("/") + "com\\sinoair\\iemisgateway").replace("file:/", "") + File.separator + "XMLAndXSD" + File.separator + "update" + File.separator + "Webservice-call2xsd.xml";
        BaseLogger.info("----wxx---xsdPath = --" + xsdPath + "----");
        XMLUtil.writeFile(xmlStr, xmlPath);
        Document document = XMLUtil.xmlVerification(xmlStr);
        if (document == null) return XMLUtil.combinateReturnMessage("invalid xml");
        if (!XMLUtil.xmlValiadation(document)) return XMLUtil.combinateReturnMessage("wrong name or password");
//        if (!XMLUtil.verificateXMLByXSD(xsdPath, xmlPath)) return XMLUtil.combinateReturnMessage("not pass xsd verification");//todo 为了能够接到更多的数据，就把这个校验放开了，数据过来一部分总比全部不过来要好，否则得客服来k单。
        String result;
        try {
            result = new UpdateOrderClient().updateOrder(xmlStr);
        } catch (Exception e) {
            e.printStackTrace();
            result = XMLUtil.combinateReturnMessage(e.getMessage());
        }
        return result;
    }
}
