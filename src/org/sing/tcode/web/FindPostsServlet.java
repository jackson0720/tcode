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

@WebServlet("/find_posts")
public class FindPostsServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html;charset=utf-8");
		 String pid = req.getParameter("name");
		 PostsService service = new PostsService();
		 List<Posts> list = service.listPostByName(pid);
		 String json = new Gson().toJson(list);
		 //响应回页面
		 resp.setContentType("application/json;charset=utf-8");
		 resp.getWriter().println(json);
	}
	
	

}
