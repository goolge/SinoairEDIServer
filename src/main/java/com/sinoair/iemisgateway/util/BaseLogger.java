package com.sinoair.iemisgateway.util;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-4
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
public class BaseLogger {
    private static Logger logger = Logger.getRootLogger();

    public static void info(Object message) {
        logger.info(message);   
    }

    public static void error(Object message) {
        logger.error(message);   
    }

    public static void debug(Object message) {
        logger.debug(message);   
    }

    public static void warn(Object message) {
        logger.warn(message);   
    }
}
