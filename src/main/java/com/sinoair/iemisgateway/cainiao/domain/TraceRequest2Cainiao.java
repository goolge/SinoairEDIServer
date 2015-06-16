package com.sinoair.iemisgateway.cainiao.domain;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-20
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
public class TraceRequest2Cainiao {
    String logistic_provider_id;
    String mailNos;
    String txLogisticID;
    String time;
    String desc;
    String city;
    String facilityType;
    String facilityNo;
    String facilityName;
    String action;

    public String combiteTraceXml4Cainiao() {

//        return "<request>\n" +  //消息内容(message content)
//                "\t<tracesList>\n" +
//                "\t<tracesElement>\n" +
//                "\t\t<logisticProviderID>" + logistic_provider_id + "</logisticProviderID>\n" +
//                "\t\t<mailNos>" + mailNos + "</mailNos> \n" +
//                "\t\t<txLogisticID>" + txLogisticID + "</txLogisticID>\n" +
//                "\t\t\t<traces>\n" +
//                "\t\t\t\t<trace>\n" +
//                "\t\t\t\t\t<time>" + time + "</time>\n" +
//                "\t\t\t\t\t<desc>" + desc + "</desc>\n" +
//                "\t\t\t\t\t<city>" + city + "</city>\n" +
//                "\t\t\t\t\t<facilityType>" + facilityType + "</facilityType>\n" +
//                "\t\t\t\t\t<facilityNo>" + facilityNo + "</facilityNo>\n" +
//                "\t\t\t\t\t<facilityName>" + facilityName + "</facilityName>\n" +
//                "\t\t\t\t\t<action>" + action + "</action>\n" +
//                "\t\t\t\t</trace>\n" +
//                "\t\t\t</traces>\n" +
//                "\t</tracesElement>\n" +
//                "\t</tracesList>\n" +
//                "</request>"; //todo 环境有request节点
        return
                "\t<tracesList>\n" +
                        "\t<tracesElement>\n" +
                        "\t\t<logisticProviderID>" + logistic_provider_id + "</logisticProviderID>\n" +
                        "\t\t<mailNos>" + mailNos + "</mailNos> \n" +
                        "\t\t<txLogisticID>" + txLogisticID + "</txLogisticID>\n" +
                        "\t\t\t<traces>\n" +
                        "\t\t\t\t<trace>\n" +
                        "\t\t\t\t\t<time>" + time + "</time>\n" +
                        "\t\t\t\t\t<desc>" + desc + "</desc>\n" +
                        "\t\t\t\t\t<city>" + city + "</city>\n" +
                        "\t\t\t\t\t<facilityType>" + facilityType + "</facilityType>\n" +
                        "\t\t\t\t\t<facilityNo>" + facilityNo + "</facilityNo>\n" +
                        "\t\t\t\t\t<facilityName>" + facilityName + "</facilityName>\n" +
                        "\t\t\t\t\t<action>" + action + "</action>\n" +
                        "\t\t\t\t</trace>\n" +
                        "\t\t\t</traces>\n" +
                        "\t</tracesElement>\n" +
                        "\t</tracesList>\n"; //todo 环境有request节点
    }

    public String getLogistic_provider_id() {
        return logistic_provider_id;
    }

    public void setLogistic_provider_id(String logistic_provider_id) {
        this.logistic_provider_id = logistic_provider_id;
    }

    public String getMailNos() {
        return mailNos;
    }

    public void setMailNos(String mailNos) {
        this.mailNos = mailNos;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getFacilityNo() {
        return facilityNo;
    }

    public void setFacilityNo(String facilityNo) {
        this.facilityNo = facilityNo;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
