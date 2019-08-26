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
import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.UserService;

import com.google.code.kaptcha.Constants;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  resp.setContentType("text/html;charset=utf-8");
		  PrintWriter pw = resp.getWriter(); 
		  String userName = req.getParameter("userName"); 
		  String password = req.getParameter("password"); 
		  String ip = InetAddress.getLocalHost().getHostAddress();
		  //获取表单提交的验证码
		  String code = req.getParameter("code"); 
		  //验证账号密码
		  UserService service = new UserService(); 
		  try { 
			  String sessionCode = (String)req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			  if(!code.equals(sessionCode)) {
				  throw new UserException("验证码错误");
			  }
			  User users = service.getUserByName(userName,password);
			  service.updateNum(ip, userName);
			  HttpSession session = req.getSession();
			  session.setAttribute("user", users); 
			  resp.sendRedirect("index.html");
		  
		  //System.out.println(users.getU_account()+users.getU_password()+users.getU_num()+users.getU_email()+users.getU_IP()+users.getU_state());
		  }catch(Exception e) {
			  e.printStackTrace();
			  throw new UserException("登录失败");
		}
		 
		/*
		 * resp.setContentType("text/html;charset=utf-8"); PrintWriter pw =
		 * resp.getWriter(); String userName = req.getParameter("userName"); String
		 * password = req.getParameter("password"); //获取表单提交的验证码 String code =
		 * req.getParameter("code"); User u = new User(); User user = new User();
		 * user.setU_account(userName); user.setU_password(password);
		 * u.setU_account(userName);
		 * u.setU_IP(InetAddress.getLocalHost().getHostAddress()); //验证账号密码 UserService
		 * service = new UserService(); try { User users=service.getUserByName(user);
		 * //账号验证通过后，验证码校验 //从会话作用域获取验证码 String sessionCode =
		 * (String)req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		 * //比较表单提交过来的验证码 if(!code.equals(sessionCode)) { throw new
		 * RuntimeException("验证码错误"); } service.updateNum(u); HttpSession session =
		 * req.getSession(); session.setAttribute("user", users);
		 * resp.sendRedirect("index.html");
		 * //System.out.println(session.getAttribute("user").toString());
		 * }catch(RuntimeException e) { pw.println(e.getMessage()); }
		 */
	}
	
	

}
