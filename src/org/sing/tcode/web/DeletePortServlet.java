package org.sing.tcode.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.PostsService;

@WebServlet("/del_Port")
public class DeletePortServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;setchar=utf-8");
		String[] ids= request.getParameterValues("option");
		System.out.println(ids);
		PostsService service = new PostsService();
		try {
			service.deletePosts(ids);
		} catch (UserException e) {
			response.getWriter().println(e.getMessage());
		}
	}
}
