package com.sinoair.iemisgateway.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
    static String xsdPath;


    @BeforeClass
    public static void setUp() throws Exception {
        xsdPath = getMainRootPath() + "XMLAndXSD/cainiao/uploadOrderCainiao.xsd";
    }

    //todo 菜鸟添加新节点就会报错
    @Ignore
    @Test
    public void testVerificateXMLByXSD() throws Exception {
        xmlPath = getTestRootPath() + "XMLAndXSD/cainiao/" + "uploadOrderCainiao2015-07-09T16-52-09-914.xml";
        boolean result = XMLUtil.verificateXMLByXSD(xsdPath, xmlPath);
        Assert.assertEquals(true, result);
    }
    @Ignore
    @Test
    public void testVerificateXMLByXSDPass() throws Exception {
        xmlPath = getTestRootPath() + "XMLAndXSD/cainiao/uploadOrderCainiaoNormal.xml";
        boolean result = XMLUtil.verificateXMLByXSD(xsdPath, xmlPath);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testVerificateXMLByXSDNotPass() throws Exception {
        xmlPath = getTestRootPath() + "XMLAndXSD/cainiao/uploadOrderCainiaoNodeMissing.xml";
        boolean result = XMLUtil.verificateXMLByXSD(xsdPath, xmlPath);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testWriteFile() throws Exception {
        String xml = "123213213213";
        String filePath = "D:\\11111111111111111111111111111111/" + DateUtil.getSysdateYMD() + "/wxx.txt";
        XMLUtil.writeFile(xml, filePath);
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
