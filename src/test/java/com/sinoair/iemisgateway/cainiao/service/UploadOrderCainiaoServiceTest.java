package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.util.BaseLogger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testUtil.CommonTestCaseTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-4-24
 * Time: 上午9:05
 * To change this template use File | Settings | File Templates.
 */
public class UploadOrderCainiaoServiceTest extends CommonTestCaseTemplate {
    UploadOrderCainiaoService uploadOrderCainiaoService;
    String xml;
    String mailNo = "LPwxx06176666007";
    String orderCode = "LPwxx06176666007";
    String packageCode = "YWES2015617wxx2";
    String packageWeight = "1232";
    String ordersInPackage = "1";
    String resultExpected;
    private String resultActual;


    //xml格式异常情况
//    @Test
    public void testUploadOrderCainiaoInvalidXml() throws Exception {
        xml = "<logisticsEventsRequest>\n";
        resultExpected = "<responses>\n" +
                "\t<responseItems>\n" +
                "\t\t<response>\n" +
                "\t\t\t<success>false</success>\n" +
                "\t\t\t<reason>S01</reason>\n" +
                "\t\t</response>\n" +
                "\t</responseItems>\n" +
                "</responses>";

        resultActual = uploadOrderCainiaoService.uploadOrderCainiao(xml);
        Assert.assertEquals(resultExpected, resultActual);
    }

    //正常情况
    @Test
    public void testUploadOrderCainiao() throws Exception {
        xml = "<logisticsEventsRequest>\n" +
                "\t<logisticsEvent>\n" +
                "\t\t<eventHeader>\n" +
                "\t\t\t<eventType>LOGISTICS_BATCH_SEND</eventType>\n" +
                "\t\t\t<eventTime>2015-05-19 19:09:33</eventTime>\n" +
                "\t\t\t<eventSource>taobao</eventSource>\n" +
                "\t\t\t<eventTarget>DISTRIBUTOR_903172</eventTarget>\n" +
                "\t\t</eventHeader>\n" +
                "\t\t<eventBody>\n" +
                "\t\t\t<OrderInfos>\n" +
                "\t\t\t\t<product>\n" +
                "\t\t\t\t\t<productNameCN>家具</productNameCN>\n" +
                "\t\t\t\t\t<productNameEN>furniture</productNameEN>\n" +
                "\t\t\t\t\t<productQantity>1</productQantity>\n" +
                "\t\t\t\t\t<productCateCN>家具</productCateCN>\n" +
                "\t\t\t\t\t<productCateEN>furniture</productCateEN>\n" +
                "\t\t\t\t\t<productId />\n" +
                "\t\t\t\t\t<producingArea />\n" +
                "\t\t\t\t\t<productWeight>1900</productWeight>\n" +
                "\t\t\t\t\t<productPrice>2000</productPrice>\n" +
                "\t\t\t\t</product>\n" +
                "\t\t\t</OrderInfos>\n" +
                "\t\t\t<ecCompanyId>taobao</ecCompanyId>\n" +
                "\t\t\t<whCode>Tran_Store_904817</whCode>\n" +
                "\t\t\t<logisticsOrderId>35917668244</logisticsOrderId>\n" +
                "\t\t\t<tradeId>3018196085</tradeId>\n" +
                "\t\t\t<mailNo>" + mailNo + "</mailNo>\n" +
                "\t\t\t<Rcountry>ES</Rcountry>\n" +
                "\t\t\t<Rprovince>Barcelona</Rprovince>\n" +
                "\t\t\t<Rcity>Alcala de Henares</Rcity>\n" +
                "\t\t\t<Remail>rhh07@sina.cn</Remail>\n" +
                "\t\t\t<Raddress>Avenida Paralelo 15, 2</Raddress>\n" +
                "\t\t\t<Rpostcode>08004</Rpostcode>\n" +
                "\t\t\t<Rname>Marco a Garcia</Rname>\n" +
                "\t\t\t<Rphone>934439830</Rphone>\n" +
                "\t\t\t<Sname>tee2</Sname>\n" +
                "\t\t\t<SwangwangId>aliqatest01</SwangwangId>\n" +
                "\t\t\t<Sprovince>ZheJiang</Sprovince>\n" +
                "\t\t\t<Scity>HangZhou</Scity>\n" +
                "\t\t\t<Saddress>shangtang</Saddress>\n" +
                "\t\t\t<Sphone>28433934013</Sphone>\n" +
                "\t\t\t<Spostcode>941814</Spostcode>\n" +
                "\t\t\t<channel>HK</channel>\n" +
                "\t\t\t<Itotleweight>2168</Itotleweight>\n" +
                "\t\t\t<Itotlevalue>2000</Itotlevalue>\n" +
                "\t\t\t<totleweight>2168</totleweight>\n" +
                "\t\t\t<country>CN</country>\n" +
                "\t\t\t<mailKind>1</mailKind>\n" +
                "\t\t\t<mailClass>L</mailClass>\n" +
                "\t\t\t<batchNo>LP00035917668244</batchNo>\n" +
                "\t\t\t<mailType>SUMAITONG</mailType>\n" +
                "\t\t\t<faceType>2</faceType>\n" +
                "\t\t\t<undeliveryOption>2</undeliveryOption>\n" +
                "\t\t\t<hasBattery>false</hasBattery>\n" +
                "\t\t\t<pickUpAddress>中国浙江省杭州市江干区东港家苑三区底商5-6号花园兜街</pickUpAddress>\n" +
                "\t\t\t<packageCode>" + packageCode + "</packageCode>\n" +
                "\t\t\t<orderCode>" + orderCode + "</orderCode>\n" +
                "\t\t\t<packageWeight>" + packageWeight + "</packageWeight>\n" +
                "\t\t\t<ordersInPackage>" + ordersInPackage + "</ordersInPackage>\n" +
                "\t\t</eventBody>\n" +
                "\t</logisticsEvent>\n" +
                "</logisticsEventsRequest>";
        resultExpected = "<responses>\n" +
                "\t<responseItems>\n" +
                "\t\t<response>\n" +
                "\t\t\t<success>true</success>\n" +
                "\t\t</response>\n" +
                "\t</responseItems>\n" +
                "</responses>";
        BaseLogger.debug("xml = " + xml);
        BaseLogger.info("xml shorten" + xml.replace("\n", "").replace("\t", ""));
        resultActual = uploadOrderCainiaoService.uploadOrderCainiao(xml);
        Assert.assertEquals(resultExpected, resultActual);
        //todo 待用connection取到iemis的的插入数据
    }

    @Before
    public void setUp() throws Exception {
        showEnvironment();
        uploadOrderCainiaoService = new UploadOrderCainiaoService();

    }

    @After
    public void tearDown() throws Exception {

    }
}
