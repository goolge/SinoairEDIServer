package com.sinoair.iemisgateway.util.iemisWSClient.updateOrder;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 14-7-9
 * Time: 上午9:09
 * To change this template use File | Settings | File Templates.
 */
public class UpdateOrderClient {


    public String updateOrder(String xml) throws Exception {
        String result = null;
        try {
            //todo-wxx-n 培训
            String UpdateOrderServiceHttpSoap11Endpoint_address = "http://172.17.0.229:7001/services/UpdateOrderService.UpdateOrderServiceHttpSoap11Endpoint/";
            //todo-wxx-n 生产
//            String UpdateOrderServiceHttpSoap11Endpoint_address = "http://172.17.0.191:7001/services/UpdateOrderService.UpdateOrderServiceHttpSoap11Endpoint/";
            UpdateOrderServiceLocator locator = new UpdateOrderServiceLocator();
            locator.setUpdateOrderServiceHttpSoap11EndpointEndpointAddress(UpdateOrderServiceHttpSoap11Endpoint_address);
            UpdateOrderServicePortType service = locator.getUpdateOrderServiceHttpSoap11Endpoint();
            result = service.updateOrder(xml);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new Exception("redirect error");//todo-wxx-u 这个名字太老土了
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new Exception("redirect error");
        }
        return result;
    }
}
