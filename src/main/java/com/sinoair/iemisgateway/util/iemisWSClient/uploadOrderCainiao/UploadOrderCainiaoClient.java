package com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao;


import com.sinoair.iemisgateway.util.PropertiesUtil;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-9
 * Time: 上午9:09
 * To change this template use File | Settings | File Templates.
 */
public class UploadOrderCainiaoClient {
    public String uploadOrderCainiao(String xml) throws Exception {
        String result = null;
        try {

            String UploadOrderCainiaoServiceHttpSoap12Endpoint_address = PropertiesUtil.readProperty("cainiao", "UploadOrderCainiaoServiceHttpSoap12Endpoint_address");
            BaseLogger.info("UploadOrderCainiaoServiceHttpSoap12Endpoint_address = " + UploadOrderCainiaoServiceHttpSoap12Endpoint_address);
            UploadOrderCainiaoServiceLocator locator = new UploadOrderCainiaoServiceLocator();
            locator.setUploadOrderCainiaoServiceHttpSoap12EndpointEndpointAddress(UploadOrderCainiaoServiceHttpSoap12Endpoint_address);
            UploadOrderCainiaoServicePortType service = locator.getUploadOrderCainiaoServiceHttpSoap12Endpoint();
            result = service.uploadOrderCainiao(xml);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new Exception("redirect error");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new Exception("redirect error");
        }
        return result;
    }
}
