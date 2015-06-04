package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.DateUtil;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import com.sinoair.iemisgateway.util.XMLUtil;
import com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoClient;
import org.dom4j.Document;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-7
 * Time: 下午1:57
 * 上传运单
 */
public class UploadOrderCainiaoService {

    public String uploadOrderCainiao(String xmlStr) {
        BaseLogger.info("----菜鸟请求报文 = --" + xmlStr + "----");
        String xmlPath = PropertiesUtil.readProperty("common","historyRootPath") + File.separator + "Cainiao" + File.separator + "uploadOrderCainiao" + DateUtil.getDateWith() + ".xml";
//        String xsdPath = (Thread.currentThread().getContextClassLoader().getResource("/") + "com\\sinoair\\iemisgateway").replace("file:/", "") + File.separator + "XMLAndXSD" + File.separator + "upload" + File.separator + "Webservice-call1xsd.xml";
        XMLUtil.writeFile(xmlStr, xmlPath);
        Document document = XMLUtil.xmlVerification(xmlStr);
        if (document == null) {
            BaseLogger.info("非法的XML格式");
            return XMLUtil.combinateReturnMessage4Cainiao(false,"S01");
        }
        if (!XMLUtil.xmlValiadationCainiao(document)) {
            BaseLogger.info("非法的数字签名");
            return XMLUtil.combinateReturnMessage4Cainiao(false, "S02");
        }
        //if (!XMLUtil.verificateXMLByXSD(xsdPath, xmlPath)) return XMLUtil.combinateReturnMessage("not pass xsd verification");//
        String result;
        try {
            result = new UploadOrderCainiaoClient().uploadOrderCainiao(xmlStr);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLogger.info("e = " + e);
            BaseLogger.info("系统异常,应该是191有问题");
            result = XMLUtil.combinateReturnMessage4Cainiao(false,"S07");
        }
        return result;
    }
}
