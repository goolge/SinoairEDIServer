package com.sinoair.iemisgateway.royalMail.domain;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-8
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class RoyalMailTrace {
    private String B1_Recordtype = "";
    private String B2_Wirenumber = "";
    private String B3_Item_code = "";
    private String B4_Sendersreference = "";
    private String B5_Destination = "";
    private String B6_Eventlocation = "";
    private String B7_Eventcode = "";
    private String B8_Eventlocationcode = "";
    private String B9_Logdate = "";
    private String B10_Logtime = "";
    private String B11_Service = "";
    private String B12_Despatchsite = "";
    private String B13_Spare1 = "";
    private String B14_Spare2 = "";
    private String B15_Spare3 = "";

    public RoyalMailTrace(){}
    public RoyalMailTrace(String[] arr){
        this.copyProperties(arr);
    }

    /**
     * 数据装载
     *
     * @param arr
     */
    public void copyProperties(String[] arr) {
        if (arr != null && arr.length != 15) {
            this.B1_Recordtype = arr[0];
            this.B2_Wirenumber = arr[1];
            this.B3_Item_code = arr[2];
            this.B4_Sendersreference = arr[3];
            this.B5_Destination = arr[4];
            this.B6_Eventlocation = arr[5];
            this.B7_Eventcode = arr[6];
            this.B8_Eventlocationcode = arr[7];
            this.B9_Logdate = arr[8];
            this.B10_Logtime = arr[9];
            this.B11_Service = arr[10];
            this.B12_Despatchsite = arr[11];
            this.B13_Spare1 = arr[12];
            this.B14_Spare2 = arr[13];
            this.B15_Spare3 = arr[14];
        }
    }

    public String getB1_Recordtype() {
        return B1_Recordtype;
    }

    public void setB1_Recordtype(String b1_Recordtype) {
        B1_Recordtype = b1_Recordtype;
    }

    public String getB2_Wirenumber() {
        return B2_Wirenumber;
    }

    public void setB2_Wirenumber(String b2_Wirenumber) {
        B2_Wirenumber = b2_Wirenumber;
    }

    public String getB3_Item_code() {
        return B3_Item_code;
    }

    public void setB3_Item_code(String b3_Item_code) {
        B3_Item_code = b3_Item_code;
    }

    public String getB4_Sendersreference() {
        return B4_Sendersreference;
    }

    public void setB4_Sendersreference(String b4_Sendersreference) {
        B4_Sendersreference = b4_Sendersreference;
    }

    public String getB5_Destination() {
        return B5_Destination;
    }

    public void setB5_Destination(String b5_Destination) {
        B5_Destination = b5_Destination;
    }

    public String getB6_Eventlocation() {
        return B6_Eventlocation;
    }

    public void setB6_Eventlocation(String b6_Eventlocation) {
        B6_Eventlocation = b6_Eventlocation;
    }

    public String getB7_Eventcode() {
        return B7_Eventcode;
    }

    public void setB7_Eventcode(String b7_Eventcode) {
        B7_Eventcode = b7_Eventcode;
    }

    public String getB8_Eventlocationcode() {
        return B8_Eventlocationcode;
    }

    public void setB8_Eventlocationcode(String b8_Eventlocationcode) {
        B8_Eventlocationcode = b8_Eventlocationcode;
    }

    public String getB9_Logdate() {
        return B9_Logdate;
    }

    public void setB9_Logdate(String b9_Logdate) {
        B9_Logdate = b9_Logdate;
    }

    public String getB10_Logtime() {
        return B10_Logtime;
    }

    public void setB10_Logtime(String b10_Logtime) {
        B10_Logtime = b10_Logtime;
    }

    public String getB11_Service() {
        return B11_Service;
    }

    public void setB11_Service(String b11_Service) {
        B11_Service = b11_Service;
    }

    public String getB12_Despatchsite() {
        return B12_Despatchsite;
    }

    public void setB12_Despatchsite(String b12_Despatchsite) {
        B12_Despatchsite = b12_Despatchsite;
    }

    public String getB13_Spare1() {
        return B13_Spare1;
    }

    public void setB13_Spare1(String b13_Spare1) {
        B13_Spare1 = b13_Spare1;
    }

    public String getB14_Spare2() {
        return B14_Spare2;
    }

    public void setB14_Spare2(String b14_Spare2) {
        B14_Spare2 = b14_Spare2;
    }

    public String getB15_Spare3() {
        return B15_Spare3;
    }

    public void setB15_Spare3(String b15_Spare3) {
        B15_Spare3 = b15_Spare3;
    }
}
