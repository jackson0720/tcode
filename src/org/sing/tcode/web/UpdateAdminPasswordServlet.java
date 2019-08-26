package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/update_admin_pwd")
public class UpdateAdminPasswordServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
//		HttpSession session = req.getSession();
//		session.setAttribute("info", account);
		String json = new Gson().toJson(account);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
		System.out.println(json);
	}
	
	

}
