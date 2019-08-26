package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.UserType;
import org.sing.tcode.service.UserGroupService;

import com.google.gson.Gson;

@WebServlet("/listgroup")
public class ListGroupServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		UserGroupService service = new UserGroupService();
		List<UserType> g = service.listGroup();
		String json = new Gson().toJson(g);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println(json);
		System.out.println(json);
		
	}
	
	

}
