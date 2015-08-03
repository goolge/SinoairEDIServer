package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.domain.ExpressEdiLog;
import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.DateUtil;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import com.sinoair.iemisgateway.util.XMLUtil;
import com.sinoair.iemisgateway.util.iemisWSClient.PreUploadOrderCainiaoClient;
import com.sinoair.iemisgateway.util.iemisWSClient.common.CommonClient;
import com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoClient;
import org.dom4j.Document;

import java.io.File;

/**
 * Created by WangXX4 on 2015/7/8.
 */
public class PreUploadOrderCainiaoService {
    public String preUploadOrderCainiao(String xmlStr) {
        ExpressEdiLog expressEdiLog = new ExpressEdiLog();
        expressEdiLog.setEel_partner("cainiao");
        expressEdiLog.setEel_interface("preUploadOrderCainiao");
        expressEdiLog.setEel_request(xmlStr);
        BaseLogger.info("----cainiao report message = --" + xmlStr + "----");
        String xmlPath = PropertiesUtil.readProperty("common", "historyRootPath") + File.separator + "Cainiao" + File.separator + "preUploadOrderCainiao" + DateUtil.getDateWith() + ".xml";
        String xsdPath = UploadOrderCainiaoService.class.getResource("/XMLAndXSD/cainiao/preUploadOrderCainiao.xsd").getPath();
        BaseLogger.info("--------------" + File.separator + "---------------------------");
        if ("\\".equals(File.separator)) {
            xsdPath = xsdPath.substring(1);
        }
        BaseLogger.info("---xsdPath---" + xsdPath);
        XMLUtil.writeFile(xmlStr, xmlPath);
        String result;
        Document document = XMLUtil.xmlVerification(xmlStr);
        if (document == null) {
            BaseLogger.info("非法的XML格式");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S01");
            expressEdiLog.insertRecord(result, "Y", "非法的XML格式");
            return result;
        }
        if (!XMLUtil.xmlValiadationCainiao(document)) {
            BaseLogger.info("非法的数字签名");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S02");
            expressEdiLog.insertRecord(result, "Y", "非法的数字签名");
            return result;
        }
        String[] checkArray = {"Raddress", "Rcity"};
        if (!XMLUtil.xmlVerificateCainiao(document, checkArray)) {
            BaseLogger.info("xml缺失关键节点：Raddress、Rcity");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S01");
            expressEdiLog.insertRecord(result, "Y", "xml缺失关键节点：Raddress、Rcity");
            return result;
        }
        try {
            result = new PreUploadOrderCainiaoClient().execute(xmlStr);  //todo
            expressEdiLog.insertRecord(result, "N", "");
        } catch (Exception e) {
            e.printStackTrace();
            BaseLogger.info("e = " + e);
            BaseLogger.info("系统异常,应该是191有问题");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S07");
            expressEdiLog.insertRecord(result, "Y", "系统异常,应该是191有问题");
        }
        return result;
    }
}
