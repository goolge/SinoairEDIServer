/**
 * UpdateOrderServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sinoair.iemisgateway.util.iemisWSClient.updateOrder;

public class UpdateOrderServiceLocator extends org.apache.axis.client.Service implements UpdateOrderService {

    public UpdateOrderServiceLocator() {
    }


    public UpdateOrderServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UpdateOrderServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UpdateOrderServiceHttpSoap11Endpoint
    private java.lang.String UpdateOrderServiceHttpSoap11Endpoint_address = "http://172.17.0.229:7001/services/UpdateOrderService.UpdateOrderServiceHttpSoap11Endpoint/";

    public java.lang.String getUpdateOrderServiceHttpSoap11EndpointAddress() {
        return UpdateOrderServiceHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UpdateOrderServiceHttpSoap11EndpointWSDDServiceName = "UpdateOrderServiceHttpSoap11Endpoint";

    public java.lang.String getUpdateOrderServiceHttpSoap11EndpointWSDDServiceName() {
        return UpdateOrderServiceHttpSoap11EndpointWSDDServiceName;
    }

    public void setUpdateOrderServiceHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        UpdateOrderServiceHttpSoap11EndpointWSDDServiceName = name;
    }

    public UpdateOrderServicePortType getUpdateOrderServiceHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UpdateOrderServiceHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUpdateOrderServiceHttpSoap11Endpoint(endpoint);
    }

    public UpdateOrderServicePortType getUpdateOrderServiceHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            UpdateOrderServiceSoap11BindingStub _stub = new UpdateOrderServiceSoap11BindingStub(portAddress, this);
            _stub.setPortName(getUpdateOrderServiceHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUpdateOrderServiceHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        UpdateOrderServiceHttpSoap11Endpoint_address = address;
    }


    // Use to get a proxy class for UpdateOrderServiceHttpSoap12Endpoint
    private java.lang.String UpdateOrderServiceHttpSoap12Endpoint_address = "http://172.17.0.229:7001/services/UpdateOrderService.UpdateOrderServiceHttpSoap12Endpoint/";

    public java.lang.String getUpdateOrderServiceHttpSoap12EndpointAddress() {
        return UpdateOrderServiceHttpSoap12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UpdateOrderServiceHttpSoap12EndpointWSDDServiceName = "UpdateOrderServiceHttpSoap12Endpoint";

    public java.lang.String getUpdateOrderServiceHttpSoap12EndpointWSDDServiceName() {
        return UpdateOrderServiceHttpSoap12EndpointWSDDServiceName;
    }

    public void setUpdateOrderServiceHttpSoap12EndpointWSDDServiceName(java.lang.String name) {
        UpdateOrderServiceHttpSoap12EndpointWSDDServiceName = name;
    }

    public UpdateOrderServicePortType getUpdateOrderServiceHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UpdateOrderServiceHttpSoap12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUpdateOrderServiceHttpSoap12Endpoint(endpoint);
    }

    public UpdateOrderServicePortType getUpdateOrderServiceHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            UpdateOrderServiceSoap12BindingStub _stub = new UpdateOrderServiceSoap12BindingStub(portAddress, this);
            _stub.setPortName(getUpdateOrderServiceHttpSoap12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUpdateOrderServiceHttpSoap12EndpointEndpointAddress(java.lang.String address) {
        UpdateOrderServiceHttpSoap12Endpoint_address = address;
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
            if (UpdateOrderServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                UpdateOrderServiceSoap11BindingStub _stub = new UpdateOrderServiceSoap11BindingStub(new java.net.URL(UpdateOrderServiceHttpSoap11Endpoint_address), this);
                _stub.setPortName(getUpdateOrderServiceHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
            if (UpdateOrderServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                UpdateOrderServiceSoap12BindingStub _stub = new UpdateOrderServiceSoap12BindingStub(new java.net.URL(UpdateOrderServiceHttpSoap12Endpoint_address), this);
                _stub.setPortName(getUpdateOrderServiceHttpSoap12EndpointWSDDServiceName());
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
        if ("UpdateOrderServiceHttpSoap11Endpoint".equals(inputPortName)) {
            return getUpdateOrderServiceHttpSoap11Endpoint();
        }
        else if ("UpdateOrderServiceHttpSoap12Endpoint".equals(inputPortName)) {
            return getUpdateOrderServiceHttpSoap12Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UpdateOrderService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UpdateOrderServiceHttpSoap11Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://webService.service.operation.iemis.sinoair.com", "UpdateOrderServiceHttpSoap12Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UpdateOrderServiceHttpSoap11Endpoint".equals(portName)) {
            setUpdateOrderServiceHttpSoap11EndpointEndpointAddress(address);
        }
        else 
if ("UpdateOrderServiceHttpSoap12Endpoint".equals(portName)) {
            setUpdateOrderServiceHttpSoap12EndpointEndpointAddress(address);
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
