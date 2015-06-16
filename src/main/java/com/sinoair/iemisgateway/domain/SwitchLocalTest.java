package com.sinoair.iemisgateway.domain;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 13-9-28
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
public class SwitchLocalTest implements Switch {
    private String root = "D:\\idea\\IDEA10\\SinoairEDIServer";    //todo-wxx-本地根目录
    private String desPath = "D:/uploadFile/";//客户excel,及上传iemis excel生成路径
    private String invoicePath = "d:/uploadFile/invoice.xls"; //发票生成路径
    private String DBPath = "D:/idea/IDEA10/EDIService/data/db/iems"; //derby数据库路径
    private String url = "http://172.17.0.229:7001/IEMISWebApp/operation/export/newaramex_eawb_import_action.jsp";//服务器路径
//    private String url = "http://localhost:7001/IEMISWebApp/operation/export/newaramex_eawb_import_action.jsp";//服务器路径
    private String to = "wangxx4@sinoair.com";
    private String cc = "wangxx4@sinoair.com";
    private String bcc = "wangxx4@sinoair.com";

    public String getRoot() {
        return root;
    }

    public String getTo() {
        return to;
    }


    public String getCc() {
        return cc;
    }

    public String getBcc() {
        return bcc;
    }
    public String getDesPath() {
        return desPath;
    }

    public String getInvoicePath() {
        return invoicePath;
    }

    public String getDBPath() {
        return DBPath;
    }

    public String getUrl() {
        return url;
    }

}
