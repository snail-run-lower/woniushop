package com.woniuxy.shop.entity;

import java.util.List;

/**
 * 封装每页查询的数据
 * @author l
 *
 * @param <E>
 */
public class PageBean<E> {
	private List<E> data;//当前页数据
	private int totalNum;//总数
	private int totalPage;//总页数
	private int pageSize;//每页的数量
	private int page;//当前页
	private int numsOfCurrPage;//当前页的数量
	public List<E> getData() {
		return data;
	}
	public void setData(List<E> data) {
		this.data = data;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNumsOfCurrPage() {
		return numsOfCurrPage;
	}
	public void setNumsOfCurrPage(int numsOfCurrPage) {
		this.numsOfCurrPage = numsOfCurrPage;
	}
	@Override
	public String toString() {
		return "PageBean [data=" + data + ", totalNum=" + totalNum + ", totalPage=" + totalPage + ", pageSize="
				+ pageSize + ", page=" + page + ", numsOfCurrPage=" + numsOfCurrPage + "]";
	}
	

}
