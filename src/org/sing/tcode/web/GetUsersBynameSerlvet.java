package org.sing.tcode.web;
import javax.servlet.http.*;

import org.sing.tcode.entity.ResponseVO;
import org.sing.tcode.entity.Users;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
@WebServlet("/userByname")
public class GetUsersBynameSerlvet extends BasicServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		ResponseVO vo=null;
		String txtname = request.getParameter("txtname");
		UserService service = new UserService();
		Users us= service.getUsersByname(txtname);
		vo=success(200,us);
		String json = new Gson().toJson(vo);
		response.getWriter().print(json);
	}
}
