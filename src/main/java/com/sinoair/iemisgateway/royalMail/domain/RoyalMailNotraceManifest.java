package com.sinoair.iemisgateway.royalMail.domain;

import com.sinoair.iemisgateway.util.StringUtil;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-8-11
 * Time: 下午1:17
 * To change this template use File | Settings | File Templates.
 */
public class RoyalMailNotraceManifest {
    public static final String PARTNER_CODE="ROYALMAIL";
    public static final String RMURL="ftg.bdtg.royalmailgroup.com";
    public static final String USERNAME="E000647";
    public static final String PASSWORD="EW4AGiD5";
    public static final int PROTNUM=22022;
    String RecordTypeIndicator11_A1 = "00";
    String VersionNumber12_A2 = "02";
    String FileType13_A3 = "RMCM";
    String FileSerialNumber_A4;
    String FileSubmissionDate_A5;
    String FileSubmissionTime_A6;
    String ContactEmail_A7 = "ZHANGMJ@SINOAIR.COM";
    public static final String WireNumber_A8 = "W9EA";
    String SiteSystemName_A9 = "";
    String CradleID_A10 = "9999";
    String FileStatus_A11 = "LIVE";

    String RecordTypeIndicator_B1 = "01";
    String VersionNumber_B2 = "02";
    String ContactName_B3 = "mike";
    String ContactTelephone_B4 = "4402089310200";
    String ContactEmail_B5 = "mike@ccllhr.com";
    String BusinessName_B6 = "CCL";
    String DespatchAddress1_B7 = "Unit4 Radius Park Faggs Road Feltham";
    String DespatchAddress2_B8 = "";
    String DespatchAddress3_B9 = "";
    String Posttown_B10 = "london";
    String Postcode_B11 = "TW14 0NG";
    String Country_B12 = "GBR";
    String PostingLocation_B13 = "9000446082";
    String ReceivingHUB_B14 = "002673";

    String RecordTypeIndicator_D1 = "09";
    String VersionNumber_D2 = "02";
    String RecordCount_D3;

    List<RoyalMailItem> royalMailItemList;

    public String getMessageContent() {
        StringBuffer sb = new StringBuffer();
        if (royalMailItemList != null && royalMailItemList.size() > 0) {
            sb.append(StringUtil.nullAndCommaProcess(RecordTypeIndicator11_A1));
            sb.append(StringUtil.nullAndCommaProcess(VersionNumber12_A2));
            sb.append(StringUtil.nullAndCommaProcess(FileType13_A3));
            sb.append(StringUtil.nullAndCommaProcess(StringUtil.getStringSpace(FileSerialNumber_A4,4,1)));
            sb.append(StringUtil.nullAndCommaProcess(StringUtil.getFixedStringSpace(FileSubmissionDate_A5,8)));
            sb.append(StringUtil.nullAndCommaProcess(StringUtil.getFixedStringSpace(FileSubmissionTime_A6,6)));
            sb.append(StringUtil.nullAndCommaProcess(ContactEmail_A7));
            sb.append(StringUtil.nullAndCommaProcess(WireNumber_A8));
            sb.append(StringUtil.nullAndCommaProcess(SiteSystemName_A9));
            sb.append(StringUtil.nullAndCommaProcess(CradleID_A10));
            sb.append(StringUtil.nullAndCommaProcess(FileStatus_A11));
            sb.append("\r\n");

            sb.append(StringUtil.nullAndCommaProcess(RecordTypeIndicator_B1));
            sb.append(StringUtil.nullAndCommaProcess(VersionNumber_B2));
            sb.append(StringUtil.nullAndCommaProcess(ContactName_B3));
            sb.append(StringUtil.nullAndCommaProcess(ContactTelephone_B4));
            sb.append(StringUtil.nullAndCommaProcess(ContactEmail_B5));
            sb.append(StringUtil.nullAndCommaProcess(BusinessName_B6));
            sb.append(StringUtil.nullAndCommaProcess(DespatchAddress1_B7));
            sb.append(StringUtil.nullAndCommaProcess(DespatchAddress2_B8));
            sb.append(StringUtil.nullAndCommaProcess(DespatchAddress3_B9));
            sb.append(StringUtil.nullAndCommaProcess(Posttown_B10));
            sb.append(StringUtil.nullAndCommaProcess(Postcode_B11));
            sb.append(StringUtil.nullAndCommaProcess(Country_B12));
            sb.append(StringUtil.nullAndCommaProcess(PostingLocation_B13));
            sb.append(StringUtil.nullAndCommaProcess(ReceivingHUB_B14));
            sb.append("\r\n");
            for (int i = 0; i < royalMailItemList.size(); i++) {
                RoyalMailItem royalMailItem = royalMailItemList.get(i);
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getRecordTypeIndicator_C1()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getVersionNumber_C2()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getItemNumber_C3()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getRecipientName_C4()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getBusinessName_C5()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getDeliveryAddress1_C6()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getDeliveryAddress2_C7()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getDeliveryAddress3_C8()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getPosttown_C9()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getPostcode_C10()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSortCode_C11()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getContract_C12()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getServiceID_C13()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getServiceEnhancement_C14()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getAccountNumber_C15()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSendersReference1_C16()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSpareAttribute_C17()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSafeplace_C18()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getItemWeight_C19()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSpareAttribute_C20()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSpareAttribute_C21()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getDeliveryOption_C22()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getDeclaredValue_C23()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getNotificationCode_C24()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getRecipientEmail_C25()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getRecipientTelephone_C26()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSpareAttribute_C27()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getSpareAttribute_C28()));
                sb.append(StringUtil.nullAndCommaProcess(royalMailItem.getRecipientContactNo_C29()));
                sb.append("\r\n");

            }

            this.RecordCount_D3=StringUtil.getIntFormString(5,(royalMailItemList.size() + 3));
            sb.append(StringUtil.nullAndCommaProcess(RecordTypeIndicator_D1));
            sb.append(StringUtil.nullAndCommaProcess(VersionNumber_D2));
            sb.append(RecordCount_D3);
        }

        return sb.toString();
    }

    public void setRecordTypeIndicator11_A1(String recordTypeIndicator11_A1) {
        RecordTypeIndicator11_A1 = recordTypeIndicator11_A1;
    }

    public void setVersionNumber12_A2(String versionNumber12_A2) {
        VersionNumber12_A2 = versionNumber12_A2;
    }

    public void setFileType13_A3(String fileType13_A3) {
        FileType13_A3 = fileType13_A3;
    }

    public void setFileSerialNumber_A4(String fileSerialNumber_A4) {
        FileSerialNumber_A4 = fileSerialNumber_A4;
    }

    public void setFileSubmissionDate_A5(String fileSubmissionDate_A5) {
        FileSubmissionDate_A5 = fileSubmissionDate_A5;
    }

    public void setFileSubmissionTime_A6(String fileSubmissionTime_A6) {
        FileSubmissionTime_A6 = fileSubmissionTime_A6;
    }

    public void setContactEmail_A7(String contactEmail_A7) {
        ContactEmail_A7 = contactEmail_A7;
    }

    public void setSiteSystemName_A9(String siteSystemName_A9) {
        SiteSystemName_A9 = siteSystemName_A9;
    }

    public void setCradleID_A10(String cradleID_A10) {
        CradleID_A10 = cradleID_A10;
    }

    public void setFileStatus_A11(String fileStatus_A11) {
        FileStatus_A11 = fileStatus_A11;
    }

    public void setRecordTypeIndicator_B1(String recordTypeIndicator_B1) {
        RecordTypeIndicator_B1 = recordTypeIndicator_B1;
    }

    public void setVersionNumber_B2(String versionNumber_B2) {
        VersionNumber_B2 = versionNumber_B2;
    }

    public void setContactName_B3(String contactName_B3) {
        ContactName_B3 = contactName_B3;
    }

    public void setContactTelephone_B4(String contactTelephone_B4) {
        ContactTelephone_B4 = contactTelephone_B4;
    }

    public void setContactEmail_B5(String contactEmail_B5) {
        ContactEmail_B5 = contactEmail_B5;
    }

    public void setBusinessName_B6(String businessName_B6) {
        BusinessName_B6 = businessName_B6;
    }

    public void setDespatchAddress1_B7(String despatchAddress1_B7) {
        DespatchAddress1_B7 = despatchAddress1_B7;
    }

    public void setDespatchAddress2_B8(String despatchAddress2_B8) {
        DespatchAddress2_B8 = despatchAddress2_B8;
    }

    public void setDespatchAddress3_B9(String despatchAddress3_B9) {
        DespatchAddress3_B9 = despatchAddress3_B9;
    }

    public void setPosttown_B10(String posttown_B10) {
        Posttown_B10 = posttown_B10;
    }

    public void setPostcode_B11(String postcode_B11) {
        Postcode_B11 = postcode_B11;
    }

    public void setCountry_B12(String country_B12) {
        Country_B12 = country_B12;
    }

    public void setPostingLocation_B13(String postingLocation_B13) {
        PostingLocation_B13 = postingLocation_B13;
    }

    public void setReceivingHUB_B14(String receivingHUB_B14) {
        ReceivingHUB_B14 = receivingHUB_B14;
    }

    public void setRecordTypeIndicator_D1(String recordTypeIndicator_D1) {
        RecordTypeIndicator_D1 = recordTypeIndicator_D1;
    }

    public void setVersionNumber_D2(String versionNumber_D2) {
        VersionNumber_D2 = versionNumber_D2;
    }

    public void setRecordCount_D3(String recordCount_D3) {
        RecordCount_D3 = recordCount_D3;
    }

    public void setRoyalMailItemList(List<RoyalMailItem> royalMailItemList) {
        this.royalMailItemList = royalMailItemList;
    }

    public String getWireNumber_A8() {
        return WireNumber_A8;
    }
}
