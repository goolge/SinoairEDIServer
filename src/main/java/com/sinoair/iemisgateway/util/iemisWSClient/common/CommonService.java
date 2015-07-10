/**
 * CommonService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sinoair.iemisgateway.util.iemisWSClient.common;

public interface CommonService extends javax.xml.rpc.Service {
    public java.lang.String getCommonServiceHttpSoap11EndpointAddress();

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException;

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getCommonServiceHttpSoap12EndpointAddress();

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException;

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
