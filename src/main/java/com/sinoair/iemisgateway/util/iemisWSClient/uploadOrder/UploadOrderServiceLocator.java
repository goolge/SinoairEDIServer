/**
 * UploadOrderServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sinoair.iemisgateway.util.iemisWSClient.uploadOrder;

public class UploadOrderServiceLocator extends org.apache.axis.client.Service implements UploadOrderService {

    public UploadOrderServiceLocator() {
    }


    public UploadOrderServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UploadOrderServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UploadOrderServiceHttpSoap12Endpoint
    private java.lang.String UploadOrderServiceHttpSoap12Endpoint_address = "http://172.17.0.229:7001/services/UploadOrderService.UploadOrderServiceHttpSoap12Endpoint/";

    public java.lang.String getUploadOrderServiceHttpSoap12EndpointAddress() {
        return UploadOrderServiceHttpSoap12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UploadOrderServiceHttpSoap12EndpointWSDDServiceName = "UploadOrderServiceHttpSoap12Endpoint";

    public java.lang.String getUploadOrderServiceHttpSoap12EndpointWSDDServiceName() {
        return UploadOrderServiceHttpSoap12EndpointWSDDServiceName;
    }

    public void setUploadOrderServiceHttpSoap12EndpointWSDDServiceName(java.lang.String name) {
        UploadOrderServiceHttpSoap12EndpointWSDDServiceName = name;
    }

    public UploadOrderServicePortType getUploadOrderServiceHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UploadOrderServiceHttpSoap12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUploadOrderServiceHttpSoap12Endpoint(endpoint);
    }

    public UploadOrderServicePortType getUploadOrderServiceHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            UploadOrderServiceSoap12BindingStub _stub = new UploadOrderServiceSoap12BindingStub(portAddress, this);
            _stub.setPortName(getUploadOrderServiceHttpSoap12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUploadOrderServiceHttpSoap12EndpointEndpointAddress(java.lang.String address) {
        UploadOrderServiceHttpSoap12Endpoint_address = address;
    }


    // Use to get a proxy class for UploadOrderServiceHttpSoap11Endpoint
    private java.lang.String UploadOrderServiceHttpSoap11Endpoint_address = "http://172.17.0.229:7001/services/UploadOrderService.UploadOrderServiceHttpSoap11Endpoint/";

    public java.lang.String getUploadOrderServiceHttpSoap11EndpointAddress() {
        return UploadOrderServiceHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UploadOrderServiceHttpSoap11EndpointWSDDServiceName = "UploadOrderServiceHttpSoap11Endpoint";

    public java.lang.String getUploadOrderServiceHttpSoap11EndpointWSDDServiceName() {
        return UploadOrderServiceHttpSoap11EndpointWSDDServiceName;
    }

    public void setUploadOrderServiceHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        UploadOrderServiceHttpSoap11EndpointWSDDServiceName = name;
    }

    public UploadOrderServicePortType getUploadOrderServiceHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UploadOrderServiceHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUploadOrderServiceHttpSoap11Endpoint(endpoint);
    }

    public UploadOrderServicePortType getUploadOrderServiceHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            UploadOrderServiceSoap11BindingStub _stub = new UploadOrderServiceSoap11BindingStub(portAddress, this);
            _stub.setPortName(getUploadOrderServiceHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUploadOrderServiceHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        UploadOrderServiceHttpSoap11Endpoint_address = address;
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
            if (UploadOrderServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                UploadOrderServiceSoap12BindingStub _stub = new UploadOrderServiceSoap12BindingStub(new java.net.URL(UploadOrderServiceHttpSoap12Endpoint_address), this);
                _stub.setPortName(getUploadOrderServiceHttpSoap12EndpointWSDDServiceName());
                return _stub;
            }
            if (UploadOrderServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                UploadOrderServiceSoap11BindingStub _stub = new UploadOrderServiceSoap11BindingStub(new java.net.URL(UploadOrderServiceHttpSoap11Endpoint_address), this);
                _stub.setPortName(getUploadOrderServiceHttpSoap11EndpointWSDDServiceName());
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
        if ("UploadOrderServiceHttpSoap12Endpoint".equals(inputPortName)) {
            return getUploadOrderServiceHttpSoap12Endpoint();
        }
        else if ("UploadOrderServiceHttpSoap11Endpoint".equals(inputPortName)) {
            return getUploadOrderServiceHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UploadOrderService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UploadOrderServiceHttpSoap12Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UploadOrderServiceHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UploadOrderServiceHttpSoap12Endpoint".equals(portName)) {
            setUploadOrderServiceHttpSoap12EndpointEndpointAddress(address);
        }
        else 
if ("UploadOrderServiceHttpSoap11Endpoint".equals(portName)) {
            setUploadOrderServiceHttpSoap11EndpointEndpointAddress(address);
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
