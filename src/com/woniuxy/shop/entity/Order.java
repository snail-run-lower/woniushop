package com.woniuxy.shop.entity;

/**
 * 订单表
 * 
 * @author l
 *
 */
public class Order {
	private int id;
	private String orderNo;
	private int userId;
	private String orderTime;
	private String receiveInfo;
	private double money;
	private String payType;
	private String payTime;
	private String status;
	private String startOrderTime;
	private String endOrderTime;
	
	public String getStartOrderTime() {
		return startOrderTime;
	}
	public void setStartOrderTime(String startOrderTime) {
		this.startOrderTime = startOrderTime;
	}
	public String getEndOrderTime() {
		return endOrderTime;
	}
	public void setEndOrderTime(String endOrderTime) {
		this.endOrderTime = endOrderTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getReceiveInfo() {
		return receiveInfo;
	}
	public void setReceiveInfo(String receiveInfo) {
		this.receiveInfo = receiveInfo;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNo=" + orderNo + ", userId=" + userId + ", orderTime=" + orderTime
				+ ", receiveInfo=" + receiveInfo + ", money=" + money + ", payType=" + payType + ", payTime=" + payTime
				+ ", status=" + status + "]";
	}

}
