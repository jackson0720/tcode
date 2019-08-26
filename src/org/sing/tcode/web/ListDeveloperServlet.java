package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.Posts;
import org.sing.tcode.service.PostsService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/list_Dev")
public class ListDeveloperServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PostsService service = new PostsService();
		List<Posts> post = service.listPostByName("开发讨论");
		String json = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(post);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println(json);
	}
	
	

}
