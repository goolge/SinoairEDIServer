package com.sinoair.iemisgateway.correos.domain;

import com.sinoair.iemisgateway.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-10
 * Time: 下午9:19
 * To change this template use File | Settings | File Templates.
 */
public class CorreosManifest {
    private List<CorreosItem> correosItemList = new ArrayList<CorreosItem>();

    public String getMessageContent() {
        StringBuffer sb = new StringBuffer();
        if (correosItemList != null && correosItemList.size() > 0) {
            for (int i = 0; i < correosItemList.size(); i++) {
                CorreosItem correosItem = correosItemList.get(i);
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC1_Position()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC2_Yearofcreationofstructureandversion()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC3_Productcode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC4_FrankingType()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC5_LabellerCode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC6_Contractnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC7_Clientnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC8_Frankingmachinenumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC9_Amountfranked()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC10_Shipmentcode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC11_Consignmentnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC12_Consignmentreference()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC13_Authorisationforpartialconsignmentdeliveries()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC14_Totalpackages()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC15_Packagenumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC16_Manifestnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC17_Promotioncode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC18_Reasonforresending()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC19_Name()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC20_Surname1()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC21_Surname2()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC22_TaxpayerIdentificationNumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC23_Company()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC24_Contactperson()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC25_Streettype()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC26_Streetname()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC27_Number()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC28_Maindoor()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC29_Block()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC30_Stairway()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC31_Floor()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC32_Door()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC33_Town()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC34_Province()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC35_PC()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC36_ZIP()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC37_Country()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC38_Chosenoffice()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC39_DestinationinternationalPOBOX()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC40_DestinationPOBOX()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC41_Addresseecontacttelephone()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC42_Email()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC43_CustomerReference()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC44_Deliverymode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC45_Weight()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC46_Length()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC47_Height()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC48_Width()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC49_Insurance()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC50_InsuredValue()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC51_Cashondelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC52_Cashondeliveryamount()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC53_Typeofcashondelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC54_Accountnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC55_Deliverytoaddresseeonly()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC56_Proofofdeliveryformat()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC57_e_AR_PEEreference()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC58_Senderinformationfore_AR_PEE()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC59_Deliverywithcollectionindicator()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC60_Shipmentdescriptiontocollect()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC61_Printlabelcollection()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC62_PriorDelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC63_Generatereturndelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC64_Returndeliverycode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC65_Returndeliveryexpirydate()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC66_Returndeliveryallowspackaging()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC67_Returndeliverycode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC68_AddresseeSMSNumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC69_SenderSMSNumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC70_SMSlanguage()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC71_SMSlanguage()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC72_Homecollection()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC73_Deliverynotereturn()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC74_Saturdaydelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC75_Day_definitedelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC76_Time_slotdelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC77_Prepaidpackaging()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC78_Prepaidpackagingcode()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC79_CodeofpointofadmissionorProvinceofadmission()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC80_Promotionalslogan()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC81_Envisageddepositdate()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC82_Observations1()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC83_Observations2()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC84_Returninstructionsintheeventofnondeliveryforinternationalparcels()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC85_Shipmenttype()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC86_Commercialshipment()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC87_Invoiceinexcessof500euros()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC88_CustomsDeclarationFormforexportwithCorreos()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC89_Amount()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC90_Descriptionofgoods()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC91_Netweight()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC92_Netvalue()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC93_Pricingnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC94_Countryoforigin()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC95_Amount()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC96_Descriptionofgoods()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC97_Netweight()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC98_Netvalue()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC99_Pricingnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC100_Countryoforigin()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC101_Amount()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC102_Descriptionofgoods()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC103_Netweight()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC104_Netvalue()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC105_Pricingnumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC106_Countryoforigin()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC107_Invoiceattached()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC108_Licenseattached()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC109_Certificateattached()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC110_Name()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC111_Surname1()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC112_Surname2()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC113_TaxpayerIdentificationNumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC114_Company()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC115_Contactperson()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC116_Streettype()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC117_Streetname()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC118_Number()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC119_Maindoor()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC120_Block()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC121_Stairway()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC122_Floor()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC123_Door()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC124_Town()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC125_Province()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC126_PC()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC127_Sendertelephonenumber()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC128_Email()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC129_POBOXforcashondelivery()));
                sb.append(StringUtil.nullAndTabProcess(correosItem.getC130_PostalofficecodewherepostalordenfronCODwillbepaid()));
                sb.append(correosItem.getC131_Endofregistration());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public List<CorreosItem> getCorreosItemList() {
        return correosItemList;
    }

    public void setCorreosItemList(List<CorreosItem> correosItemList) {
        this.correosItemList = correosItemList;
    }
}
