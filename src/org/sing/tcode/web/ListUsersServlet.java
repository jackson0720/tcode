package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

 
import org.sing.tcode.entity.Users;
import org.sing.tcode.service.UserService;
import org.sing.tcode.util.PageBean;

import com.google.gson.Gson;

 

import java.io.*;
 
@WebServlet("/listUsers")
public class ListUsersServlet extends BasicServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum;
		int pageSize;
		try { 
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} catch (Exception e) {
			pageNum=1;
			pageSize=5;
		}
		UserService service = new UserService();
		PageBean<Users>  pageBean = service.listUsers(pageNum, pageSize); 
	 String json = new Gson().toJson(pageBean);
	 response.setContentType("application/json;charset=utf-8");
	 response.getWriter().print(json);

	}
}
