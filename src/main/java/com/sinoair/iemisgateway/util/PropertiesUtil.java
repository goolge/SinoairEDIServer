package com.sinoair.iemisgateway.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-31
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesUtil {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/db.properties");
        prop.load(in);     ///加载属性列表
        Iterator<String> it = prop.stringPropertyNames().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key + ":" + prop.getProperty(key));
        }
        in.close();
    }

    public static String readProperty(String fileName, String key)  {
//        String value = "";
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/"+fileName+".properties");
        try {
            prop.load(in);     ///加载属性列表
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return prop.getProperty(key);
    }
}
