package com.woniuxy.shop.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		context.setAttribute("base", context.getContextPath());
	}
}
