package com.sinoair.iemisgateway.correos.domain;

import com.sinoair.iemisgateway.util.StringUtil;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-10
 * Time: 下午6:54
 * To change this template use File | Settings | File Templates.
 */
public class CorreosItem {

    static final double RATE=0.95;

    private String C1_Position	="";
    private String C2_Yearofcreationofstructureandversion	="2014v001";
    private String C3_Productcode	="S0132";
    private String C4_FrankingType	="FP";
    private String C5_LabellerCode	="48K2";
    private String C6_Contractnumber	="54016535";
    private String C7_Clientnumber	="80734693";
    private String C8_Frankingmachinenumber	="";
    private String C9_Amountfranked	="";
    private String C10_Shipmentcode	="";
    private String C11_Consignmentnumber	="";
    private String C12_Consignmentreference	="";
    private String C13_Authorisationforpartialconsignmentdeliveries	="";
    private String C14_Totalpackages	="01";
    private String C15_Packagenumber	="01";
    private String C16_Manifestnumber	="MD"+C5_LabellerCode+"04";
    private String C17_Promotioncode	="";
    private String C18_Reasonforresending	="";
    private String C19_Name	="";
    private String C20_Surname1	="";
    private String C21_Surname2	="";
    private String C22_TaxpayerIdentificationNumber	="";
    private String C23_Company	="";
    private String C24_Contactperson	="";
    private String C25_Streettype	="";
    private String C26_Streetname	="";
    private String C27_Number	="";
    private String C28_Maindoor	="";
    private String C29_Block	="";
    private String C30_Stairway	="";
    private String C31_Floor	="";
    private String C32_Door	="";
    private String C33_Town	="";
    private String C34_Province	="";
    private String C35_PC	="";
    private String C36_ZIP	="";
    private String C37_Country	="";
    private String C38_Chosenoffice	="";
    private String C39_DestinationinternationalPOBOX	="";
    private String C40_DestinationPOBOX	="";
    private String C41_Addresseecontacttelephone	="";
    private String C42_Email	="";
    private String C43_CustomerReference	="";
    private String C44_Deliverymode	="ST";
    private String C45_Weight	="";
    private String C46_Length	="";
    private String C47_Height	="";
    private String C48_Width	="";
    private String C49_Insurance	="S";
    private String C50_InsuredValue	="";
    private String C51_Cashondelivery	="";
    private String C52_Cashondeliveryamount	="";
    private String C53_Typeofcashondelivery	="";
    private String C54_Accountnumber	="";
    private String C55_Deliverytoaddresseeonly	="";
    private String C56_Proofofdeliveryformat	="";
    private String C57_e_AR_PEEreference	="";
    private String C58_Senderinformationfore_AR_PEE	="";
    private String C59_Deliverywithcollectionindicator	="";
    private String C60_Shipmentdescriptiontocollect	="";
    private String C61_Printlabelcollection	="";
    private String C62_PriorDelivery	="";
    private String C63_Generatereturndelivery	="";
    private String C64_Returndeliverycode	="";
    private String C65_Returndeliveryexpirydate	="";
    private String C66_Returndeliveryallowspackaging	="";
    private String C67_Returndeliverycode	="";
    private String C68_AddresseeSMSNumber	="";
    private String C69_SenderSMSNumber	="";
    private String C70_SMSlanguage	="";
    private String C71_SMSlanguage	="";
    private String C72_Homecollection	="";
    private String C73_Deliverynotereturn	="";
    private String C74_Saturdaydelivery	="";
    private String C75_Day_definitedelivery	="";
    private String C76_Time_slotdelivery	="";
    private String C77_Prepaidpackaging	="";
    private String C78_Prepaidpackagingcode	="";
    private String C79_CodeofpointofadmissionorProvinceofadmission	="";
    private String C80_Promotionalslogan	="";
    private String C81_Envisageddepositdate	="";
    private String C82_Observations1	="";
    private String C83_Observations2	="";
    private String C84_Returninstructionsintheeventofnondeliveryforinternationalparcels	="";
    private String C85_Shipmenttype	="2";
    private String C86_Commercialshipment	="";
    private String C87_Invoiceinexcessof500euros	="";
    private String C88_CustomsDeclarationFormforexportwithCorreos	="";
    private String C89_Amount	="";
    private String C90_Descriptionofgoods	="";
    private String C91_Netweight	="";
    private String C92_Netvalue	="";
    private String C93_Pricingnumber	="";
    private String C94_Countryoforigin	="";
    private String C95_Amount	="";
    private String C96_Descriptionofgoods	="";
    private String C97_Netweight	="";
    private String C98_Netvalue	="";
    private String C99_Pricingnumber	="";
    private String C100_Countryoforigin	="";
    private String C101_Amount	="";
    private String C102_Descriptionofgoods	="";
    private String C103_Netweight	="";
    private String C104_Netvalue	="";
    private String C105_Pricingnumber	="";
    private String C106_Countryoforigin	="";
    private String C107_Invoiceattached	="";
    private String C108_Licenseattached	="";
    private String C109_Certificateattached	="";
    private String C110_Name	="";
    private String C111_Surname1	="";
    private String C112_Surname2	="";
    private String C113_TaxpayerIdentificationNumber	="";
    private String C114_Company	="Sinotrans-Correos";
    private String C115_Contactperson	="";
    private String C116_Streettype	="";
    private String C117_Streetname	="CAM 2 Ctra de Villaverde a Vallecas Km. 3,5";
    private String C118_Number	="";
    private String C119_Maindoor	="";
    private String C120_Block	="";
    private String C121_Stairway	="";
    private String C122_Floor	="";
    private String C123_Door	="";
    private String C124_Town	="Madrid";
    private String C125_Province	="";
    private String C126_PC	="28070";
    private String C127_Sendertelephonenumber	="";
    private String C128_Email	="";
    private String C129_POBOXforcashondelivery	="";
    private String C130_PostalofficecodewherepostalordenfronCODwillbepaid	="";
    private String C131_Endofregistration	="E";


    public CorreosItem(){}
    public void  CorreosItem(HashMap<String, Object> mapParam,String timeString,int txtNum){
        this.copyProperties(mapParam,timeString,txtNum);
    }
    public void copyProperties(HashMap<String, Object> mapParam,String timeString,int txtNum){
        if(mapParam!=null){
          this.C10_Shipmentcode= StringUtil.getStringSpace(mapParam.get("EAWB_REFERENCE1").toString(),23,0);
          this.C11_Consignmentnumber=getShipmentCode(this.C10_Shipmentcode.substring(0, 15), 23);
          this.C16_Manifestnumber=this.C16_Manifestnumber+timeString+StringUtil.getIntFormString(2,txtNum);
          this.C19_Name=StringUtil.getStringSpace(mapParam.get("EAWB_CONSIGNEE_ACCOUNTNAME").toString(),50,0);
          this.C26_Streetname=StringUtil.getStringSpace(mapParam.get("EAWB_DELIVER_ADDRESS").toString(),50,0);
          this.C33_Town=StringUtil.getStringSpace(mapParam.get("EAWB_DESTCITY").toString(),50,0);
          this.C34_Province=StringUtil.getStringSpace(mapParam.get("EAWB_DESTSTATE").toString(),50,0);
          this.C35_PC=StringUtil.getFixedStringSpace(mapParam.get("EAWB_DELIVER_POSTCODE").toString(),5);
          this.C41_Addresseecontacttelephone=StringUtil.getPhoneNum(mapParam.get("EAWB_DELIVER_PHONE"),12);
          this.C45_Weight=StringUtil.getStringSpace(convertWeightGram(mapParam.get("EAWB_DECLAREGROSSWEIGHT").toString()),5,0);
          this.C50_InsuredValue=StringUtil.getIntFormString(6,convertInsureValue(mapParam.get("EAWB_DECLAREVALUE").toString()));
          this.C110_Name=StringUtil.getStringSpace(mapParam.get("EAWB_SHIPPER_ACCOUNTNAME").toString(),50,0);
        }

    }
    private String convertWeightGram(String weightKg){
        String weightGram="0";
        if(weightKg!=null && !"".equals(weightKg)){
          try{
           Double weight = Double.parseDouble(weightKg)*1000; //单位是kg ,要转换成g
           java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#");
           weightGram=decimalFormat.format(weight);
          }catch (Exception e){
              e.printStackTrace();
              weightGram="0";
          }
        }
        return weightGram;
    }
    private int convertInsureValue(String value){
      int money=60;
      if(value!=null && !"".equals(value)){
          try{
           money = (int) Math.rint(Double.parseDouble(value) * RATE * 100);
            if(money<60){
              money=60;
            }
          }catch (Exception e){
              e.printStackTrace();
               money=60;
          }
      }

      return money;
    }
    /**
     * 根据mailNo截取获得ShipmentCode
     *
     * @param mailNo
     * @param divisor
     * @return
     */
    private String getShipmentCode(String mailNo, int divisor) {
        String aa = mailNo;
        char[] cs = aa.toCharArray();
        int a = 0;
        for (int i : cs) {
            a += i;
        }
        ;
        int remainder = a % (divisor);
        return aa + getBarcode(remainder);
    }

    /**
     * 获取 ShipmentCode 最后一位生成规则
     *
     * @param remainder
     * @return
     */
    private String getBarcode(int remainder) {
        String code = "";
        if (0 == remainder) {
            code = "T";
        } else if (1 == remainder) {
            code = "R";
        } else if (2 == remainder) {
            code = "W";
        } else if (3 == remainder) {
            code = "A";
        } else if (4 == remainder) {
            code = "G";
        } else if (5 == remainder) {
            code = "M";
        } else if (6 == remainder) {
            code = "Y";
        } else if (7 == remainder) {
            code = "F";
        } else if (8 == remainder) {
            code = "P";
        } else if (9 == remainder) {
            code = "D";
        } else if (10 == remainder) {
            code = "X";
        } else if (11 == remainder) {
            code = "B";
        } else if (12 == remainder) {
            code = "N";
        } else if (13 == remainder) {
            code = "J";
        } else if (14 == remainder) {
            code = "Z";
        } else if (15 == remainder) {
            code = "S";
        } else if (16 == remainder) {
            code = "Q";
        } else if (17 == remainder) {
            code = "V";
        } else if (18 == remainder) {
            code = "H";
        } else if (19 == remainder) {
            code = "L";
        } else if (20 == remainder) {
            code = "C";
        } else if (21 == remainder) {
            code = "K";
        } else if (22 == remainder) {
            code = "E";
        }
        return code;

    }

    public String getC1_Position() {
        return C1_Position;
    }

    public void setC1_Position(String c1_Position) {
        C1_Position = c1_Position;
    }

    public String getC10_Shipmentcode() {
        return C10_Shipmentcode;
    }

    public void setC10_Shipmentcode(String c10_Shipmentcode) {
        C10_Shipmentcode = c10_Shipmentcode;
    }

    public String getC19_Name() {
        return C19_Name;
    }

    public void setC19_Name(String c19_Name) {
        C19_Name = c19_Name;
    }

    public String getC26_Streetname() {
        return C26_Streetname;
    }

    public void setC26_Streetname(String c26_Streetname) {
        C26_Streetname = c26_Streetname;
    }

    public String getC33_Town() {
        return C33_Town;
    }

    public void setC33_Town(String c33_Town) {
        C33_Town = c33_Town;
    }

    public String getC34_Province() {
        return C34_Province;
    }

    public void setC34_Province(String c34_Province) {
        C34_Province = c34_Province;
    }

    public String getC35_PC() {
        return C35_PC;
    }

    public void setC35_PC(String c35_PC) {
        C35_PC = c35_PC;
    }

    public String getC41_Addresseecontacttelephone() {
        return C41_Addresseecontacttelephone;
    }

    public void setC41_Addresseecontacttelephone(String c41_Addresseecontacttelephone) {
        C41_Addresseecontacttelephone = c41_Addresseecontacttelephone;
    }

    public String getC45_Weight() {
        return C45_Weight;
    }

    public void setC45_Weight(String c45_Weight) {
        C45_Weight = c45_Weight;
    }

    public String getC50_InsuredValue() {
        return C50_InsuredValue;
    }

    public void setC50_InsuredValue(String c50_InsuredValue) {
        C50_InsuredValue = c50_InsuredValue;
    }

    public String getC110_Name() {
        return C110_Name;
    }

    public void setC110_Name(String c110_Name) {
        C110_Name = c110_Name;
    }

    public String getC2_Yearofcreationofstructureandversion() {
        return C2_Yearofcreationofstructureandversion;
    }

    public String getC3_Productcode() {
        return C3_Productcode;
    }

    public String getC4_FrankingType() {
        return C4_FrankingType;
    }

    public String getC5_LabellerCode() {
        return C5_LabellerCode;
    }

    public String getC6_Contractnumber() {
        return C6_Contractnumber;
    }

    public String getC7_Clientnumber() {
        return C7_Clientnumber;
    }

    public String getC8_Frankingmachinenumber() {
        return C8_Frankingmachinenumber;
    }

    public String getC9_Amountfranked() {
        return C9_Amountfranked;
    }

    public String getC11_Consignmentnumber() {
        return C11_Consignmentnumber;
    }

    public String getC12_Consignmentreference() {
        return C12_Consignmentreference;
    }

    public String getC13_Authorisationforpartialconsignmentdeliveries() {
        return C13_Authorisationforpartialconsignmentdeliveries;
    }

    public String getC14_Totalpackages() {
        return C14_Totalpackages;
    }

    public String getC15_Packagenumber() {
        return C15_Packagenumber;
    }

    public String getC16_Manifestnumber() {
        return C16_Manifestnumber;
    }

    public String getC17_Promotioncode() {
        return C17_Promotioncode;
    }

    public String getC18_Reasonforresending() {
        return C18_Reasonforresending;
    }

    public String getC20_Surname1() {
        return C20_Surname1;
    }

    public String getC21_Surname2() {
        return C21_Surname2;
    }

    public String getC22_TaxpayerIdentificationNumber() {
        return C22_TaxpayerIdentificationNumber;
    }

    public String getC23_Company() {
        return C23_Company;
    }

    public String getC24_Contactperson() {
        return C24_Contactperson;
    }

    public String getC25_Streettype() {
        return C25_Streettype;
    }

    public String getC27_Number() {
        return C27_Number;
    }

    public String getC28_Maindoor() {
        return C28_Maindoor;
    }

    public String getC29_Block() {
        return C29_Block;
    }

    public String getC30_Stairway() {
        return C30_Stairway;
    }

    public String getC31_Floor() {
        return C31_Floor;
    }

    public String getC32_Door() {
        return C32_Door;
    }

    public String getC36_ZIP() {
        return C36_ZIP;
    }

    public String getC37_Country() {
        return C37_Country;
    }

    public String getC38_Chosenoffice() {
        return C38_Chosenoffice;
    }

    public String getC39_DestinationinternationalPOBOX() {
        return C39_DestinationinternationalPOBOX;
    }

    public String getC40_DestinationPOBOX() {
        return C40_DestinationPOBOX;
    }

    public String getC42_Email() {
        return C42_Email;
    }

    public String getC43_CustomerReference() {
        return C43_CustomerReference;
    }

    public String getC44_Deliverymode() {
        return C44_Deliverymode;
    }

    public String getC46_Length() {
        return C46_Length;
    }

    public String getC47_Height() {
        return C47_Height;
    }

    public String getC48_Width() {
        return C48_Width;
    }

    public String getC49_Insurance() {
        return C49_Insurance;
    }

    public String getC51_Cashondelivery() {
        return C51_Cashondelivery;
    }

    public String getC52_Cashondeliveryamount() {
        return C52_Cashondeliveryamount;
    }

    public String getC53_Typeofcashondelivery() {
        return C53_Typeofcashondelivery;
    }

    public String getC54_Accountnumber() {
        return C54_Accountnumber;
    }

    public String getC55_Deliverytoaddresseeonly() {
        return C55_Deliverytoaddresseeonly;
    }

    public String getC56_Proofofdeliveryformat() {
        return C56_Proofofdeliveryformat;
    }

    public String getC57_e_AR_PEEreference() {
        return C57_e_AR_PEEreference;
    }

    public String getC58_Senderinformationfore_AR_PEE() {
        return C58_Senderinformationfore_AR_PEE;
    }

    public String getC59_Deliverywithcollectionindicator() {
        return C59_Deliverywithcollectionindicator;
    }

    public String getC60_Shipmentdescriptiontocollect() {
        return C60_Shipmentdescriptiontocollect;
    }

    public String getC61_Printlabelcollection() {
        return C61_Printlabelcollection;
    }

    public String getC62_PriorDelivery() {
        return C62_PriorDelivery;
    }

    public String getC63_Generatereturndelivery() {
        return C63_Generatereturndelivery;
    }

    public String getC64_Returndeliverycode() {
        return C64_Returndeliverycode;
    }

    public String getC65_Returndeliveryexpirydate() {
        return C65_Returndeliveryexpirydate;
    }

    public String getC66_Returndeliveryallowspackaging() {
        return C66_Returndeliveryallowspackaging;
    }

    public String getC67_Returndeliverycode() {
        return C67_Returndeliverycode;
    }

    public String getC68_AddresseeSMSNumber() {
        return C68_AddresseeSMSNumber;
    }

    public String getC69_SenderSMSNumber() {
        return C69_SenderSMSNumber;
    }

    public String getC70_SMSlanguage() {
        return C70_SMSlanguage;
    }

    public String getC71_SMSlanguage() {
        return C71_SMSlanguage;
    }

    public String getC72_Homecollection() {
        return C72_Homecollection;
    }

    public String getC73_Deliverynotereturn() {
        return C73_Deliverynotereturn;
    }

    public String getC74_Saturdaydelivery() {
        return C74_Saturdaydelivery;
    }

    public String getC75_Day_definitedelivery() {
        return C75_Day_definitedelivery;
    }

    public String getC76_Time_slotdelivery() {
        return C76_Time_slotdelivery;
    }

    public String getC77_Prepaidpackaging() {
        return C77_Prepaidpackaging;
    }

    public String getC78_Prepaidpackagingcode() {
        return C78_Prepaidpackagingcode;
    }

    public String getC79_CodeofpointofadmissionorProvinceofadmission() {
        return C79_CodeofpointofadmissionorProvinceofadmission;
    }

    public String getC80_Promotionalslogan() {
        return C80_Promotionalslogan;
    }

    public String getC81_Envisageddepositdate() {
        return C81_Envisageddepositdate;
    }

    public String getC82_Observations1() {
        return C82_Observations1;
    }

    public String getC83_Observations2() {
        return C83_Observations2;
    }

    public String getC84_Returninstructionsintheeventofnondeliveryforinternationalparcels() {
        return C84_Returninstructionsintheeventofnondeliveryforinternationalparcels;
    }

    public String getC85_Shipmenttype() {
        return C85_Shipmenttype;
    }

    public String getC86_Commercialshipment() {
        return C86_Commercialshipment;
    }

    public String getC87_Invoiceinexcessof500euros() {
        return C87_Invoiceinexcessof500euros;
    }

    public String getC88_CustomsDeclarationFormforexportwithCorreos() {
        return C88_CustomsDeclarationFormforexportwithCorreos;
    }

    public String getC89_Amount() {
        return C89_Amount;
    }

    public String getC90_Descriptionofgoods() {
        return C90_Descriptionofgoods;
    }

    public String getC91_Netweight() {
        return C91_Netweight;
    }

    public String getC92_Netvalue() {
        return C92_Netvalue;
    }

    public String getC93_Pricingnumber() {
        return C93_Pricingnumber;
    }

    public String getC94_Countryoforigin() {
        return C94_Countryoforigin;
    }

    public String getC95_Amount() {
        return C95_Amount;
    }

    public String getC96_Descriptionofgoods() {
        return C96_Descriptionofgoods;
    }

    public String getC97_Netweight() {
        return C97_Netweight;
    }

    public String getC98_Netvalue() {
        return C98_Netvalue;
    }

    public String getC99_Pricingnumber() {
        return C99_Pricingnumber;
    }

    public String getC100_Countryoforigin() {
        return C100_Countryoforigin;
    }

    public String getC101_Amount() {
        return C101_Amount;
    }

    public String getC102_Descriptionofgoods() {
        return C102_Descriptionofgoods;
    }

    public String getC103_Netweight() {
        return C103_Netweight;
    }

    public String getC104_Netvalue() {
        return C104_Netvalue;
    }

    public String getC105_Pricingnumber() {
        return C105_Pricingnumber;
    }

    public String getC106_Countryoforigin() {
        return C106_Countryoforigin;
    }

    public String getC107_Invoiceattached() {
        return C107_Invoiceattached;
    }

    public String getC108_Licenseattached() {
        return C108_Licenseattached;
    }

    public String getC109_Certificateattached() {
        return C109_Certificateattached;
    }

    public String getC111_Surname1() {
        return C111_Surname1;
    }

    public String getC112_Surname2() {
        return C112_Surname2;
    }

    public String getC113_TaxpayerIdentificationNumber() {
        return C113_TaxpayerIdentificationNumber;
    }

    public String getC114_Company() {
        return C114_Company;
    }

    public String getC115_Contactperson() {
        return C115_Contactperson;
    }

    public String getC116_Streettype() {
        return C116_Streettype;
    }

    public String getC117_Streetname() {
        return C117_Streetname;
    }

    public String getC118_Number() {
        return C118_Number;
    }

    public String getC119_Maindoor() {
        return C119_Maindoor;
    }

    public String getC120_Block() {
        return C120_Block;
    }

    public String getC121_Stairway() {
        return C121_Stairway;
    }

    public String getC122_Floor() {
        return C122_Floor;
    }

    public String getC123_Door() {
        return C123_Door;
    }

    public String getC124_Town() {
        return C124_Town;
    }

    public String getC125_Province() {
        return C125_Province;
    }

    public String getC126_PC() {
        return C126_PC;
    }

    public String getC127_Sendertelephonenumber() {
        return C127_Sendertelephonenumber;
    }

    public String getC128_Email() {
        return C128_Email;
    }

    public String getC129_POBOXforcashondelivery() {
        return C129_POBOXforcashondelivery;
    }

    public String getC130_PostalofficecodewherepostalordenfronCODwillbepaid() {
        return C130_PostalofficecodewherepostalordenfronCODwillbepaid;
    }

    public String getC131_Endofregistration() {
        return C131_Endofregistration;
    }
}

