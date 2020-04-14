package com.woniuxy.shop.dao;

import java.util.List;

import com.woniuxy.shop.entity.Admin;

public class AdminDAO {
	/**
	 * 增加管理员
	 * @param admin
	 */
	public void add(Admin admin) {
		String sql="insert into shop_admin (account,password,role,lastlogintime,lastloginip,status) values (?,?,?,?,?,?)";
		Object[] params= {admin.getAccount(),admin.getPassword(),admin.getRole(),admin.getLastlogintime(),admin.getLastloginip(),admin.getStatus()};
		DbHelper.execute(sql, params);
	}
	/**
	 * 根据id获取管理员账户信息
	 * @return
	 */
	public Admin getAdminById(int id) {
		String sql="select * from shop_admin where id=?";
		return DbHelper.queryForSingleRow(sql, Admin.class, id);
	}
	/**
	 * 根据账户获取管理员账户信息
	 * @param account
	 * @return
	 */
	public Admin getAdminByAccount(String account) {
		String sql="select * from shop_admin where account=?";
		return DbHelper.queryForSingleRow(sql, Admin.class, account);
	}
	/**
	 * 修改账户中的信息
	 * @param admin
	 */
	public void updateAdmin(Admin admin) {
		String sql="update shop_admin set account=?,password=?,role=?,status=? where id=?";
		Object[] params= {admin.getAccount(),admin.getPassword(),admin.getRole(),admin.getStatus(),admin.getId()};
		DbHelper.execute(sql, params);
	}
	/**
	 * 获取所有管理员信息
	 * @return
	 */
	public List<Admin> getAdmins(){
		String sql="select * from shop_admin ";
		return DbHelper.query(sql, Admin.class);
	}
	/**
	 * 删除指定id的管理员账户
	 * @param id
	 */
	public void deleteAdminById(int id) {
		String sql="delete from shop_admin where id=?";
		DbHelper.execute(sql, id);
	}

}
