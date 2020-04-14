package com.woniuxy.shop.dao;

import java.util.List;

import com.woniuxy.shop.entity.OrderDetails;
import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.entity.Remark;

public class RemarkDAO {
	/**
	 * 增加评论
	 * 
	 * @param admin
	 */
	public void add(Remark remark) {
		String sql = "insert into shop_remark (userid,goodsid,score,content,remarktime,status) values (?,?,?,?,?,?)";
		Object[] params = { remark.getUserId(), remark.getGoodsId(), remark.getScore(), remark.getContent(),
				remark.getRemarkTime(), remark.getStatus() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 根据id获取评论
	 * 
	 * @return
	 */
	public Remark getRemarkById(int id) {
		String sql = "select * from shop_remark where id=?";
		return DbHelper.queryForSingleRow(sql, Remark.class, id);
	}

	/**
	 * 根据goodsid获取评论
	 * 
	 * @return
	 */
	public List<Remark> getRemarksByGoods(int goodsid) {
		String sql = "select * from shop_remark where goodsid=?";
		return DbHelper.query(sql, Remark.class, goodsid);
	}

	/**
	 * 修改评论
	 * 
	 * @param admin
	 */
	public void updateRemark(Remark remark) {
		String sql = "update shop_remark set userid=?,goodsid=?,score=?,content=?,remarktime=?,status=? where id=?";
		Object[] params = { remark.getUserId(), remark.getGoodsId(), remark.getScore(), remark.getContent(),
				remark.getRemarkTime(), remark.getStatus(), remark.getId() };
		DbHelper.execute(sql, params);
	}

	/**
	 * 分页展示多条件查找的评论
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageBean<Remark> getRemarksByPage(int page, int pageSize, Remark remark) {
		String sql = "select * from shop_remark where 1=1 ";// 在语句后加条件1=1接and
		String sql1 = "select count(*) from shop_remark where 1=1 ";
		if (remark.getGoodsId() != 0) {
			sql += " and goodsid=" + remark.getGoodsId();
			sql1 += " and goodsid=" + remark.getGoodsId();
		}
		if (remark.getStatus() != null && !remark.getStatus().trim().equals("")) {
			sql += " and status='" + remark.getStatus() + "'";
			sql1 += " and status='" + remark.getStatus() + "'";
		}
		sql += " limit ?,?";
		return DbHelper.getDataByPage(sql, sql1, Remark.class, page, pageSize);
	}

	/**
	 * 删除指定id的评论
	 * 
	 * @param id
	 */
	public void deleteRemarkById(int id) {
		String sql = "delete from shop_remark where id=?";
		DbHelper.execute(sql, id);
	}

}
