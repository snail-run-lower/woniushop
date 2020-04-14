package com.woniuxy.shop.service;

import com.woniuxy.shop.dao.RemarkDAO;
import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.entity.Remark;

public class RemarkService {
	RemarkDAO remarkDAO=new RemarkDAO();
	public PageBean<Remark> getRemarksByPage(int page, int pageSize, Remark remark) {
		return remarkDAO.getRemarksByPage(page, pageSize, remark);
	}
		

}
