package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sing.tcode.entity.UserInfo;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/userinfo")
public class ListUserInfoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setCharacterEncoding("utf-8");
		 req.setCharacterEncoding("utf-8");
		 String name = req.getParameter("username");
		 UserService service = new UserService();
		 UserInfo list = service.getUserInfoByAccount(name);
		 String json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(list);
		 //响应回页面
		 resp.setContentType("application/json;charset=utf-8");
		 resp.getWriter().println(json);
		 System.out.println(json);
	}
	
	

}
