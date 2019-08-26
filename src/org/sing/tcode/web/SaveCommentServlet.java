package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.entity.User;
import org.sing.tcode.service.CommentService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Date;
@WebServlet("/save_comment")
public class SaveCommentServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		String comm = request.getParameter("textarea_comment");
		
//		System.out.println(comm);
//		System.out.println(account);
		
		HttpSession session = request.getSession();
		CommentService service = new CommentService();
		User user = (User)session.getAttribute("user");
		String id = (String)session.getAttribute("id");
		Comment comment = new Comment();
		comment.setC_cid(id);
		comment.setC_text(comm);
		comment.setC_caccount(user.getU_account());
		comment.setC_ctime(new Date());
		
		service.saveComment(comment);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(comment);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		
	}
}
