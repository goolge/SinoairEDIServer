package com.sinoair.iemisgateway.royalMail.domain;

import com.sinoair.iemisgateway.util.DateUtil;
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
    public static final String PARTNER_CODE = "ROYALMAILNOTRACE";
    public static final String FILENAMEPREFIX = "COSS";
    public static final String PREADVICE3 = "_PreAdvice3_";
    public static final String A4_GenericAcccountNumber = "0418383000";

    String A1_RecordTypeIndicator = "00";
    String A2_FileVersionNumber = "03";
    String A3_FileType = "RMBS";
    String A5_GenericAcccountCode = "MULTIPLE";
    String A6_BatchNumber = "1";
    String A7_DespatchCollectionTime = "";
    String A8_EarlistCollectionDateTime = "";
    String A9_LatestCollectionTime = "";
    //todo 测试期间“TEST”,上线以后LIVE
    String A10_FileStatus = "TEST";
    String A11_FileSubmissionDateTime = "";
    String A12_WireNumber = "W9EA";
    String A13_HashAuthenticaiton = "";
    String A14_ChannelIdentifier = "OB";

    String B1_RecordTypeIndicator = "01";
    String B2_FileVersionNumber = "03";
    String B3_SenderName = "ccl";
    String B4_SendersAddressLine1 = "Unit4 Radius Park Faggs Road Feltham";
    String B5_SendersAddressLine2 = "";
    String B6_SendersAddressLine3 = "";
    String B7_SendersAddressLine4 = "";
    String B8_SendersAddressLine5 = "";
    String B9_SendersPosttown = "london";
    String B10_SenderPostcode = "TW14 0NG";
    String B11_ContactName = "Mike";
    String B12_ContactTelephone = "4402089310200";
    String B13_Vehicle = "";
    String B14_PaymentMethod = "";
    String B15_PaymentValue = "";
    String B16_ContactEmail = "mike@ccllhr.com";
    String B17_ReceivingHUB = "002673";
    String B18_PostingLocationNumber = "9000446082";

    String D1_RecordTypeIndicator = "09";
    String D2_VersionNumber = "03";
    String D3_RecordCount = "";

    List<RoyalMailNotraceItem> royalMailNotraceItemList;

    public String getMessageContent() {
        StringBuffer sb = new StringBuffer();
        if (royalMailNotraceItemList != null && royalMailNotraceItemList.size() > 0) {
            sb.append(StringUtil.nullAndCommaQuotesProcess(A1_RecordTypeIndicator));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A2_FileVersionNumber));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A3_FileType));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A4_GenericAcccountNumber));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A5_GenericAcccountCode));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A6_BatchNumber));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A7_DespatchCollectionTime));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A8_EarlistCollectionDateTime));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A9_LatestCollectionTime));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A10_FileStatus));
            this.A11_FileSubmissionDateTime= DateUtil.getDateStrAheadHours(-8, "yyyy-MM-dd'T'HH:mm:ss+08:00");
            sb.append(StringUtil.nullAndCommaQuotesProcess(A11_FileSubmissionDateTime));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A12_WireNumber));
            sb.append(StringUtil.nullAndCommaQuotesProcess(A13_HashAuthenticaiton));
            sb.append("\""+StringUtil.nullProcess(A14_ChannelIdentifier,null)+"\"");
            sb.append("\r\n");

            sb.append(StringUtil.nullAndCommaQuotesProcess(B1_RecordTypeIndicator));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B2_FileVersionNumber));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B3_SenderName));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B4_SendersAddressLine1));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B5_SendersAddressLine2));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B6_SendersAddressLine3));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B7_SendersAddressLine4));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B8_SendersAddressLine5));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B9_SendersPosttown));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B10_SenderPostcode));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B11_ContactName));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B12_ContactTelephone));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B13_Vehicle));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B14_PaymentMethod));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B15_PaymentValue));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B16_ContactEmail));
            sb.append(StringUtil.nullAndCommaQuotesProcess(B17_ReceivingHUB));
            sb.append("\""+StringUtil.nullProcess(B18_PostingLocationNumber,null)+"\"");
            sb.append("\r\n");
            for (int i = 0; i < royalMailNotraceItemList.size(); i++) {
                RoyalMailNotraceItem  royalMailNotraceItem= royalMailNotraceItemList.get(i);
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC1_RecordTypeIndicator()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC2_FileVersionNumber()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC3_ConsignmentNumber()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC4_ServiceID()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC5_WeekendHandlingCode()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC6_SpareField()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC7_SpareField()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC8_SenderReference1()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC9_CollectionIDLocationID()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC10_ContractNumber()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC11_ConsignmentWeight()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC12_NumberofItems()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC13_SpareField()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC14_RecipientBusinessName()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC15_DeliveryAddress1()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC16_DeliveryAddress2()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC17_DeliveryAddress3()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC18_DeliveryPostTown()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC19_DeliveryPostcodeDPS()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC20_RecipientName()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC21_BuildingNumber()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC22_BuildingName()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC23_DeliveryAddress4()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC24_DeliveryAddress5()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC25_DeliveryCountry()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC26_ZISOBillToDestinationCode()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC27_UniqueItemID()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC28_ItemWeight()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC29_WeightType()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC30_TypeofItem()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC31_Format()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC32_Diemensions()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC33_DiemensionsType()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC34_DeclaredValue()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC35_PricePaid()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC36_POLFADcode()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC37_Dienumber()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC38_1DTrackingNumber()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC39_Class()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC40_DateofProduction()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC41_ProvisionalDate()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC42_TariffRate()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC43_TariffVersion()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC44_Sortcode()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC45_ContractCode()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC46_SenderReference2()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC47_ServiceEnhancement()));
                sb.append(StringUtil.nullAndCommaQuotesProcess(royalMailNotraceItem.getC48_RecipientContactNo()));
                sb.append("\""+StringUtil.nullProcess(royalMailNotraceItem.getC49_ExpectedDateofDelivery(),null)+"\"");
                sb.append("\r\n");
            }
            this.D3_RecordCount = StringUtil.getStringSpace(""+(royalMailNotraceItemList.size() + 3),5,1);
            sb.append(StringUtil.nullAndCommaQuotesProcess(D1_RecordTypeIndicator));
            sb.append(StringUtil.nullAndCommaQuotesProcess(D2_VersionNumber));
            sb.append("\""+StringUtil.nullProcess(D3_RecordCount,null)+"\"");

        }

        return sb.toString();
    }

    public void setRoyalMailNotraceItemList(List<RoyalMailNotraceItem> royalMailNotraceItemList) {
        this.royalMailNotraceItemList = royalMailNotraceItemList;
    }

    public static String getPartnerCode() {
        return PARTNER_CODE;
    }

    public static String getFilenameprefix() {
        return FILENAMEPREFIX;
    }

    public static String getPreadvice3() {
        return PREADVICE3;
    }

    public String getA1_RecordTypeIndicator() {
        return A1_RecordTypeIndicator;
    }

    public String getA2_FileVersionNumber() {
        return A2_FileVersionNumber;
    }

    public String getA3_FileType() {
        return A3_FileType;
    }

    public String getA4_GenericAcccountNumber() {
        return A4_GenericAcccountNumber;
    }

    public String getA5_GenericAcccountCode() {
        return A5_GenericAcccountCode;
    }

    public String getA6_BatchNumber() {
        return A6_BatchNumber;
    }

    public String getA7_DespatchCollectionTime() {
        return A7_DespatchCollectionTime;
    }

    public String getA8_EarlistCollectionDateTime() {
        return A8_EarlistCollectionDateTime;
    }

    public String getA9_LatestCollectionTime() {
        return A9_LatestCollectionTime;
    }

    public String getA10_FileStatus() {
        return A10_FileStatus;
    }

    public String getA11_FileSubmissionDateTime() {
        return A11_FileSubmissionDateTime;
    }

    public String getA12_WireNumber() {
        return A12_WireNumber;
    }

    public String getA13_HashAuthenticaiton() {
        return A13_HashAuthenticaiton;
    }

    public String getA14_ChannelIdentifier() {
        return A14_ChannelIdentifier;
    }

    public String getB1_RecordTypeIndicator() {
        return B1_RecordTypeIndicator;
    }

    public String getB2_FileVersionNumber() {
        return B2_FileVersionNumber;
    }

    public String getB3_SenderName() {
        return B3_SenderName;
    }

    public String getB4_SendersAddressLine1() {
        return B4_SendersAddressLine1;
    }

    public String getB5_SendersAddressLine2() {
        return B5_SendersAddressLine2;
    }

    public String getB6_SendersAddressLine3() {
        return B6_SendersAddressLine3;
    }

    public String getB7_SendersAddressLine4() {
        return B7_SendersAddressLine4;
    }

    public String getB8_SendersAddressLine5() {
        return B8_SendersAddressLine5;
    }

    public String getB9_SendersPosttown() {
        return B9_SendersPosttown;
    }

    public String getB10_SenderPostcode() {
        return B10_SenderPostcode;
    }

    public String getB11_ContactName() {
        return B11_ContactName;
    }

    public String getB12_ContactTelephone() {
        return B12_ContactTelephone;
    }

    public String getB13_Vehicle() {
        return B13_Vehicle;
    }

    public String getB14_PaymentMethod() {
        return B14_PaymentMethod;
    }

    public String getB15_PaymentValue() {
        return B15_PaymentValue;
    }

    public String getB16_ContactEmail() {
        return B16_ContactEmail;
    }

    public String getB17_ReceivingHUB() {
        return B17_ReceivingHUB;
    }

    public String getB18_PostingLocationNumber() {
        return B18_PostingLocationNumber;
    }

    public String getD1_RecordTypeIndicator() {
        return D1_RecordTypeIndicator;
    }

    public String getD2_VersionNumber() {
        return D2_VersionNumber;
    }

    public String getD3_RecordCount() {
        return D3_RecordCount;
    }
}
