package com.sinoair.iemisgateway.util.iemisWSClient.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by WangXX4 on 2015/7/8.
 */
public class CommonClientTest {
    CommonClient commonClient;
    String className;
    String xml;
    @Before
    public void setUp() throws Exception {
        commonClient = new CommonClient();
        className = "wxx";
        xml = "<logisticsEventsRequest><logisticsEvent><eventHeader><eventType>LOGISTICS_BATCH_SEND</eventType><eventTime>2015-05-19 19:09:33</eventTime><eventSource>taobao</eventSource><eventTarget>DISTRIBUTOR_903172</eventTarget></eventHeader><eventBody><OrderInfos><product><productNameCN></productNameCN><productNameEN>furniture</productNameEN><productQantity>1</productQantity><productCateCN></productCateCN><productCateEN>furniture</productCateEN><productId /><producingArea /><productWeight>1900</productWeight><productPrice>2000</productPrice></product></OrderInfos><ecCompanyId>taobao</ecCompanyId><whCode>Tran_Store_904817</whCode><logisticsOrderId>35917668244</logisticsOrderId><tradeId>3018196085</tradeId><mailNo>LPwxx0714001</mailNo><Rcountry>ES</Rcountry><Rprovince>Barcelona</Rprovince><Rcity>Alcala de Henares</Rcity><Remail>rhh07@sina.cn</Remail><Raddress>Avenida Paralelo 15, 2</Raddress><Rpostcode>08004</Rpostcode><Rname>Marco a Garcia</Rname><Rphone>934439830</Rphone><Sname>tee2</Sname><SwangwangId>aliqatest01</SwangwangId><Sprovince>ZheJiang</Sprovince><Scity>HangZhou</Scity><Saddress>shangtang</Saddress><Sphone>28433934013</Sphone><Spostcode>941814</Spostcode><channel>HK</channel><Itotleweight>2168</Itotleweight><Itotlevalue>2000</Itotlevalue><totleweight>2168</totleweight><country>CN</country><mailKind>1</mailKind><mailClass>L</mailClass><batchNo>LP00035917668244</batchNo><mailType>SUMAITONG</mailType><faceType>2</faceType><undeliveryOption>2</undeliveryOption><hasBattery>false</hasBattery><pickUpAddress></pickUpAddress><packageCode>YWES20150724001</packageCode><orderCode>LPwxx0623001</orderCode><packageWeight>1232</packageWeight><ordersInPackage>1</ordersInPackage></eventBody></logisticsEvent></logisticsEventsRequest>\n";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExecuteError() throws Exception {
        className = "wxx";
        xml = "something";

        String resultExcepted = "error";
        String resultActual = commonClient.execute(className, xml);
        System.out.println("resultExcepted = " + resultExcepted);
        System.out.println("resultActual = " + resultActual);
        Assert.assertEquals(resultExcepted, resultActual);
    }
    @Test
      public void testExecute() throws Exception {
        className = "PreUploadOrderCainiao";
        xml = "<logisticsEventsRequest><logisticsEvent><eventHeader><eventType>LOGISTICS_BATCH_SEND</eventType><eventTime>2015-05-19 19:09:33</eventTime><eventSource>taobao</eventSource><eventTarget>DISTRIBUTOR_903172</eventTarget></eventHeader><eventBody><OrderInfos><product><productNameCN></productNameCN><productNameEN>furniture</productNameEN><productQantity>1</productQantity><productCateCN></productCateCN><productCateEN>furniture</productCateEN><productId /><producingArea /><productWeight>1900</productWeight><productPrice>2000</productPrice></product></OrderInfos><ecCompanyId>taobao</ecCompanyId><whCode>Tran_Store_904817</whCode><logisticsOrderId>35917668244</logisticsOrderId><tradeId>3018196085</tradeId><mailNo>LPwxx0714001</mailNo><Rcountry>ES</Rcountry><Rprovince>Barcelona</Rprovince><Rcity>Alcala de Henares</Rcity><Remail>rhh07@sina.cn</Remail><Raddress>Avenida Paralelo 15, 2</Raddress><Rpostcode>08004</Rpostcode><Rname>Marco a Garcia</Rname><Rphone>934439830</Rphone><Sname>tee2</Sname><SwangwangId>aliqatest01</SwangwangId><Sprovince>ZheJiang</Sprovince><Scity>HangZhou</Scity><Saddress>shangtang</Saddress><Sphone>28433934013</Sphone><Spostcode>941814</Spostcode><channel>HK</channel><Itotleweight>2168</Itotleweight><Itotlevalue>2000</Itotlevalue><totleweight>2168</totleweight><country>CN</country><mailKind>1</mailKind><mailClass>L</mailClass><batchNo>LP00035917668244</batchNo><mailType>SUMAITONG</mailType><faceType>2</faceType><undeliveryOption>2</undeliveryOption><hasBattery>false</hasBattery><pickUpAddress></pickUpAddress><packageCode>YWES20150724001</packageCode><orderCode>LPwxx0623001</orderCode><packageWeight>1232</packageWeight><ordersInPackage>1</ordersInPackage></eventBody></logisticsEvent></logisticsEventsRequest>\n";
        String resultExcepted = "<responses>\n" +
                "\t<responseItems>\n" +
                "\t\t<response>\n" +
                "\t\t\t<success>true</success>\n" +
                "\t\t</response>\n" +
                "\t</responseItems>\n" +
                "</responses>";
        String resultActual = commonClient.execute(className, xml);
        System.out.println("resultExcepted = " + resultExcepted);
        System.out.println("resultActual = " + resultActual);
        Assert.assertEquals(resultExcepted, resultActual);
    }
}