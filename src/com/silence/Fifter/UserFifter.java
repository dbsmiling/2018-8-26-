package com.silence.Fifter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import com.silence.Sevice.UserService;
import com.silence.Sevicelmp.UserServicelmp;

public class UserFifter  implements Filter {

	@Override
	public void destroy() {
		System.out.println("销毁了fifter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("进入了fifter");
		
		chain.doFilter(request, response);
//		UserService us = new UserServicelmp();
//		List<Map> a =(List<Map>) us.UserService("gmail@gamil.com");
//		System.out.println("a.size():"+a.size());
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("初始化fifter");
		
	}

}
