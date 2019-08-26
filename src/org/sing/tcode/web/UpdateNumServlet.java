package org.sing.tcode.web;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.User;
import org.sing.tcode.service.UserService;

@WebServlet("/updatenum")
public class UpdateNumServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String account = req.getParameter("account");
		User user = new User();
		user.setU_account(account);
		user.setU_IP(InetAddress.getLocalHost().getHostAddress());
		UserService service = new UserService();
		service.updateNum(InetAddress.getLocalHost().getHostAddress(),account);
	}
	
	

}
