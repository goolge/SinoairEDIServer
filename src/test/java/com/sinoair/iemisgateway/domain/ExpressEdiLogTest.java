package com.sinoair.iemisgateway.domain;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressEdiLogTest {
    ExpressEdiLog expressEdiLog;
    @Before
    public void setUp() throws Exception {
         expressEdiLog = new ExpressEdiLog();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInsertRecord() throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<100;i++) {
            sb.append("我是中国人");
        }
        System.out.println("sb.length() = " + sb.length());
        expressEdiLog.setEel_partner("cainiao");
        expressEdiLog.setEel_interface("1");

        expressEdiLog.setEel_request(sb.toString());
        expressEdiLog.setEel_response("3");
        expressEdiLog.setEel_is_exception("4");
        expressEdiLog.setEel_comments("6");
        expressEdiLog.insertRecord();
    }
}