package com.sinoair.iemisgateway.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-8-12
 * Time: 下午3:34
 * To change this template use File | Settings | File Templates.
 */
public class DateUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetDateStrAheadHours() throws Exception {
        System.out.println(DateUtil.getDateStrAheadHours(-7,"yyyy-MM-dd hh:mm:ss"));
        System.out.println(DateUtil.getDateStrAheadHours(-7,"hhmmss"));
        Assert.assertEquals("20150812", DateUtil.getDateStrAheadHours(-7,"yyyyMMdd"));
    }
}
