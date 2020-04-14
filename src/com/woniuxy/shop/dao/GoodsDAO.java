package com.woniuxy.shop.dao;

import java.util.List;

import com.woniuxy.shop.entity.Goods;
import com.woniuxy.shop.entity.PageBean;

public class GoodsDAO {
	/**
	 * 增加图书
	 * 
	 * @param admin
	 */
	public void add(Goods goods) {
		String sql = "insert into shop_goods (name,categoryid,description,image,stock,marketprice,salesprice,score,status) values (?,?,?,?,?,?,?,?,?)";
		Object[] params = { goods.getName(), goods.getCategoryId(), goods.getDescription(), goods.getImage(),
				goods.getStock(), goods.getMarketPrice(), goods.getSalesPrice(), goods.getScore(), goods.getStatus() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 根据id获取图书
	 * 
	 * @return
	 */
	public Goods getGoodsById(int id) {
		String sql = "select * from shop_goods where id=? and is_delete=0";
		return DbHelper.queryForSingleRow(sql, Goods.class, id);
	}

	/**
	 * 获取该商品类别商品的数量
	 * 
	 * @param categoryid
	 * @return
	 */
	public int getNumByCategoryId(int categoryid) {
		String sql = "select count(*) from shop_goods where categoryid=? ";
		return ((Long) DbHelper.queryForScalar(sql, categoryid)).intValue();

	}

	/**
	 * 修改图书信息
	 * 
	 * @param admin
	 */
	public void updateGoods(Goods goods) {
		String sql = "update shop_goods set name=?,categoryid=?,description=?,image=?,stock=?,marketprice=?,salesprice=?,score=?,status=? where id=?";
		Object[] params = { goods.getName(), goods.getCategoryId(), goods.getDescription(), goods.getImage(),
				goods.getStock(), goods.getMarketPrice(), goods.getSalesPrice(), goods.getScore(), goods.getStatus(),
				goods.getId() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 多条件分页查询，类别、评分、价格、状态
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageBean<Goods> getGoodsesByPage(int page, int pageSize, Goods goods) {
		String sql = "select g.*,c.`name` categoryname from shop_category c,shop_goods g where g.`categoryid`=c.`id` and g.`is_delete`=0 "
				+ createCondition(goods) + " limit ?,?";
		String sql1 = "select count(*) from shop_goods g where is_delete=0 " + createCondition(goods);

		return DbHelper.getDataByPage(sql, sql1, Goods.class, page, pageSize);
	}

	@SuppressWarnings("unused")
	private String createCondition(Goods goods) {
		String condition = "";
		if (goods == null) {
			return condition;
		}
		if (goods.getCategoryId() != 0) {// 类别
			condition += " and g.categoryid=" + goods.getCategoryId();
		}
		if (goods.getName() != null && !goods.getName().trim().equals("")) {
			condition += " and g.name like '%" + goods.getName() + "%'";
		}
		if (goods.getLowStock() != 0) {// 库存
			condition += " and g.stock>=" + goods.getLowStock();
		}
		if (goods.getHighStock() != 0) {
			condition += " and g.stock<=" + goods.getHighStock();
		}
		if (goods.getLowSalesPrice() != 0) {// 价格
			condition += " and g.salesprice>=" + goods.getLowSalesPrice();
		}
		if (goods.getHighSalesPrice() != 0) {
			condition += " and g.salesprice<=" + goods.getHighSalesPrice();
		}
		if (goods.getLowScore() != 0) {// 评分
			condition += " and g.score>=" + goods.getScore();
		}
		if (goods.getHighScore() != 0) {
			condition += " and g.score<=" + goods.getScore();
		}
		// 状态
		if (goods.getStatus() != null && !goods.getStatus().trim().equals("")) {
			condition += " and g.status='" + goods.getStatus() + "'";
		}
		return condition;
	}

	/**
	 * 删除指定id的图书(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
	public void deleteGoodsById(int id) {
		String sql = "update shop_goods set is_delete=1 where id=?";
		DbHelper.execute(sql, id);
	}

	/**
	 * 恢复指定id的管理员账户(逻辑删除,默认为0，1代表删除)
	 * 
	 * @param id
	 */
	public void recoverCategoryById(int id) {
		String sql = "update shop_goods set is_delete=0 where id=?";
		DbHelper.execute(sql, id);
	}

}
