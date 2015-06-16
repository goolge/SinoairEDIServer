package com.sinoair.iemisgateway.util;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-2
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {
    public static String null2EmptyString(String input) {
        if (input == null) {
            return "";
        } else {
            return input;
        }
    }
}
