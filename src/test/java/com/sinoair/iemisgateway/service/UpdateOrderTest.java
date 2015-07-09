package com.sinoair.iemisgateway.service;

import com.sinoair.iemisgateway.util.BaseLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-14
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
public class UpdateOrderTest {
    @Test
    public void say(){
        BaseLogger.info("UpdateOrderTest.say");
    }
// //    @Test
    public void testUpdateOrder() throws Exception {
        Assert.assertEquals(resultEX, updateOrder.updateOrder(xml)); //正常情况
        Assert.assertEquals(resultEX1, updateOrder.updateOrder(xml1)); //xml格式不对
        Assert.assertEquals(resultEX2, updateOrder.updateOrder(xml2)); //用户名密码错误
        Assert.assertEquals(resultEX3, updateOrder.updateOrder(xml3)); //86号不存在

    }
    String xml = "";
    String xml1 = "";
    String xml2 = "";
    String xml3 = "";
    String resultEX = "";
    String resultEX1 = "";
    String resultEX2 = "";
    String resultEX3 = "";
    UpdateOrder updateOrder;
    @Before
    public void init() {
        updateOrder = new UpdateOrder();
        xml = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSGET>\n" +
                "    <AccessRequest>\n" +
                "        <username>jolly</username>\n" +
                "        <password>jolly</password>\n" +
                "    </AccessRequest>\n" +
                "    <Orders>\n" +
                "        <Order>\n" +
                "            <!--sinoair number-->\n" +
                "            <SinoairNO>860113859745</SinoairNO>\n" +
                "            <!--jolly number-->\n" +
                "            <OrderNo>123456</OrderNo>\n" +
                "            <!-- 预报实重-->\n" +
                "            <actualWeight>8.9</actualWeight>\n" +
                "            <!-- 预报体积重-->\n" +
                "            <cubWeight>4.4</cubWeight>\n" +
                "            <!-- 海关申报价值-->\n" +
                "            <declareValue>19.27</declareValue>\n" +
                "        </Order>\n" +
                "    </Orders>\n" +
                "</WSGET>";
        resultEX = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSRETURN>\n" +
                "    <Orders>\n" +
                "        <Order>\n" +
                "            <SinoairNO>860113859745</SinoairNO>\n" +
                "            <Status>Y</Status>\n" +
                "            <Comments></Comments>\n" +
                "        </Order>\n" +
                "    </Orders>\n" +
                "</WSRETURN>";
        xml1 = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "    <AccessRequest>\n" +
                "        <username>jolly</username>\n" +
                "        <password>jolly</password>\n" +
                "    </AccessRequest>\n" +
                "    <Orders>\n" +
                "        <Order>\n" +
                "            <!--sinoair number-->\n" +
                "            <SinoairNO>860113859745</SinoairNO>\n" +
                "            <!--jolly number-->\n" +
                "            <OrderNo>123456</OrderNo>\n" +
                "            <!-- 预报实重-->\n" +
                "            <actualWeight>8.9</actualWeight>\n" +
                "            <!-- 预报体积重-->\n" +
                "            <cubWeight>4.4</cubWeight>\n" +
                "            <!-- 海关申报价值-->\n" +
                "            <declareValue>19.27</declareValue>\n" +
                "        </Order>\n" +
                "    </Orders>\n" +
                "</WSGET>";
        resultEX1 = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSRETURN>\n" +
                "    <Error>invalid xml</Error>\n" +
                "</WSRETURN>";
        xml2 = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSGET>\n" +
                "    <AccessRequest>\n" +
                "        <username>jolly2</username>\n" +
                "        <password>jolly</password>\n" +
                "    </AccessRequest>\n" +
                "    <Orders>\n" +
                "        <Order>\n" +
                "            <!--sinoair number-->\n" +
                "            <SinoairNO>860113859745</SinoairNO>\n" +
                "            <!--jolly number-->\n" +
                "            <OrderNo>123456</OrderNo>\n" +
                "            <!-- 预报实重-->\n" +
                "            <actualWeight>8.9</actualWeight>\n" +
                "            <!-- 预报体积重-->\n" +
                "            <cubWeight>4.4</cubWeight>\n" +
                "            <!-- 海关申报价值-->\n" +
                "            <declareValue>19.27</declareValue>\n" +
                "        </Order>\n" +
                "    </Orders>\n" +
                "</WSGET>";
        resultEX2 = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSRETURN>\n" +
                "    <Error>wrong name or password</Error>\n" +
                "</WSRETURN>";
        xml3 = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSGET>\n" +
                "    <AccessRequest>\n" +
                "        <username>jolly</username>\n" +
                "        <password>jolly</password>\n" +
                "    </AccessRequest>\n" +
                "    <Orders>\n" +
                "        <Order>\n" +
                "            <!--sinoair number-->\n" +
                "            <SinoairNO>11111111111</SinoairNO>\n" +
                "            <!--jolly number-->\n" +
                "            <OrderNo>123456</OrderNo>\n" +
                "            <!-- 预报实重-->\n" +
                "            <actualWeight>8.9</actualWeight>\n" +
                "            <!-- 预报体积重-->\n" +
                "            <cubWeight>4.4</cubWeight>\n" +
                "            <!-- 海关申报价值-->\n" +
                "            <declareValue>19.27</declareValue>\n" +
                "        </Order>\n" +
                "    </Orders>\n" +
                "</WSGET>";
        resultEX3 = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<WSRETURN>\n" +
                "    <Orders>\n" +
                "        <Order>\n" +
                "            <SinoairNO>11111111111</SinoairNO>\n" +
                "            <Status>N</Status>\n" +
                "            <Comments>SinoairNO not found</Comments>\n" +
                "        </Order>\n" +
                "    </Orders>\n" +
                "</WSRETURN>";

    }
}
