package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.entity.Reply;
import org.sing.tcode.service.CommentService;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
@WebServlet("/list_reply")
public class ListReplyServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute("id");
		CommentService cs = new CommentService();
		List<Reply> r = cs.listReply(pid);
//		System.out.println("info:"+pid);
		String json = new Gson().toJson(r);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	//	System.out.println(json);

	}
}
