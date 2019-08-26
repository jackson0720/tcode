package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.User;
import org.sing.tcode.service.PostsService;

import com.google.gson.Gson;

import java.io.*;
@WebServlet("/del_goods")
public class DelGoodsServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		PostsService service = new PostsService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String pid = (String)session.getAttribute("id");
		String acc = service.DelGoods(user.getU_account(), pid);
		String json = new Gson().toJson(acc);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		
	}
}
