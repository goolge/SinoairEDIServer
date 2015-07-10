package com.sinoair.iemisgateway.util.iemisWSClient.common;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Created by WangXX4 on 2015/7/8.
 */
public class CommonClient {
    public String execute(String className,String xml) throws Exception {
          String result = null;
          try {
              //todo-wxx-n 培训
              String CommonServiceHttpSoap11Endpoint_address = "http://172.17.0.229:7001/services/CommonService.CommonServiceHttpSoap12Endpoint/";
              //todo-wxx-n 生产
  //            String CommonServiceHttpSoap11Endpoint_address = "http://172.17.0.191:7001/services/CommonService.CommonServiceHttpSoap11Endpoint/";
              CommonServiceLocator locator = new CommonServiceLocator();
              locator.setCommonServiceHttpSoap11EndpointEndpointAddress(CommonServiceHttpSoap11Endpoint_address);
              CommonServicePortType service = locator.getCommonServiceHttpSoap11Endpoint();
              result = service.execute(className,xml);
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
