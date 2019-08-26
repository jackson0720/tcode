package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.service.CommentService;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
@WebServlet("/list_comment")
public class ListCommentServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		CommentService service = new CommentService();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		List<Comment> comment = service.listComment(id);
		session.setAttribute("comment",comment);
//		System.out.println("id="+id);
		System.out.println(comment);
		String json = new Gson().toJson(comment);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		System.out.println("json="+json);
//		System.out.println(id);
	}
}
