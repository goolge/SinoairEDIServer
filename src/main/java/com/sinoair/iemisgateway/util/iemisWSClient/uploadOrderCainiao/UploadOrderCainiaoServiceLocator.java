/**
 * UploadOrderCainiaoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao;

public class UploadOrderCainiaoServiceLocator extends org.apache.axis.client.Service implements com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoService {

    public UploadOrderCainiaoServiceLocator() {
    }


    public UploadOrderCainiaoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UploadOrderCainiaoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UploadOrderCainiaoServiceHttpSoap12Endpoint
    private java.lang.String UploadOrderCainiaoServiceHttpSoap12Endpoint_address = "http://172.17.0.229:7001/services/UploadOrderCainiaoService.UploadOrderCainiaoServiceHttpSoap12Endpoint/";

    public java.lang.String getUploadOrderCainiaoServiceHttpSoap12EndpointAddress() {
        return UploadOrderCainiaoServiceHttpSoap12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UploadOrderCainiaoServiceHttpSoap12EndpointWSDDServiceName = "UploadOrderCainiaoServiceHttpSoap12Endpoint";

    public java.lang.String getUploadOrderCainiaoServiceHttpSoap12EndpointWSDDServiceName() {
        return UploadOrderCainiaoServiceHttpSoap12EndpointWSDDServiceName;
    }

    public void setUploadOrderCainiaoServiceHttpSoap12EndpointWSDDServiceName(java.lang.String name) {
        UploadOrderCainiaoServiceHttpSoap12EndpointWSDDServiceName = name;
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServicePortType getUploadOrderCainiaoServiceHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UploadOrderCainiaoServiceHttpSoap12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUploadOrderCainiaoServiceHttpSoap12Endpoint(endpoint);
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServicePortType getUploadOrderCainiaoServiceHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap12BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap12BindingStub(portAddress, this);
            _stub.setPortName(getUploadOrderCainiaoServiceHttpSoap12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUploadOrderCainiaoServiceHttpSoap12EndpointEndpointAddress(java.lang.String address) {
        UploadOrderCainiaoServiceHttpSoap12Endpoint_address = address;
    }


    // Use to get a proxy class for UploadOrderCainiaoServiceHttpSoap11Endpoint
    private java.lang.String UploadOrderCainiaoServiceHttpSoap11Endpoint_address = "http://172.17.0.229:7001/services/UploadOrderCainiaoService.UploadOrderCainiaoServiceHttpSoap11Endpoint/";

    public java.lang.String getUploadOrderCainiaoServiceHttpSoap11EndpointAddress() {
        return UploadOrderCainiaoServiceHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UploadOrderCainiaoServiceHttpSoap11EndpointWSDDServiceName = "UploadOrderCainiaoServiceHttpSoap11Endpoint";

    public java.lang.String getUploadOrderCainiaoServiceHttpSoap11EndpointWSDDServiceName() {
        return UploadOrderCainiaoServiceHttpSoap11EndpointWSDDServiceName;
    }

    public void setUploadOrderCainiaoServiceHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        UploadOrderCainiaoServiceHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServicePortType getUploadOrderCainiaoServiceHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UploadOrderCainiaoServiceHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUploadOrderCainiaoServiceHttpSoap11Endpoint(endpoint);
    }

    public com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServicePortType getUploadOrderCainiaoServiceHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap11BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap11BindingStub(portAddress, this);
            _stub.setPortName(getUploadOrderCainiaoServiceHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUploadOrderCainiaoServiceHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        UploadOrderCainiaoServiceHttpSoap11Endpoint_address = address;
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
            if (com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap12BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap12BindingStub(new java.net.URL(UploadOrderCainiaoServiceHttpSoap12Endpoint_address), this);
                _stub.setPortName(getUploadOrderCainiaoServiceHttpSoap12EndpointWSDDServiceName());
                return _stub;
            }
            if (com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap11BindingStub _stub = new com.sinoair.iemisgateway.util.iemisWSClient.uploadOrderCainiao.UploadOrderCainiaoServiceSoap11BindingStub(new java.net.URL(UploadOrderCainiaoServiceHttpSoap11Endpoint_address), this);
                _stub.setPortName(getUploadOrderCainiaoServiceHttpSoap11EndpointWSDDServiceName());
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
        if ("UploadOrderCainiaoServiceHttpSoap12Endpoint".equals(inputPortName)) {
            return getUploadOrderCainiaoServiceHttpSoap12Endpoint();
        }
        else if ("UploadOrderCainiaoServiceHttpSoap11Endpoint".equals(inputPortName)) {
            return getUploadOrderCainiaoServiceHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UploadOrderCainiaoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UploadOrderCainiaoServiceHttpSoap12Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UploadOrderCainiaoServiceHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UploadOrderCainiaoServiceHttpSoap12Endpoint".equals(portName)) {
            setUploadOrderCainiaoServiceHttpSoap12EndpointEndpointAddress(address);
        }
        else 
if ("UploadOrderCainiaoServiceHttpSoap11Endpoint".equals(portName)) {
            setUploadOrderCainiaoServiceHttpSoap11EndpointEndpointAddress(address);
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
