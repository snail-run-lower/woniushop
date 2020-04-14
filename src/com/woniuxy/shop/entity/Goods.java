package com.woniuxy.shop.entity;

/**
 * 图书表
 * 
 * @author l
 *
 */
public class Goods {
	private int id;
	private String name;
	private int categoryId;
	private String categoryName;
	private String description;
	private String image;
	private int stock;
	private double marketPrice;
	private double salesPrice;
	private double score;
	private String status;
	private int lowStock;
	private int highStock;
	private double lowSalesPrice;
	private double highSalesPrice;
	private double lowScore;
	private double highScore;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getLowScore() {
		return lowScore;
	}
	public void setLowScore(double lowScore) {
		this.lowScore = lowScore;
	}
	public double getHighScore() {
		return highScore;
	}
	public void setHighScore(double highScore) {
		this.highScore = highScore;
	}
	public double getLowSalesPrice() {
		return lowSalesPrice;
	}
	public void setLowSalesPrice(double lowSalesPrice) {
		this.lowSalesPrice = lowSalesPrice;
	}
	public double getHighSalesPrice() {
		return highSalesPrice;
	}
	public void setHighSalesPrice(double highSalesPrice) {
		this.highSalesPrice = highSalesPrice;
	}
	public int getLowStock() {
		return lowStock;
	}
	public void setLowStock(int lowStock) {
		this.lowStock = lowStock;
	}
	public int getHighStock() {
		return highStock;
	}
	public void setHighStock(int highStock) {
		this.highStock = highStock;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", description=" + description
				+ ", image=" + image + ", stock=" + stock + ", marketPrice=" + marketPrice + ", salesPrice="
				+ salesPrice + ", score=" + score + ", status=" + status + "]";
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
