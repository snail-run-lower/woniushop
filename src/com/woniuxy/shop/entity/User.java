package com.woniuxy.shop.entity;
/**
 * 用户表
 * @author l
 *
 */
public class User {
	private int id;
	private String account;
	private String password;
	private String email;
	private String avatar;
	private int score;
	private String status;
	private int lowScore;
	private int highScore;
	
	public int getLowScore() {
		return lowScore;
	}
	public void setLowScore(int lowScore) {
		this.lowScore = lowScore;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", email=" + email + ", avatar="
				+ avatar + ", score=" + score + ", status=" + status + "]";
	}

}
