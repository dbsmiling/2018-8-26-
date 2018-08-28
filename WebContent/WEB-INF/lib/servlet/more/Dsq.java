package com.backstage.servlet.more;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 嘉如新上人
 * 后台设置定时器。时间，传输至前台
 */
public class Dsq extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ds = request.getParameter("ds");
		String sj = request.getParameter("sj");
		// ds=定时时间   sj=时间
		
		
	}
}
