package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.User;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.UserService;

@WebServlet("/admin_login")
public class LoginAdminServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User();
		user.setU_account(userName);
		user.setU_password(password);
		UserService service = new UserService();
		try {
			User u = service.listadmin(user);
			HttpSession session = req.getSession();
			session.setAttribute("admin", u); 
			resp.sendRedirect("back-index.html");
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException("登录失败");
		}
	}
}
