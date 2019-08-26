package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.LoginInfo;
import org.sing.tcode.service.LoginInfoService;

import com.google.gson.Gson;
@WebServlet("/list_user_info")
public class ListLoginInfoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		 resp.setContentType("text/plain;charset=utf-8");
		 LoginInfoService service = new LoginInfoService();
		 String account = req.getParameter("account");
		 LoginInfo l = new LoginInfo();
		 l.setLg_account(account);
		 List<LoginInfo> list = service.listadmin(l);
		 String json = new Gson().toJson(list);
		 resp.setContentType("application/json;charset=utf-8");
		 resp.getWriter().print(json);
		 System.out.println(json);
		 //System.out.println(account);
	}
	
	

}
