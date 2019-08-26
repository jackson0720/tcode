package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.Fans;
import org.sing.tcode.entity.User;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;

import java.io.*;
@WebServlet("/add_fans")
public class AddFansServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{

		HttpSession session = request.getSession();
		//被关注者账号
		String paccount = (String)session.getAttribute("account");
		//粉丝账号
		User user = (User)session.getAttribute("user");
		
		Fans fans = new Fans();
		fans.setF_focus(paccount);
		fans.setF_account(user.getU_account());
		UserService service = new UserService();
		try {			
			service.addFans(fans);
			String json = new Gson().toJson(fans);
			System.out.println(json);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json);
		}catch(UserException e) {
			response.getWriter().print("添加失败，这谁顶得住啊？");
		}
	}
}
