package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.Port;
import org.sing.tcode.entity.User;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.PortService;
import org.sing.tcode.service.UserService;
@WebServlet("/insertport")
public class InsertPortServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
		if(user != null) {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String pname = request.getParameter("pname");
			String txttarea = request.getParameter("txttarea");
			String se = request.getParameter("se");
			UserService service1 = new UserService();
			PortService service = new PortService();
			Port p = new Port();
			p.setPaccount(pname);
			p.setPtype(se);
			p.setPtext(txttarea);		
			try {
				service.insertPort(p);
				service1.updateUserPointer(10, pname);
				
			} catch (UserException e) {
				throw new UserException(e.getMessage());
			}
		}else {
			response.sendRedirect("Login.html");
		}
		
	}
	
}
