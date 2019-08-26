package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.Posts;
import org.sing.tcode.service.PostsService;
import org.sing.tcode.service.PostssService;
import org.sing.tcode.util.DateUtil;
import org.sing.tcode.util.PageBean;
import org.sing.tcode.util.PageBean2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/list_postss")
public class ListPostssServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum;
		int pageSize;
		try { 
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} catch (Exception e) {
			pageNum=1;
			pageSize=5;
		}
		PostssService service = new PostssService();
		PageBean<Posts>  pageBean = service.listPosts(pageNum, pageSize);
		String json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(pageBean);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(json);
		 
	}

	

}
