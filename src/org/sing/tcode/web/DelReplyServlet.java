package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.service.CommentService;

import java.io.*;
@WebServlet("/del_reply")
public class DelReplyServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute("id");
		Comment comment = (Comment)session.getAttribute("comment");//通过get到comment来获取楼层数
		System.out.println(session.getId());
		CommentService service = new CommentService();
		service.DelReply(pid, comment.getC_no());
		
	}
}
