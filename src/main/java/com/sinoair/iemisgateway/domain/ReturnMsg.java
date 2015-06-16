package com.sinoair.iemisgateway.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: DuCC
 * Date: 13-7-19
 * Time: 上午9:08
 * To change this template use File | Settings | File Templates.
 */
public class ReturnMsg {
    private String orderNo;
    private String airwaybill;

    public ReturnMsg() {
    }

    public ReturnMsg(String orderNo, String airwaybill) {
        this.orderNo = orderNo;
        this.airwaybill = airwaybill;
    }

    private List<String> messageList=new ArrayList<String>();
    public void addMessage(String message) {
        messageList.add(message);
    }
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAirwaybill() {
        return airwaybill;
    }

    public void setAirwaybill(String airwaybill) {
        this.airwaybill = airwaybill;
    }
     public String parseMessage() {
        String strXml = "<Message>\n"
         +"<returnMsg>\n" +
                 "<orderNo>" + getOrderNo() + "</orderNo>\n" +
                 "<msg>";
        for (int i = 0; i < messageList.size(); i++) {
            String message = messageList.get(i);
            strXml = strXml + message + ";";
        }
        strXml = strXml  + "</msg>\n" +
                    "<airwaybill>" + getAirwaybill() + "</airwaybill>\n" +
                    "</returnMsg>\n"
                + "</Message>";
        return strXml;
    }
}
