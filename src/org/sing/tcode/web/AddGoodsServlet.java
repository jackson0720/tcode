package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.omg.CORBA.UserException;
import org.sing.tcode.entity.Fans;
import org.sing.tcode.entity.Forgood;
import org.sing.tcode.entity.User;
import org.sing.tcode.exception.PostsException;
import org.sing.tcode.service.PostsService;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;

import java.io.*;
@WebServlet("/add_goods")
public class AddGoodsServlet extends HttpServlet{
	
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute("id");
		User user = (User)session.getAttribute("user"); 
		
		PostsService service = new PostsService();
		try {
			Forgood good = new Forgood();
			good.setFg_pid(pid);
			good.setFg_account(user.getU_account());
			good.setFg_good(1);
			service.AddGoods(good);
			String json = new Gson().toJson(good);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json);
		}catch(PostsException e) {
			response.getWriter().print(e);
		}
		
	}

}
