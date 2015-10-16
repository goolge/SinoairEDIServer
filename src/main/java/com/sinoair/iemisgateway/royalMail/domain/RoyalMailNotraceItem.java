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
public class RoyalMailNotraceItem {

    String C1_RecordTypeIndicator="02";
    String C2_FileVersionNumber="03";
    String C3_ConsignmentNumber="";
    String C4_ServiceID="CRL01";
    String C5_WeekendHandlingCode="";
    String C6_SpareField="";
    String C7_SpareField="";
    //todo 国际运单号？还是LP号？
    String C8_SenderReference1="";
    String C9_CollectionIDLocationID="";
    String C10_ContractNumber="";
    String C11_ConsignmentWeight="";
    String C12_NumberofItems="";
    String C13_SpareField="";
    String C14_RecipientBusinessName="";
    String C15_DeliveryAddress1="";
    String C16_DeliveryAddress2="";
    String C17_DeliveryAddress3="";
    String C18_DeliveryPostTown="";
    String C19_DeliveryPostcodeDPS="";
    String C20_RecipientName="";
    String C21_BuildingNumber="";
    String C22_BuildingName="";
    String C23_DeliveryAddress4="";
    String C24_DeliveryAddress5="";
    String C25_DeliveryCountry="";
    //todo 写死的吗？
    String C26_ZISOBillToDestinationCode="GBR";
    String C27_UniqueItemID="";
    String C28_ItemWeight="";
    String C29_WeightType="1";
    String C30_TypeofItem="";
    String C31_Format="4";
    String C32_Diemensions="";
    String C33_DiemensionsType="";
    String C34_DeclaredValue="";
    String C35_PricePaid="";
    String C36_POLFADcode="";
    String C37_Dienumber="";
    String C38_1DTrackingNumber="";
    String C39_Class="F";
    String C40_DateofProduction="";
    String C41_ProvisionalDate="";
    String C42_TariffRate="";
    String C43_TariffVersion="";
    String C44_Sortcode="GBR";
    String C45_ContractCode="";
    String C46_SenderReference2="";
    String C47_ServiceEnhancement="";
    String C48_RecipientContactNo="";
    String C49_ExpectedDateofDelivery="";

    HashMap<String, Object> mapParam = null;
      public RoyalMailNotraceItem() {
    }

    public RoyalMailNotraceItem(HashMap<String, Object> mapParam) {
        this.copyProperties(mapParam);
    }

     /**
     * 数据装载
     * @param mapParam
     */
    public void copyProperties(HashMap<String, Object> mapParam) {
        this.mapParam=mapParam;
        String EAWB_REFERENCE1=String.valueOf(mapParam.get("EAWB_REFERENCE1"));
        this.C8_SenderReference1= StringUtil.getStringSpace(EAWB_REFERENCE1,20,0);
        this.C20_RecipientName= StringUtil.getStringSpace(String.valueOf(mapParam.get("EAWB_CONSIGNEE_ACCOUNTNAME")),50,0);
        String[] addressArr = generateAddress(String.valueOf(mapParam.get("EAWB_DELIVER_ADDRESS")));
        this.C15_DeliveryAddress1=addressArr[0];
        this.C16_DeliveryAddress2=addressArr[1];
        this.C17_DeliveryAddress3=addressArr[2];
        this.C18_DeliveryPostTown=StringUtil.getStringSpace(String.valueOf(mapParam.get("EAWB_DESTCITY")), 12, 0);
        this.C19_DeliveryPostcodeDPS= StringUtil.getStringSpace(String.valueOf(mapParam.get("EAWB_DELIVER_POSTCODE")), 9, 0);
        this.C27_UniqueItemID=StringUtil.getFixedString(StringUtil.calculate("0B"+RoyalMailNotraceManifest.A4_GenericAcccountNumber+EAWB_REFERENCE1.substring(EAWB_REFERENCE1.length()-8)), 21, " ", false);
        Double weight = Double.parseDouble(mapParam.get("EAWB_DECLAREGROSSWEIGHT").toString()) * 1000; //单位是kg ,要转换成g
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#");
        this.C28_ItemWeight=StringUtil.getString(decimalFormat.format(weight), 7, 1, "0", false);
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

    public String getC1_RecordTypeIndicator() {
        return C1_RecordTypeIndicator;
    }

    public void setC1_RecordTypeIndicator(String c1_RecordTypeIndicator) {
        C1_RecordTypeIndicator = c1_RecordTypeIndicator;
    }

    public String getC2_FileVersionNumber() {
        return C2_FileVersionNumber;
    }

    public void setC2_FileVersionNumber(String c2_FileVersionNumber) {
        C2_FileVersionNumber = c2_FileVersionNumber;
    }

    public String getC3_ConsignmentNumber() {
        return C3_ConsignmentNumber;
    }

    public void setC3_ConsignmentNumber(String c3_ConsignmentNumber) {
        C3_ConsignmentNumber = c3_ConsignmentNumber;
    }

    public String getC4_ServiceID() {
        return C4_ServiceID;
    }

    public void setC4_ServiceID(String c4_ServiceID) {
        C4_ServiceID = c4_ServiceID;
    }

    public String getC5_WeekendHandlingCode() {
        return C5_WeekendHandlingCode;
    }

    public void setC5_WeekendHandlingCode(String c5_WeekendHandlingCode) {
        C5_WeekendHandlingCode = c5_WeekendHandlingCode;
    }

    public String getC6_SpareField() {
        return C6_SpareField;
    }

    public void setC6_SpareField(String c6_SpareField) {
        C6_SpareField = c6_SpareField;
    }

    public String getC7_SpareField() {
        return C7_SpareField;
    }

    public void setC7_SpareField(String c7_SpareField) {
        C7_SpareField = c7_SpareField;
    }

    public String getC8_SenderReference1() {
        return C8_SenderReference1;
    }

    public void setC8_SenderReference1(String c8_SenderReference1) {
        C8_SenderReference1 = c8_SenderReference1;
    }

    public String getC9_CollectionIDLocationID() {
        return C9_CollectionIDLocationID;
    }

    public void setC9_CollectionIDLocationID(String c9_CollectionIDLocationID) {
        C9_CollectionIDLocationID = c9_CollectionIDLocationID;
    }

    public String getC10_ContractNumber() {
        return C10_ContractNumber;
    }

    public void setC10_ContractNumber(String c10_ContractNumber) {
        C10_ContractNumber = c10_ContractNumber;
    }

    public String getC11_ConsignmentWeight() {
        return C11_ConsignmentWeight;
    }

    public void setC11_ConsignmentWeight(String c11_ConsignmentWeight) {
        C11_ConsignmentWeight = c11_ConsignmentWeight;
    }

    public String getC12_NumberofItems() {
        return C12_NumberofItems;
    }

    public void setC12_NumberofItems(String c12_NumberofItems) {
        C12_NumberofItems = c12_NumberofItems;
    }

    public String getC13_SpareField() {
        return C13_SpareField;
    }

    public void setC13_SpareField(String c13_SpareField) {
        C13_SpareField = c13_SpareField;
    }

    public String getC14_RecipientBusinessName() {
        return C14_RecipientBusinessName;
    }

    public void setC14_RecipientBusinessName(String c14_RecipientBusinessName) {
        C14_RecipientBusinessName = c14_RecipientBusinessName;
    }

    public String getC15_DeliveryAddress1() {
        return C15_DeliveryAddress1;
    }

    public void setC15_DeliveryAddress1(String c15_DeliveryAddress1) {
        C15_DeliveryAddress1 = c15_DeliveryAddress1;
    }

    public String getC16_DeliveryAddress2() {
        return C16_DeliveryAddress2;
    }

    public void setC16_DeliveryAddress2(String c16_DeliveryAddress2) {
        C16_DeliveryAddress2 = c16_DeliveryAddress2;
    }

    public String getC17_DeliveryAddress3() {
        return C17_DeliveryAddress3;
    }

    public void setC17_DeliveryAddress3(String c17_DeliveryAddress3) {
        C17_DeliveryAddress3 = c17_DeliveryAddress3;
    }

    public String getC18_DeliveryPostTown() {
        return C18_DeliveryPostTown;
    }

    public void setC18_DeliveryPostTown(String c18_DeliveryPostTown) {
        C18_DeliveryPostTown = c18_DeliveryPostTown;
    }

    public String getC19_DeliveryPostcodeDPS() {
        return C19_DeliveryPostcodeDPS;
    }

    public void setC19_DeliveryPostcodeDPS(String c19_DeliveryPostcodeDPS) {
        C19_DeliveryPostcodeDPS = c19_DeliveryPostcodeDPS;
    }

    public String getC20_RecipientName() {
        return C20_RecipientName;
    }

    public void setC20_RecipientName(String c20_RecipientName) {
        C20_RecipientName = c20_RecipientName;
    }

    public String getC21_BuildingNumber() {
        return C21_BuildingNumber;
    }

    public void setC21_BuildingNumber(String c21_BuildingNumber) {
        C21_BuildingNumber = c21_BuildingNumber;
    }

    public String getC22_BuildingName() {
        return C22_BuildingName;
    }

    public void setC22_BuildingName(String c22_BuildingName) {
        C22_BuildingName = c22_BuildingName;
    }

    public String getC23_DeliveryAddress4() {
        return C23_DeliveryAddress4;
    }

    public void setC23_DeliveryAddress4(String c23_DeliveryAddress4) {
        C23_DeliveryAddress4 = c23_DeliveryAddress4;
    }

    public String getC24_DeliveryAddress5() {
        return C24_DeliveryAddress5;
    }

    public void setC24_DeliveryAddress5(String c24_DeliveryAddress5) {
        C24_DeliveryAddress5 = c24_DeliveryAddress5;
    }

    public String getC25_DeliveryCountry() {
        return C25_DeliveryCountry;
    }

    public void setC25_DeliveryCountry(String c25_DeliveryCountry) {
        C25_DeliveryCountry = c25_DeliveryCountry;
    }

    public String getC26_ZISOBillToDestinationCode() {
        return C26_ZISOBillToDestinationCode;
    }

    public void setC26_ZISOBillToDestinationCode(String c26_ZISOBillToDestinationCode) {
        C26_ZISOBillToDestinationCode = c26_ZISOBillToDestinationCode;
    }

    public String getC27_UniqueItemID() {
        return C27_UniqueItemID;
    }

    public void setC27_UniqueItemID(String c27_UniqueItemID) {
        C27_UniqueItemID = c27_UniqueItemID;
    }

    public String getC28_ItemWeight() {
        return C28_ItemWeight;
    }

    public void setC28_ItemWeight(String c28_ItemWeight) {
        C28_ItemWeight = c28_ItemWeight;
    }

    public String getC29_WeightType() {
        return C29_WeightType;
    }

    public void setC29_WeightType(String c29_WeightType) {
        C29_WeightType = c29_WeightType;
    }

    public String getC30_TypeofItem() {
        return C30_TypeofItem;
    }

    public void setC30_TypeofItem(String c30_TypeofItem) {
        C30_TypeofItem = c30_TypeofItem;
    }

    public String getC31_Format() {
        return C31_Format;
    }

    public void setC31_Format(String c31_Format) {
        C31_Format = c31_Format;
    }

    public String getC32_Diemensions() {
        return C32_Diemensions;
    }

    public void setC32_Diemensions(String c32_Diemensions) {
        C32_Diemensions = c32_Diemensions;
    }

    public String getC33_DiemensionsType() {
        return C33_DiemensionsType;
    }

    public void setC33_DiemensionsType(String c33_DiemensionsType) {
        C33_DiemensionsType = c33_DiemensionsType;
    }

    public String getC34_DeclaredValue() {
        return C34_DeclaredValue;
    }

    public void setC34_DeclaredValue(String c34_DeclaredValue) {
        C34_DeclaredValue = c34_DeclaredValue;
    }

    public String getC35_PricePaid() {
        return C35_PricePaid;
    }

    public void setC35_PricePaid(String c35_PricePaid) {
        C35_PricePaid = c35_PricePaid;
    }

    public String getC36_POLFADcode() {
        return C36_POLFADcode;
    }

    public void setC36_POLFADcode(String c36_POLFADcode) {
        C36_POLFADcode = c36_POLFADcode;
    }

    public String getC37_Dienumber() {
        return C37_Dienumber;
    }

    public void setC37_Dienumber(String c37_Dienumber) {
        C37_Dienumber = c37_Dienumber;
    }

    public String getC38_1DTrackingNumber() {
        return C38_1DTrackingNumber;
    }

    public void setC38_1DTrackingNumber(String c38_1DTrackingNumber) {
        C38_1DTrackingNumber = c38_1DTrackingNumber;
    }

    public String getC39_Class() {
        return C39_Class;
    }

    public void setC39_Class(String c39_Class) {
        C39_Class = c39_Class;
    }

    public String getC40_DateofProduction() {
        return C40_DateofProduction;
    }

    public void setC40_DateofProduction(String c40_DateofProduction) {
        C40_DateofProduction = c40_DateofProduction;
    }

    public String getC41_ProvisionalDate() {
        return C41_ProvisionalDate;
    }

    public void setC41_ProvisionalDate(String c41_ProvisionalDate) {
        C41_ProvisionalDate = c41_ProvisionalDate;
    }

    public String getC42_TariffRate() {
        return C42_TariffRate;
    }

    public void setC42_TariffRate(String c42_TariffRate) {
        C42_TariffRate = c42_TariffRate;
    }

    public String getC43_TariffVersion() {
        return C43_TariffVersion;
    }

    public void setC43_TariffVersion(String c43_TariffVersion) {
        C43_TariffVersion = c43_TariffVersion;
    }

    public String getC44_Sortcode() {
        return C44_Sortcode;
    }

    public void setC44_Sortcode(String c44_Sortcode) {
        C44_Sortcode = c44_Sortcode;
    }

    public String getC45_ContractCode() {
        return C45_ContractCode;
    }

    public void setC45_ContractCode(String c45_ContractCode) {
        C45_ContractCode = c45_ContractCode;
    }

    public String getC46_SenderReference2() {
        return C46_SenderReference2;
    }

    public void setC46_SenderReference2(String c46_SenderReference2) {
        C46_SenderReference2 = c46_SenderReference2;
    }

    public String getC47_ServiceEnhancement() {
        return C47_ServiceEnhancement;
    }

    public void setC47_ServiceEnhancement(String c47_ServiceEnhancement) {
        C47_ServiceEnhancement = c47_ServiceEnhancement;
    }

    public String getC48_RecipientContactNo() {
        return C48_RecipientContactNo;
    }

    public void setC48_RecipientContactNo(String c48_RecipientContactNo) {
        C48_RecipientContactNo = c48_RecipientContactNo;
    }

    public String getC49_ExpectedDateofDelivery() {
        return C49_ExpectedDateofDelivery;
    }

    public void setC49_ExpectedDateofDelivery(String c49_ExpectedDateofDelivery) {
        C49_ExpectedDateofDelivery = c49_ExpectedDateofDelivery;
    }
}
