package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.DateUtil;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import com.sinoair.iemisgateway.util.XMLUtil;
import com.sinoair.iemisgateway.util.iemisWSClient.PreUploadOrderCainiaoClient;
import org.dom4j.Document;

import java.io.File;

/**
 * Created by WangXX4 on 2015/7/8.
 */
public class PreUploadOrderCainiaoService {
    public String preUploadOrderCainiao(String xmlStr) {
        BaseLogger.info("----cainiao report message = --" + xmlStr + "----");
        String xmlPath = PropertiesUtil.readProperty("common", "historyRootPath") + File.separator
                + "Cainiao" + File.separator
                + "preUploadOrder" + File.separator
                + DateUtil.getSysdateYMD() + File.separator
                + "preUploadOrderCainiao" + DateUtil.getDateWith() + ".xml"; //todo
        BaseLogger.debug("--------------" + xmlPath + "---------------------------");
        String xsdPath = UploadOrderCainiaoService.class.getResource("/XMLAndXSD/cainiao/preUploadOrderCainiao.xsd").getPath();//todo
        BaseLogger.info("--------------" + File.separator + "---------------------------");
        if ("\\".equals(File.separator)) {
            xsdPath = xsdPath.substring(1);
        }
        BaseLogger.info("---xsdPath---" + xsdPath);
        XMLUtil.writeFile(xmlStr, xmlPath);
        Document document = XMLUtil.xmlVerification(xmlStr);
        if (document == null) {
            BaseLogger.info("非法的XML格式");
            return XMLUtil.combinateReturnMessage4Cainiao(false, "S01");
        }
        if (!XMLUtil.xmlValiadationCainiao(document)) {
            BaseLogger.info("非法的数字签名");
            return XMLUtil.combinateReturnMessage4Cainiao(false, "S02");
        }
//        todo
        if (!XMLUtil.verificateXMLByXSD(xsdPath, xmlPath)) {
            BaseLogger.info("未通过xsd校验");
            return XMLUtil.combinateReturnMessage4Cainiao(false, "S01");
        }
        String result;
        try {
            result = new PreUploadOrderCainiaoClient().execute(xmlStr);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLogger.info("e = " + e);
            BaseLogger.info("系统异常,应该是191有问题");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S07");
        }
        return result;
    }
}
