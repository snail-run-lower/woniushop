package com.woniuxy.shop.entity;

/**
 * 订单明细表
 * 
 * @author l
 *
 */
public class OrderDetails {
	private int id;
	private int orderId;
	private int goodsId;
	private int nums;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", orderId=" + orderId + ", goodsId=" + goodsId + ", nums=" + nums
				+ ", price=" + price + "]";
	}

}
