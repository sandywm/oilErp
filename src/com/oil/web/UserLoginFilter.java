package com.oil.web;

import com.oil.factory.AppFactory;
import com.oil.tools.Ability;
import com.oil.util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
public class UserLoginFilter implements Filter{
	
	private String forwardPath = null;
	private FilterConfig filterConfig = null;

	public void destroy()
	{
		this.forwardPath = null;
		this.filterConfig = null;
	}

	@SuppressWarnings("null")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String requestUrl[] = httpServletRequest.getRequestURI().split(";");
		//攻击检测增加代码---start
		String param =  httpServletRequest.getQueryString();//action动作地址
		String filePath = "e:\\attackReport.txt";
		String oldParam = param;
		
		//攻击检测增加代码---end
	    String requesturi = requestUrl[0]; 
		// 通过检查session中的变量，过滤请求
		HttpSession session = httpServletRequest.getSession(false);
		String userName = "";
		if(session != null){
			userName = String.valueOf(session.getAttribute("userName"));
		}
		Integer loginFlag_dataBase = -1;
		if(userName.equals("") || userName.equals("null")){
			if(!requesturi.endsWith("/login.do") 
					&& !requesturi.endsWith("/authImg")
					&& !requesturi.endsWith("jsp")
					&& !requesturi.endsWith("css") 
					&& !requesturi.endsWith("js")
					&& !requesturi.endsWith("png")
					&& !requesturi.endsWith("gif")
					&& !requesturi.endsWith("jpg")
					&& !requesturi.endsWith("jpeg")
					&& !requesturi.endsWith("ico")
					&& !requesturi.endsWith("ttf")
					&& !requesturi.endsWith("json")
					&& !requesturi.endsWith(httpServletRequest.getContextPath()+ "/")){
                String url = "window.top.location.href='login.do?action=loginOut'";
				String authorizeScript = "由于您60分钟内没上线，系统已强制您下线，请重新登录！";
				Ability.PrintAuthorizeScript(url,authorizeScript, httpServletResponse);
                return;
            }
			chain.doFilter(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
		this.forwardPath = filterConfig.getInitParameter("forwardpath");
	}
}
