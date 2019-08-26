package org.sing.tcode.web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Port;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.BackCateService;

import com.google.gson.Gson;

@WebServlet("/cate")
public class BackCateServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String ptype = request.getParameter("ptype");
		BackCateService service = new BackCateService();
		try {
			List<Port> list = service.listBackCate(ptype);
			String json = new Gson().toJson(list);
			response.getWriter().println(json);
		} catch (UserException e) {
			response.getWriter().println(e.getMessage());
			
		}
		
	}
}
