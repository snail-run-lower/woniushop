package com.woniuxy.shop.service;

import com.woniuxy.shop.dao.GoodsDAO;
import com.woniuxy.shop.dao.OrderDAO;
import com.woniuxy.shop.dao.OrderDetailsDAO;
import com.woniuxy.shop.dao.UserDAO;
import com.woniuxy.shop.entity.Goods;
import com.woniuxy.shop.entity.Order;
import com.woniuxy.shop.entity.OrderDetails;
import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.entity.User;
import com.woniuxy.shop.exception.DAOException;
import com.woniuxy.shop.exception.ServiceException;
import com.woniuxy.shop.exception.StockNotEnoughException;

public class OrderService {
	OrderDAO orderDAO = new OrderDAO();
	OrderDetailsDAO oDsDAO = new OrderDetailsDAO();
	GoodsDAO gsDAO = new GoodsDAO();
	UserDAO userDAO = new UserDAO();

	/**
	 * 下订单：同时增加订单表和明细表
	 * 
	 * @param order
	 * @param oDs
	 */
	public void add(Order order, OrderDetails... oDs) {
		try {
			// 设置订单编号
			order.setOrderNo(orderDAO.setOrderNo());
			double money = 0;
			for (int i = 0; i < oDs.length; i++) {
				// 通过商品id获取商品信息然后获取商品价格、库存
				Goods goods = gsDAO.getGoodsById((oDs[i].getGoodsId()));
				// 如果库存小于订单明细中的商品数量，下订单失败，如果大于，下单成功并更新库存
				if (goods.getStock() < oDs[i].getNums()) {
					throw new StockNotEnoughException(goods.getName() + "库存不足");
				} else {
					goods.setStock(goods.getStock() - oDs[i].getNums());
					gsDAO.updateGoods(goods);
				}
				// 设置单价
				oDs[i].setPrice(goods.getSalesPrice());
				money += oDs[i].getPrice() * oDs[i].getNums();
			}
			order.setMoney(money);
			orderDAO.add(order);
			order = orderDAO.getOrderByNo(order.getOrderNo());
			for (int i = 0; i < oDs.length; i++) {
				oDs[i].setOrderId(order.getId());
			}
			oDsDAO.add(oDs);
			// 下单成功更新用户积分
			User user = userDAO.getUserById(order.getUserId());
			user.setScore(user.getScore() + 1);
			userDAO.updateUser(user);

		} catch (DAOException e) {
			throw new ServiceException("sql执行出错", e);
		}
	}

	public PageBean<Order> getByPage(int page, int pageSize, Order order) {
		return orderDAO.getOrdersByPage(page, pageSize, order);
	}
}
