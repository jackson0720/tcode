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
@WebServlet("/view_postss")
public class ViewPostsServlets extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session =request.getSession();
		Posts posts =(Posts)session.getAttribute("posts");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(posts);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}

}
