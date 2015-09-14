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
    public static String nullAndCommaProcess(String src) {
        return nullProcess(src, ",");
    }

    public static String nullAndSemicolonProcess(String src) {
        return nullProcess(src, ";");
    }
     public static String nullAndTabProcess(String src) {
        return nullProcess(src, "\t");
    }

    public static String nullProcess(String src, String Separator) {
        if (src == null) {
            return Separator;
        } else {
            if (Separator == null || "".equals(Separator.trim())) {
                return src.replace("\n", " ").replace("\r", " ");
            } else {
                return src.replace(Separator, " ").replace("\n", " ").replace("\r", " ") + Separator;
            }

        }
    }

     /**
     * 获取格式化整数
     * len：int 格式化后的数据长度;num:int 需要格式化的整数;
     * 如果数字长度超过len，则从头截取长度为len的字符
     * 返回值:String 格式化后的整数
     */
    public static String getIntFormString(int len, int num)  {
        String strRecord = String.valueOf(num);
        return getFixedString(strRecord, len, "0", true);
    }

    /**
     * @param str    字符串  根据最大最小长度，截取字符串，如果字符串长度太短，用空格补充
     * @param maxLen 最大长度
     * @param minLen 最小长度
     * @return
     */
    public static String getStringSpace(String str, int maxLen, int minLen) {
        return getString(str, maxLen, minLen, " ", false);
    }

    /**
     * @param str    字符串
     * @param maxLen 最大长度
     * @param minLen 最小长度
     * @param addStr 补充字符
     * @param ahead  补充字符放到前面
     * @return
     */
    public static String getString(String str, int maxLen, int minLen, String addStr, boolean ahead) {
        if (maxLen <= 0 || minLen < 0 || (maxLen < minLen)) {
            return "";
        }
        if (addStr == null || "".equals(addStr) || addStr.length() != 1) {
            addStr = " ";
        }
        if (str == null) {
            str = "";
        }
        int len = str.length();
        if (len < minLen) {
            for (int i = 0; i < (minLen - len); i++) {
                if (ahead) {
                    str = addStr + str;
                } else {
                    str = str + addStr;
                }
            }
        } else if (len > maxLen) {
            str = str.substring(0, maxLen);
        }
        return str;
    }

    /**
     * 取得定长的字符串
     *
     * @param str
     * @param len
     * @param addStr
     * @param ahead  补充字符串放到前面
     * @return
     */
    public static String getFixedString(String str, int len, String addStr, boolean ahead) {
        if (len <= 0) {
            return "";
        }
        if (addStr == null || "".equals(addStr) || addStr.length() != 1) {
            addStr = " ";
        }
        int strLen = str.length();
        if (strLen > len) {
            str = str.substring(0, len);
        } else if (strLen < len) {
            for (int i = 0; i < len - strLen; i++) {
                if (ahead) {
                    str = addStr + str;
                } else {
                    str = str + addStr;
                }

            }
        }
        return str;
    }

    /**
     * 取得定长的字符串，如果长度不够则
     *
     * @param str
     * @param len
     * @return
     */
    public static String getFixedStringSpace(String str, int len) {
        return getFixedString(str, len, " ", false);
    }

    /**
     * 截掉所有非数字字符，如果长度超过N，截取后N位，一般用于纯数字的电话号码
     *
     * @param obj
     * @param length 最大长度
     * @return
     */
    public static String getPhoneNum(Object obj, int length) {
        String str = "";
        if (obj == null) {
            str = "";
        } else {
            str = obj.toString().replaceAll("\\D", "");
        }
        if (str.length() > length) {
            str = str.substring(str.length() - length);
        }
        return str;
    }

    /**
     * 循环截掉，字符串开头的字符
     * @param str
     * @param character
     * @return
     */
    public static String trimCharacterLeft(String str,String character){
        if(str==null || "".equals(str) || character==null || "".equals(character)){
            return "";
        }
        while(str.startsWith(character)){
         str=str.replaceFirst(character,"");
        }
        return str;
    }




}
