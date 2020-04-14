package com.woniuxy.shop.dao;

import java.util.List;

import com.woniuxy.shop.entity.Cart;
import com.woniuxy.shop.entity.Goods;
import com.woniuxy.shop.entity.PageBean;


public class CartDAO {
	/**
	 * 增加购物车
	 * 
	 * @param admin
	 */
	public void add(Cart cart) {
		String sql = "insert into shop_cart (userid,goodsid,nums,price,addtime) values (?,?,?,?,?)";
		Object[] params = {cart.getUserId(),cart.getGoodsId(),cart.getNums(),cart.getPrice(),cart.getAddTime()};
		DbHelper.execute(sql, params);
	}

	/**
	 * 根据id获取购物车信息
	 * 
	 * @return
	 */
	public Cart getCartById(int id) {
		String sql = "select * from shop_cart where id=?";
		return DbHelper.queryForSingleRow(sql, Cart.class, id);
	}
	
	/**
	 * 根据userid获取购物车信息
	 * 
	 * @return
	 */
	public List<Cart> getCartByUser(int userId) {
		String sql = "select * from shop_cart where userid=?";
		return DbHelper.query(sql, Cart.class, userId);
	}

	/**
	 * 修改购物车中的信息
	 * 
	 * @param admin
	 */
	public void updateCart(Cart cart) {
		String sql = "update shop_cart set userid=?,goodsid=?,nums=?,price=?,addtime=? where id=?";
		Object[] params = {cart.getUserId(),cart.getGoodsId(),cart.getNums(),cart.getPrice(),cart.getAddTime(),cart.getId()};
		DbHelper.execute(sql, params);
	}

	/**
	 * 分页展示所有的购物车
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public static PageBean<Cart> getCartsByPage(int page, int pageSize){
		String sql = "select * from shop_cart limit ?,?";
		String sql1="select count(*) from shop_cart ";
		return DbHelper.getDataByPage(sql, sql1, Cart.class, page, pageSize);
	}

	/**
	 * 删除指定id的管理员账户
	 * 
	 * @param id
	 */
	public void deleteCartById(int id) {
		String sql = "delete from shop_cart where id=?";
		DbHelper.execute(sql, id);
	}

}
