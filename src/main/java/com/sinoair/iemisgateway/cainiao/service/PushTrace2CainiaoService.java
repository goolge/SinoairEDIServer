package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.cainiao.domain.TraceRequest2Cainiao;
import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.ConnectionFactory;
import com.sinoair.iemisgateway.util.HttpRequestUtil;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-15
 * Time: 上午9:02
 * To change this template use File | Settings | File Templates.
 */
public class PushTrace2CainiaoService {
    public  void push() {
        Connection connection = null;
        Statement statement = null;
        Statement statement4Update = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnectionInProperties();
//            connection = ConnectionFactory.get200Connection();//todo 测试
//            connection = ConnectionFactory.get194Connection();//todo 生产
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement4Update = connection.createStatement();
            resultSet = getResultSet(statement);

            int successRow = 0;
            int failedRow = 0;
            int line=0;
            while (resultSet.next()) {
                line++;
                if(line%100==0){
                    connection.commit();
                }
                String eba_syscode = resultSet.getString("eba_syscode");

                TraceRequest2Cainiao traceRequest2Cainiao = getTraceRequest2Cainiao(resultSet);
                String result = "";
                result = pushTrace2Cainiao(traceRequest2Cainiao);
                String QA = "";
                if (checkCainiaoReturn(result)) {
                    QA = "s";
                    successRow++;
                } else {
                    QA = "f";
                    failedRow++;
                }
                String updateSql = "update expressbusinessactivity set QA='" + QA + "' where eba_syscode='" + eba_syscode + "'";
                BaseLogger.info("updateSql = " + updateSql);
                statement4Update.executeUpdate(updateSql);

            }
            BaseLogger.info(new Date().toString() + "---------------------共给菜鸟发送条" + (successRow + failedRow) + "轨迹;发送成功" + successRow + "条;发送失败" + failedRow + "条------------------------");
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private static TraceRequest2Cainiao getTraceRequest2Cainiao(ResultSet resultSet) throws SQLException {
        TraceRequest2Cainiao traceRequest2Cainiao = new TraceRequest2Cainiao();
        String facilityType = "1";// 暂时写死
        String facilityNo = "01010001";// 暂时写死
        String facilityName = "sinoair";//暂时写死
        traceRequest2Cainiao.setFacilityType(facilityType);
        traceRequest2Cainiao.setFacilityNo(facilityNo);
        traceRequest2Cainiao.setFacilityName(facilityName);

        String logistic_provider_id = resultSet.getString("eawb_servicetype"); //CP编号CPCode（菜鸟会提供）
        String mailNos = resultSet.getString("eawb_reference1");
        String txLogisticID = resultSet.getString("eawb_reference2");
        String time = resultSet.getString("eba_occurtime");
        String desc = resultSet.getString("eba_remark");
        String city = resultSet.getString("eba_occurplace");
        traceRequest2Cainiao.setLogistic_provider_id(logistic_provider_id);
        traceRequest2Cainiao.setMailNos(mailNos);
        traceRequest2Cainiao.setTxLogisticID(txLogisticID);
        traceRequest2Cainiao.setTime(time);
        traceRequest2Cainiao.setCity(city);

        traceRequest2Cainiao = translateAction2Cainiao(resultSet.getString("ead_code"), resultSet.getString("east_code"), desc, traceRequest2Cainiao);
        return traceRequest2Cainiao;
    }

    private static ResultSet getResultSet(Statement statement) throws SQLException {
        String sql = "select eba_syscode,ep.eawb_printcode,eawb_servicetype,eawb_reference1,eawb_reference2,eba_occurtime,eba_remark,eba_occurplace,ead_code, east_code" +
                " from eawbpre ep, expressbusinessactivity eba" +
                " where ep.eawb_printcode = eba.eawb_printcode" +
                " AND (EBA.QA IS NULL OR EBA.QA <> 's')" +
                "AND " +
                "(ead_code IN ('FC_INBOUND','DELIVERY') " +
                "OR east_code IN ('ASS','ASF','CP','CLRD','CUSN','SI','RM','DEF') )" +
                " and ep.eawb_so_code ='00060491'" +
                " AND ep.eawb_handletime>SYSDATE-60";//todo 为了提高查询效率，只查找最近两个月处理的单子
        BaseLogger.info(new Date().toString() + "查询最近两个月所有的需要发给菜鸟的轨迹:" + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }


    /**
     * 检查上传菜鸟的轨迹是否被菜鸟正常接收
     *
     * @param xml
     * @return
     */
    private static boolean checkCainiaoReturn(String xml) {//todo
        if (xml == null) {
            return false;
        } else if (xml.toUpperCase().contains("<SUCCESS>TRUE</SUCCESS>")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 翻译菜鸟的环节，而后装饰TraceRequest2
     * @param ead_code
     * @param east_code
     * @param desc
     * @param traceRequest2Cainiao
     * @return
     */
    private static TraceRequest2Cainiao translateAction2Cainiao(String ead_code, String east_code, String desc, TraceRequest2Cainiao traceRequest2Cainiao) {//todo
        String action = "";
        //todo 得判断不能为空或者null或者“null”，否则菜鸟不显示轨迹
        if ("FC_INBOUND".equals(ead_code)) {
            action = "CAI_GOT";
            desc = "Got the item from yanwen.";
        } else if ("ASS".equals(east_code)) {
            action = "CAI_AIR_DELIVERY";
            desc = "Aviation security success.";
        } else if ("ASF".equals(east_code)) {
            action = "CAI_AIR_DELIVERY_FAIL";
            desc = "Aviation security failed" + ((desc == null || desc.equals("") || desc.equalsIgnoreCase("null")) ? "." : ":" + desc);
        } else if ("CP".equals(east_code)) {
            action = "CAI_CUSTOMS_CLR_SUC";
        } else if ("CLRD".equals(east_code)) {
            action = "CAI_CUSTOMS_CLR";
        } else if ("CUSN".equals(east_code)) {
            action = "CAI_CUSTOMS_CLR_FAIL";
        } else if ("SI".equals(east_code)) {
            action = "CAI_AE_PROCESS";
            desc = (desc == null || desc.equals("") || desc.equalsIgnoreCase("null")) ? "" : desc;
        } else if ("RM".equals(east_code)) {
            action = "CAI_AE_ARRIVED";
            desc = (desc == null || desc.equals("") || desc.equalsIgnoreCase("null")) ? "" : desc;
        }else if ("DELIVERY".equals(ead_code)) {
            action = "CAI_SIGN_IN";
            desc = (desc == null || desc.equals("") || desc.equalsIgnoreCase("null")) ? "" : desc;
        }else if ("DEF".equals(east_code)) {
            action = "CAI_SIGN_IN_FAIL";
            desc = (desc == null || desc.equals("") || desc.equalsIgnoreCase("null")) ? "" : desc;
        }
        traceRequest2Cainiao.setAction(action);
        traceRequest2Cainiao.setDesc(desc);
        return traceRequest2Cainiao;
    }

    /**
     *
     * @param traceRequest2Cainiao pojo
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */

    public String pushTrace2Cainiao(TraceRequest2Cainiao traceRequest2Cainiao) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String logistics_interface = traceRequest2Cainiao.combiteTraceXml4Cainiao();
        return pushTrace2Cainiao(logistics_interface, traceRequest2Cainiao.getLogistic_provider_id());
    }

    /**
     * @param logistics_interface  消息内容(message content)
     * @param logistic_provider_id
     * @return
     */
    public String pushTrace2Cainiao(String logistics_interface, String logistic_provider_id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String requestUrl2CainiaoTraces = "http://pac.partner.taobao.com/gateway/pac_message_receiver.do"; //todo 生产
        String session_type = "";
        requestUrl2CainiaoTraces = "http://pac.partner.taobao.net/gateway/pac_message_receiver.do";  // todo 测试
//            requestUrl = "http://pac.tbsandbox.com/gateway/pac_message_receiver.do"; //todo 联调平台
//            session_type = "&session_type=debug";//todo 联调平台
        requestUrl2CainiaoTraces = PropertiesUtil.readProperty("cainiao", "requestUrl2CainiaoTraces");
        BaseLogger.info("requestUrl2CainiaoTraces = " + requestUrl2CainiaoTraces);
        String msg_type = "TRACEPUSH"; //消息类型默认是TRACEPUSH
        String signKey = PropertiesUtil.readProperty("cainiao", "signKey");//秘钥
        BaseLogger.info("signKey = " + signKey + "---");
        String data_digest = doSign(logistics_interface, signKey); //消息正文的摘要（签名）
        String requestParam = "logistics_interface=" + URLEncoder.encode(logistics_interface, "utf-8") +
                "&logistic_provider_id=" + logistic_provider_id +
                "&msg_type=" + msg_type +
                session_type +
                "&data_digest=" + URLEncoder.encode(data_digest, "utf-8");

        String resultPost = HttpRequestUtil.sendPost(requestUrl2CainiaoTraces, requestParam);
        return resultPost;
    }

    /**
     * 淘宝加密
     * @param content
     * @param keys
     * @return
     */
    public  String doSign(String content, String keys) {
        String sign = "";
        content = content + keys;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes("utf-8"));
            sign = new String(Base64.encodeBase64(md.digest()), "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sign;
    }

}
