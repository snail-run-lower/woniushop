package com.woniuxy.shop.service;

import com.woniuxy.shop.dao.GoodsDAO;
import com.woniuxy.shop.entity.Goods;
import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.exception.DAOException;
import com.woniuxy.shop.exception.ServiceException;

public class GoodsService {
	GoodsDAO goodsDAO = new GoodsDAO();
	/**
	 * 多条件分页查询
	 * @param page
	 * @param pageSize
	 * @param goods
	 * @return
	 */
	public PageBean<Goods> getGoodsesByPage(int page, int pageSize, Goods goods) {
		return goodsDAO.getGoodsesByPage(page, pageSize, goods);
	}

	public void add(Goods goods) {
		try {
			goodsDAO.add(goods);
		} catch (DAOException e) {
			throw new ServiceException("SQL执行出错", e);
		}
	}
}
