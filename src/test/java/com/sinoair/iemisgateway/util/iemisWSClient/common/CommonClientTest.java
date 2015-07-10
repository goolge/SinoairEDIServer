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
        xml = "good";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExecute() throws Exception {

        String resultExcepted = "hellowxxgood";
        String resultActual = commonClient.execute(className, xml);
        System.out.println("resultExcepted = " + resultExcepted);
        System.out.println("resultActual = " + resultActual);
        Assert.assertEquals(resultExcepted, resultActual);
    }
}