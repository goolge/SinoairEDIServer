package com.sinoair.iemisgateway.cainiao.action;

import org.junit.Test;

import static org.junit.Assert.*;

public class PushTrace2CainiaoActionTest {

    @Test
    public void testMain() throws Exception {
        PushTrace2CainiaoAction pushTrace2CainiaoAction = new PushTrace2CainiaoAction();
        pushTrace2CainiaoAction.main(new String[]{"1"});
    }
}