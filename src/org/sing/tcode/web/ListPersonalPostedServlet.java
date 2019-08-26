package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Posts;
import org.sing.tcode.service.CommentService;
import org.sing.tcode.service.PostsService;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
@WebServlet("/list_personalposted")
public class ListPersonalPostedServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		PostsService service = new PostsService();
		
		HttpSession session = request.getSession();
		String account = (String)session.getAttribute("account");
		
		List<Posts> posts = service.listPostByAccount(account);
//		System.out.println(account);
		String json = new Gson().toJson(posts);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
//		System.out.println(service.listPostByAccount(account));
		
		
	}
}
