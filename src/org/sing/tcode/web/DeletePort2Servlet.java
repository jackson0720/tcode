package org.sing.tcode.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.PortService;
import org.sing.tcode.service.PostsService;

@WebServlet("/delete_port")
public class DeletePort2Servlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;setchar=utf-8");
		String p = request.getParameter("pno");
		System.out.println(p);
		PortService service = new PortService();
		try {
			service.deletePort(p);
			System.out.println("请求已到达Servlet");
		} catch (UserException e) {
			response.getWriter().println(e.getMessage());
		}
	}
}
