package org.sing.tcode.web;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.entity.User;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;

import java.io.*;
@WebServlet("/del_fans")
public class DelFansServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		UserService service = new UserService();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String acc = service.delFans(user.getU_account());
		String json = new Gson().toJson(acc);
		response.setContentType("application/json;charet=utf-8");
		response.getWriter().print(json);
		
	}
}
