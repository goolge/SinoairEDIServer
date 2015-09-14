package com.sinoair.iemisgateway.royalMail.domain;

import com.sinoair.iemisgateway.util.StringUtil;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-8-11
 * Time: 下午2:14
 * To change this template use File | Settings | File Templates.
 */
public class RoyalMailItem {

    String RecordTypeIndicator_C1 = "03";
    String VersionNumber_C2 = "02";
    String ItemNumber_C3 = "";
    String RecipientName_C4 = "";
    String BusinessName_C5 = "";
    String DeliveryAddress1_C6 = "";
    String DeliveryAddress2_C7 = "";
    String DeliveryAddress3_C8 = "";
    String Posttown_C9 = "";
    String Postcode_C10 = "";
    String SortCode_C11 = "";
    String Contract_C12 = "575801TL";
    String ServiceID_C13 = "TPL01";
    String ServiceEnhancement_C14 = "";
    String AccountNumber_C15 = "0418383000";
    String SendersReference1_C16 = "";
    String SpareAttribute_C17 = "";
    String Safeplace_C18 = "";
    String ItemWeight_C19 = "";
    String SpareAttribute_C20 = "";
    String SpareAttribute_C21 = "";
    String DeliveryOption_C22 = "";
    String DeclaredValue_C23 = "";
    String NotificationCode_C24 = "";
    String RecipientEmail_C25 = "";
    String RecipientTelephone_C26 = "";
    String SpareAttribute_C27 = "";
    String SpareAttribute_C28 = "";
    String RecipientContactNo_C29 = "";

    HashMap<String, Object> mapParam = null;

    public  RoyalMailItem(){}
    public  RoyalMailItem(HashMap<String, Object> mapParam){
        this.copyProperties(mapParam);
    }

    /**
     * 数据装载
     *
     * @param mapParam
     */
    public void copyProperties(HashMap<String, Object> mapParam) {
        this.mapParam = mapParam;
        //todo 取值字段要做修改
        this.ItemNumber_C3 = StringUtil.getFixedString(String.valueOf(mapParam.get("EAWB_REFERENCE1")), 13, " ", false);
        this.RecipientName_C4 = StringUtil.getStringSpace(String.valueOf(mapParam.get("EAWB_CONSIGNEE_ACCOUNTNAME")), 50, 1);
        String[] addressArr = generateAddress(String.valueOf(mapParam.get("EAWB_DELIVER_ADDRESS")));
        this.DeliveryAddress1_C6 = addressArr[0];
        this.DeliveryAddress2_C7 = addressArr[1];
        this.DeliveryAddress3_C8 = addressArr[2];
        this.Posttown_C9= StringUtil.getStringSpace(String.valueOf(mapParam.get("EAWB_DESTCITY")), 30, 1);
        this.Postcode_C10= StringUtil.getStringSpace(String.valueOf(mapParam.get("EAWB_DELIVER_POSTCODE")), 8, 5);
        this.SortCode_C11= StringUtil.getFixedString(String.valueOf(mapParam.get("SORTCODE")), 3, " ",false);
        this.SendersReference1_C16=StringUtil.getStringSpace(String.valueOf(mapParam.get("EAWB_REFERENCE2")), 20, 0);
        Double weight = Double.parseDouble(mapParam.get("EAWB_DECLAREGROSSWEIGHT").toString()) * 1000; //单位是kg ,要转换成g
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#");
        this.ItemWeight_C19=StringUtil.getString(decimalFormat.format(weight), 7, 1,"0",false);
        this.RecipientEmail_C25=StringUtil.getStringSpace(mapParam.get("EAWB_DELIVER_EMAIL").toString(),60,0);
        this.RecipientContactNo_C29=StringUtil.getPhoneNum(mapParam.get("EAWB_DELIVER_PHONE").toString(), 20);
    }

    private String[] generateAddress(String address) {
        String[] addressArr = new String[]{"", "", ""};
        addressArr[0] = address.length() > 50 ? address.substring(0, 50) : address;
        if (address.length() > 50) {
            if (address.length() < 101) {
                addressArr[1] = address.substring(50, address.length());
            } else {
                addressArr[1] = address.substring(50, 100);
            }
        }
        if (address.length() > 100) {
            if (address.length() < 151) {
                addressArr[2] = address.substring(100, address.length());
            } else {
                addressArr[2] = address.substring(100, 150);
            }
        }
        return addressArr;
    }

    public void setItemNumber_C3(String itemNumber_C3) {
        ItemNumber_C3 = itemNumber_C3;
    }

    public void setRecipientName_C4(String recipientName_C4) {
        RecipientName_C4 = recipientName_C4;
    }

    public void setDeliveryAddress1_C6(String deliveryAddress1_C6) {
        DeliveryAddress1_C6 = deliveryAddress1_C6;
    }

    public void setPosttown_C9(String posttown_C9) {
        Posttown_C9 = posttown_C9;
    }

    public void setPostcode_C10(String postcode_C10) {
        Postcode_C10 = postcode_C10;
    }

    public void setSortCode_C11(String sortCode_C11) {
        SortCode_C11 = sortCode_C11;
    }

    public void setServiceID_C13(String serviceID_C13) {
        ServiceID_C13 = serviceID_C13;
    }

    public void setSendersReference1_C16(String sendersReference1_C16) {
        SendersReference1_C16 = sendersReference1_C16;
    }

    public void setItemWeight_C19(String itemWeight_C19) {
        ItemWeight_C19 = itemWeight_C19;
    }

    public String getItemNumber_C3() {
        return ItemNumber_C3;
    }

    public String getRecipientName_C4() {
        return RecipientName_C4;
    }

    public String getDeliveryAddress1_C6() {
        return DeliveryAddress1_C6;
    }

    public String getPosttown_C9() {
        return Posttown_C9;
    }

    public String getPostcode_C10() {
        return Postcode_C10;
    }

    public String getSortCode_C11() {
        return SortCode_C11;
    }

    public String getServiceID_C13() {
        return ServiceID_C13;
    }

    public String getSendersReference1_C16() {
        return SendersReference1_C16;
    }

    public String getItemWeight_C19() {
        return ItemWeight_C19;
    }

    public String getDeliveryAddress2_C7() {
        return DeliveryAddress2_C7;
    }

    public void setDeliveryAddress2_C7(String deliveryAddress2_C7) {
        DeliveryAddress2_C7 = deliveryAddress2_C7;
    }

    public String getDeliveryAddress3_C8() {
        return DeliveryAddress3_C8;
    }

    public void setDeliveryAddress3_C8(String deliveryAddress3_C8) {
        DeliveryAddress3_C8 = deliveryAddress3_C8;
    }

    public String getRecipientContactNo_C29() {
        return RecipientContactNo_C29;
    }

    public void setRecipientContactNo_C29(String recipientContactNo_C29) {
        RecipientContactNo_C29 = recipientContactNo_C29;
    }

    public String getRecordTypeIndicator_C1() {
        return RecordTypeIndicator_C1;
    }

    public String getVersionNumber_C2() {
        return VersionNumber_C2;
    }

    public String getBusinessName_C5() {
        return BusinessName_C5;
    }

    public String getContract_C12() {
        return Contract_C12;
    }

    public String getServiceEnhancement_C14() {
        return ServiceEnhancement_C14;
    }

    public String getAccountNumber_C15() {
        return AccountNumber_C15;
    }

    public String getSpareAttribute_C17() {
        return SpareAttribute_C17;
    }

    public String getSafeplace_C18() {
        return Safeplace_C18;
    }

    public String getSpareAttribute_C20() {
        return SpareAttribute_C20;
    }

    public String getSpareAttribute_C21() {
        return SpareAttribute_C21;
    }

    public String getDeliveryOption_C22() {
        return DeliveryOption_C22;
    }

    public String getDeclaredValue_C23() {
        return DeclaredValue_C23;
    }

    public String getNotificationCode_C24() {
        return NotificationCode_C24;
    }

    public String getRecipientEmail_C25() {
        return RecipientEmail_C25;
    }

    public String getRecipientTelephone_C26() {
        return RecipientTelephone_C26;
    }

    public String getSpareAttribute_C27() {
        return SpareAttribute_C27;
    }

    public String getSpareAttribute_C28() {
        return SpareAttribute_C28;
    }

    public HashMap<String, Object> getMapParam() {
        return mapParam;
    }

    public void setMapParam(HashMap<String, Object> mapParam) {
        this.mapParam = mapParam;
    }
}
