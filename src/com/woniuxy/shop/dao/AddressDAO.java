package com.woniuxy.shop.dao;

import java.util.List;

import com.woniuxy.shop.entity.Address;
import com.woniuxy.shop.entity.PageBean;

public class AddressDAO {
	/**
	 * 增加用户地址
	 * 
	 * @param a
	 */
	public void add(Address a) {
		String sql = "insert into shop_address (userid,accept,telephone,province,city,area,address,isdefault) values (?,?,?,?,?,?,?,?)";
		Object[] params = { a.getUserId(), a.getAccept(), a.getTelephone(), a.getProvince(), a.getCity(), a.getArea(),
				a.getAddress(), a.getIsDefault() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 获取指定用户的地址数
	 * 
	 * @param account
	 * @return
	 */
	public  int getAddressNums(int userId) {
		String sql = "select count(*) from shop_address where userid=?";
		return ((Long) DbHelper.queryForScalar(sql, userId)).intValue();
	}

	/**
	 * 将用户之前的默认地址改为否
	 * 
	 * @param account
	 */
	public  void updateIsDefault(int userId) {
		String sql = "update shop_address set isdefault='n' where userid=? and isdefault='y' ";
		DbHelper.execute(sql, userId);
	}
	/**
	 * 设置收货地址是否默认
	 * 
	 * @param id
	 * @param isDefault
	 */
	public void updateDefaultById(int id, String isDefault) {
		String sql = "update shop_address set isdefault=? where id=?";
		DbHelper.execute(sql, isDefault, id);
	}
	/**
	 * 获取指定用户的所有地址
	 * @param UserId
	 * @return
	 */
	public List<Address> getAddressByUser(int UserId){
		String sql="select * from shop_address where userid=?";
		return DbHelper.query(sql, Address.class, UserId);
	}
	/**
	 * 更改地址的某个数据
	 * @param columnName
	 * @param res
	 * @param id
	 */
	public void updateAddress(Address address) {
		String sql="update shop_address set accept=?,telephone=?,province=?,city=?,area=?,address=? where id=? ";
		Object[] params= {address.getAccept(),address.getTelephone(),address.getProvince(),address.getCity(),address.getArea(),address.getAddress(),address.getId()};
		DbHelper.execute(sql, params);
	} 
	/**
	 * 获取指定页的数据
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public static PageBean<Address> getAddressesByPage(int page, int pageSize){
		String sql = "select * from shop_address limit ?,?";
		String sql1="select count(*) from shop_address";
		return DbHelper.getDataByPage(sql, sql1, Address.class, page, pageSize);
	}
	/**
	 * 获取指定id的地址
	 * @param id
	 * @return
	 */
	public Address getAddressById(int id) {
		String sql="select * from shop_address where id=?";
		return DbHelper.queryForSingleRow(sql, Address.class,id);
	}
	/**
	 * 删除指定id的收货地址
	 * @param id
	 */
	public void deleteAddressById(int id) {
		String sql="delete from shop_address where id=?";
		DbHelper.execute(sql, id);
	}

}
