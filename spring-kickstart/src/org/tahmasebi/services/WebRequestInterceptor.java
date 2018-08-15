package org.tahmasebi.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebRequestInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		response.addHeader("Access-Control-Allow-Origin","http://localhost:4200");
//		response.addHeader("Access-Control-Allow-Methods","GET, POST, PATCH, PUT, DELETE, OPTIONS");
//		response.addHeader("Access-Control-Allow-Headers","*");
//		response.addHeader("Access-Control-Allow-Credentials","true");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
