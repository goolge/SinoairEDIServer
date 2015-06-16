package com.sinoair.iemisgateway.cainiao.action;

import com.sinoair.iemisgateway.cainiao.service.PushTrace2CainiaoService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-5-13
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class PushTrace2CainiaoAction {
    public static void main(String[] args)  {
        PushTrace2CainiaoService pushTrace2CainiaoService = new PushTrace2CainiaoService();
        try {
            pushTrace2CainiaoService.push();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
