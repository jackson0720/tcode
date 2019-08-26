package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Port;
import org.sing.tcode.service.PortService;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
@WebServlet("/portbyname")
public class ListPortBynameServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		 String name=request.getParameter("name");
		 PortService service = new PortService();
		List<Port> list = service.listPortByname(name);
		String json = new Gson().toJson(list);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		System.out.println(json);
	}

}
