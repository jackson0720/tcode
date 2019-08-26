package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.User;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;

@WebServlet("/getpwd")
public class GetPasswordServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("mail");
		UserService service = new UserService();
		User user = service.getPasswordByEmail(account);
		String json = new Gson().toJson(user);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
	}
	
	

}
