package com.woniuxy.shop.web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.woniuxy.shop.entity.Category;
import com.woniuxy.shop.entity.Goods;
import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.exception.ServiceException;
import com.woniuxy.shop.service.CategoryService;
import com.woniuxy.shop.service.GoodsService;
import com.woniuxy.shop.service.ServiceProxyFactory;

@WebServlet("/goods")
@MultipartConfig
public class GoodsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoryService categoryService = ServiceProxyFactory.getProxy(CategoryService.class);
	GoodsService goodsService = ServiceProxyFactory.getProxy(GoodsService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF8");
		String opr = req.getParameter("opr");
		if ("initAdd".equals(opr)) {
			doinitAdd(req, resp);
		} else if ("add".equals(opr)) {
			doAdd(req, resp);
		} else if ("query".equals(opr)) {
			doQuery(req, resp);
		}
	}

	private void doQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		页码
		int page = 1;
		String p = req.getParameter("p");
		if (p != null) {
			page = Integer.parseInt(p);
		}
//		获取查询条件
		String name = req.getParameter("name");
		String categoryid = req.getParameter("categoryId");
		String status = req.getParameter("status");
		String lowStock = req.getParameter("lowstock");
		String highStock = req.getParameter("highstock");
//		查询条件
		Goods goods = new Goods();
		if (categoryid != null && !categoryid.equals("")) {
			goods.setCategoryId(Integer.parseInt(categoryid));
		}

		if (name != null && !name.trim().equals("")) {
			goods.setName(name);
		}
		if (status != null && !status.trim().equals("")) {
			goods.setStatus(status);
		}

		if (lowStock != null && !lowStock.equals("")) {
			goods.setLowStock(Integer.parseInt(lowStock));
		}
		if (highStock != null && !highStock.equals("")) {
			goods.setHighStock(Integer.parseInt(highStock));
		}

//		多条件分页查询
		PageBean<Goods> pageBean = goodsService.getGoodsesByPage(page, 3, goods);
//		获取商品类别传到页面
		List<Category> categories = categoryService.getCategorys();
		req.setAttribute("categories", categories);
//		商品信息传到页面
		req.setAttribute("pageBean", pageBean);
//		查询条件再传回页面
		req.setAttribute("goods", goods);
		req.getRequestDispatcher("goods/goods_list.jsp").forward(req, resp);
	}

	private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		int categoryid = Integer.parseInt(req.getParameter("categoryid"));
		int stock = Integer.parseInt(req.getParameter("stock"));
		double marketprice = Double.parseDouble(req.getParameter("marketprice"));
		double salesprice = Double.parseDouble(req.getParameter("salesprice"));
		// 获取文件
		Part part = req.getPart("image");
		// 得到tomcat的绝对路径
		String path = getServletContext().getRealPath("/images/goods");
		// 产生一个唯一的文件名
		String filename = UUID.randomUUID().toString().replace("-", "");
		// 将文件存储到指定位置
		part.write(path + "/" + filename);
		String image = "/images/goods/" + filename;
		String description = req.getParameter("content");
		Goods goods = new Goods();
		goods.setName(name);
		goods.setCategoryId(categoryid);
		goods.setStock(stock);
		goods.setMarketPrice(marketprice);
		goods.setSalesPrice(salesprice);
		goods.setImage(image);
		goods.setDescription(description);
		goods.setStatus("y");
		try {
		goodsService.add(goods);
		}catch(ServiceException e) {
			req.setAttribute("msg", "系统维护中");
			req.getRequestDispatcher("goods?opr=add").forward(req, resp);
		}
	}

	private void doinitAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categories = categoryService.getCategorys();
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("goods/goods_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
