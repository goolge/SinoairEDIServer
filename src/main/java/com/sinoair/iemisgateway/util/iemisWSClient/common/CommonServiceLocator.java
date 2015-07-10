/**
 * CommonServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sinoair.iemisgateway.util.iemisWSClient.common;

public class CommonServiceLocator extends org.apache.axis.client.Service implements com.sinoair.iemisgateway.util.iemisWSClient.common.CommonService {

    public CommonServiceLocator() {
    }


    public CommonServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CommonServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CommonServiceHttpSoap11Endpoint
    private java.lang.String CommonServiceHttpSoap11Endpoint_address = "http://172.17.0.229:7001/services/CommonService.CommonServiceHttpSoap11Endpoint/";

    public java.lang.String getCommonServiceHttpSoap11EndpointAddress() {
        return CommonServiceHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CommonServiceHttpSoap11EndpointWSDDServiceName = "CommonServiceHttpSoap11Endpoint";

    public java.lang.String getCommonServiceHttpSoap11EndpointWSDDServiceName() {
        return CommonServiceHttpSoap11EndpointWSDDServiceName;
    }

    public void setCommonServiceHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        CommonServiceHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CommonServiceHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCommonServiceHttpSoap11Endpoint(endpoint);
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap11BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap11BindingStub(portAddress, this);
            _stub.setPortName(getCommonServiceHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCommonServiceHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        CommonServiceHttpSoap11Endpoint_address = address;
    }


    // Use to get a proxy class for CommonServiceHttpSoap12Endpoint
    private java.lang.String CommonServiceHttpSoap12Endpoint_address = "http://172.17.0.229:7001/services/CommonService.CommonServiceHttpSoap12Endpoint/";

    public java.lang.String getCommonServiceHttpSoap12EndpointAddress() {
        return CommonServiceHttpSoap12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CommonServiceHttpSoap12EndpointWSDDServiceName = "CommonServiceHttpSoap12Endpoint";

    public java.lang.String getCommonServiceHttpSoap12EndpointWSDDServiceName() {
        return CommonServiceHttpSoap12EndpointWSDDServiceName;
    }

    public void setCommonServiceHttpSoap12EndpointWSDDServiceName(java.lang.String name) {
        CommonServiceHttpSoap12EndpointWSDDServiceName = name;
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CommonServiceHttpSoap12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCommonServiceHttpSoap12Endpoint(endpoint);
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType getCommonServiceHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap12BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap12BindingStub(portAddress, this);
            _stub.setPortName(getCommonServiceHttpSoap12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCommonServiceHttpSoap12EndpointEndpointAddress(java.lang.String address) {
        CommonServiceHttpSoap12Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap11BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap11BindingStub(new java.net.URL(CommonServiceHttpSoap11Endpoint_address), this);
                _stub.setPortName(getCommonServiceHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
            if (com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap12BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.common.CommonServiceSoap12BindingStub(new java.net.URL(CommonServiceHttpSoap12Endpoint_address), this);
                _stub.setPortName(getCommonServiceHttpSoap12EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CommonServiceHttpSoap11Endpoint".equals(inputPortName)) {
            return getCommonServiceHttpSoap11Endpoint();
        }
        else if ("CommonServiceHttpSoap12Endpoint".equals(inputPortName)) {
            return getCommonServiceHttpSoap12Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "CommonService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "CommonServiceHttpSoap11Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "CommonServiceHttpSoap12Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CommonServiceHttpSoap11Endpoint".equals(portName)) {
            setCommonServiceHttpSoap11EndpointEndpointAddress(address);
        }
        else 
if ("CommonServiceHttpSoap12Endpoint".equals(portName)) {
            setCommonServiceHttpSoap12EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
