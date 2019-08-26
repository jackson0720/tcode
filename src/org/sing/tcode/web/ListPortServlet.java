package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.Port;
import org.sing.tcode.service.PortService;

import com.google.gson.Gson;

@WebServlet("/listport")
public class ListPortServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		 PortService service = new PortService();
		 List<Port> list = service.listPort();
		 String json = new Gson().toJson(list);
		 response.setContentType("application/json;charset=utf-8");
		 response.getWriter().print(json);
		 System.out.println(json);
	}
}
