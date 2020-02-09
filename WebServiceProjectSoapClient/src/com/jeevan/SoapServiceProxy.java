package com.jeevan;

public class SoapServiceProxy implements com.jeevan.SoapService {
  private String _endpoint = null;
  private com.jeevan.SoapService soapService = null;
  
  public SoapServiceProxy() {
    _initSoapServiceProxy();
  }
  
  public SoapServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSoapServiceProxy();
  }
  
  private void _initSoapServiceProxy() {
    try {
      soapService = (new com.jeevan.SoapServiceServiceLocator()).getSoapService();
      if (soapService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)soapService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)soapService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (soapService != null)
      ((javax.xml.rpc.Stub)soapService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.jeevan.SoapService getSoapService() {
    if (soapService == null)
      _initSoapServiceProxy();
    return soapService;
  }
  
  public int add(int a, int b) throws java.rmi.RemoteException{
    if (soapService == null)
      _initSoapServiceProxy();
    return soapService.add(a, b);
  }
  
  
}