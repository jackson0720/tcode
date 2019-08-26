package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.omg.CORBA.UserException;
import org.sing.tcode.entity.Fans;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;

import java.io.*;
@WebServlet("/count_fans")
public class CountFansServlet extends HttpServlet{
	
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		UserService service = new UserService();
		
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		Integer fans = service.accountFans(account);
//		System.out.println(fans);
		String json = new Gson().toJson(fans);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		
		
		
	}
}
