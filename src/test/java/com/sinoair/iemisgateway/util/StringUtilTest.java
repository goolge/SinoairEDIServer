package com.sinoair.iemisgateway.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-17
 * Time: 下午5:33
 * To change this template use File | Settings | File Templates.
 */
public class StringUtilTest {
    @Test
    public void testCalculate() throws Exception {
       // System.out.println("hhah:"+ StringUtil.calculate("0B0418383000","94286899"));
   //Assert.assertEquals("0B012722900021AC35B21", StringUtil.calculate("0B0127229000","21AC35B2"));
  // Assert.assertEquals("0B012722900021AC35B21", StringUtil.calculate("0B0418383000","94286899"));
    /*Assert.assertEquals("0B0418383000113701162", StringUtil.calculate("0B0418383000","11370116"));
    Assert.assertEquals("0B041838300011370124F", StringUtil.calculate("0B0418383000","11370124"));
    Assert.assertEquals("0B0418383000000369948", StringUtil.calculate("0B0418383000","00036994"));
*/
    //Assert.assertEquals("0B012722900021AC35B21", StringUtil.calculate("0B012722900021AC35B2"));
    Assert.assertEquals("0B012722900021AC35B21", StringUtil.calculate("OB041838300027112411"));
   /* Assert.assertEquals("0B0418383000113701162", StringUtil.calculate("0B041838300011370116"));
    Assert.assertEquals("0B041838300011370124F", StringUtil.calculate("0B041838300011370124"));
    Assert.assertEquals("0B0418383000000369948", StringUtil.calculate("0B041838300000036994"));*/

    }

    @Test
    public void testGetRMLetterByNum() throws Exception {
        Assert.assertEquals("A", StringUtil.getRMLetterByNum(10));
        Assert.assertEquals("B", StringUtil.getRMLetterByNum(11));
        Assert.assertEquals("C", StringUtil.getRMLetterByNum(12));
        Assert.assertEquals("D", StringUtil.getRMLetterByNum(13));
        Assert.assertEquals("E", StringUtil.getRMLetterByNum(14));
        Assert.assertEquals("F", StringUtil.getRMLetterByNum(15));
        Assert.assertEquals("1", StringUtil.getRMLetterByNum(1));

    }

    @Test
    public void testGetRMNumByLetter() throws Exception {
               Assert.assertEquals(10, StringUtil.getRMNumByLetter("A"));
               Assert.assertEquals(11, StringUtil.getRMNumByLetter("B"));
               Assert.assertEquals(12, StringUtil.getRMNumByLetter("C"));
               Assert.assertEquals(13, StringUtil.getRMNumByLetter("D"));
               Assert.assertEquals(14, StringUtil.getRMNumByLetter("E"));
               Assert.assertEquals(15, StringUtil.getRMNumByLetter("F"));



    }

    @Test
    public void testGetHexadecimal() throws Exception {
               Assert.assertEquals(7, StringUtil.getHexadecimal(22));
               Assert.assertEquals(9, StringUtil.getHexadecimal(24));
               Assert.assertEquals(7, StringUtil.getHexadecimal(14));
               Assert.assertEquals(2, StringUtil.getHexadecimal(2));
    }
}
