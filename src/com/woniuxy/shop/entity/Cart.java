package com.woniuxy.shop.entity;

/**
 * 购物车表
 * 
 * @author l
 *
 */
public class Cart {
	private int id;
	private int userId;
	private int goodsId;
	private int nums;
	private double price;
	private String addTime;
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", goodsId=" + goodsId + ", nums=" + nums + ", price=" + price
				+ ", addTime=" + addTime + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	

}
