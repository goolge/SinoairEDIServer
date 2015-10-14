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
    public static String getIntFormString(int len, int num) {
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
     *
     * @param str
     * @param character
     * @return
     */
    public static String trimCharacterLeft(String str, String character) {
        if (str == null || "".equals(str) || character == null || "".equals(character)) {
            return "";
        }
        while (str.startsWith(character)) {
            str = str.replaceFirst(character, "");
        }
        return str;
    }

    public static String calculate(String acount, String lpNum) {
        if (lpNum == null || lpNum.length() < 8) {
            return "";
        }
        String itemId = acount + lpNum.substring(lpNum.length() - 8);
        int int0 = getRMNumByLetter(itemId.substring(0, 1));
        int int1 = getHexadecimal(getRMNumByLetter(itemId.substring(1, 2)) * 2);
        int int2 = getRMNumByLetter(itemId.substring(2, 3));
        int int3 = getHexadecimal(getRMNumByLetter(itemId.substring(3, 4)) * 2);
        int int4 = getRMNumByLetter(itemId.substring(4, 5));
        int int5 = getHexadecimal(getRMNumByLetter(itemId.substring(5, 6)) * 2);
       /* System.out.println(itemId.substring(5, 6) + "int5:" + int5);*/
        int int6 = getRMNumByLetter(itemId.substring(6, 7));
        int int7 = getHexadecimal(getRMNumByLetter(itemId.substring(7, 8)) * 2);
       /* System.out.println(itemId.substring(7, 8) + "int7:" + int7);*/
        int int8 = getRMNumByLetter(itemId.substring(8, 9));
        int int9 = getHexadecimal(getRMNumByLetter(itemId.substring(9, 10)) * 2);
        int int10 = getRMNumByLetter(itemId.substring(10, 11));
        int int11 = getHexadecimal(getRMNumByLetter(itemId.substring(11, 12)) * 2);
        int int12 = getRMNumByLetter(itemId.substring(12, 13));
        int int13 = getHexadecimal(getRMNumByLetter(itemId.substring(13, 14)) * 2);
        int int14 = getRMNumByLetter(itemId.substring(14, 15));
        int int15 = getHexadecimal(getRMNumByLetter(itemId.substring(15, 16)) * 2);
        int int16 = getRMNumByLetter(itemId.substring(16, 17));
        int int17 = getHexadecimal(getRMNumByLetter(itemId.substring(17, 18)) * 2);
        int int18 = getRMNumByLetter(itemId.substring(18, 19));
        int int19 = getHexadecimal(getRMNumByLetter(itemId.substring(19)) * 2);
       /* System.out.println("int0:" + int0);
        System.out.println("int1:" + int1);
        System.out.println("int2:" + int2);
        System.out.println("int3:" + int3);
        System.out.println("int4:" + int4);
        System.out.println("int5:" + int5);
        System.out.println("int6:" + int6);
        System.out.println("int7:" + int7);
        System.out.println("int8:" + int8);
        System.out.println("int9:" + int9);
        System.out.println("int10:" + int10);
        System.out.println("int11:" + int11);
        System.out.println("int12:" + int12);
        System.out.println("int13:" + int13);
        System.out.println("int14:" + int14);
        System.out.println("int15:" + int15);
        System.out.println("int16:" + int16);
        System.out.println("int17:" + int17);
        System.out.println("int18:" + int18);
        System.out.println("int19:" + int19);*/
        int totalInt = int0 + int1 + int2 + int3 + int4 + int5 + int6 + int7 + int8 + int9 + int10 + int11 + int12 + int13 + int14 + int15 + int16 + int17 + int18 + int19;
        /*System.out.println("totalInt:" + totalInt);*/
        int charInt = totalInt % 16;
        charInt = 16 - charInt;
        return itemId + getRMLetterByNum(charInt);
    }
    public static String calculate(String str20) {
       if (str20 == null || str20.length() < 8) {
            return "";
        }
        int totalInt=0;
        for(int i=0;i<str20.length();i++){
            int num=0;

          if(i==str20.length()-1){
           num=getRMNumByLetter(str20.substring(i));
          }else{
           num=getRMNumByLetter(str20.substring(i, i+1));
          }
          if(i%2==1){
           num=getHexadecimal(num*2);
          }
          totalInt=totalInt+num;

        }

       /* System.out.println("xintotalInt:" + totalInt);*/
        int charInt = totalInt % 16;
        charInt = 16 - charInt;
        return str20 + getRMLetterByNum(charInt);
    }

    public static String getRMLetterByNum(int num) {
        String letter = num + "";
        if (num == 10) {
            letter = "A";
        } else if (num == 11) {
            letter = "B";
        } else if (num == 12) {
            letter = "C";
        } else if (num == 13) {
            letter = "D";
        } else if (num == 14) {
            letter = "E";
        } else if (num == 15) {
            letter = "F";
        }
        return letter;
    }

    public static int getRMNumByLetter(String letter) {
        int num = 0;
        if (letter != null) {
            if (letter.equals("A")) {
                num = 10;
            } else if (letter.equals("B")) {
                num = 11;
            } else if (letter.equals("C")) {
                num = 12;
            } else if (letter.equals("D")) {
                num = 13;
            } else if (letter.equals("E")) {
                num = 14;
            } else if (letter.equals("F")) {
                num = 15;
            } else {
                num = Integer.parseInt(letter);
            }
        }

        return num;

    }

    public static int getHexadecimal(int num) {
        int stNum = 0;
        int ge = num % 16;
        int shi = Integer.parseInt((Math.floor(num / 16) + "").substring(0, (Math.floor(num / 16) + "").indexOf(".")));
        stNum = Integer.parseInt(shi + "" + ge);
        //十位数为0，代表着没有除开，余数就是被除数，规则里只有这种情况才会除2
        if (shi == 0 && stNum >= 10 && stNum <= 15) {
            stNum = stNum / 2;
        } else {
            stNum = shi + ge;
        }

        return stNum;
    }
}
