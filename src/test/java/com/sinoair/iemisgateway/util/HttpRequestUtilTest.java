package com.sinoair.iemisgateway.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.*;

import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-2
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class HttpRequestUtilTest {
    String requestUrl = "";
    String requestParam = "";

    @Before
    public void setUp() throws Exception {
        requestUrl = "http://pac.tbsandbox.com/gateway/pac_message_receiver.do"; //todo 联调平台
        String logistics_interface = "wxx";//消息内容(message content)
        String logistic_provider_id = "DISTRIBUTOR_902950"; //CP编号CPCode（菜鸟会提供）
        String msg_type = "TRACEPUSH"; //消息类型默认是TRACEPUSH
        String data_digest = ""; //消息正文的摘要（签名）
        String signKey = "v6lfQ5XH677s5I4835tgecOOBmZ9u7T9"; //秘钥
        String rawStr = logistics_interface + signKey;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        data_digest = new String(Base64.encode(md5.digest(rawStr.getBytes("utf-8"))));
        BaseLogger.info("data_digest = " + data_digest);
        String session_type = "debug";//todo 联调平台
        requestParam = "logistics_interface=" + URLEncoder.encode(logistics_interface, "utf-8") +
                "&logistic_provider_id=" + logistic_provider_id +
                "&msg_type=" + msg_type +
                "&session_type=" + session_type +
                "&data_digest=" + URLEncoder.encode(data_digest, "utf-8");
        BaseLogger.info("requestUrl = " + requestUrl);
        BaseLogger.info("requestParam = " + requestParam);
        BaseLogger.info("requestUrl?requestParam = " + requestUrl + "?" + requestParam);
    }

    @After
    public void tearDown() throws Exception {

    }
    @Ignore
    @Test
    public void testSendGet() throws Exception {
        //发送 GET 请求
        String resultExcepted = "<response><success>false</success><errorCode>NULL_VALUE</errorCode><errorMsg>没有要执行回传的任务用例</errorMsg></response>";
                String resultActual = HttpRequestUtil.sendGet(requestUrl, requestParam);
                BaseLogger.info("resultExcepted = " + resultExcepted);
                BaseLogger.info("resultActual = " + resultActual);
                Assert.assertEquals(resultExcepted, resultActual);
    }
    @Ignore
    @Test
    public void testSendPost() throws Exception {
        //发送 POST 请求
         String resultExcepted = "<response><success>false</success><errorCode>NULL_VALUE</errorCode><errorMsg>没有要执行回传的任务用例</errorMsg></response>";
                String resultActual = HttpRequestUtil.sendPost(requestUrl, requestParam);
                BaseLogger.info("resultExcepted = " + resultExcepted);
                BaseLogger.info("resultActual = " + resultActual);
                Assert.assertEquals(resultExcepted, resultActual);
    }
}
