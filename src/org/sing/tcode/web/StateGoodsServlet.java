package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Forgood;
import org.sing.tcode.entity.User;
import org.sing.tcode.service.PostsService;

import com.google.gson.Gson;

import java.io.*;
@WebServlet("/state_goods")
public class StateGoodsServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		PostsService service = new PostsService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String pid = (String)session.getAttribute("id");
		
		Forgood good = new Forgood();
		good.setFg_account(user.getU_account());
		good.setFg_pid(pid);
		
		int state = service.StateGoods(good);
//		System.out.println(user.getU_account());
		
		String json = new Gson().toJson(state);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
}
