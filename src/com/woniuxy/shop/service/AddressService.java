package com.woniuxy.shop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.woniuxy.shop.dao.AddressDAO;
import com.woniuxy.shop.dao.DbHelper;
import com.woniuxy.shop.entity.Address;
import com.woniuxy.shop.exception.AddressNumOverageException;
import com.woniuxy.shop.exception.DAOException;
import com.woniuxy.shop.exception.ServiceException;

public class AddressService {
	/**
	 * 增加收货地址
	 * 
	 * @param address
	 */
	public void addAddress(Address address) {
		try {
			AddressDAO addressDAO=new AddressDAO();
			List<Address> addresses = addressDAO.getAddressByUser(address.getUserId());
			if (addresses.size() == 5) {
				throw new  AddressNumOverageException("地址数量已达上限");
			}
			if (addresses.size() == 0) {// 如果是第一个，默认改为是后加进去
				address.setIsDefault("y");
			} else {
				if (address.getIsDefault().equals("y")) {// 如果不是第一个，默认为是，将之前的默认改为否，再加进去
					for (Address a : addresses) {
						if (a.getIsDefault().equals("y")) {
							addressDAO.updateDefaultById(a.getId(), "n");
							break;
						}
					}
				}
			}
			addressDAO.add(address);// 如果不是第一个且默认为否，直接加
		} catch (DAOException e) {
			throw new ServiceException("SQL执行出错", e);
		}
	}

}
