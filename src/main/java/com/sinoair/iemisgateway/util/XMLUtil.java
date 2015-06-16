package com.sinoair.iemisgateway.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-14
 * Time: 下午2:42
 * To change this template use File | Settings | File Templates.
 */
public class XMLUtil {

    /**
     * 身份验证
     *
     * @param document
     * @return
     */

    public static boolean xmlValiadation(Document document) {
        Node nodeUsername = document.selectSingleNode("//username");
        Node nodePassword = document.selectSingleNode("//password");
        String username = nodeUsername.getText();
        String password = nodePassword.getText();
        System.out.println("username:" + username + "   password:" + password);
        return validateNameAndPWD(username, password);
    }
 public static boolean xmlValiadationCainiao(Document document) {
//        Node nodeUsername = document.selectSingleNode("//username");
//        Node nodePassword = document.selectSingleNode("//password");
//        String username = nodeUsername.getText();
//        String password = nodePassword.getText();
//        System.out.println("username:" + username + "   password:" + password);
     //todo 校验菜鸟身份
        return true;
    }

    private static boolean validateNameAndPWD(String username, String password) {
        if ("LCM".equals(username) && "LCM".equals(password)) return true;
        if ("jolly".equals(username) && "jolly".equals(password)) return true;
        return false;
    }

    /**
     * xml格式检查
     *
     * @param xml
     * @return
     */
    public static Document xmlVerification(String xml) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            System.out.println("e = " + e);
//            e.printStackTrace();
            return null;
        }
        //todo-wxx-u xsd检查
        return document;
    }

    /**
     * xsd检查
     *
     * @param xsdPath
     * @return
     */
    public static boolean verificateXMLByXSD(String xsdPath, String xmlPath) {
        // 建立schema工厂
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        // 建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证
        File schemaFile = new File(xsdPath);
        try {
            // 利用schema工厂，接收验证文档文件对象生成Schema对象
            Schema schema = schemaFactory.newSchema(schemaFile);
            // 通过Schema产生针对于此Schema的验证器，利用schenaFile进行验证
            Validator validator = schema.newValidator();
            // 得到验证的数据源
            Source source = new StreamSource(xmlPath);
            // 开始验证，成功输出success!!!，失败输出fail
            validator.validate(source);
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void writeFile(String xml, String FilePath) {
        try {
            File xmlFile = new File(FilePath);
            System.out.println(xmlFile.getAbsolutePath());
            xmlFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(xmlFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            if(xml==null)xml="";
            outputStreamWriter.write(xml);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String combinateReturnMessage(String message) {
        return "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSRETURN>\n" +
                "\t<Error>" + message + "</Error>\n" +
                "</WSRETURN>";

    }
    public static String combinateReturnMessage4Cainiao(boolean success,String reason) {
//        标识	说明
//        S01	非法的XML格式
//        S02	非法的数字签名
//        S06	服务器请求超时，目标主机不可达请重试
//        S07	系统异常，请重试
//        S12	非法的请求参数
//        S13	业务服务异常
//        S14	系统流控
//        S20	业务报文校验参数不通过

        return "<responses>\n" +
                "\t<responseItems>\n" +
                "\t\t<response>\n" +
                (success ? ("\t\t\t<success>true</success>\n") : ("\t\t\t<success>false</success>\n" +
                        "\t\t\t<reason>" + reason + "</reason>\n")) +
                "\t\t</response>\n" +
                "\t</responseItems>\n" +
                "</responses>";
    }
}
