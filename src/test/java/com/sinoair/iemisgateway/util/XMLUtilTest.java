package com.sinoair.iemisgateway.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testUtil.CommonTestCaseTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-8-25
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class XMLUtilTest extends CommonTestCaseTemplate {

    String xmlPath;
    String xsdPath;


    @Before
    public void setUp() throws Exception {
        xsdPath = getMainRootPath()+"XMLAndXSD/cainiao/uploadOrderCainiao.xsd";
    }



    @Test
    public void testVerificateXMLByXSDPass() throws Exception {
        xmlPath =getTestRootPath()+ "XMLAndXSD/cainiao/uploadOrderCainiaoNormal.xml";
        boolean result = XMLUtil.verificateXMLByXSD(xsdPath, xmlPath);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testVerificateXMLByXSDNotPass() throws Exception {
        xmlPath =getTestRootPath()+ "XMLAndXSD/cainiao/uploadOrderCainiaoNodeMissing.xml";
        boolean result = XMLUtil.verificateXMLByXSD(xsdPath, xmlPath);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testWriteFile() throws Exception {

    }

    @Test
    public void testCombinateReturnMessage() throws Exception {

    }
      @Test
    public void testXmlValiadation() throws Exception {

    }

    @Test
    public void testXmlVerification() throws Exception {

    }
}
