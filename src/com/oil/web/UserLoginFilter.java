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
		Integer loginFlag = -1;
		String loginType = "";
		Integer userId = 0;
		if(session != null){
			
		}
		Integer loginFlag_dataBase = -1;
		if(userId.equals(0)){
			if(!requesturi.endsWith("/login.do") 
					&& !requesturi.endsWith("/common.do")
					&& !requesturi.endsWith("/login.do")
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
			try {
				
				//获取数据库中指定currentUser的loginFlag
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!loginFlag.equals(loginFlag_dataBase)){
				session.invalidate();
				String url = "window.top.location.href='login.do?action=loginOut'";
				String authorizeScript = "该账号已经在别处登录，系统已强制您下线，请重新登录！";
				Ability.PrintAuthorizeScript(url,authorizeScript, httpServletResponse);
			}else{
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
		this.forwardPath = filterConfig.getInitParameter("forwardpath");
	}
}
