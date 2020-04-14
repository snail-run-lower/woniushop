package com.woniuxy.shop.service;

import com.woniuxy.shop.dao.AdminDAO;
import com.woniuxy.shop.entity.Admin;
import com.woniuxy.shop.exception.AccountExistException;
import com.woniuxy.shop.exception.DAOException;
import com.woniuxy.shop.exception.ServiceException;

public class AdminService {
	AdminDAO adminDAO = new AdminDAO();

	public void add(Admin admin) {
		try {
			Admin a = adminDAO.getAdminByAccount(admin.getAccount().trim());
			if (a == null) {
				adminDAO.add(admin);
			} else {
				throw new AccountExistException("账户已存在");
			}

		} catch (DAOException e) {
			throw new ServiceException("SQL执行出错", e);
		}
	}

	public Admin getAdmin(String account) {
		try {
			return adminDAO.getAdminByAccount(account);
		} catch (DAOException e) {
			throw new ServiceException("系统维护中", e);
		}
	}
}
