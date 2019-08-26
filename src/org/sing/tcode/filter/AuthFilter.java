package org.sing.tcode.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.User;

public class AuthFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
//		HttpServletResponse response = (HttpServletResponse)resp;
//        HttpServletRequest request = (HttpServletRequest)req;
//        HttpSession session = request.getSession();
//        User user = (User)session.getAttribute("user");
//        String uri = request.getRequestURI();
//        //简单判断缓存中是否有用户
//        if(user==null){//没有用户
//            //判断用户是否是选择跳到登录界面
//            if(uri.endsWith("Login.html")||uri.endsWith("login.do")){
//                chain.doFilter(request, response);
//            }else{
//            	response.sendRedirect(request.getContextPath()+"/Login.html");
//            }    
//        }else{//有用户
////        	Integer num = user.getU_num();
////        	System.out.println(num);
////        	if(num == 0) {
////        		response.sendRedirect(request.getContextPath()+"/technology.html");
////        	}
//            chain.doFilter(request, response);
//        }
//        chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
