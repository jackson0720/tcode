package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.service.PostsService;

import com.google.gson.Gson;

import java.io.*;
@WebServlet("/count_goods")
public class CountGoodsServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		PostsService service = new PostsService();
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute("id");
		Integer goods = service.countGoods(pid);
		String json = new Gson().toJson(goods);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
}
