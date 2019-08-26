package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.entity.Reply;
import org.sing.tcode.entity.User;
import org.sing.tcode.entity.bbsReply;
import org.sing.tcode.service.CommentService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Date;
import java.util.List;
@WebServlet("/add_reply")
public class AddReplyServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		String content = request.getParameter("content");
		String caccount = request.getParameter("caccount");
		Integer storey = Integer.parseInt(request.getParameter("cinfo"));
		//String caccount = request.getParameter("caccount");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
//		@SuppressWarnings("unchecked")
//		List<Comment> list = (List<Comment>)session.getAttribute("comment");
//		int i;
//		for( i=1;i<=list.size();i++) {
//			
//		}
//		Comment comment = (Comment)list.get(i-1);
//
//		System.out.println("comment"+comment.getC_uaccount());
		CommentService service = new CommentService();
		bbsReply reply = new bbsReply();
		reply.setPid((String)session.getAttribute("id"));//帖子id
		reply.setCstorey(storey);
		reply.setUcommentId(caccount);
		reply.setUreplyId(user.getU_account());	 //回复者账号
		reply.setUreplyNick(user.getU_account());//回复者昵称
		reply.setContent(content);
		reply.setReplyTime(new Date());
		
		service.AddReply(reply);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(reply);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
//		System.out.println(json);
	}
}
