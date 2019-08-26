package org.sing.tcode.web;
import javax.servlet.http.*;

import org.sing.tcode.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
@WebServlet("/deleteUser")
public class DeleteUsersServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 UserService service =  new UserService();
	 String name = request.getParameter("name");
	 service.deleteUsers(name);
	}
	
}
