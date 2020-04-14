package com.woniuxy.shop.service;

import com.woniuxy.shop.dao.UserDAO;
import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.entity.User;
import com.woniuxy.shop.exception.AccountExistException;
import com.woniuxy.shop.exception.DAOException;
import com.woniuxy.shop.exception.EmailExistException;
import com.woniuxy.shop.exception.ServiceException;

/**
 * 用户的业务逻辑类
 * 
 * @author
 *
 */
public class UserService {
	private UserDAO userDAO=new UserDAO();
	/**
	 * 用户注册
	 * 
	 * @param user
	 */
	public void reg(User user) {
		
		try {
			int a = userDAO.getAccountNums(user.getAccount());
			int e = userDAO.getEmailNums(user.getEmail());
			if (a == 1) {
				throw new ServiceException(new AccountExistException("账户已存在"));
			}
			if (e == 1) {
				throw new ServiceException(new EmailExistException("账户已存在"));
			}
			userDAO.add(user);
		} catch (DAOException e) {
			throw new ServiceException("SQL执行出错", e);
		}
	}
	/**
	 * 分页多条件查询
	 * @param page
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public PageBean<User> getUsersByPage(int page, int pageSize, User user) {
		return userDAO.getUsersByPage(page, pageSize, user);
	}
}
