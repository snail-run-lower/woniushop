package com.woniuxy.shop.service;

import java.util.List;

import com.woniuxy.shop.dao.CategoryDAO;
import com.woniuxy.shop.dao.GoodsDAO;
import com.woniuxy.shop.entity.Category;
import com.woniuxy.shop.exception.CategoryExistException;
import com.woniuxy.shop.exception.DAOException;
import com.woniuxy.shop.exception.ReferenceException;
import com.woniuxy.shop.exception.ServiceException;

public class CategoryService {
	CategoryDAO categoryDAO = new CategoryDAO();
	GoodsDAO goodsDAO=new GoodsDAO();
	/**
	 * 增加商品类别
	 * 
	 * @param category
	 */
	public void add(Category category) {
		try {
			Category c = categoryDAO.getCategoryByName(category.getName());
			if (c == null) {
				categoryDAO.add(category);
			} else {
				throw new CategoryExistException("商品类别已存在");
			}
		} catch (DAOException e) {
			throw new ServiceException("SQL执行出错", e);
		}
	}

	/**
	 * 查询所有的商品类别
	 * 
	 * @return
	 */
	public List<Category> getCategorys() {
		return categoryDAO.getCategorys();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		try {
			if(goodsDAO.getNumByCategoryId(id)>0) {
				throw new ReferenceException("存在该商品类别的商品，无法删除");
			}
			categoryDAO.deleteCategoryById(id);
		} catch (DAOException e) {
			throw new ServiceException("SQL执行出错", e);
		}
	}

	/**
	 * 修改
	 * 
	 * @param category
	 */
	public void update(Category category) {
		categoryDAO.updateCategory(category);
	}

	public Category getById(int id) {
		return categoryDAO.getCategoryById(id);
	}
}
