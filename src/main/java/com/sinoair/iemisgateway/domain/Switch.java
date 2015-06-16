package com.sinoair.iemisgateway.domain;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 13-9-28
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public interface Switch {
    String desPath = "";//客户excel,及上传iemis excel生成路径
    String invoicePath = ""; //发票生成路径
    String DBPath = ""; //derby数据库路径
    String root = "";    //todo-wxx-n 根目录
    String url = "";//服务器路径
    String to = "";
    String cc = "";
    String bcc = "";

    public String getRoot();

    public String getTo();

    public String getCc();

    public String getBcc();

    public String getDesPath();

    public String getInvoicePath();

    public String getDBPath();

    public String getUrl();

}
