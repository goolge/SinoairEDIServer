package com.sinoair.iemisgateway.domain;

/**
 * Created by IntelliJ IDEA.
 * User: DuCC
 * Date: 13-7-18
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
public class Order {
    private String orderNo;//todo-wxx-n电商单号
    private String referenceNO; //todo-wxx-n 转单号
    private String pickUpProvince;     //todo-wxx-n 取件省
    private String desCity3C;     //todo-wxx-n 目的城市三字码
    private String customerNo;    //客户编号
    private String shipperEN;    //发货人（英文）
    private String shippingCompanyEN;//发货公司（英文）
    private String shippingCompanyCN; //发货公司（中文）
    private String shipperAddressEN;  //发货地址英文
    private String shipperAddressCN;  //发货地址中文
    private String depCity;         //始发城市
    private String shipperPhone;    //发货人电话
    private String receiveCompanyEN; //收件人公司英文
    private String receiverEN;        //收件人英文
    private String receiverAddressEN; //收件人地址英文
    private String receiverPost;     //收件人邮编
    private String receiverPhone;     //收件人电话
    private String desCity;         //目的城市
    private String desCountry;         //目的国家
    private String productNameEN;     //品名英文
    private String productNameCN;      //品名中文
    private String cargoType;      //货物类型
    private double declareValue;   //海关申报价值
    private int quantity = 0;//数量
    private String HScode;    //Hscode海关编码
    private String deliverChannel;    //发运渠道
    private double actualWeight = 0; //实重
    private double cubWeight = 0;    //体积重
    private double netWeight = 0;   //净重
    private String unit; //计量单位
    private String packageType;  //包裹类型
    private int pieces = 0;      //件数
    private String allInOne;//集货
    private String serviceType; //服务类型
    private String airwaybill;   //运单号
    private String email;     //邮箱
    private String isEcommerce; //是否是电商Y代表是，N代表不是
    private double freightPrice;     //运价
    private String sacid;//分公司id
    private String saccode; //分公司saccode
    private String receiverState; //收件人州省
    private String taxCode;  //todo-wxx-n 税费方式 只有英邮有

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReferenceNO() {
        return referenceNO;
    }

    public void setReferenceNO(String referenceNO) {
        this.referenceNO = referenceNO;
    }

    public String getDesCity3C() {
        return desCity3C;
    }

    public void setDesCity3C(String desCity3C) {
        this.desCity3C = desCity3C;
    }

    public String getPickUpProvince() {
        return pickUpProvince;
    }

    public void setPickUpProvince(String pickUpProvince) {
        this.pickUpProvince = pickUpProvince;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getShipperEN() {
        return shipperEN;
    }

    public void setShipperEN(String shipperEN) {
        this.shipperEN = shipperEN;
    }

    public String getShippingCompanyEN() {
        return shippingCompanyEN;
    }

    public void setShippingCompanyEN(String shippingCompanyEN) {
        this.shippingCompanyEN = shippingCompanyEN;
    }

    public String getShippingCompanyCN() {
        return shippingCompanyCN;
    }

    public void setShippingCompanyCN(String shippingCompanyCN) {
        this.shippingCompanyCN = shippingCompanyCN;
    }

    public String getShipperAddressEN() {
        return shipperAddressEN;
    }

    public void setShipperAddressEN(String shipperAddressEN) {
        this.shipperAddressEN = shipperAddressEN;
    }

    public String getShipperAddressCN() {
        return shipperAddressCN;
    }

    public void setShipperAddressCN(String shipperAddressCN) {
        this.shipperAddressCN = shipperAddressCN;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }

    public String getReceiveCompanyEN() {
        return receiveCompanyEN;
    }

    public void setReceiveCompanyEN(String receiveCompanyEN) {
        this.receiveCompanyEN = receiveCompanyEN;
    }

    public String getReceiverEN() {
        return receiverEN;
    }

    public void setReceiverEN(String receiverEN) {
        this.receiverEN = receiverEN;
    }

    public String getReceiverAddressEN() {
        return receiverAddressEN;
    }

    public void setReceiverAddressEN(String receiverAddressEN) {
        this.receiverAddressEN = receiverAddressEN;
    }

    public String getReceiverPost() {
        return receiverPost;
    }

    public void setReceiverPost(String receiverPost) {
        this.receiverPost = receiverPost;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getEcommerce() {
        return isEcommerce;
    }

    public void setEcommerce(String ecommerce) {
        isEcommerce = ecommerce;
    }

    public String getDesCity() {
        return desCity;
    }

    public void setDesCity(String desCity) {
        this.desCity = desCity;
    }

    public String getDesCountry() {
        return desCountry;
    }

    public void setDesCountry(String desCountry) {
        this.desCountry = desCountry;
    }

    public String getProductNameEN() {
        return productNameEN;
    }

    public void setProductNameEN(String productNameEN) {
        this.productNameEN = productNameEN;
    }

    public String getProductNameCN() {
        return productNameCN;
    }

    public void setProductNameCN(String productNameCN) {
        this.productNameCN = productNameCN;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public double getDeclareValue() {
        return declareValue;
    }

    public void setDeclareValue(double declareValue) {
        this.declareValue = declareValue;
    }

    public String getHScode() {
        return HScode;
    }

    public void setHScode(String HScode) {
        this.HScode = HScode;
    }

    public String getDeliverChannel() {
        return deliverChannel;
    }

    public void setDeliverChannel(String deliverChannel) {
        this.deliverChannel = deliverChannel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }


    public String getAllInOne() {
        return allInOne;
    }

    public void setAllInOne(String allInOne) {
        this.allInOne = allInOne;
    }

    public String getDepCity() {
        return depCity;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAirwaybill() {
        return airwaybill;
    }

    public void setAirwaybill(String airwaybill) {
        this.airwaybill = airwaybill;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public double getCubWeight() {
        return cubWeight;
    }

    public void setCubWeight(double cubWeight) {
        this.cubWeight = cubWeight;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSacid() {
        return sacid;
    }

    public void setSacid(String sacid) {
        this.sacid = sacid;
    }

    public String getSaccode() {
        return saccode;
    }

    public void setSaccode(String saccode) {
        this.saccode = saccode;
    }

    public double getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(double freightPrice) {
        this.freightPrice = freightPrice;
    }
}
