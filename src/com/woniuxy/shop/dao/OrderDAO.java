package com.woniuxy.shop.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.woniuxy.shop.entity.Order;
import com.woniuxy.shop.entity.PageBean;

public class OrderDAO {
	/**
	 * 增加订单
	 * 
	 * @param admin
	 */
	public void add(Order order) {
		String sql = "insert into shop_order (orderno,userid,ordertime,receiveinfo,money,paytype,paytime,status) values (?,?,?,?,?,?,?,?)";
		Object[] params = { order.getOrderNo(), order.getUserId(), order.getOrderTime(), order.getReceiveInfo(),
				order.getMoney(), order.getPayType(), order.getPayTime(), order.getStatus() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 根据id获取订单
	 * 
	 * @return
	 */
	public Order getOrderById(int id) {
		String sql = "select * from shop_order where id=? and is_delete=0";
		return DbHelper.queryForSingleRow(sql, Order.class, id);
	}

	/**
	 * 根据订单编号获取订单
	 * 
	 * @return
	 */
	public Order getOrderByNo(String orderNo) {
		String sql = "select * from shop_order where orderno=? and is_delete=0";
		return DbHelper.queryForSingleRow(sql, Order.class, orderNo);
	}

	/**
	 * 修改订单信息
	 * 
	 * @param admin
	 */
	public void updateOrder(Order order) {
		String sql = "update shop_order set orderno=?,userid=?,ordertime=?,receiveinfo=?,money=?,paytype=?,paytime=?,status=? where id=?";
		Object[] params = { order.getOrderNo(), order.getUserId(), order.getOrderTime(), order.getReceiveInfo(),
				order.getMoney(), order.getPayType(), order.getPayTime(), order.getStatus(), order.getId() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 分页展示按条件查找的订单,订单编号、订单时间、用户
	 * @param page
	 * @param pageSize
	 * @param order
	 * @return
	 */
	public static PageBean<Order> getOrdersByPage(int page, int pageSize, Order order) {
		String sql = "select * from shop_order where is_delete=0 ";
		String sql1 = "select count(*) from shop_order where is_delete=0 ";
		if (order.getOrderNo() != null && !order.getOrderNo().trim().equals("")) {
			sql += " and orderno like '%" + order.getOrderNo() + "%'";
			sql1 += " and orderno like '%" + order.getOrderNo() + "%'";
		}
		if (order.getStartOrderTime() != null && !order.getStartOrderTime().trim().equals("")) {
			sql += " and ordertime >= '" + order.getStartOrderTime() + "'";
			sql1 += " and ordertime >= '" + order.getStartOrderTime() + "'";
		}
		if (order.getEndOrderTime() != null && !order.getEndOrderTime().trim().equals("")) {
			sql += " and ordertime <= '" + order.getEndOrderTime() + "'";
			sql1 += " and ordertime <= '" + order.getEndOrderTime() + "'";
		}
		if (order.getUserId() != 0) {
			sql += " and userid=" + order.getUserId();
			sql1 += " and userid=" + order.getUserId();
		}
		sql += " limit ?,?";

		return DbHelper.getDataByPage(sql, sql1, Order.class, page, pageSize);
	}


	/**
	 * 删除指定id的订单(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
	public void deleteOrderById(int id) {
		String sql = "update shop_order set is_delete=1 where id=?";
		DbHelper.execute(sql, id);
	}

	/**
	 * 恢复删除的订单(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
	public void recoverOrderById(int id) {
		String sql = "update shop_order set is_delete=0 where id=?";
		DbHelper.execute(sql, id);
	}

	/**
	 * 设置产生一个后四位随机的订单编号
	 * 
	 * @return
	 */
	public String setOrderNo() {
		int i = 0;
		while (i < 1000) {
			i = (int) (Math.random() * 10000) + 1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNo = "WN" + sdf.format(new Date()) + i;
		return orderNo;
	}

}
