package org.sing.tcode.web;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.LoginInfo;
import org.sing.tcode.service.LoginInfoService;
import org.sing.tcode.util.DateUtil;

@WebServlet("/add_login_info")
public class AddLoginInfoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		//String time = 
		String name = InetAddress.getLocalHost().getHostName();
		String account = req.getParameter("account");
		String info = new java.sql.Date(new Date().getTime())+"在名为"+name+"的设备上进行了登录！";
		String IP = InetAddress.getLocalHost().getHostAddress();
		LoginInfo i = new LoginInfo();
		i.setLg_account(account);
		i.setLg_info(info);
		i.setLg_ip(IP);
		LoginInfoService service = new LoginInfoService();
		service.addInfo(i);
		
		
		
	}
	
	

}
