package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.User;
import org.sing.tcode.entity.UserInfo;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/listadmin_info")
public class ListAdminInfoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		UserService service = new UserService();
		List<UserInfo> info = service.listAdminByGroupid();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(info);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
		System.out.println(json);
	}
	
	

}
