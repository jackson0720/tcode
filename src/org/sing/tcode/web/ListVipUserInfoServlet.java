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

@WebServlet("/list_vip_user_info")
public class ListVipUserInfoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		UserService service = new UserService();
		UserInfo uinfo = service.listVipInfoByAccount(account);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(uinfo);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
		System.out.println(json);
	}
	
	

}
