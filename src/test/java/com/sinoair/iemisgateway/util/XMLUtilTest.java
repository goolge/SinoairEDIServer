package com.sinoair.iemisgateway.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-8-25
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */
public class XMLUtilTest {
    String xsdPath1;
    String xmlPath1;
    String xsdPath2;
    String xmlPath2;
    @Before
    public void setUp() throws Exception {
           //classpath
//        xsdPath1 = "D:/idea/IDEA10/SinoairEDIServer/out/artifacts/SinoairEDIServer/WEB-INF/classes/com/sinoair/iemisgateway/XMLAndXSD/upload/Webservice-call1xsd.xml";
//        xmlPath1 = "D:/idea/IDEA10/SinoairEDIServer/out/artifacts/SinoairEDIServer/WEB-INF/classes/com/sinoair/iemisgateway/XMLAndXSD/upload/Webservice-call1.xml";
        //src
        xmlPath1 = "D:\\Program Files\\tomcat\\apache-tomcat-6.0.14\\bin\\sinoairEDIServerXML\\FIRST\\upload2014-08-26T15-33-43-883.xml";
        xsdPath1 = "D:\\idea\\IDEA10\\SinoairEDIServer\\src\\com\\sinoair\\iemisgateway\\XMLAndXSD\\upload\\Webservice-call1xsd.xml";

        xmlPath2 = "D:/idea/IDEA10/SinoairEDIServer/out/artifacts/SinoairEDIServer/WEB-INF/classes/com/sinoair/iemisgateway/XMLAndXSD/update/Webservice-call2.xml";
         xsdPath2 = "D:/idea/IDEA10/SinoairEDIServer/out/artifacts/SinoairEDIServer/WEB-INF/classes/com/sinoair/iemisgateway/XMLAndXSD/update/Webservice-call2xsd.xml";

    }

    @Test
    public void testXmlValiadation() throws Exception {

    }

    @Test
    public void testXmlVerification() throws Exception {

    }

//    @Test
    public void testVerificateXMLByXSD() throws Exception {
        boolean check1 = XMLUtil.verificateXMLByXSD(xsdPath1, xmlPath1);
        boolean check2 = XMLUtil.verificateXMLByXSD(xsdPath2, xmlPath2);
        Assert.assertEquals(true,check1);
        Assert.assertEquals(true,check2);

    }

    @Test
    public void testWriteFile() throws Exception {

    }

    @Test
    public void testCombinateReturnMessage() throws Exception {

    }
}
