package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.cainiao.domain.TraceRequest2Cainiao;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-14
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class PushTrace2CainiaoServiceTest {
    PushTrace2CainiaoService PushTrace2CainiaoService = null;

    @Before
    public void setUp() throws Exception {
        PushTrace2CainiaoService = new PushTrace2CainiaoService();
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * 回传物流公司签收CAI_AIR_DELIVERY
     *
     * @throws Exception
     */
    @Test
    public void testPushTrace2Cainiao() throws Exception {
        String logistic_provider_id = "DISTRIBUTOR_902950";
        String mailNos = "RA100001009FI";
        String txLogisticID = "LP00012015280275";
        String time = "2014-12-06 18:00:00";
        String desc = "wxx test";
        String city = "ShenZhen";
        String facilityType = "1";
        String facilityNo = "01010001";
        String facilityName = "wxx";
        String action = "CAI_AIR_DELIVERY";
        TraceRequest2Cainiao traceRequest2Cainiao = new TraceRequest2Cainiao();
        traceRequest2Cainiao.setFacilityType(facilityType);
        traceRequest2Cainiao.setFacilityNo(facilityNo);
        traceRequest2Cainiao.setFacilityName(facilityName);
        traceRequest2Cainiao.setLogistic_provider_id(logistic_provider_id);
        traceRequest2Cainiao.setMailNos(mailNos);
        traceRequest2Cainiao.setTxLogisticID(txLogisticID);
        traceRequest2Cainiao.setTime(time);
        traceRequest2Cainiao.setCity(city);
        traceRequest2Cainiao.setAction(action);
        traceRequest2Cainiao.setDesc(desc);
//        String resultExcepted = "<response><logisticProviderID /><responseItems><response><success>true</success><mailNos>120150513131100</mailNos><txLogisticID>LP20150513131100</txLogisticID><reason /></response></responseItems></response>";
        String resultExcepted = "<responses>  <logisticProviderID>DISTRIBUTOR_902950</logisticProviderID>  <responseItems>    <response>      <mailNos>RA100001009FI</mailNos>      <txLogisticID>LP00012015280275</txLogisticID>      <success>true</success>    </response>  </responseItems></responses>";
        String resultActual = PushTrace2CainiaoService.pushTrace2Cainiao(traceRequest2Cainiao.combiteTraceXml4Cainiao(), traceRequest2Cainiao.getLogistic_provider_id());
        System.out.println("resultActual = " + resultActual);
        System.out.println("resultExcepted = " + resultExcepted);
        Assert.assertEquals(resultExcepted, resultActual);
    }


    @Test
    public void testDoSign() throws Exception {
       // http://bifrost.tbsandbox.com/cp/sign_efficacy.htm?spm=a219l.7404944.0.0.mCheZM
        String content = "wxx123";
        String keys = "v6lfQ5XH677s5I4835tgecOOBmZ9u7T9";

        String resultExcepted = "54pNwgW8AZj/KHFY8q0mrQ==";
        String resultActual = PushTrace2CainiaoService.doSign(content, keys);
        System.out.println("resultExcepted = " + resultExcepted);
        System.out.println("resultActual = " + resultActual);
        Assert.assertEquals(resultExcepted, resultActual);
    }
}
