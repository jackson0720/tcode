package org.sing.tcode.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.Posts;
import org.sing.tcode.entity.User;
import org.sing.tcode.service.PostsService;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/add_posts")
public class AddPostsServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String nick = req.getParameter("nick");
		String theme = req.getParameter("title");
		String account = req.getParameter("account");
		String text = req.getParameter("text");
		String plate = req.getParameter("plate");
		PostsService service = new PostsService();
		UserService service1 = new UserService();
		Posts p = new Posts();
		p.setP_ptheme(theme);
		p.setP_paccount(account);
		p.setP_pnick(nick);
		p.setP_ptext(text);
		p.setP_pgood(0);
		p.setP_pplate(plate);
		p.setP_ptime(new Date());
		service.addPosts(p);
		service1.updateUserPointer(5, account);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(p);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
		resp.sendRedirect("technology.html");
		}
}
