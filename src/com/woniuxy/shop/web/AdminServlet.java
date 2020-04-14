package com.woniuxy.shop.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.shop.entity.Admin;
import com.woniuxy.shop.exception.AccountExistException;
import com.woniuxy.shop.exception.ServiceException;
import com.woniuxy.shop.service.AdminService;
import com.woniuxy.shop.service.ServiceProxyFactory;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	AdminService adminService = ServiceProxyFactory.getProxy(AdminService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF8");
		String opr = req.getParameter("opr");
		if ("add".equals(opr)) {
			doAdd(req, resp);
		}
		if ("login".equals(opr)) {
			doLogin(req, resp);
		}
	}

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		req.setAttribute("account", account);
		req.setAttribute("password", password);
		String passcode = req.getParameter("passcode");
		if (passcode == null || passcode.trim().equals("")) {
			req.setAttribute("msg", "请输入验证码");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		String captcha = req.getSession().getAttribute("captcha").toString();
		if (!captcha.equals(passcode)) {
			req.setAttribute("msg", "验证码不正确");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		try {
			Admin admin = adminService.getAdmin(account);
			if (admin == null) {
				req.setAttribute("msg", "账户不存在");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else if (!admin.getPassword().equals(password)) {
				req.setAttribute("msg", "密码不正确");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				req.getSession().setAttribute("admin", admin);
				resp.sendRedirect("index.jsp");
			}

		} catch (ServiceException e) {
			req.setAttribute("msg", "系统维护中...");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

	private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String password1 = req.getParameter("password1");
		// 校验账户是否为空
		if (account == null || account.trim().equals("")) {
			req.setAttribute("accountmsg", "账户不能为空");
			req.getRequestDispatcher("admin/admin_add.jsp").forward(req, resp);
			return;
		}
		// 校验账户长度
		if (account.length() <= 3 || account.length() >= 15) {
			req.setAttribute("accountmsg", "账户长度不符合要求");
			req.getRequestDispatcher("admin/admin_add.jsp").forward(req, resp);
			return;
		}
		// 校验密码是否为空
		if (password == null || password.trim().equals("")) {
			req.setAttribute("passwordmsg", "密码不能为空");
			req.getRequestDispatcher("admin/admin_add.jsp").forward(req, resp);
			return;
		}
		// 校验密码长度
		if (password.length() <= 5 || password.length() >= 15) {
			req.setAttribute("passwordmsg", "密码长度不符合要求");
			req.getRequestDispatcher("admin/admin_add.jsp").forward(req, resp);
			return;
		}
		// 确认密码
		if (!password.equals(password1)) {
			req.setAttribute("password1msg", "确认密码与密码不一致");
			req.getRequestDispatcher("admin/admin_add.jsp").forward(req, resp);
			return;
		}
		Admin admin = new Admin();
		admin.setAccount(account);
		admin.setPassword(password);
		admin.setRole("1");
		admin.setStatus("y");
		try {
			adminService.add(admin);
		} catch (ServiceException e) {
			if (e.getCause() instanceof AccountExistException) {
				req.setAttribute("accountmsg", "账户已存在");
			}
			req.getRequestDispatcher("admin/admin_add.jsp").forward(req, resp);
		}
		resp.sendRedirect("admin/admin_add.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
