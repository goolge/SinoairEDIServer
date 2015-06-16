package com.sinoair.iemisgateway.util.iemisWSClient.uploadOrder;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-9
 * Time: 上午9:09
 * To change this template use File | Settings | File Templates.
 */
public class UploadOrderClient {
    public String uploadOrder(String xml) throws Exception {
        String result = null;
        try {
            //TODO-WXX-N    培训
            String UploadOrderServiceHttpSoap12Endpoint_address = "http://172.17.0.229:7001/services/UploadOrderService.UploadOrderServiceHttpSoap12Endpoint/";

            //TODO-WXX-N    生产
//            String UploadOrderServiceHttpSoap12Endpoint_address = "http://172.17.0.191:7001/services/UploadOrderService.UploadOrderServiceHttpSoap12Endpoint/";

            UploadOrderServiceLocator locator = new UploadOrderServiceLocator();
            locator.setUploadOrderServiceHttpSoap12EndpointEndpointAddress(UploadOrderServiceHttpSoap12Endpoint_address);
            UploadOrderServicePortType service = locator.getUploadOrderServiceHttpSoap12Endpoint();
            result = service.uploadOrder(xml);
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
