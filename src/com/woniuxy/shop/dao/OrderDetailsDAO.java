package com.woniuxy.shop.dao;

import java.util.List;

import com.woniuxy.shop.entity.Cart;
import com.woniuxy.shop.entity.OrderDetails;
import com.woniuxy.shop.entity.PageBean;

public class OrderDetailsDAO {
	/**
	 * 增加订单明细
	 * 
	 * @param admin
	 */
//	public void add(OrderDetails orderDetails) {
//		String sql = "insert into shop_order_details (orderid,goodsid,nums,price) values (?,?,?,?)";
//		Object[] params = {orderDetails.getOrderId(),orderDetails.getGoodsId(),orderDetails.getNums(),orderDetails.getPrice()};
//		DbHelper.execute(sql, params);
//	}
	/**
	 * 增加订单明细
	 * @param orderDetails
	 */
	public void add(OrderDetails... orderDetails) {
		String sql = "insert into shop_order_details (orderid,goodsid,nums,price) values (?,?,?,?)";
		Object[][] params = new Object[orderDetails.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { orderDetails[i].getOrderId(), orderDetails[i].getGoodsId(),
					orderDetails[i].getNums(), orderDetails[i].getPrice() };

		}
		DbHelper.executeBatch(sql, params);
	}

	/**
	 * 根据id获取订单明细
	 * 
	 * @return
	 */
	public OrderDetails getOrderDetailsById(int id) {
		String sql = "select * from shop_order_details where id=?";
		return DbHelper.queryForSingleRow(sql, OrderDetails.class, id);
	}

	/**
	 * 根据orderid获取订单明细
	 * 
	 * @return
	 */
	public List<OrderDetails> getOrderDetailsesByOrder(int orderid) {
		String sql = "select * from shop_order_details where orderid=?";
		return DbHelper.query(sql, OrderDetails.class, orderid);
	}

	/**
	 * 修改订单明细中的信息
	 * 
	 * @param orderDetails
	 */
	public void updateOrderDetails(OrderDetails orderDetails) {
		String sql = "update shop_order_details set orderid=?,goodsid=?,nums=?,price=? where id=?";
		Object[] params = { orderDetails.getOrderId(), orderDetails.getGoodsId(), orderDetails.getNums(),
				orderDetails.getPrice(), orderDetails.getId() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 分页展示所有的订单明细
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public static PageBean<OrderDetails> getOrderDetailsesByPage(int page, int pageSize) {
		String sql = "select * from shop_order_details limit ?,?";
		String sql1 = "select count(*) from shop_order_details ";
		return DbHelper.getDataByPage(sql, sql1, OrderDetails.class, page, pageSize);
	}

	/**
	 * 删除指定id的订单明细
	 * 
	 * @param id
	 */
	public void deleteOrderDetailsById(int id) {
		String sql = "delete from shop_order_details where id=?";
		DbHelper.execute(sql, id);
	}

}
