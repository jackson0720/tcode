package org.sing.tcode.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.User;
import org.sing.tcode.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/checkip")
public class CheckIPServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8"); //转码
	    PrintWriter out = resp.getWriter();
	    HttpSession session = req.getSession();
		String account = req.getParameter("account");
		String IP = req.getParameter("ip");
		User user = new User();
		user.setU_account(account);
		user.setU_IP(InetAddress.getLocalHost().getHostAddress());
		String oIp = IP;
		String nIp = InetAddress.getLocalHost().getHostAddress();
		String arr[] = oIp.split("\\.");
		String arr1[] = nIp.split("\\.");
		String oIp_three_arr = arr[2];
		String nIp_three_arr = arr1[2];
		if(!oIp_three_arr.equals(nIp_three_arr)) {
			session.setAttribute("message", "IP与最后一次登录IP匹配异常，你的账号将受到二次登录限制！");
			UserService service = new UserService();
			service.updateUserState(account);
			service.updateNum(nIp,account);
			session.removeAttribute("user");
			Gson gson = new Gson();
			String json = gson.toJson(session.getAttribute("message"));
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(json);
			
		}
		else {
			session.setAttribute("message", "检测完成，您可继续访问网站！");
			UserService service = new UserService();
			service.updateNum(nIp,account);
			Gson gson = new Gson();
			String json = gson.toJson(session.getAttribute("message"));
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(json);
		}
		
	}
}
