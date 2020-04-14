package com.woniuxy.shop.dao;

import com.woniuxy.shop.entity.Cart;
import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.entity.User;

/**
 * 用户DAO
 * 
 * @author
 *
 */
public class UserDAO {
	/**
	 * 增加用户
	 * 
	 * @param user
	 */
	public void add(User user) {
		String sql = "insert into shop_user (account,password,email,avatar,score,status) values (?,?,?,?,?,?)";
		Object[] obj = { user.getAccount(), user.getPassword(), user.getEmail(), user.getAvatar(), user.getScore(),
				user.getStatus() };
		DbHelper.execute(sql, obj);
	}

	/**
	 * 查询指定账户的数量
	 * 
	 * @param account
	 * @return
	 */
	public int getAccountNums(String account) {
		String sql = "select count(*) from shop_user where account=? and is_delete=0";
		return ((Long) DbHelper.queryForScalar(sql, account)).intValue();
	}

	/**
	 * 查询指定邮箱的数量
	 * 
	 * @param account
	 * @return
	 */
	public int getEmailNums(String email) {
		String sql = "select count(*) from shop_user where email=? and is_delete=0";
		return ((Long) DbHelper.queryForScalar(sql, email)).intValue();
	}

	/**
	 * 根据账户查用户
	 * 
	 * @param account
	 * @return
	 */
	public User getByAccount(String account) {
		String sql = "select count(*) from shop_user where account=? and is_delete=0";
		return DbHelper.queryForSingleRow(sql, User.class, account);
	}

	/**
	 * 根据id查用户
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(int id) {
		String sql = "select * from shop_user where id=? and is_delete=0";
		return DbHelper.queryForSingleRow(sql, User.class, id);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		String sql = "update shop_user set account=?,password=?,email=?,avatar=?,score=?,status=? where id=?";
		Object[] obj = { user.getAccount(), user.getPassword(), user.getEmail(), user.getAvatar(), user.getScore(),
				user.getStatus(), user.getId() };
		DbHelper.execute(sql, obj);
	}

	/**
	 * 分页展示按条件查找的的用户信息,账户、积分、状态
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageBean<User> getUsersByPage(int page, int pageSize, User user) {
		String sql = "select * from shop_user where is_delete=0 ";
		String sql1 = "select count(*) from shop_user where is_delete=0 ";
		if (user.getAccount() != null && !user.getAccount().trim().equals("")) {
			sql += " and account like '%" + user.getAccount() + "%'";
			sql1 += " and account like '%" + user.getAccount() + "%'";
		}
		if (user.getLowScore() != 0) {
			sql += " and score>=" + user.getLowScore();
			sql1 += " and score>=" + user.getLowScore();
		}
		if (user.getHighScore() != 0) {
			sql += " and score<=" + user.getHighScore();
			sql1 += " and score<=" + user.getHighScore();
		}
		if (user.getStatus() != null && !user.getStatus().trim().equals("")) {
			sql += " and status='" + user.getStatus() + "'";
			sql1 += " and status='" + user.getStatus() + "'";
		}
		sql += " limit ?,?";
		return DbHelper.getDataByPage(sql, sql1, User.class, page, pageSize);
	}

	/**
	 * 删除指定id的用户(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
	public void deleteCategoryById(int id) {
		String sql = "update shop_user set is_delete=1 where id=?";
		DbHelper.execute(sql, id);
	}

	/**
	 * 恢复指定id的用户(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
	public void recoverCategoryById(int id) {
		String sql = "update shop_user set is_delete=0 where id=?";
		DbHelper.execute(sql, id);
	}

}
