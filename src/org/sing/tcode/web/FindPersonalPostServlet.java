package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.entity.Posts;
import org.sing.tcode.service.CommentService;
import org.sing.tcode.service.PostsService;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
@WebServlet("/find_personalpost")
public class FindPersonalPostServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		String p_pid = request.getParameter("p_pid");
//		System.out.println(p_pid);
		PostsService service = new PostsService();
		Posts posts = service.showPostByPid(p_pid);
		
		String json = new Gson().toJson(posts);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		
		System.out.println(json);
		
		
	}
}
