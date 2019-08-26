package org.sing.tcode.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.Posts;
import org.sing.tcode.entity.User;
import org.sing.tcode.service.UserService;

import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/logins")
public class LoginServlets extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession();
		User users =(User)session.getAttribute("user");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(users);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
	}

}
