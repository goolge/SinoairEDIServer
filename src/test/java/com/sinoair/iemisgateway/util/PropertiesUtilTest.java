package com.sinoair.iemisgateway.util;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        String resultExcepted = "jdbc:oracle:thin";
        String resultActual = PropertiesUtil.readProperty("db", "database.jdbc.connectionURL");
        System.out.println("resultExcepted = " + resultExcepted);
        System.out.println("resultActual = " + resultActual);
        Assert.assertEquals(true, resultActual.startsWith(resultExcepted));
    }

    @Test
    public void testReadPropertyNotExist() throws Exception {
        String resultExcepted = null;
        String resultActual = PropertiesUtil.readProperty("db", "wew");
        System.out.println("resultExcepted = " + resultExcepted);
        System.out.println("resultActual = " + resultActual);
        Assert.assertEquals(resultExcepted, resultActual);
    }
}
