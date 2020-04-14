package com.woniuxy.shop.dao;

import java.util.List;

import com.woniuxy.shop.entity.Category;


public class CategoryDAO {
	/**
	 * 增加图书类别
	 * 
	 * @param admin
	 */
	public void add(Category category) {
		String sql = "insert into shop_category (name,status) values (?,?)";
		Object[] params = {category.getName(),category.getStatus() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 根据name获取图书类别
	 * 
	 * @return
	 */
	public Category getCategoryByName(String name) {
		String sql = "select * from shop_category where name=? and is_delete=0";
		return DbHelper.queryForSingleRow(sql, Category.class, name);
	}
	public Category getCategoryById(int id) {
		String sql = "select * from shop_category where id=? and is_delete=0";
		return DbHelper.queryForSingleRow(sql, Category.class, id);
	}
	
	

	/**
	 * 修改图书类别
	 * 
	 * @param admin
	 */
	public void updateCategory(Category category) {
		String sql = "update shop_category set name=?,status=? where id=?";
		Object[] params = {category.getName(),category.getStatus(),category.getId()};
		DbHelper.execute(sql, params);
	}

	/**
	 * 获取所有图书类别
	 * 
	 * @return
	 */
	public List<Category> getCategorys() {
		String sql = "select * from shop_category where is_delete=0 ";
		return DbHelper.query(sql, Category.class);
	}
	/**
	 * 删除指定id的图书类
	 * @param id
	 */
	public void deleteCategoryById(int id) {
		String sql="delete from shop_category where id=?";
		DbHelper.execute(sql, id);
	}

	/**
	 * 删除指定id的图书类(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
//	public void deleteCategoryById(int id) {
//		String sql = "update shop_category set is_delete=1 where id=?";
//		DbHelper.execute(sql, id);
//	}
	/**
	 * 恢复指定id的管理员账户(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
	public void recoverCategoryById(int id) {
		String sql = "update shop_category set is_delete=0 where id=?";
		DbHelper.execute(sql, id);
	}
}
