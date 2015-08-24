package com.sinoair.iemisgateway.cainiao.service;

import com.sinoair.iemisgateway.cainiao.domain.TraceRequest2Cainiao;
import com.sinoair.iemisgateway.domain.ExpressEdiLog;
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
    public void push() {
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
            int line = 0;
            while (resultSet.next()) {
                line++;
                if (line % 100 == 0) {
                    BaseLogger.info("commit once erery 100 line");
                    connection.commit();
                }
                String eba_syscode = resultSet.getString("eba_syscode");

                TraceRequest2Cainiao traceRequest2Cainiao = getTraceRequest2Cainiao(resultSet);
                String result = "";
                String logistics_interface = traceRequest2Cainiao.combiteTraceXml4Cainiao();
                BaseLogger.info(logistics_interface);
                String QA = "";
                try {
                    result = pushTrace2Cainiao(logistics_interface, traceRequest2Cainiao.getLogistic_provider_id());
                    if (checkCainiaoReturn(result)) {
                        QA = "s";
                        successRow++;
                        new ExpressEdiLog("cainiao","pushTrace2Cainiao",logistics_interface,result,ExpressEdiLog.is_not_exception,"").insertRecord();
                    } else {
                        QA = "f";
                        failedRow++;
                        new ExpressEdiLog("cainiao","pushTrace2Cainiao",logistics_interface,result,ExpressEdiLog.is_exception,"").insertRecord();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    QA = "f";
                    failedRow++;
                    new ExpressEdiLog("cainiao","pushTrace2Cainiao",logistics_interface,result,ExpressEdiLog.is_exception,e.getMessage()).insertRecord();
                }

                String updateSql = "update expressbusinessactivity set QA='" + QA + "' where eba_syscode='" + eba_syscode + "'";
                BaseLogger.info("updateSql = " + updateSql);
                statement4Update.executeUpdate(updateSql);

            }
            BaseLogger.info(new Date().toString() + "---------------------共给菜鸟发送条" + (successRow + failedRow) + "轨迹;发送成功" + successRow + "条;发送失败" + failedRow + "条------------------------");
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
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
        String sinoair_desc = resultSet.getString("eba_remark");
        String city = resultSet.getString("eba_occurplace");
        String cainiao_desc = resultSet.getString("EAT_PARTNER_ACTIVITY_DESC");
        String action = resultSet.getString("eat_partner_activity_code");
        traceRequest2Cainiao.setLogistic_provider_id(logistic_provider_id);
        traceRequest2Cainiao.setMailNos(mailNos);
        traceRequest2Cainiao.setTxLogisticID(txLogisticID);
        traceRequest2Cainiao.setTime(time);
        traceRequest2Cainiao.setCity(city);
        traceRequest2Cainiao.setAction(action);
        traceRequest2Cainiao.setDesc(formatDesc(sinoair_desc, cainiao_desc, city));
//        traceRequest2Cainiao = translateAction2Cainiao(resultSet.getString("ead_code"), resultSet.getString("east_code"), desc, traceRequest2Cainiao);
        return traceRequest2Cainiao;
    }

    protected static String formatDesc(String sinoair_desc, String cainiao_desc, String city) {
        String desc = cainiao_desc.replace("City", city);
        if (sinoair_desc == null || "".equals(sinoair_desc) || "null".equalsIgnoreCase(sinoair_desc)) {
            desc = desc + ".";
        } else {
            desc = desc + ":" + sinoair_desc;
        }
        return desc;
    }

    private static ResultSet getResultSet(Statement statement) throws SQLException {
        String date=PropertiesUtil.readProperty("cainiao","date");
        String sql = "SELECT EBA_SYSCODE,EP.EAWB_PRINTCODE,EAWB_SERVICETYPE,EAWB_REFERENCE1,EAWB_REFERENCE2,EBA_OCCURTIME,EBA_REMARK,EBA.EBA_OCCURPLACE,EBA.EAD_CODE,EBA.EAST_CODE,EAT.EAT_PARTNER_ACTIVITY_DESC,EAT.EAT_PARTNER_ACTIVITY_CODE\n  FROM EAWBPRE EP, EXPRESSBUSINESSACTIVITY EBA,EXPRESSACTIVITYTRANSLATE EAT\n" +
                " WHERE EP.EAWB_PRINTCODE = EBA.EAWB_PRINTCODE\n" +
                " AND EAT.EAD_CODE=EBA.EAD_CODE\n" +
                " AND EAT.EAST_CODE=EBA.EAST_CODE\n" +
                " AND EAT.EAT_PARTNER_ID='cainiao'\n" +
                " AND (EBA.QA IS NULL OR EBA.QA <> 's')\n" +
                " AND EP.EAWB_SO_CODE = '00060491'\n" +
                " AND EP.EAWB_HANDLETIME > "+date;//todo 为了提高查询效率，只查找最近两个月处理的单子
        BaseLogger.info("查询最近两个月所有的需要发给菜鸟的轨迹:\n" + sql);
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

        String resultPost = "";
        resultPost = HttpRequestUtil.sendPost(requestUrl2CainiaoTraces, requestParam);
        return resultPost;
    }

    /**
     * 淘宝加密
     *
     * @param content
     * @param keys
     * @return
     */
    public String doSign(String content, String keys) {
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
