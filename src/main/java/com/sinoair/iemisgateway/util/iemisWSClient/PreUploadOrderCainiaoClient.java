package com.sinoair.iemisgateway.util.iemisWSClient;

import com.sinoair.iemisgateway.util.iemisWSClient.common.CommonClient;

/**
 * Created by WangXX4 on 2015/7/8.
 */
public class PreUploadOrderCainiaoClient{
    public String execute(String xml) throws Exception {
        CommonClient commonClient = new CommonClient();
        return commonClient.execute("PreUploadOrderCainiao", xml);
    }
}
