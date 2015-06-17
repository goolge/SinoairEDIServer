package com.sinoair.iemisgateway.cainiao.action;

import com.sinoair.iemisgateway.cainiao.service.PushTrace2CainiaoService;

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
            pushTrace2CainiaoService.push();
    }
}
