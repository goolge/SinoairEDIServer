package com.sinoair.iemisgateway.util;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-31
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReadPropertyExist() throws Exception {

        String resultExcepted = "SYSDATE-60";
        String resultActual = PropertiesUtil.readProperty("cainiao", "date");
        BaseLogger.info("resultExcepted = " + resultExcepted);
        BaseLogger.info("resultActual = " + resultActual);
        Assert.assertEquals(resultExcepted, resultActual);
    }

    @Test
    public void testReadPropertyNotExist() throws Exception {
        String resultExcepted = null;
        String resultActual = PropertiesUtil.readProperty("db", "wew");
        BaseLogger.info("resultExcepted = " + resultExcepted);
        BaseLogger.info("resultActual = " + resultActual);
        Assert.assertEquals(resultExcepted, resultActual);
    }
}
