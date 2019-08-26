package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.Posts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/getresult")
public class GetResultServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		HttpSession session =req.getSession();
		String res = (String) session.getAttribute("message");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(res);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
	}
	
	

}
