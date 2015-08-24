package com.sinoair.iemisgateway.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-31
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesUtil {
    public static String readProperty(String fileName, String key) {
        String value = "";
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/" + fileName + ".properties");
        try {
            prop.load(in);     ///加载属性列表
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        value = prop.getProperty(key);
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
