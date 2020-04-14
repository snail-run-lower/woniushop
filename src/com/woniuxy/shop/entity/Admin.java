package com.woniuxy.shop.entity;
/**
 * 管理员表
 * @author l
 */

public class Admin {
	private int id;
	private String account;
	private String password;
	private String role;
	private String lastlogintime;
	private String lastloginip;
	private String status;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", account=" + account + ", password=" + password + ", role=" + role
				+ ", lastlogintime=" + lastlogintime + ", lastloginip=" + lastloginip + ", status=" + status + "]";
	}
	

}
