package com.sinoair.iemisgateway.correos.service;

import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import com.sinoair.iemisgateway.util.*;
import com.sinoair.iemisgateway.util.sftp.SftpConnection;
import com.sinoair.iemisgateway.util.sftp.SftpUpload;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-5-11
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
public class SendManifestService {
    /**
     * 获取需要发送预报的数据集合
     *
     * @param conn
     * @return
     * @throws Exception
     */
    public ArrayList getInfoList(Connection conn) throws Exception {
        String sql = "select distinct eawb.EAWB_CONSIGNEE_ACCOUNTNAME," + //1
                "eawb.EAWB_DELIVER_ADDRESS," +//2
                "eawb.EAWB_DESTCITY," + //3
                "eawb.EAWB_DESTSTATE," + //4
                "eawb.EAWB_DELIVER_POSTCODE," +  //5
                "eawb.EAWB_DELIVER_PHONE," + //6
                "eawb.EAWB_DECLAREGROSSWEIGHT," +   //7
                "eawb.EAWB_SHIPPER_ACCOUNTNAME," +  //8
                "eawb.EAWB_PICKUP_ADDRESS," +  //9
                "eawb.EAWB_DEPARTCITY," +  //10
                "eawb.EAWB_DEPARTSTATE," +  //11
                "eawb.EAWB_PICKUP_POSTCODE," +   //12
                "eawb.EAWB_PICKUP_PHONE," +  //13
                "eawb.EAWB_REFERENCE1," +
                "eawb.EAWB_PRINTCODE," +
                "nvl(eawb.EAWB_DECLAREVALUE,0) EAWB_DECLAREVALUE," +
                "eawb.EAWB_DELIVER_EMAIL " + //14
                " from expressairwaybill eawb,express_correos_manifest ecm" +
                " where ecm.eawb_printcode=eawb.eawb_printcode" +
                " and ecm.ecm_status='PENDING'";
        ExeSQL texesql = new ExeSQL();
        texesql.setConnection(conn);
        ArrayList arrayList = texesql.execSqltoArr(sql);
        // BaseLogger.info("aaaa:"+sql);
        return arrayList;
    }
     public ArrayList getInfoListSome(Connection conn) throws Exception {
        String sql = "select eawb.EAWB_CONSIGNEE_ACCOUNTNAME," + //1
                "eawb.EAWB_DELIVER_ADDRESS," +//2
                "eawb.EAWB_DESTCITY," + //3
                "eawb.EAWB_DESTSTATE," + //4
                "eawb.EAWB_DELIVER_POSTCODE," +  //5
                "eawb.EAWB_DELIVER_PHONE," + //6
                "eawb.EAWB_DECLAREGROSSWEIGHT," +   //7
                "eawb.EAWB_SHIPPER_ACCOUNTNAME," +  //8
                "eawb.EAWB_PICKUP_ADDRESS," +  //9
                "eawb.EAWB_DEPARTCITY," +  //10
                "eawb.EAWB_DEPARTSTATE," +  //11
                "eawb.EAWB_PICKUP_POSTCODE," +   //12
                "eawb.EAWB_PICKUP_PHONE," +  //13
                "eawb.EAWB_REFERENCE1," +
                "eawb.EAWB_PRINTCODE," +
                "nvl(eawb.EAWB_DECLAREVALUE,0) EAWB_DECLAREVALUE " + //14
                "eawb.EAWB_DELIVER_EMAIL " + //14
                " from expressairwaybill eawb,express_correos_manifest ecm" +
                " where ecm.eawb_printcode=eawb.eawb_printcode" +
                " and eawb.eawb_printcode in('880002462295','880002873722','880002795492','880002844685','880002805305','880002641801','880002833672','880002572852')";
        ExeSQL texesql = new ExeSQL();
        texesql.setConnection(conn);
        ArrayList arrayList = texesql.execSqltoArr(sql);
        BaseLogger.info("aaaa:"+sql);
        return arrayList;
    }

    /**
     * 根据数据集合，在本地生成报文
     *
     * @param arrData
     * @param app_dir
     * @return
     * @throws Exception
     */
    public boolean generateInfoCorreos(ArrayList arrData, String app_dir) throws Exception {
        int maxLine = 1000;//一个文档最多存放1000行数据，0代表没有限制
        int lineNum = 1;
        int txtNum = 1;
        boolean newFile = false;
        StringBuffer sbOne = new StringBuffer();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date=new Date();
        String time = df.format(date);
        String productCode3 = PropertiesUtil.readProperty("correos", "productCode3");
        String labellerCode5 = PropertiesUtil.readProperty("correos", "labellerCode5");
        String contractNumber6 = PropertiesUtil.readProperty("correos", "contractNumber6");
        String clientNumber7 = PropertiesUtil.readProperty("correos", "clientNumber7");
        Double rate = Double.parseDouble(PropertiesUtil.readProperty("correos", "exchangeRate"));
        String verversion2 = PropertiesUtil.readProperty("correos", "verversion2");
        String frankingType4 = PropertiesUtil.readProperty("correos", "frankingType4");
        String totalPackages14 = PropertiesUtil.readProperty("correos", "totalPackages14");
        String packageNumber15 = PropertiesUtil.readProperty("correos", "packageNumber15");
        String deliveryMode44 = PropertiesUtil.readProperty("correos", "deliveryMode44");
        String insurance49 = PropertiesUtil.readProperty("correos", "insurance49");
        String shipmentType85 = PropertiesUtil.readProperty("correos", "shipmentType85");
        //String name110 = PropertiesUtil.readProperty("correos", "name110");
        String company114 = PropertiesUtil.readProperty("correos", "company114");
        String streetName117 = PropertiesUtil.readProperty("correos", "streetName117");
        String town124 = PropertiesUtil.readProperty("correos", "town124");
       // String province125 = PropertiesUtil.readProperty("correos", "province125");
        String PC126 = PropertiesUtil.readProperty("correos", "PC126");
        //String senderTelephoneNumber127 = PropertiesUtil.readProperty("correos", "senderTelephoneNumber127");
        String endOfRegistration131 = PropertiesUtil.readProperty("correos", "endOfRegistration131");
        String MD16 = PropertiesUtil.readProperty("correos", "MD16");
        String Num16 = PropertiesUtil.readProperty("correos", "Num16");
        for (int i = 0; i < arrData.size(); i++) {
            StringBuffer sb = new StringBuffer();
            HashMap map = (HashMap) arrData.get(i);
            //BaseLogger.info("map:"+map);
            //第一行，同时不是list的最后一个
            if (lineNum == 1 && i != (arrData.size() - 1)) {
                sb.append("C");
                lineNum += 1;
            } else
                //最后一行，或者是list的最后一个,且list只有一个
                if ( (lineNum == maxLine  || (i == (arrData.size() - 1) && arrData.size()!=1) ) && lineNum!=1 ) {
                    sb.append("F");
                    lineNum = 1;
                } else
                    //第一行，同时是list的最后一个
                    if (lineNum == 1 && i == (arrData.size() - 1)) {
                        sb.append("U");
                        lineNum = 1;
                    } else {
                        sb.append("R");
                        lineNum += 1;
                    }
            String mailNo = map.get("EAWB_REFERENCE1").toString();
            sb.append(getStrCorreos(0, "", "", false, true));
            sb.append(getStrCorreos(8, verversion2, " ", true, true));//第2
            sb.append(getStrCorreos(5, productCode3, " ", true, true));//第3
            sb.append(getStrCorreos(2, frankingType4, " ", true, true));//第4
            sb.append(getStrCorreos(4, labellerCode5, " ", false, true));//第5
            sb.append(getStrCorreos(8, contractNumber6, " ", false, true));//第6
            sb.append(getStrCorreos(8, clientNumber7, " ", true, true));//第7
            sb.append(getStrCorreos(0, "", " ", false, true));//第8
            sb.append(getStrCorreos(0, "", "", false, true));//第9
            sb.append(getStrCorreos(23, mailNo, " ", false, true));//第10
            sb.append(getStrCorreos(16, getShipmentCode(mailNo.substring(0, 15), 23), " ", false, true));//第11
            sb.append(getStrCorreos(0, "", "", false, true));//第12
            sb.append(getStrCorreos(0, "", "", false, true));//第13
            sb.append(getStrCorreos(2, totalPackages14, "", false, true));//第14
            sb.append(getStrCorreos(2, packageNumber15, "", false, true));//第15
            sb.append(getStrCorreos(24, MD16 + labellerCode5 + Num16 + time + GetIntFormString(2, txtNum), " ", true, true));//第16
            sb.append(getStrCorreos(0, "", "", false, true));//第17
            sb.append(getStrCorreos(0, "", "", false, true));//第18
            sb.append(getStrCorreos(50, map.get("EAWB_CONSIGNEE_ACCOUNTNAME").toString(), " ", false, true));//第19
            sb.append(getStrCorreos(0, "", "", false, true));//第20
            sb.append(getStrCorreos(0, "", "", false, true));//第21
            sb.append(getStrCorreos(0, "", "", false, true));//第22
            sb.append(getStrCorreos(0, "", "", false, true));//第23
            sb.append(getStrCorreos(0, "", "", false, true));//第24
            sb.append(getStrCorreos(0, "", "", false, true));//第25
            sb.append(getStrCorreos(50, map.get("EAWB_DELIVER_ADDRESS").toString(), "", false, true));//第26
            sb.append(getStrCorreos(0, "", "", false, true));//第27
            sb.append(getStrCorreos(0, "", "", false, true));//第28
            sb.append(getStrCorreos(0, "", "", false, true));//第29
            sb.append(getStrCorreos(0, "", "", false, true));//第30
            sb.append(getStrCorreos(0, "", "", false, true));//第31
            sb.append(getStrCorreos(0, "", "", false, true));//第32
            sb.append(getStrCorreos(50, map.get("EAWB_DESTCITY").toString(), "", false, true));//第33
            sb.append(getStrCorreos(40, map.get("EAWB_DESTSTATE").toString(), "", false, true));//第34
            sb.append(getStrCorreos(5, map.get("EAWB_DELIVER_POSTCODE").toString(), "", false, true));//第35
            sb.append(getStrCorreos(0, "", "", false, true));//第36
            sb.append(getStrCorreos(0, "", "", false, true));//第37
            sb.append(getStrCorreos(0, "", "", false, true));//第38
            sb.append(getStrCorreos(0, "", "", false, true));//第39
            sb.append(getStrCorreos(0, "", "", false, true));//第40
            sb.append(getStrCorreos(12, getNumLength(map.get("EAWB_DELIVER_PHONE"), 12), "", false, true));//第41
            sb.append(getStrCorreos(50, map.get("EAWB_DELIVER_POSTCODE").toString(), "", false, true));//第42 收件人邮箱
            sb.append(getStrCorreos(0, "", "", false, true));//第43
            sb.append(getStrCorreos(2, deliveryMode44, "", false, true));//第44
            Double weight = Double.parseDouble(map.get("EAWB_DECLAREGROSSWEIGHT").toString())*1000; //单位是kg ,要转换成g
           java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#");
            sb.append(getStrCorreos(5, decimalFormat.format(weight), "", false, true));//第45
            sb.append(getStrCorreos(0, "", "", false, true));//第46
            sb.append(getStrCorreos(0, "", "", false, true));//第47
            sb.append(getStrCorreos(0, "", "", false, true));//第48
            sb.append(getStrCorreos(1, insurance49, "", false, true));//第49
            int money = (int) Math.rint(Double.parseDouble(map.get("EAWB_DECLAREVALUE").toString()) * rate * 100);
            if(money<60){
              money=60;
            }
            sb.append(getStrCorreos(6, GetIntFormString(6, money), "", false, true));//第50   申报价值，转化成欧分 最小值为60欧分
            sb.append(getStrCorreos(0, "", "", false, true));//第51
            sb.append(getStrCorreos(0, "", "", false, true));//第52
            sb.append(getStrCorreos(0, "", "", false, true));//第53
            sb.append(getStrCorreos(0, "", "", false, true));//第54
            sb.append(getStrCorreos(0, "", "", false, true));//第55
            sb.append(getStrCorreos(0, "", "", false, true));//第56
            sb.append(getStrCorreos(0, "", "", false, true));//第57
            sb.append(getStrCorreos(0, "", "", false, true));//第58
            sb.append(getStrCorreos(0, "", "", false, true));//第59
            sb.append(getStrCorreos(0, "", "", false, true));//第60
            sb.append(getStrCorreos(0, "", "", false, true));//第61
            sb.append(getStrCorreos(0, "", "", false, true));//第62
            sb.append(getStrCorreos(0, "", "", false, true));//第63
            sb.append(getStrCorreos(0, "", "", false, true));//第64
            sb.append(getStrCorreos(0, "", "", false, true));//第65
            sb.append(getStrCorreos(0, "", "", false, true));//第66
            sb.append(getStrCorreos(0, "", "", false, true));//第67
            sb.append(getStrCorreos(0, "", "", false, true));//第68
            sb.append(getStrCorreos(0, "", "", false, true));//第69
            sb.append(getStrCorreos(0, "", "", false, true));//第70
            sb.append(getStrCorreos(0, "", "", false, true));//第71
            sb.append(getStrCorreos(0, "", "", false, true));//第72
            sb.append(getStrCorreos(0, "", "", false, true));//第73
            sb.append(getStrCorreos(0, "", "", false, true));//第74
            sb.append(getStrCorreos(0, "", "", false, true));//第75
            sb.append(getStrCorreos(0, "", "", false, true));//第76
            sb.append(getStrCorreos(0, "", "", false, true));//第77
            sb.append(getStrCorreos(0, "", "", false, true));//第78
            sb.append(getStrCorreos(0, "", "", false, true));//第79
            sb.append(getStrCorreos(0, "", "", false, true));//第80
            sb.append(getStrCorreos(0, "", "", false, true));//第81
            sb.append(getStrCorreos(0, "", "", false, true));//第82
            sb.append(getStrCorreos(0, "", "", false, true));//第83
            sb.append(getStrCorreos(0, "", "", false, true));//第84
            sb.append(getStrCorreos(1, shipmentType85, "", false, true));//第85
            sb.append(getStrCorreos(0, "", "", false, true));//第86
            sb.append(getStrCorreos(0, "", "", false, true));//第87
            sb.append(getStrCorreos(0, "", "", false, true));//第88
            sb.append(getStrCorreos(0, "", "", false, true));//第89
            sb.append(getStrCorreos(0, "", "", false, true));//第90
            sb.append(getStrCorreos(0, "", "", false, true));//第91
            sb.append(getStrCorreos(0, "", "", false, true));//第92
            sb.append(getStrCorreos(0, "", "", false, true));//第93
            sb.append(getStrCorreos(0, "", "", false, true));//第94
            sb.append(getStrCorreos(0, "", "", false, true));//第95
            sb.append(getStrCorreos(0, "", "", false, true));//第96
            sb.append(getStrCorreos(0, "", "", false, true));//第97
            sb.append(getStrCorreos(0, "", "", false, true));//第98
            sb.append(getStrCorreos(0, "", "", false, true));//第99
            sb.append(getStrCorreos(0, "", "", false, true));//第100
            sb.append(getStrCorreos(0, "", "", false, true));//第101
            sb.append(getStrCorreos(0, "", "", false, true));//第102
            sb.append(getStrCorreos(0, "", "", false, true));//第103
            sb.append(getStrCorreos(0, "", "", false, true));//第104
            sb.append(getStrCorreos(0, "", "", false, true));//第105
            sb.append(getStrCorreos(0, "", "", false, true));//第106
            sb.append(getStrCorreos(0, "", "", false, true));//第107
            sb.append(getStrCorreos(0, "", "", false, true));//第108
            sb.append(getStrCorreos(0, "", "", false, true));//第109
            sb.append(getStrCorreos(50, map.get("EAWB_SHIPPER_ACCOUNTNAME").toString(), "", false, true));//第110   name110
            sb.append(getStrCorreos(0, "", "", false, true));//第111
            sb.append(getStrCorreos(0, "", "", false, true));//第112
            sb.append(getStrCorreos(0, "", "", false, true));//第113
            sb.append(getStrCorreos(50, company114, "", false, true));//第114   company114
            sb.append(getStrCorreos(0, "", "", false, true));//第115
            sb.append(getStrCorreos(0, "", "", false, true));//第116
            sb.append(getStrCorreos(50,streetName117, "", false, true));//第117   streetName117        map.get("EAWB_PICKUP_ADDRESS").toString()
            sb.append(getStrCorreos(0, "", "", false, true));//第118
            sb.append(getStrCorreos(0, "", "", false, true));//第119
            sb.append(getStrCorreos(0, "", "", false, true));//第120
            sb.append(getStrCorreos(0, "", "", false, true));//第121
            sb.append(getStrCorreos(0, "", "", false, true));//第122
            sb.append(getStrCorreos(0, "", "", false, true));//第123
            sb.append(getStrCorreos(25, town124, "", false, true));//第124      town124      map.get("EAWB_DEPARTCITY").toString()
            sb.append(getStrCorreos(40,"" , "", false, true));//第125     province125   map.get("EAWB_DEPARTSTATE").toString()
            sb.append(getStrCorreos(5, PC126, "", false, true));//第126    PC126    map.get("EAWB_PICKUP_POSTCODE").toString()
            sb.append(getStrCorreos(12, "", "", false, true));//第127    senderTelephoneNumber127  getNumLength(map.get("EAWB_PICKUP_PHONE"),12)
            sb.append(getStrCorreos(0, "", "", false, true));//第128
            sb.append(getStrCorreos(0, "", "", false, true));//第129
            sb.append(getStrCorreos(0, "", "", false, true));//第130
            sb.append(getStrCorreos(1, endOfRegistration131, "", false, false));//第131
            sb.append("\n");
            sbOne.append(sb.toString());
            if (sb.substring(0, 1).equals("U") || sb.substring(0, 1).equals("F")) {
                newFile = true;
                String strFilePath = "";
                strFilePath = app_dir + "FD" + labellerCode5 + time + ".txt";
                FileUtil.generateFile(sbOne.toString(), strFilePath);

                time = df.format(addOneSecond(date,txtNum));
                txtNum += 1;
                sbOne = new StringBuffer();
            }

        }
        return newFile;
    }
    public Date addOneSecond(Date date,int num) {
        Calendar calendar = Calendar
                .getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, num);
        return calendar.getTime();
    }

    /**
     * 格式话取得西邮报文字符串
     *
     * @param length 字符长度
     * @param str    字段
     * @param reStr  如果字符长度不够，用什么字符来替补
     * @param flag   字符是否定长
     * @param ifTab  是否添加制表符
     * @return
     */
    private String getStrCorreos(int length, String str, String reStr, boolean flag, boolean ifTab) {
        if (length == 0 && ifTab) {
            return "\t";
        }
        String returnStr = str;
        String append = reStr;
        int beNum = length - str.length();
        if (flag && beNum > 0) {
            for (int i = 0; i < beNum; i++) {
                returnStr += append;
            }
        }
        if (beNum < 0) {
            returnStr = returnStr.substring(0, length);
        }
        if (ifTab) {
            returnStr = returnStr + "\t";
        }
        return returnStr;
    }

    /**
     * 根据mailNo截取获得ShipmentCode
     *
     * @param mailNo
     * @param divisor
     * @return
     */
    private String getShipmentCode(String mailNo, int divisor) {
        String aa = mailNo;
        char[] cs = aa.toCharArray();
        int a = 0;
        for (int i : cs) {
            a += i;
        }
        ;
        int remainder = a % (divisor);
        return aa + getBarcode(remainder);
    }

    /**
     * 获取 ShipmentCode 最后一位生成规则
     *
     * @param remainder
     * @return
     */
    private String getBarcode(int remainder) {
        String code = "";
        if (0 == remainder) {
            code = "T";
        } else if (1 == remainder) {
            code = "R";
        } else if (2 == remainder) {
            code = "W";
        } else if (3 == remainder) {
            code = "A";
        } else if (4 == remainder) {
            code = "G";
        } else if (5 == remainder) {
            code = "M";
        } else if (6 == remainder) {
            code = "Y";
        } else if (7 == remainder) {
            code = "F";
        } else if (8 == remainder) {
            code = "P";
        } else if (9 == remainder) {
            code = "D";
        } else if (10 == remainder) {
            code = "X";
        } else if (11 == remainder) {
            code = "B";
        } else if (12 == remainder) {
            code = "N";
        } else if (13 == remainder) {
            code = "J";
        } else if (14 == remainder) {
            code = "Z";
        } else if (15 == remainder) {
            code = "S";
        } else if (16 == remainder) {
            code = "Q";
        } else if (17 == remainder) {
            code = "V";
        } else if (18 == remainder) {
            code = "H";
        } else if (19 == remainder) {
            code = "L";
        } else if (20 == remainder) {
            code = "C";
        } else if (21 == remainder) {
            code = "K";
        } else if (22 == remainder) {
            code = "E";
        }
        return code;

    }

    /**
     * 获取格式化整数
     * len：int 格式化后的数据长度;record:int 需要格式化的整数;
     * 返回值:String 格式化后的整数
     */
    private String GetIntFormString(int len, int record) throws Exception {
        String strRecord = String.valueOf(record);
        int recordLen = len - strRecord.length();
        for (int i = 0; i < recordLen; i++) {
            strRecord = "0" + strRecord;
        }
        return strRecord;
    }

    //截掉所有非数字字符，如果长度超过N，截取后N位
    private String getNumLength(Object obj, int length) {
        String str = "";
        if (obj == null) {
            str = "";
        } else {
            str = obj.toString().replaceAll("\\D", "");
        }
        if (str.length() > 12) {
            str = str.substring(str.length() - 12);
        }
        return str;
    }

    /**
     * 批量更新报文数据状态为、Success
     *
     * @param arrData
     * @param conn
     * @throws Exception
     */
    public void updateSuccess(ArrayList arrData, Connection conn) throws Exception {

        PreparedStatement updateStatus = conn.prepareStatement(" update express_correos_manifest ecm set ecm.ecm_status='SUCCESS',ecm.ecm_errorcode='',ecm.ecm_errorfileName='',ecm.ecm_comments='',ecm.ECM_HANDLETIME=sysdate  where ecm.eawb_printcode=? ");
        if (arrData != null) {
            for (int i = 0; i < arrData.size(); i++) {
                HashMap map = (HashMap) arrData.get(i);
                String eawb_printcode = map.get("EAWB_PRINTCODE").toString();
                updateStatus.setString(1, eawb_printcode);
                updateStatus.addBatch();
            }
            conn.setAutoCommit(false);
            updateStatus.executeBatch();
        }


    }

    public void sendManifest(Connection conn,String historyRootPath) throws Exception, SQLException {
        //组织西邮报文数据，在本地存一份备份，然后放到西邮服务器，同时更新运单状态为发送成功
        ArrayList arrayList = null;
        try {

            //1.获取需要发送预报的运单数据；
            arrayList = getInfoList(conn);
            //2.更新数据状态为已发送
            updateSuccess(arrayList, conn);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }
        ConnectionFactory.closeConnection(conn);
        //3.在本地生成西邮报文
        int sendNum = arrayList == null ? 0 : arrayList.size();
        LogUtil.log(" 发送报文-获取发预报的数据条数：" + sendNum);
        String  localpfileDir = historyRootPath+"/correos/out/manifest/";
        String  localpfileDirCopy =historyRootPath+"/correos/bak/out/manifest/";
        generateInfoCorreos(arrayList, localpfileDir);
        //4.向西邮发送预报,同时备份本地文件
        File[] files = FileUtil.getFiles(localpfileDir);
        if (files != null && files.length > 0) {
            ch.ethz.ssh2.Connection connsft = SftpConnection.getSFTPConnection(PropertiesUtil.readProperty("correos", "correosUrl"), PropertiesUtil.readProperty("correos", "pfUsername"), PropertiesUtil.readProperty("correos", "keyFileManifest"));
            if (connsft != null) {
                LogUtil.log(" 发送报文-连接西邮服务器成功:报文个数"+files.length);
                SFTPv3Client sftPv3Client = new SFTPv3Client(connsft);
                SftpUpload.upload(localpfileDir, PropertiesUtil.readProperty("correos", "correospfDirTemp"), sftPv3Client, null);
                SftpUpload.upload(localpfileDir, PropertiesUtil.readProperty("correos", "correospfDirBak"), sftPv3Client, localpfileDirCopy);

                 Vector vector=sftPv3Client.ls(PropertiesUtil.readProperty("correos", "correospfDirTemp"));
                for (int i = 0; i < vector.size(); i++){
                 SFTPv3DirectoryEntry aa = (SFTPv3DirectoryEntry) vector.elementAt(i);
                 if(aa.filename.startsWith("FD")){
                  sftPv3Client.mv(PropertiesUtil.readProperty("correos", "correospfDirTemp")+aa.filename,PropertiesUtil.readProperty("correos", "correospfDir")+aa.filename);
                 }
                }

                sftPv3Client.close();
                connsft.close();
                LogUtil.log(" 发送报文-上传西邮服务器报文成功！");
            }

        }
    }

    public static void main(String[] args) throws Exception {
        Connection conn = ConnectionFactory.get194Connection();
        String historyRootPath="D:/express/SinoairEDIServerHistory";
        SendManifestService generateInfo = new SendManifestService();
        generateInfo.sendManifest(conn,historyRootPath);

    }
}
