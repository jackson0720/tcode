package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.Posts;
import org.sing.tcode.service.PostsService;

import com.google.gson.Gson;

@WebServlet("/view_posts")
public class ViewPostsServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("pid");
		String account = req.getParameter("account");
		PostsService service = new PostsService();
		Posts posts = service.viewPostByTheme(id);
		HttpSession session = req.getSession();
		session.setAttribute("posts",posts);
		session.setAttribute("id", id);
		session.setAttribute("account", account);
		resp.sendRedirect("personalPosts.html");
	}
	
	

}
