package com.woniuxy.shop.entity;

/**
 * 图书分类表
 * 
 * @return
 */
public class Category {
	private int id;
	private String name;
	private String status;
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
