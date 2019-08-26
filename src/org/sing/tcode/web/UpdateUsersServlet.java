package org.sing.tcode.web;
import javax.servlet.http.*;

import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
@WebServlet("/updateUsers")
public class UpdateUsersServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws  ServletException,IOException{
		
		int state =Integer.valueOf(request.getParameter("state"));
		String name = request.getParameter("name");
		UserService service = new UserService();
	 
		service.updateUsers(state, name);
		service.updateUserss(state, name);
		
	}
	
}
