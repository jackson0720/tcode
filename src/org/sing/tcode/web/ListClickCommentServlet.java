package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.service.CommentService;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
@WebServlet("/list_clickcomment")
public class ListClickCommentServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		CommentService service = new CommentService();
		
		String id = request.getParameter("p_pid");
		System.out.println(id);
		List<Comment> comment = service.listComment(id);
//		System.out.println(comment);
		String json = new Gson().toJson(comment);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		
	}

}
