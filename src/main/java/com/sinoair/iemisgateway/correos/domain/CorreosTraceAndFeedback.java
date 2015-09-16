package com.sinoair.iemisgateway.correos.domain;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-10
 * Time: 下午9:30
 * To change this template use File | Settings | File Templates.
 */
public class CorreosTraceAndFeedback {
    private String t1_Position = "";
    private String t2_Yearofcreationofstructureandversion = "";
    private String t3_Shipmentcode = "";
    private String t4_ClientReference = "";
    private String t5_StatusCode = "";
    private String t6_DescriptionofStatus = "";
    private String t7_StatusDate = "";
    private String t8_StatusTime = "";
    private String tn_AssociatedDatum="";

    public CorreosTraceAndFeedback(){}
    public CorreosTraceAndFeedback(String[] arrTrace){
        this.copyProperties(arrTrace);
    }
    public void copyProperties(String[] arrTrace){
        if(arrTrace!=null && arrTrace.length<8){
            this.t1_Position=arrTrace[0];
            this.t2_Yearofcreationofstructureandversion=arrTrace[1];
            this.t3_Shipmentcode=arrTrace[2];
            this.t4_ClientReference=arrTrace[3];
            this.t5_StatusCode=arrTrace[4];
            this.t6_DescriptionofStatus=arrTrace[5];
            this.t7_StatusDate=arrTrace[6];
            this.t8_StatusTime=arrTrace[7];
            if(arrTrace.length>8){
                for(int i=8;i<arrTrace.length;i++){
                 tn_AssociatedDatum+=arrTrace[i];
                }
            }
        }
    }

    public String getT1_Position() {
        return t1_Position;
    }

    public void setT1_Position(String t1_Position) {
        this.t1_Position = t1_Position;
    }

    public String getT2_Yearofcreationofstructureandversion() {
        return t2_Yearofcreationofstructureandversion;
    }

    public void setT2_Yearofcreationofstructureandversion(String t2_Yearofcreationofstructureandversion) {
        this.t2_Yearofcreationofstructureandversion = t2_Yearofcreationofstructureandversion;
    }

    public String getT3_Shipmentcode() {
        return t3_Shipmentcode;
    }

    public void setT3_Shipmentcode(String t3_Shipmentcode) {
        this.t3_Shipmentcode = t3_Shipmentcode;
    }

    public String getT4_ClientReference() {
        return t4_ClientReference;
    }

    public void setT4_ClientReference(String t4_ClientReference) {
        this.t4_ClientReference = t4_ClientReference;
    }

    public String getT5_StatusCode() {
        return t5_StatusCode;
    }

    public void setT5_StatusCode(String t5_StatusCode) {
        this.t5_StatusCode = t5_StatusCode;
    }

    public String getT6_DescriptionofStatus() {
        return t6_DescriptionofStatus;
    }

    public void setT6_DescriptionofStatus(String t6_DescriptionofStatus) {
        this.t6_DescriptionofStatus = t6_DescriptionofStatus;
    }

    public String getT7_StatusDate() {
        return t7_StatusDate;
    }

    public void setT7_StatusDate(String t7_StatusDate) {
        this.t7_StatusDate = t7_StatusDate;
    }

    public String getT8_StatusTime() {
        return t8_StatusTime;
    }

    public void setT8_StatusTime(String t8_StatusTime) {
        this.t8_StatusTime = t8_StatusTime;
    }

    public String getTn_AssociatedDatum() {
        return tn_AssociatedDatum;
    }

    public void setTn_AssociatedDatum(String tn_AssociatedDatum) {
        this.tn_AssociatedDatum = tn_AssociatedDatum;
    }
}
