package com.woniuxy.shop.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.shop.entity.Category;
import com.woniuxy.shop.exception.CategoryExistException;
import com.woniuxy.shop.exception.ReferenceException;
import com.woniuxy.shop.exception.ServiceException;
import com.woniuxy.shop.service.CategoryService;
import com.woniuxy.shop.service.ServiceProxyFactory;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoryService categoryService = ServiceProxyFactory.getProxy(CategoryService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF8");
		String opr = req.getParameter("opr");
		if ("add".equals(opr)) {
			doAdd(req, resp);
		} else if ("query".equals(opr)) {
			doQuery(req, resp);
		} else if ("delete".equals(opr)) {
			doDel(req, resp);
		} else if ("initUpdate".equals(opr)) {
			doinitUpdate(req, resp);
		} else if ("update".equals(opr)) {
			doUpdate(req, resp);
		}
	}

	private void doinitUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("categoryid"));
		Category category = categoryService.getById(id);
		if (category == null) {
			req.setAttribute("msg", "商品类已被删除");
			req.getRequestDispatcher("category/category_update.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("category", category);
		req.getRequestDispatcher("category/category_update.jsp").forward(req, resp);
		return;

	}

	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("categoryid"));
		String name = req.getParameter("name");
		String status = req.getParameter("status");
		Category category = categoryService.getById(id);
		if (name == null || name.trim().equals("")) {
			req.setAttribute("msg", "商品类名不能为空");
			req.setAttribute("category", category);
			req.getRequestDispatcher("category/category_update.jsp").forward(req, resp);
			return;
		}
//		校验名字是否重复
		List<Category> categorys = categoryService.getCategorys();
		for (Category c : categorys) {
			if (c.getName().equals(name) && c.getId() != id) {
				req.setAttribute("msg", "商品类名已存在");
				req.setAttribute("category", category);
				req.getRequestDispatcher("category/category_update.jsp").forward(req, resp);
				return;
			}
		}
		
		category.setName(name.trim());
		category.setStatus(status);
		try {
			categoryService.update(category);
		} catch (ServiceException e) {
			req.setAttribute("msg", "正在加载99%...");
			req.setAttribute("category", category);
			req.getRequestDispatcher("category/category_update.jsp").forward(req, resp);
			return;
			
		}
		resp.sendRedirect("category?opr=query");

	}

	private void doDel(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("categoryid"));
		try {
		categoryService.delete(id);
		}catch(ServiceException e) {
			if(e.getCause() instanceof ReferenceException) {
				req.setAttribute("msg", "存在该类别的商品，无法删除");
			}else {
				req.setAttribute("msg", "系统维护中，删除失败！");
			}
			List<Category> categorys = categoryService.getCategorys();
			req.setAttribute("categorys", categorys);// 将数据存储到request作用域
			req.getRequestDispatcher("category/category_list.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("category?opr=query");
	}

	private void doQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categorys = categoryService.getCategorys();
		req.setAttribute("categorys", categorys);// 将数据存储到request作用域
		req.getRequestDispatcher("category/category_list.jsp").forward(req, resp);// 请求分发？
	}

	private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		if (name == null || name.trim().equals("")) {
			req.setAttribute("msg", "商品类名不能为空");
			req.getRequestDispatcher("category/category_add.jsp").forward(req, resp);
			return;
		}
		Category category = new Category();
		category.setName(name.trim());
		category.setStatus("y");
		try {
			categoryService.add(category);

		} catch (ServiceException e) {
			if (e.getCause() instanceof CategoryExistException) {
				req.setAttribute("msg", "类名已存在");
				req.setAttribute("category", category);
				req.getRequestDispatcher("category/category_add.jsp").forward(req, resp);
				return;
			}
		}
		resp.sendRedirect("category?opr=query");
//		req.getRequestDispatcher("category/category_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
