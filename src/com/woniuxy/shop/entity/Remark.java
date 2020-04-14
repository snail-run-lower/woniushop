package com.woniuxy.shop.entity;
/**
 * 评论表
 * @author l
 *
 */
public class Remark {
	private int id;
	   private int userId;
	   private int goodsId;
	   private int score;
	   private String content;
	   private String remarkTime;
	   private String status;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemarkTime() {
		return remarkTime;
	}
	public void setRemarkTime(String remarkTime) {
		this.remarkTime = remarkTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Remark [id=" + id + ", userId=" + userId + ", goodsId=" + goodsId + ", score=" + score + ", content="
				+ content + ", remarkTime=" + remarkTime + ", status=" + status + "]";
	}

}
