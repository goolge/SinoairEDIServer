package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.domain.ExpressEdiLog;
import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.DateUtil;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import com.sinoair.iemisgateway.util.XMLUtil;
import com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoClient;
import org.dom4j.Document;

import java.io.File;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-7
 * Time: 下午1:57
 * 上传运单
 */
public class UploadOrderCainiaoService {

    public String uploadOrderCainiao(String xmlStr) {
        long time9=0;
        long time8=0;
        long time1 = new Date().getTime();
        System.out.println("time1 = " + time1);
        ExpressEdiLog expressEdiLog = new ExpressEdiLog();
        expressEdiLog.setEel_partner("cainiao");
        expressEdiLog.setEel_interface("uploadOrderCainiao");
        expressEdiLog.setEel_request(xmlStr);
        BaseLogger.info("----cainiao batch message = --" + xmlStr + "----");
        String xmlPath = PropertiesUtil.readProperty("common", "historyRootPath") + File.separator + "Cainiao" + File.separator + "uploadOrderCainiao" + DateUtil.getDateWith() + ".xml";
        String xsdPath = UploadOrderCainiaoService.class.getResource("/XMLAndXSD/cainiao/uploadOrderCainiao.xsd").getPath();
        BaseLogger.info("--------------" + File.separator + "---------------------------");
        if ("\\".equals(File.separator)) {
            xsdPath = xsdPath.substring(1);
        }
        BaseLogger.info("---xsdPath---" + xsdPath);
        long time2 = new Date().getTime();
        System.out.println("time2 = " + time2);
        XMLUtil.writeFile(xmlStr, xmlPath);
        long time3 = new Date().getTime();
        System.out.println("time3 = " + time3);
        String result;
        Document document = XMLUtil.xmlVerification(xmlStr);
        long time4 = new Date().getTime();
        System.out.println("time4 = " + time4);
        if (document == null) {
            BaseLogger.info("非法的XML格式");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S01");
            expressEdiLog.insertRecord(result, "Y", "非法的XML格式");
            return result;
        }
        long time5 = new Date().getTime();
        System.out.println("time5 = " + time5);
        if (!XMLUtil.xmlValiadationCainiao(document)) {
            BaseLogger.info("非法的数字签名");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S02");
            expressEdiLog.insertRecord(result, "Y", "非法的数字签名");
            return result;
        }
        long time6 = new Date().getTime();
        System.out.println("time6 = " + time6);
        String[] checkArray = {"Raddress", "Rcity"};
        if (!XMLUtil.xmlVerificateCainiao(document, checkArray)) {
            BaseLogger.info("xml缺失关键节点：Raddress、Rcity");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S01");
            expressEdiLog.insertRecord(result, "Y", "xml缺失关键节点：Raddress、Rcity");
            return result;
        }
        long time7 = new Date().getTime();
        System.out.println("time7 = " + time7);
        try {
            long beginTime = new Date().getTime();
            result = new UploadOrderCainiaoClient().uploadOrderCainiao(xmlStr);
            long endTime = new Date().getTime();
            System.out.println("beginTime = " + beginTime);
            System.out.println("endTime = " + endTime);
            System.out.println("调用webservice耗时" + (endTime - beginTime));
             time8 = new Date().getTime();
            System.out.println("time8 = " + time8);
            expressEdiLog.insertRecord(result, "N", "");
             time9 = new Date().getTime();
            System.out.println("time9 = " + time9);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLogger.info("e = " + e);
            BaseLogger.info("系统异常,应该是191有问题");
            result = XMLUtil.combinateReturnMessage4Cainiao(false, "S07");
            expressEdiLog.insertRecord(result, "Y", "系统异常,应该是191有问题");
        }
        long time10 = new Date().getTime();
        System.out.println("time10 = " + time10);
        System.out.println("(time9.5 处理http请求= " + (time10 - time1));
        System.out.println("(time8.5 插入log= " + (time9 - time8));
        System.out.println("(time7.5 调用ws= " + (time8 - time7));
        System.out.println("(time6.5 = " + (time7 - time6));
        System.out.println("(time5.5 = " + (time6 - time5));
        System.out.println("(time4.5 = " + (time5 - time4));
        System.out.println("(time3.5 = " + (time4 - time3));
        System.out.println("(time2.5 = " + (time3 - time2));
        System.out.println("(time1.5 = " + (time2 - time1));
        return result;
    }


}
