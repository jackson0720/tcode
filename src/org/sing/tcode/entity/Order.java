package org.sing.tcode.entity;

public class Order {
	
	private String orderid ;
	private String orderpay ;
	private String ordername;
	private String message;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderpay() {
		return orderpay;
	}
	public void setOrderpay(String orderpay) {
		this.orderpay = orderpay;
	}
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", orderpay=" + orderpay + ", ordername=" + ordername + ", message="
				+ message + "]";
	}
	
	

}
